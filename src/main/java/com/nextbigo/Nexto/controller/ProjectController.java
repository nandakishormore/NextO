package com.nextbigo.Nexto.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nextbigo.Nexto.dto.project.ProjectRequest;
import com.nextbigo.Nexto.dto.project.ProjectResponse;
import com.nextbigo.Nexto.dto.project.ProjectSummaryResponse;
import com.nextbigo.Nexto.service.ProjectService;

import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/projects")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ProjectController {

	ProjectService projectService;

	@GetMapping
	public ResponseEntity<List<ProjectSummaryResponse>> getMyProjects() {
		return ResponseEntity.ok(projectService.getUserProjects());
	}

	@GetMapping("/{id}")
	public ResponseEntity<ProjectResponse> getProjectById(@PathVariable Long id) {
		return ResponseEntity.ok(projectService.getUserProjectById(id));
	}

	@PostMapping
	public ResponseEntity<ProjectResponse> createProject(@RequestBody @Valid ProjectRequest request) {
		return ResponseEntity.status(HttpStatus.CREATED).body(projectService.createProject(request));
	}

	@PatchMapping("/{id}")
	public ResponseEntity<ProjectResponse> updateProject(@PathVariable Long id, @RequestBody @Valid ProjectRequest request) {
		return ResponseEntity.ok(projectService.updateProject(id, request));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteProject(@PathVariable Long id) {
		String response = "failed";
		try {
		response = projectService.softDeleteProject(id);
		} catch (Exception e) {
			return ResponseEntity.ok(response);
		}
		return ResponseEntity.ok(response);
	} 
}