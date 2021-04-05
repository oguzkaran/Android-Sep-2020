/*----------------------------------------------------------------------------------------------------------------------
	RationalException sınıfı
----------------------------------------------------------------------------------------------------------------------*/
package org.csystem.math;

public class RationalException extends RuntimeException {
    private final RationalExceptionStatus m_rationalExceptionStatus;

    public RationalException(String s, RationalExceptionStatus rationalExceptionStatus)
    {
        super(s);
        m_rationalExceptionStatus = rationalExceptionStatus;
    }

    public RationalExceptionStatus getRationalExceptionStatus()
    {
        return m_rationalExceptionStatus;
    }

    public String getMessage()
    {
        return String.format("Mesage:%s, Exception Status:%s", super.getMessage(), m_rationalExceptionStatus);
    }
}
