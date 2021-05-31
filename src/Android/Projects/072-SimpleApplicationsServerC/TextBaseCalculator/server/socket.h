/*----------------------------------------------------------------------
FILE        : socket.c
AUTHOR      : Kaan Aslan, Oguz Karan
LAST UPDATE : 24.09.2018
PLATFORM    : POSIX systems

Header file for utility functions for BSD sockets

Copyleft (c) 1993 by C and System Programmers Association (CSD)
All Rights Free
-----------------------------------------------------------------------*/

#ifndef SOCKET_H_
#define SOCKET_H_

#ifdef __cplusplus
extern "C" {
#endif
    
int init_tcp_server(unsigned short port_no, int back_log, unsigned long ip_addr, int *sock);
int connect_to_server(unsigned short port_no, const char *host_name, int *sock);
int read_socket(int sock, void *buf, size_t count);
int write_socket(int sock, const void *buf, size_t count);
int is_port_available_v4(int portno);

#ifdef __cplusplus
}
#endif

#endif /* SOCKET_H_ */