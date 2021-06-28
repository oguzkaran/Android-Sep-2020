/*----------------------------------------------------------------------
FILE        : errorutil.h
AUTHOR      : Kaan Aslan, Oguz Karan
LAST UPDATE : 31.05.2021
PLATFORM    : POSIX systems

Header file for utility functions for error handling

Copyleft (c) 1993 by C and System Programmers Association (CSD)
All Rights Free
-----------------------------------------------------------------------*/
#ifndef ERRORUTIL_H_
#define ERRORUTIL_H_

#ifdef __cplusplus
extern "C" {
#endif

void exit_sys(const char *msg);
void exit_sys_thread(const char *msg, int errnum);
void exit_failure(const char *msg);

#ifdef __cplusplus
}
#endif
#endif /* ERRORUTIL_H_ */
