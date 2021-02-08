package org.csystem.application.todoinfo.data.converter;

import org.csystem.application.todoinfo.data.dto.TodoInfoDTO;
import org.csystem.application.todoinfo.entity.TodoInfo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(implementationName = "TodoInfoDTOConverterImpl")
public interface ITodoInfoDTOConverter {
    TodoInfoDTO todoToTodoInfoDTO(TodoInfo todoInfo);
    TodoInfo todoInfoDTOToTodoInfo(TodoInfoDTO todoInfoDTO);
}
