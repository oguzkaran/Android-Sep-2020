package org.csystem.util.data;

import org.csystem.util.data.repository.RepositoryException;
import org.csystem.util.data.service.DataServiceException;

import java.util.function.Consumer;
import java.util.function.Function;

public final class DatabaseUtil {
    private DatabaseUtil()
    {}

    public static <R> R doWorkForRepository(String msg, ISupplierCallback<R> supplier)
    {
        try {
            return supplier.get();
        }
        catch (Throwable ex) {
            throw new RepositoryException(msg, ex);
        }
    }

    public static <R> R doWorkForRepository(String msg, ISupplierCallback<R> supplier, Consumer<Throwable> consumer)
    {
        try {
            return supplier.get();
        }
        catch (Throwable ex) {
            consumer.accept(ex);
            throw new RepositoryException(msg, ex);
        }
    }

    public static void doWorkForRepositoryRunnable(String msg, IActionCallback action)
    {
        try {
            action.run();
        }
        catch (Throwable ex) {
            throw new RepositoryException(msg, ex);
        }
    }

    public static void doWorkForRepositoryRunnable(String msg, IActionCallback action, Consumer<Throwable> consumer)
    {
        try {
            action.run();
        }
        catch (Throwable ex) {
            consumer.accept(ex);
            throw new RepositoryException(msg, ex);
        }
    }

    public static <R> R doWorkForService(String msg, ISupplierCallback<R> supplier)
    {
        try {
            return supplier.get();
        }
        catch (Throwable ex) {
            throw new DataServiceException(msg, ex);
        }
    }

    public static <R> R doWorkForService(String msg, ISupplierCallback<R> supplier, Consumer<Throwable> consumer)
    {
        try {
            return supplier.get();
        }
        catch (Throwable ex) {
            consumer.accept(ex);
            throw new DataServiceException(msg, ex);
        }
    }

    public static void doWorkForServiceRunnable(String msg, IActionCallback runnable)
    {
        try {
            runnable.run();
        }
        catch (Throwable ex) {
            throw new DataServiceException(msg, ex);
        }
    }

    public static void doWorkForServiceRunnable(String msg, IActionCallback runnable, Consumer<Throwable> consumer)
    {
        try {
            runnable.run();
        }
        catch (Throwable ex) {
            consumer.accept(ex);
            throw new DataServiceException(msg, ex);
        }
    }
}
