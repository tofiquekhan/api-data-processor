package com.webkorps.data.processor.rest;

import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.webkorps.data.processor.dto.ProcessedAPIDataDto;
import com.webkorps.data.processor.service.DataProcessorService;

@RestController
@RequestMapping("/api")
public class APIDataProcessorController {
	
	@Autowired
	private DataProcessorService dataProcessorService;
	
	@GetMapping
	public String test() {
		return "test";
	}

	@PostMapping
	public ResponseEntity<HashSet<ProcessedAPIDataDto>> processData(@RequestParam("lastFile") MultipartFile lastFile,@RequestParam("currentFile") MultipartFile currentFile) {
//		if(lastFile.isEmpty() || currentFile.isEmpty()) {
//			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Request must contain file");
//			 
//		}
//		
//		if(!lastFile.getContentType().equals("text/csv")||!currentFile.getContentType().equals("text/csv")) {
//			return ResponseEntity.status(HttpStatus.UNSUPPORTED_MEDIA_TYPE).body("Only CSV file Parse");
//		}
		dataProcessorService.apiDataProcessor(lastFile, currentFile);
		return ResponseEntity.ok(dataProcessorService.apiDataProcessor(lastFile, currentFile));
		
	}
	
}