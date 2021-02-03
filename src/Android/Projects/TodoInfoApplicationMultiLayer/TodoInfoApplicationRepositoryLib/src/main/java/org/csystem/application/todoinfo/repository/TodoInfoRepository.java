package org.csystem.application.todoinfo.repository;

import android.content.Context;

import org.csystem.application.todoinfo.entity.TodoInfo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.inject.Inject;

import dagger.hilt.android.qualifiers.ApplicationContext;

public class TodoInfoRepository implements ITodoInfoRepository {
    private static final ArrayList<TodoInfo> ms_todos = new ArrayList<>();
    private static long m_index;

    @Inject
    public TodoInfoRepository()
    {}

    @Override
    public long count()
    {
        return ms_todos.size();
    }

    @Override
    public Iterable<TodoInfo> findAll()
    {
        return ms_todos;
    }

    @Override
    public Iterable<TodoInfo> findByStartDate(LocalDate date)
    {
        return ms_todos.stream().filter(td -> td.getStartDate().equals(date)).collect(Collectors.toList());
    }

    @Override
    public <S extends TodoInfo> S save(S entity)
    {
        ms_todos.add(entity);
        entity.setId(++m_index);

        return entity;
    }


    ////////////////////////////////////////

    @Override
    public void delete(TodoInfo entity)
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public void deleteAll()
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public void deleteAll(Iterable<? extends TodoInfo> entities)
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public void deleteById(Long aLong)
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean exitsById(Long aLong)
    {
        throw new UnsupportedOperationException();
    }



    @Override
    public Iterable<TodoInfo> findAllById(Iterable<Long> longs)
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public Optional<TodoInfo> findById(Long aLong)
    {
        throw new UnsupportedOperationException();
    }


    @Override
    public <S extends TodoInfo> Iterable<S> save(Iterable<S> entities)
    {
        throw new UnsupportedOperationException();
    }


}
