package com.webkorps.data.processor.service.impl;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashSet;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.webkorps.data.processor.dto.APIDataDto;
import com.webkorps.data.processor.dto.ProcessedAPIDataDto;
import com.webkorps.data.processor.service.DataProcessorService;

@Service

public class DataProcessorServiceImpl implements DataProcessorService{
	
	

	@Override
	public HashSet<ProcessedAPIDataDto> apiDataProcessor(MultipartFile lastFile,MultipartFile currentFile){
		HashSet<ProcessedAPIDataDto> processedAPIDataDtos = new HashSet<ProcessedAPIDataDto>();
		HashSet<APIDataDto> lastFileApiDataDtos = excelToHashSet(lastFile);
		HashSet<APIDataDto> currentFileApiDataDtos = excelToHashSet(currentFile);
		ArrayList<APIDataDto> currentFileApiDataDtosList = new ArrayList<APIDataDto>(currentFileApiDataDtos);
		for(APIDataDto lastFileApiDataDto: lastFileApiDataDtos) {
			if(currentFileApiDataDtos.contains(lastFileApiDataDto)) {
				System.out.println("DataProcessorServiceImpl.apiDataProcessor() in if statement checking contains or not");
				int index = currentFileApiDataDtosList.indexOf(lastFileApiDataDto);
				APIDataDto currentFileApiDataDto = currentFileApiDataDtosList.get(index);
				ProcessedAPIDataDto processedAPIDataDto = new ProcessedAPIDataDto(lastFileApiDataDto.getApi(),lastFileApiDataDto.getCount()-currentFileApiDataDto.getCount(),lastFileApiDataDto.getDuration()-currentFileApiDataDto.getDuration());
				processedAPIDataDtos.add(processedAPIDataDto);
			}
		}
		return processedAPIDataDtos;
	}
	
	public static HashSet<APIDataDto> excelToHashSet(MultipartFile multipartFile){
		
		HashSet<APIDataDto> apiDataDtos = null;
		try {

			FileInputStream  in = (FileInputStream )multipartFile.getInputStream();
			XSSFWorkbook  workbook = new XSSFWorkbook(in);
			XSSFSheet sheet  = workbook.getSheetAt(0);  
			for (Row row : sheet) {
			    for (Cell cell : row) {
			    	System.out.println("DataProcessorServiceImpl.excelToHashSet() cell = "+cell);
//			    	System.out.print(cell.getStringCellValue());
			        }
			    }
//			Iterable<CSVRecord> records= CSVFormat.EXCEL.builder().setSkipHeaderRecord(true).setHeader("request url", "count", "duration").build().parse(in).getRecords();
//			apiDataDtos = new HashSet<APIDataDto>();
//			for (CSVRecord record : records) {
//			    try {
//			    APIDataDto apiDataDto = new APIDataDto();
//			    apiDataDto.setApi(record.get("request url")); 
//			    apiDataDto.setCount(Long.parseLong(record.get("count")));
//			    apiDataDto.setDuration(Double.parseDouble(record.get("duration")));
//			    apiDataDtos.add(apiDataDto);
//			    
			    
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return apiDataDtos;
	}
	
}
