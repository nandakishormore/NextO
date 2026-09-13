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

import com.nextbigo.Nexto.dto.member.InviteMemberRequest;
import com.nextbigo.Nexto.dto.member.MemberResponse;
import com.nextbigo.Nexto.dto.member.UpdateMemberRoleRequest;
import com.nextbigo.Nexto.service.ProjectMemberService;

import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/projects/{projectId}/members")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ProjectMemberController {
	
	ProjectMemberService projectMemberService;
	
	@GetMapping
	public ResponseEntity<List<MemberResponse>> getProjectMembers(@PathVariable Long projectId){
		return ResponseEntity.ok(projectMemberService.getProjectMembers(projectId));
	}
	
	@PostMapping
	public ResponseEntity<MemberResponse> inviteMember(@PathVariable Long projectId, @RequestBody @Valid InviteMemberRequest request){
		return ResponseEntity.status(HttpStatus.CREATED).body(projectMemberService.inviteMember(projectId, request));
	}
	
	@PatchMapping("/{memberId}")
	public ResponseEntity<MemberResponse> updateMemberRole(@PathVariable Long projectId, @PathVariable Long memberId, @RequestBody @Valid UpdateMemberRoleRequest request){
		return ResponseEntity.status(HttpStatus.CREATED).body(projectMemberService.updateMemberRole(projectId, memberId, request));
	}
	
	@DeleteMapping("/{memberId}")
	public ResponseEntity<Void> removeMember(@PathVariable Long projectId, @PathVariable Long memberId){
		projectMemberService.removeProjectMember(projectId, memberId);
		return ResponseEntity.noContent().build();
	}
	
	
	
}
