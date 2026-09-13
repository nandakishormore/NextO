package com.nextbigo.Nexto.dto.project;

import java.time.Instant;

public record FileNode(
		String path,
		Instant modifiedAt,
		Long size,
		String type
) {
}
