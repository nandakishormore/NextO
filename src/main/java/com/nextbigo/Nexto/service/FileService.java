package com.nextbigo.Nexto.service;

import java.util.List;

import com.nextbigo.Nexto.dto.project.FileContentResponse;
import com.nextbigo.Nexto.dto.project.FileNode;

public interface FileService {

	List<FileNode> getFileTree(long projectId, Long userId);

	FileContentResponse getFileContent(Long projectId, String path, Long userId);

}
