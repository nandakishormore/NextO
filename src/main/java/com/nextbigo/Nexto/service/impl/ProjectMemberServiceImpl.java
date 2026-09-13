package com.nextbigo.Nexto.service.impl;

import java.time.Instant;
import java.util.List;

import org.springframework.stereotype.Service;

import com.nextbigo.Nexto.Repository.ProjectMemberRepository;
import com.nextbigo.Nexto.Repository.ProjectRepository;
import com.nextbigo.Nexto.Repository.UserRepository;
import com.nextbigo.Nexto.dto.member.InviteMemberRequest;
import com.nextbigo.Nexto.dto.member.MemberResponse;
import com.nextbigo.Nexto.dto.member.UpdateMemberRoleRequest;
import com.nextbigo.Nexto.entity.Project;
import com.nextbigo.Nexto.entity.ProjectMember;
import com.nextbigo.Nexto.entity.ProjectMemberId;
import com.nextbigo.Nexto.entity.User;
import com.nextbigo.Nexto.error.ResourceNotFoundException;
import com.nextbigo.Nexto.mapper.ProjectMemberMapper;
import com.nextbigo.Nexto.security.AuthUtil;
import com.nextbigo.Nexto.service.ProjectMemberService;

import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Transactional
public class ProjectMemberServiceImpl implements ProjectMemberService{
	
	ProjectMemberRepository projectMemberRepository;
	ProjectRepository projectRepository;
	UserRepository userRepository;
	ProjectMemberMapper projectMemberMapper;
	AuthUtil authUtil;

	@Override
	public List<MemberResponse> getProjectMembers(Long projectId) {
		Project project = getAccesibleProjectById(projectId);	
		
		if(project ==null) {
			throw new ResourceNotFoundException("Project", projectId.toString());
		}
		return projectMemberRepository.findByIdProjectId(projectId).stream()
				.map(projectMemberMapper::toMemberResponsefromProjectMember).toList();
	}

	@Override
	public MemberResponse inviteMember(Long projectId, InviteMemberRequest request) {
		Project project = getAccesibleProjectById(projectId);
	
		User invitee = userRepository.findByUsername(request.username()).orElseThrow();
		
		if(invitee.getId().equals(authUtil.getCurrentUserId())) {
			throw new RuntimeException("Cannot invite Yourself!");
		}
		
		ProjectMemberId projectMemberId = new ProjectMemberId(projectId, invitee.getId());
		if(projectMemberRepository.existsById(projectMemberId)) {
			throw new RuntimeException("you are already a member!");
		}
		
		ProjectMember projectMember = ProjectMember.builder()
				.id(projectMemberId)
				.user(invitee)
				.project(project)
				.projectRole(request.role())
				.invitedAt(Instant.now())
				.build();
		
		projectMember = projectMemberRepository.save(projectMember);
		
		return projectMemberMapper.toMemberResponsefromProjectMember(projectMember);
	}

	@Override
	public MemberResponse updateMemberRole(Long projectId, Long memberId, UpdateMemberRoleRequest request) {
		
		Project project = getAccesibleProjectById(projectId);
		
		if(project ==null) {
			throw new ResourceNotFoundException("Project", projectId.toString());
		}
		
		ProjectMemberId projectMemberId = new ProjectMemberId(projectId, memberId);
		ProjectMember projectMember = projectMemberRepository.findById(projectMemberId).orElseThrow();
		
		projectMember.setProjectRole(request.role());
		projectMember = projectMemberRepository.save(projectMember);
		
		return projectMemberMapper.toMemberResponsefromProjectMember(projectMember);
	}

	@Override
	public void removeProjectMember(Long projectId, Long memberId) {
		Project project = getAccesibleProjectById(projectId);
		
		if(project ==null) {
			throw new ResourceNotFoundException("Project", projectId.toString());
		}

		ProjectMemberId projectMemberId = new ProjectMemberId(projectId, memberId);
		if (!projectMemberRepository.existsById(projectMemberId)) {
			throw new RuntimeException("you are not a member! cannot remove!");
		}

		projectMemberRepository.deleteById(projectMemberId);
	}
	
	// Internal Function
	public Project getAccesibleProjectById(Long id) {
		return projectRepository.findAccesibleProjectById(id, authUtil.getCurrentUserId())
				.orElseThrow(() -> new ResourceNotFoundException("Project",id.toString()));
	}
}
