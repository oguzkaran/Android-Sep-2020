/*----------------------------------------------------------------------
    Açıklamalar:
    - Aşağıda yazılan basit server'da client'ların uzun süre bağlı
    kalması kontrol edilmemiştir.

    - Server program ADD, MUL, SUB ve DIV komutları ile sayıları alarak 
    işlem yapar. Örneğin:
    ADD 10 20
    
    Komutların geçerlilik kontrolleri de yapılmaktadır

    - Server program BSD soketlerini kullanmaktadır. Dolsyısıyla en kolay 
    POSIX sistemlerinde çalıştırılır.

    - Programın bir POSIX sisteminde diğer kullandığı modüllerle beraber 
    en kolay derlenmesi aşağıdaki gibi yapılabilir.
    gcc -o server server.c socket.c errorutil.c -lpthread

    - Şüphesiz socket.c ve errorutil.c için dinamik ya da static kütüphane
    oluşturulup bağlama (link) aşamasında kullanılabilir
-----------------------------------------------------------------------*/
#include <stdio.h>
#include <stdlib.h>
#include <stdint.h>
#include <string.h>
#include <unistd.h>
#include <ctype.h> 
#include <arpa/inet.h>
#include <pthread.h>

#include "socket.h"
#include "errorutil.h"

#define BUFSIZE			1024


void *client_thread_proc(void *param);
char *calc(const char *msg, char *msgResponse, uint32_t *success);

int main(int argc, char **argv)
{
    int result;
    int server_sock, client_sock;    
    pthread_t tid;
    struct sockaddr_in sin_client;
    socklen_t addr_len;    
    unsigned short port_no;
    char *pend;

    if (argc != 2) 
        exit_failure("wrong number of arguments\n");
    
    port_no = (unsigned short)strtol(argv[1], &pend, 10);

    if (*pend != '\0')
        exit_failure("Invalid port number");

    addr_len = sizeof(sin_client);    
    
    if (init_tcp_server(port_no, 8, INADDR_ANY, &server_sock) == -1)
        exit_sys("init_tcp_server");

    printf("Waiting for connection...\n");

    for (;;) {
        if ((client_sock = accept(server_sock, (struct sockaddr *) &sin_client, &addr_len)) == -1) 
            exit_sys("accept");
        
        printf("Connected: %s:%d\n", inet_ntoa(sin_client.sin_addr), ntohs(sin_client.sin_port));        
        
        if ((result = pthread_create(&tid, NULL, client_thread_proc, &client_sock)) < -1)
            exit_sys_thread("pthread_create", result);

        pthread_detach(tid);
    }
    

    return 0;    
}

char *calc(const char *msg, char *msgResponse, uint32_t *success)
{	
    char cmd[128];
    int i, k;
    char *cmds[] = { "ADD", "SUB", "MUL", "DIV", NULL };
    char param1[32];
    char param2[32];
    double nparam1, nparam2;
    double result;
    char *endptr;

    *success = 0;
    
    while (isspace(*msg) && *msg != '\0')
        ++msg;

    if (*msg == '\0') {
        strcpy(msgResponse, "Error: Invalid command");        
        return msgResponse;
    }

    i = 0;
    while (!isspace(*msg) && *msg != '\0')
        cmd[i++] = *msg++;
    
    cmd[i] = '\0';

    if (*msg == '\0') {
        strcpy(msgResponse, "Error: Invalid command");        
        return msgResponse;
    }

    for (k = 0; cmds[k] != NULL; ++k)
        if (!strcmp(cmd, cmds[k]))
            break;

    if (cmds[k] == NULL) {
        strcpy(msgResponse, "Error: Command not found");
        return msgResponse;
    }
    while (isspace(*msg) && *msg != '\0')
            ++msg;
    
    if (*msg == '\0') {
        strcpy(msgResponse, "Error: Invalid command");
        return msgResponse;
    }
    i = 0;
    while (!isspace(*msg) && *msg != '\0')
        param1[i++] = *msg++;
    param1[i] = '\0';

    if (*msg == '\0') {
        strcpy(msgResponse, "Error: Invalid command");
        return msgResponse;
    }

    while (isspace(*msg) && *msg != '\0')
        ++msg;

    if (*msg == '\0') {
        strcpy(msgResponse, "Error: Invalid command");
        return msgResponse;
    }

    i = 0;
    while (!isspace(*msg) && *msg != '\0')
        param2[i++] = *msg++;
    param2[i] = '\0';

    nparam1 = strtol(param1, &endptr, 10);
    if (*endptr) {
        strcpy(msgResponse, "Error: Invalid numbers");
        return msgResponse;
    }          

    nparam2 = strtol(param2, &endptr, 10);
    if (*endptr)  {
        strcpy(msgResponse, "Error: Invalid numbers");
        return msgResponse;
    }          

    switch (k) {
        case 0:
            result = nparam1 + nparam2;
            break;
        case 1:
            result = nparam1 - nparam2;
            break;
        case 2:
            result = nparam1 * nparam2;
            break;
        case 3:
            result = nparam1 / nparam2;
            break;
    }

    sprintf(msgResponse, "%f", result);
    *success = 1;

    return msgResponse;
}

void *client_thread_proc(void *param)
{
    int sock = *(int *)param;
    uint32_t msg_len, host_len;
    int result;
    char buf[BUFSIZE], msgResponse[BUFSIZE];
    uint32_t success;
    
    for (;;) {           
        if (read_socket(sock, &msg_len, sizeof(msg_len)) != sizeof(msg_len))
            break;
        
        msg_len = ntohl(msg_len);
        
        if (msg_len >= BUFSIZE)
            break;
        
        if (msg_len == 0)
            continue;
        
        if (read_socket(sock, buf, msg_len) != msg_len)
            break;
        
        buf[msg_len] = '\0';
        
        if (!strcmp(buf, "quit"))
            break;
        
        calc(buf, msgResponse, &success);

        success = htonl(success);
        
        if (write_socket(sock, &success, sizeof(success)) != sizeof(success))
            break;
        
        msg_len = strlen(msgResponse);
        host_len = htonl(msg_len);
        if (write_socket(sock, &host_len, sizeof(host_len)) != sizeof(host_len))
            break;        
    
        if (write_socket(sock, msgResponse, msg_len) != msg_len)
            break;        
    }


    shutdown(sock, SHUT_RDWR);
    close(sock);    
}
