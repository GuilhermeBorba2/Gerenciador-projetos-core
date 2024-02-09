package com.gerenciador.projeto.repository;

import com.gerenciador.projeto.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {


}
