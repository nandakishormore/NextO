package com.nextbigo.Nexto.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.nextbigo.Nexto.dto.project.FileContentResponse;
import com.nextbigo.Nexto.dto.project.FileNode;
import com.nextbigo.Nexto.service.FileService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class FileServiceImpl implements FileService{

	@Override
	public List<FileNode> getFileTree(long projectId, Long userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FileContentResponse getFileContent(Long projectId, String path, Long userId) {
		// TODO Auto-generated method stub
		return null;
	}

}
