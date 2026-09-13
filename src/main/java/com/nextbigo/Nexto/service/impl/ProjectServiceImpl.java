package com.nextbigo.Nexto.service.impl;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.nextbigo.Nexto.Repository.ProjectMemberRepository;
import com.nextbigo.Nexto.Repository.ProjectRepository;
import com.nextbigo.Nexto.Repository.UserRepository;
import com.nextbigo.Nexto.dto.project.ProjectRequest;
import com.nextbigo.Nexto.dto.project.ProjectResponse;
import com.nextbigo.Nexto.dto.project.ProjectSummaryResponse;
import com.nextbigo.Nexto.entity.Project;
import com.nextbigo.Nexto.entity.ProjectMember;
import com.nextbigo.Nexto.entity.ProjectMemberId;
import com.nextbigo.Nexto.entity.User;
import com.nextbigo.Nexto.enums.ProjectRole;
import com.nextbigo.Nexto.error.ResourceNotFoundException;
import com.nextbigo.Nexto.mapper.ProjectMapper;
import com.nextbigo.Nexto.security.AuthUtil;
import com.nextbigo.Nexto.service.ProjectService;

import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Transactional
public class ProjectServiceImpl implements ProjectService{
	
	ProjectRepository projectRepository;
	ProjectMemberRepository projectMemberRepository;
	UserRepository userRepository;
	ProjectMapper projectMapper;
	AuthUtil authUtil;

    public ProjectResponse createProject(ProjectRequest request) {
		
		User owner = userRepository.findById(authUtil.getCurrentUserId()).orElseThrow(() -> new ResourceNotFoundException("User", authUtil.getCurrentUserId().toString()));
		
		Project project= Project.builder()
				.name(request.name())
				.isPublic(false)
				.build();
		
		project = projectRepository.save(project);
		
		ProjectMember projectMember = ProjectMember.builder()
				.id(ProjectMemberId.builder().projectId(project.getId()).userId(authUtil.getCurrentUserId()).build())
				.project(project)
				.user(owner)
				.projectRole(ProjectRole.OWNER)
				.invitedAt(Instant.now())
				.acceptedAt(Instant.now())
				.build();
		
		projectMember = projectMemberRepository.save(projectMember);
		
		return projectMapper.toProjectResponse(project);
	}
	
	@Override
	public List<ProjectSummaryResponse> getUserProjects() {
		
		return projectRepository.findAllAccesibleByUser(authUtil.getCurrentUserId())
				.stream()
				.map(project -> projectMapper.toProjectSummaryResponse(project))
				.collect(Collectors.toList());
	}

	@Override
	public ProjectResponse getUserProjectById(Long id) {
		Project project =  getAccesibleProjectById(id);
		
		return projectMapper.toProjectResponse(project);
	}

	@Override
	public ProjectResponse updateProject(Long id, ProjectRequest request) {
		
		Project project =  getAccesibleProjectById(id);
				
		project.setName(request.name());
		project = projectRepository.save(project);
		
		return projectMapper.toProjectResponse(project);
	}

	@Override
	public String softDeleteProject(Long id) {
		Project project =  getAccesibleProjectById(id);
		
		project.setDeletedAt(Instant.now());
		project = projectRepository.save(project);
		
		return "Success";
	}
	
	// Internal Function
	public Project getAccesibleProjectById(Long id) {
		return projectRepository.findAccesibleProjectById(id, authUtil.getCurrentUserId())
				.orElseThrow(() -> new ResourceNotFoundException("Project", id.toString()));
	}

}