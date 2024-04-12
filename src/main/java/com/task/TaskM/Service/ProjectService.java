package com.task.TaskM.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.task.TaskM.Entity.Project;
import com.task.TaskM.Entity.Task;
import com.task.TaskM.Repository.ProjectRepository;

@Service
public class ProjectService {

	private final ProjectRepository projectRepository;

    @Autowired
    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }
    
    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    public Project createProject(Project project) {
        return projectRepository.save(project);
    }

    public Project getProjectById(Long id) {
        return projectRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Project not found with id: " + id));
    }

    public void deleteProjectById(Long id) {
    	projectRepository.deleteById(id);
    }
    
    public List<Project> getAllProjectsByUser(Long userId) {
        return projectRepository.findAllByUserId(userId);
    }
}
