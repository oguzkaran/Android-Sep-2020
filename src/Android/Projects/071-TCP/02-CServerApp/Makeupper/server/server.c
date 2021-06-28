/*----------------------------------------------------------------------
    Açıklamalar:
    - Aşağıda yazılan basit server'da client'ların uzun süre bağlı
    kalması kontrol edilmemiştir.

    - Server program aldığı yazıyı büyük harfe çevirir. Standart ASCII
    karakterler için düzgün çalışacak şekilde tasarlanmıştır. Yani diğer
    karakterleri büyük harfe çevirmez.

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
#include <string.h>
#include <unistd.h>
#include <stdint.h>
#include <ctype.h>
#include <arpa/inet.h>
#include <pthread.h>
#include "socket.h"
#include "errorutil.h"

#define BUFSIZE			1024

void *client_thread_proc(void *param);
char *mystrupr(char *s);

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

char *mystrupr(char *s)
{
    char *p = s;
    
    while (*p = toupper(*p))
        ++p;
    
    return s;
}

void *client_thread_proc(void *param)
{
    int sock = *(int *)param;
    uint32_t msg_len, host_len;
    int result;
    char buf[BUFSIZE];
    
    for (;;) {        
        if ((result = read_socket(sock, &msg_len, sizeof(msg_len))) != sizeof(msg_len))
            break;        
        
        msg_len = ntohl(msg_len);
        printf("msg_len:%u\n", msg_len);
        printf("msg_len:%x\n", msg_len);
        
        if (msg_len >= BUFSIZE)
            break;
        
        if (msg_len == 0) {
            printf("Control if alive\n");
            continue;
        }
        
        if ((result = read_socket(sock, buf, msg_len)) != msg_len)
            break;        

        buf[result] = '\0';
        
        puts(buf);
        if (!strcmp(buf, "quit"))
            break;
        
        mystrupr(buf);
        
        msg_len = strlen(buf);        
        host_len = htonl(msg_len);        
        if (write_socket(sock, &host_len, sizeof(msg_len)) != sizeof(msg_len))
            break;
        
        if (write_socket(sock, buf, msg_len) != msg_len)
            break;
        
        puts(buf);
    }

    shutdown(sock, SHUT_RDWR);
    close(sock);    
}


