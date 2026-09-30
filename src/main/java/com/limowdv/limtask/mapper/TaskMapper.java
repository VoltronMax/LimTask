package com.limowdv.limtask.mapper;

import com.limowdv.limtask.model.Task;
import com.limowdv.limtask.model.dto.CreateTaskRequest;
import com.limowdv.limtask.model.dto.TaskResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TaskMapper {

    Task toEntity(CreateTaskRequest request);
    TaskResponse toResponse(Task task);
}
