package org.csystem.util.converter;

public interface IConverter<T, R> {
    R convert(T t);

    default T convertBack(R r)
    {
        throw new UnsupportedOperationException("convertBack not supported");
    }
}
