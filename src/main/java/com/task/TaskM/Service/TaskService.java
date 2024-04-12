package com.task.TaskM.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.task.TaskM.Entity.Task;
import java.util.List;

import com.task.TaskM.Repository.TaskRepository;

@Service
public class TaskService {

	private final TaskRepository taskRepository;

    @Autowired
    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }
    
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }
    
    public List<Task> getAllTasksByProject(Long projectId) {
        return taskRepository.findAllByProjectId(projectId);
    }
    
    public List<Task> getAllTasksByUser(Long userId) {
        return taskRepository.findAllByUserId(userId);
    }

    public Task createTask(Task task) {
        return taskRepository.save(task);
    }

    public Task getTaskById(Long id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Task not found with id: " + id));
    }

    public void deleteTaskById(Long id) {
        taskRepository.deleteById(id);
    }
}
