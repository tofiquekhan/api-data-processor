package com.webkorps.data.processor.service.impl;

import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashSet;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.webkorps.data.processor.dto.APIDataDto;
import com.webkorps.data.processor.dto.ProcessedAPIDataDto;
import com.webkorps.data.processor.service.DataProcessorService;

@Service

public class DataProcessorServiceImpl implements DataProcessorService{
	
	

	@Override
	public HashSet<ProcessedAPIDataDto> apiDataProcessor(MultipartFile dataFile1,MultipartFile dataFile2){
		HashSet<ProcessedAPIDataDto> processedAPIDataDtos = new HashSet<ProcessedAPIDataDto>();
		HashSet<APIDataDto> apiDataDtos1 = excelToHashSet(dataFile1);
		HashSet<APIDataDto> apiDataDtos2 = excelToHashSet(dataFile2);
		ArrayList<APIDataDto> apiDataDtos2List = new ArrayList<APIDataDto>(apiDataDtos2);
		for(APIDataDto dataDto: apiDataDtos1) {
			System.out.println("DataProcessorServiceImpl.apiDataProcessor().apiDataDtos1 "+dataDto.hashCode() +" contains in apiDataDtos2 = "+apiDataDtos2.contains(dataDto));
			if(apiDataDtos2.contains(dataDto)) {
				System.out.println("DataProcessorServiceImpl.apiDataProcessor() in if statement checking contains or not");
				int index = apiDataDtos2List.indexOf(dataDto);
				APIDataDto dataDto2 = apiDataDtos2List.get(index);
				ProcessedAPIDataDto processedAPIDataDto = new ProcessedAPIDataDto(dataDto.getApi(),dataDto.getCount()-dataDto2.getCount(),dataDto.getDuration()-dataDto2.getDuration());
				processedAPIDataDtos.add(processedAPIDataDto);
			}
		}
				System.out.println("DataProcessorServiceImpl.apiDataProcessor() processedAPIDataDtos"+processedAPIDataDtos);
		return processedAPIDataDtos;
	}
	
	public static HashSet<APIDataDto> excelToHashSet(MultipartFile multipartFile){
		
		HashSet<APIDataDto> apiDataDtos = null;
		
		
		
		try {

			Reader in = new InputStreamReader(multipartFile.getInputStream());
			Iterable<CSVRecord> records= CSVFormat.EXCEL.builder().setSkipHeaderRecord(true).setHeader("request url", "count", "duration").build().parse(in).getRecords();
			apiDataDtos = new HashSet<APIDataDto>();
			for (CSVRecord record : records) {
			    try {
			    APIDataDto apiDataDto = new APIDataDto();
			    apiDataDto.setApi(record.get("request url")); 
			    apiDataDto.setCount(Long.parseLong(record.get("count")));
			    apiDataDto.setDuration(Double.parseDouble(record.get("duration")));
			    apiDataDtos.add(apiDataDto);
			    
			    }catch (Exception e) {
			    	e.printStackTrace();
					continue;
				}
			}
		}catch (Exception e) {
		}
		
		
//System.out.println("DataProcessorServiceImpl.excelToHashSet() " +apiDataDtos);		
		return apiDataDtos;
	}
	
}
