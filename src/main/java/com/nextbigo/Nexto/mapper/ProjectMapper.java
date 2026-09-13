package com.nextbigo.Nexto.mapper;

import org.mapstruct.Mapper;

import com.nextbigo.Nexto.dto.project.ProjectResponse;
import com.nextbigo.Nexto.dto.project.ProjectSummaryResponse;
import com.nextbigo.Nexto.entity.Project;

@Mapper(componentModel = "spring")
public interface ProjectMapper {
	
	ProjectResponse toProjectResponse(Project project);
	
	ProjectSummaryResponse toProjectSummaryResponse(Project project);
}
