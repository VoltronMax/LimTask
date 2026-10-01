package com.limowdv.limtask.service;

import com.limowdv.limtask.mapper.TaskMapper;
import com.limowdv.limtask.model.Status;
import com.limowdv.limtask.model.Task;
import com.limowdv.limtask.model.dto.CreateTaskRequest;
import com.limowdv.limtask.model.dto.TaskResponse;
import com.limowdv.limtask.model.dto.UpdateTaskRequest;
import com.limowdv.limtask.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ITaskService implements TaskService{

    private final TaskRepository repository;
    private final TaskMapper mapper;

    public ITaskService(TaskRepository repository, TaskMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public TaskResponse crearTarea(CreateTaskRequest request) {

        Task tarea = mapper.toEntity(request);

        tarea.setEstado(Status.EN_PROCESO);
        tarea.setFechaCreacion(LocalDateTime.now());

        if(request.fechaLimite()!=null
                && request.fechaLimite().isBefore(tarea.getFechaCreacion())){
            throw new IllegalArgumentException("La fecha de vencimiento no puede ser anterior a la fecha de creacion");
        }

        tarea.setFechaLimite(request.fechaLimite());

        repository.save(tarea);
        return mapper.toResponse(tarea);

    }

    @Override
    public List<TaskResponse> consultarTareas() {
        return repository.findAll().stream()
                .map(mapper::toResponse)
                .toList();
    }

    @Override
    public TaskResponse consultarTareaPorId(Long id) {
        return mapper.toResponse(repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Tarea no encontrada")));
    }

    @Override
    public TaskResponse modificarTarea(Long id, UpdateTaskRequest request) {
        Task tarea = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Tarea no encontrada"));
        if(request.titulo()!=null){
            tarea.setTitulo(request.titulo());
        }
        if(request.descripcion()!=null){
            tarea.setDescripcion(request.descripcion());
        }
        if(request.prioridad()!=null){
            tarea.setPrioridad(request.prioridad());
        }
        if(request.estado()!=null){
            tarea.setEstado(request.estado());
        }
        if(request.fechaLimite()!=null){
            if(request.fechaLimite().isBefore(tarea.getFechaCreacion())){
                throw new IllegalArgumentException("La fecha de vencimiento no puede ser anterior a la fecha de creacion");
            }
            tarea.setFechaLimite(request.fechaLimite());
        }

        return mapper.toResponse(tarea);
    }

    @Override
    public void eliminarTarea(Long id) {
        if(!repository.existsById(id)){
            throw new IllegalArgumentException("Tarea no encontrada");
        }
        repository.deleteById(id);
    }
}
