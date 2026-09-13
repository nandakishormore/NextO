package com.nextbigo.Nexto.service;

import java.util.List;

import com.nextbigo.Nexto.dto.project.ProjectRequest;
import com.nextbigo.Nexto.dto.project.ProjectResponse;
import com.nextbigo.Nexto.dto.project.ProjectSummaryResponse;

public interface ProjectService {

	List<ProjectSummaryResponse> getUserProjects();

	ProjectResponse getUserProjectById(Long projectId);

	ProjectResponse createProject(ProjectRequest request);

	ProjectResponse updateProject(Long id, ProjectRequest request);

	String softDeleteProject(Long id);

}
