package com.gerenciador.projeto.service;

import com.gerenciador.projeto.model.DTO.TaskDto;
import com.gerenciador.projeto.model.Task;
import com.gerenciador.projeto.model.User;
import com.gerenciador.projeto.model.enums.Status;
import com.gerenciador.projeto.repository.TaskRepository;
import com.gerenciador.projeto.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class TaskService {
    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    @Autowired
    public TaskService(TaskRepository taskRepository, UserRepository userRepository) {
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
    }

    public List<Task> findAllTasks(){
        List<Task> tasks = taskRepository.findAll();
        tasks.forEach(Task:: updateStatusBasedOnDates);
        return tasks.stream().peek(task -> taskRepository.save(task)).collect(Collectors.toList());
    }

    public void verificarEAtualizarStatus(long taskId){
        Task task = taskRepository.findById(taskId)
                .orElseThrow(()-> new RuntimeException("Task not found for this id:: "+taskId));
        if(task.getStatus()==Status.PENDENTE){
            boolean codicoesAtendidas = true;

            if (codicoesAtendidas){
                task.setStatus(Status.CONCLUIDA);
                taskRepository.save(task);
            }
        }

    }
    public List <Task> findByStatus (Status status){
        return taskRepository.findByStatus(status);
    }

    public Optional<Task> findTaskById(long id){
        Task task = taskRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Task not found for this id::"+id));
        task.updateStatusBasedOnDates();
        return taskRepository.findById(id);
    }

    public Task saveTask(TaskDto taskDto){
        User user = userRepository.findById(taskDto.getUserId())
                .orElseThrow(()-> new RuntimeException("User not found for this Id:: "+taskDto.getUserId()));

        Task task = new Task();
        task.setTitle(taskDto.getTitle());
        task.setStatus(taskDto.getStatus());
        task.setDescription(taskDto.getDescription());
        task.setDueDate(taskDto.getDueDate());

        return taskRepository.save(task);
    }

    public Task updateTask(Long id, TaskDto taskDetailsDto){
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found for this id:: "+id));
        task.setTitle(taskDetailsDto.getTitle());
        task.setDescription(taskDetailsDto.getDescription());
        task.setDueDate(taskDetailsDto.getDueDate());
        task.setStatus(taskDetailsDto.getStatus());
        return taskRepository.save(task);
    }

    public void deleteTask(Long id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found for this id:: " + id));
        taskRepository.delete(task);
    }




}
