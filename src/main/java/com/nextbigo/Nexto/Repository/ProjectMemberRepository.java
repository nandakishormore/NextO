package com.nextbigo.Nexto.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nextbigo.Nexto.entity.ProjectMember;
import com.nextbigo.Nexto.entity.ProjectMemberId;

@Repository
public interface ProjectMemberRepository extends JpaRepository<ProjectMember, ProjectMemberId>{
	
	List<ProjectMember> findByIdProjectId(Long projectId);

}
