package com.gerenciador.projeto.repository;

import com.gerenciador.projeto.model.Task;
import com.gerenciador.projeto.model.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByStatus(Status status);

}
