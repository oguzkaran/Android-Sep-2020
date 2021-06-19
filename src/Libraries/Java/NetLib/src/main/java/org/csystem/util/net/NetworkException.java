/*----------------------------------------------------------------------
FILE        : NetworkException.java
AUTHOR      : Oguz Karan
LAST UPDATE : 30.09.2020

Unnchecked exception class for network applications

Copyleft (c) 1993 by C and System Programmers Association (CSD)
All Rights Free
-----------------------------------------------------------------------*/
package org.csystem.util.net;

public class NetworkException extends RuntimeException {
    public NetworkException()
    {
    }
    
    public NetworkException(String message)
    {
        this(message, null);
    }

    public NetworkException(String message, Throwable cause)
    {
        super(message, cause);
    }

    @Override
    public String getMessage()
    {
        Throwable cause = getCause();

        return String.format("{message : %s%s", super.getMessage(), cause != null ? ", causeMessage : " + cause.getMessage()  + "}": "}");
    }
}
