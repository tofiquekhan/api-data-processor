package com.webkorps.data.processor.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class APIDataProcessorController {

	@PostMapping("/upload-file")
	public ResponseEntity<String> processData(@RequestParam("file1") MultipartFile file1,@RequestParam("file2") MultipartFile file2) {
		if(file1.isEmpty() || file2.isEmpty()) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Request must contain file");
			 
		}
		if(!file1.getContentType().equals("text/csv")||!file2.getContentType().equals("text/csv")) {
			return ResponseEntity.status(HttpStatus.UNSUPPORTED_MEDIA_TYPE).body("Only CSV file Parse");
		}
//		DataStoreResult dsr = employeeService.storeInDatabase(file);
//		return ResponseEntity.ok(dsr);
		return null;
	}
	
}