/*----------------------------------------------------------------------
	FILE        : GPIOException.java
	AUTHOR      : Oğuz Karan
	LAST UPDATE : 18.06.2021

	Exception class for GPIO

	Copyleft (c) 1993 by C and System Programmers Association (CSD)
	All Rights Free
-----------------------------------------------------------------------*/
package org.csystem.util.pi.gpio.exception;

public class GPIOException extends RuntimeException {
    public GPIOException()
    {
    }

    public GPIOException(String message)
    {
        super(message);
    }

    public GPIOException(String message, Throwable cause)
    {
        super(message, cause);
    }

    public GPIOException(Throwable cause)
    {
        super(cause);
    }

    @Override
    public String getMessage()
    {
        var cause = this.getCause();
        
        return String.format("Message: %s%s", super.getMessage(),
                cause != null ? ", Cause Message: " + cause.getMessage() : "");
    }
}
