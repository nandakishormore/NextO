package com.nextbigo.Nexto.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nextbigo.Nexto.dto.project.FileContentResponse;
import com.nextbigo.Nexto.dto.project.FileNode;
import com.nextbigo.Nexto.service.FileService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/project/{id}/files")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class FileController {

	FileService fileService;
	
	@GetMapping
	public ResponseEntity<List<FileNode>> getFileTree(@PathVariable Long projectId){
		Long userId=1l;
		return ResponseEntity.ok(fileService.getFileTree(projectId, userId));
	}
	
	@GetMapping("/{*path}")
	public ResponseEntity<FileContentResponse> getFileContent(@PathVariable Long projectId, @PathVariable String path){
		Long userId=1l;
		return ResponseEntity.ok(fileService.getFileContent(projectId, path, userId));
	}
	
}
