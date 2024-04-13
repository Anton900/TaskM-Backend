package com.task.TaskM.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.task.TaskM.Entity.Project;
import com.task.TaskM.Service.ProjectService;

@RestController
@RequestMapping("/api/projects")
public class ProjectController {

	
	private final ProjectService projectService;
	
	@Autowired
	public ProjectController(ProjectService projectService) {
		this.projectService = projectService;
	}
	
	@GetMapping
    public ResponseEntity<List<Project>> getAllProjects() {
        List<Project> projectList = projectService.getAllProjects();
        return new ResponseEntity<>(projectList, HttpStatus.OK);
    }
	
	@GetMapping("/user/{userId}")
    public ResponseEntity<List<Project>> getAllProjectsByUser(@PathVariable Long userId) {
        List<Project> projectList = projectService.getAllProjectsByUser(userId);
        return new ResponseEntity<>(projectList, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Project> createProject(@RequestBody Project project) {
        Project createdProject = projectService.createProject(project);
        return new ResponseEntity<>(createdProject, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Project> getProjectById(@PathVariable Long id) {
        Project project = projectService.getProjectById(id);
        return new ResponseEntity<>(project, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProjectById(@PathVariable Long id) {
    	projectService.deleteProjectById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
