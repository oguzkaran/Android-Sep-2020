#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdint.h>
#include <unistd.h>
#include <arpa/inet.h>

#include "socket.h"
#include "errorutil.h"


int main(int argc, char **argv)
{
    int server_sock;
    int result;
    char host_name[16];
    uint32_t msg_len, host_len;
    uint32_t success;
    unsigned short port_no;
    char *pend;

    if (argc != 3)
        exit_failure("Wrong number of arguments");

    port_no = (unsigned short)strtol(argv[2], &pend, 10);

    if (*pend != '\0')
        exit_failure("Invalid port number");
    
    if (strlen(argv[1]) > 15)
        exit_failure("Invalid ip number");
    
    strcpy(host_name, argv[1]);

    if (connect_to_server(port_no, host_name, &server_sock) == -1)
        exit_sys("connect_to_server");

    for (;;) {
        static char buf[100];
        static char msg[100];

        printf("Text:");
        gets(buf);
        
        msg_len = strlen(buf);        
        host_len = htonl(msg_len);
        
        if (write_socket(server_sock, &host_len, sizeof(host_len)) != sizeof(host_len))
            exit_sys("write_socket");
        
        if (write_socket(server_sock, buf, msg_len) != msg_len)
            exit_sys("write_socket");  
        
        if (!strcmp(buf, "quit"))
            break;
        
        if (read_socket(server_sock, &success, sizeof(success)) != sizeof(success))
            exit_sys("read_socket");        
        
        success = ntohl(success);
        
        printf("%s\n", success ? "Success" : "Fail");
        
        if (read_socket(server_sock, &msg_len, sizeof(msg_len)) != sizeof(msg_len))
            exit_sys("read_socket");
        
        msg_len = ntohl(msg_len);
        
        if (read_socket(server_sock, &msg, msg_len) != msg_len)
            exit_sys("read_socket");
        
        msg[msg_len] = '\0';
        puts(msg);
    }

    shutdown(server_sock, SHUT_RDWR);
    close(server_sock);

    return 0;
}

