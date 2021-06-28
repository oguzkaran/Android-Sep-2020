/*----------------------------------------------------------------------
FILE        : socket.c
AUTHOR      : Kaan Aslan, Oguz Karan
LAST UPDATE : 26.01.2018
PLATFORM    : POSIX systems

Implementattion file for utility functions for BSD sockets 

Copyleft (c) 1993 by C and System Programmers Association (CSD)
All Rights Free
-----------------------------------------------------------------------*/
#include <string.h>
#include <netinet/in.h>
#include <arpa/inet.h>
#include <sys/socket.h>
#include <netdb.h>
#include <unistd.h>
#include "socket.h"

int init_tcp_server(unsigned short port_no, int back_log, unsigned long ip_addr, int *sock)
{    
    int server_sock;
    int result = -1;
    struct sockaddr_in sin_server;
    
    if ((server_sock = socket(AF_INET, SOCK_STREAM, IPPROTO_TCP)) == -1)
        goto EXIT;        

    sin_server.sin_family = AF_INET;
    sin_server.sin_port = htons(port_no);
    sin_server.sin_addr.s_addr = htonl(ip_addr);

    if ((result = bind(server_sock, (struct sockaddr *) &sin_server, sizeof(sin_server))) == -1) {
        close(server_sock);
        goto EXIT;
    }
    if ((result = listen(server_sock, back_log)) == -1) {
        close(server_sock);
        goto EXIT;
    }
    
    *sock = server_sock;
EXIT:
    return result;    
}

int connect_to_server(unsigned short port_no, const char *host_name, int *sock)
{
    int server_sock;    
    int result = -1;
    struct sockaddr_in sin_server;    
    struct hostent *host;
    int msg_len;

    if ((server_sock = socket(AF_INET, SOCK_STREAM, IPPROTO_TCP)) == -1) 
        goto EXIT;
    
    sin_server.sin_family = AF_INET;
    sin_server.sin_port = htons(port_no);

    if ((sin_server.sin_addr.s_addr = inet_addr(host_name)) == INADDR_NONE) {
        if ((host = gethostbyname(host_name)) == NULL)  {
            close(server_sock);
            goto EXIT;            
        }        
        memcpy(&sin_server.sin_addr.s_addr, host->h_addr_list[0], host->h_length);
    }

    if ((result = connect(server_sock, (struct sockaddr *)&sin_server, sizeof(sin_server))) == -1) {
        close(server_sock);
        goto EXIT;
    }
    *sock = server_sock;
    
EXIT:
    return result;
}

int read_socket(int sock, void *buf, size_t count)
{
    int result;
    size_t left = count, index = 0;

    while (left > 0) {
        if ((result = recv(sock, (char *)buf + index, left, 0)) == -1)
            return -1;
        
        if (result == 0)
            break;
        
        index += result;
        left -= result;
    }

    return index;
}


int write_socket(int sock, const void *buf, size_t count)
{
    int result;
    size_t left = count, index = 0;

    while (left > 0) {
        if ((result = send(sock, (char *)buf + index, left, 0)) == -1)
            return -1;
        
        if (result == 0)
            break;
        
        index += result;
        left -= result;
    }

    return index;
}

int is_port_available_v4(int port_no)
{
    int serverSock;
    struct sockaddr_in sinServer;
    int result;
    
    result = 1;
    
    if (port_no < 0 || port_no > 65535)
        return -1;

    if ((serverSock = socket(AF_INET, SOCK_STREAM, IPPROTO_TCP)) == -1) 
        return -1;

    sinServer.sin_family = AF_INET;
    sinServer.sin_port = htons(port_no);
    sinServer.sin_addr.s_addr = htonl(INADDR_ANY);

    if (bind(serverSock, (struct sockaddr *) &sinServer, sizeof(sinServer)) == -1)
        result = 0;
    
    close(serverSock);
    
    return result;
}