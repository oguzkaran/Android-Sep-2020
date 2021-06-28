/*----------------------------------------------------------------------
FILE        : errorutil.c
AUTHOR      : Kaan Aslan, Oguz Karan
LAST UPDATE : 31.05.2021
PLATFORM    : POSIX systems

Implementation file for utility functions for error handling

Copyleft (c) 1993 by C and System Programmers Association (CSD)
All Rights Free
-----------------------------------------------------------------------*/
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "errorutil.h"

void exit_sys(const char *msg)
{
    perror(msg);
    exit(EXIT_FAILURE);
}

void exit_sys_thread(const char *msg, int errnum)
{
    fprintf(stderr, "%s:%s\n", msg, strerror(errnum));
    exit(EXIT_FAILURE);
}

void exit_failure(const char *msg)
{
    fprintf(stderr, "%s\n", msg);
    exit(EXIT_FAILURE);
}