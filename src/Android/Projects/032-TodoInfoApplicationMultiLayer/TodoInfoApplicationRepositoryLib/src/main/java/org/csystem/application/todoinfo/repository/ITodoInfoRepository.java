package org.csystem.application.todoinfo.repository;

import org.csystem.application.todoinfo.entity.TodoInfo;
import org.csystem.util.data.repository.ICrudRepository;

import java.time.LocalDate;

public interface ITodoInfoRepository extends ICrudRepository<TodoInfo, Long> {
    Iterable<TodoInfo> findByStartDate(LocalDate date);
}

