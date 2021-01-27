package org.csystem.application.todoinfo.data.converter;

import org.csystem.application.todoinfo.data.dto.TodoInfoDTO;
import org.csystem.application.todoinfo.entity.TodoInfo;

public interface ITodoInfoDTOConverter {
    TodoInfoDTO todoToTodoInfoDTO(TodoInfo todoInfo);
    TodoInfo todoInfoDTOToTodoInfo(TodoInfoDTO todoInfoDTO);
}
