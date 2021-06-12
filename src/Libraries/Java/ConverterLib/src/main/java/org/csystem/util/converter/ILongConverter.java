package org.csystem.util.converter;

public interface ILongConverter<R> {
    R convert(long val);

    default long convertBack(R r)
    {
        throw new UnsupportedOperationException("convertBack not supported");
    }
}
