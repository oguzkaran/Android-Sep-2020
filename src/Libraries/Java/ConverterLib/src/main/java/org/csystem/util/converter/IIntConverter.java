package org.csystem.util.converter;

public interface IIntConverter<R> {
    R convert(int val);

    default int convertBack(R r)
    {
        throw new UnsupportedOperationException("convertBack not supported");
    }
}
