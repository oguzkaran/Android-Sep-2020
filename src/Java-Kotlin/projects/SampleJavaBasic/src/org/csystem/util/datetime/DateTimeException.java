/*----------------------------------------------------------------------------------------------------------------------
	DateTimeException sınıfı
----------------------------------------------------------------------------------------------------------------------*/
package org.csystem.util.datetime;

public class DateTimeException extends RuntimeException {
    public DateTimeException()
    {
    }

    public DateTimeException(String message)
    {
        super(message);
    }
}
