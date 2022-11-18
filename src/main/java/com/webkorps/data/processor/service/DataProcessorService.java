package com.webkorps.data.processor.service;

import java.util.HashSet;

import com.webkorps.data.processor.dto.ProcessedAPIDataDto;

public interface DataProcessorService {

	public HashSet<ProcessedAPIDataDto> apiProcessor() ;
	
}
