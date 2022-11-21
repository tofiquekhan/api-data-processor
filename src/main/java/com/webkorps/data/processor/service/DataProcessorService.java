package com.webkorps.data.processor.service;

import java.util.HashSet;

import org.springframework.web.multipart.MultipartFile;

import com.webkorps.data.processor.dto.ProcessedAPIDataDto;

public interface DataProcessorService {

	public HashSet<ProcessedAPIDataDto> apiDataProcessor(MultipartFile lastFile,MultipartFile currentFile) ;
	
}
