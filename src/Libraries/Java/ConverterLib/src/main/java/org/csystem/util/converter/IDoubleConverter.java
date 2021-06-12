package org.csystem.util.converter;

public interface IDoubleConverter<R> {
    R convert(double val);

    default double convertBack(R r)
    {
        throw new UnsupportedOperationException("convertBack not supported");
    }
}
