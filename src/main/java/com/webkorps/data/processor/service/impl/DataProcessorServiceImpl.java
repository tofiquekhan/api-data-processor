package com.webkorps.data.processor.service.impl;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashSet;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.webkorps.data.processor.dto.APIDataDto;
import com.webkorps.data.processor.dto.ProcessedAPIDataDto;
import com.webkorps.data.processor.service.DataProcessorService;

@Service

public class DataProcessorServiceImpl implements DataProcessorService {

	@Override
	public HashSet<ProcessedAPIDataDto> apiDataProcessor(MultipartFile lastFile, MultipartFile currentFile) {
		HashSet<ProcessedAPIDataDto> processedAPIDataDtos = new HashSet<ProcessedAPIDataDto>();
		HashSet<APIDataDto> lastFileApiDataDtos = excelToHashSet(lastFile);
		HashSet<APIDataDto> currentFileApiDataDtos = excelToHashSet(currentFile);
		ArrayList<APIDataDto> currentFileApiDataDtosList = new ArrayList<APIDataDto>(currentFileApiDataDtos);
		for (APIDataDto lastFileApiDataDto : lastFileApiDataDtos) {
			if (currentFileApiDataDtos.contains(lastFileApiDataDto)) {
				System.out.println(
						"DataProcessorServiceImpl.apiDataProcessor() in if statement checking contains or not");
				int index = currentFileApiDataDtosList.indexOf(lastFileApiDataDto);
				APIDataDto currentFileApiDataDto = currentFileApiDataDtosList.get(index);
				ProcessedAPIDataDto processedAPIDataDto = new ProcessedAPIDataDto(lastFileApiDataDto.getApi(),
						lastFileApiDataDto.getCount() - currentFileApiDataDto.getCount(),
						lastFileApiDataDto.getDuration() - currentFileApiDataDto.getDuration());
				processedAPIDataDtos.add(processedAPIDataDto);
			}
		}
		return processedAPIDataDtos;
	}

	public static HashSet<APIDataDto> excelToHashSet(MultipartFile multipartFile) {

		HashSet<APIDataDto> apiDataDtos = new HashSet<APIDataDto>();
		FileInputStream in = null;
		XSSFWorkbook workbook = null;
		XSSFSheet sheet = null;
		try {

			in = (FileInputStream) multipartFile.getInputStream();
			workbook = new XSSFWorkbook(in);
			sheet = workbook.getSheetAt(0);
		} catch (Exception e) {
			e.printStackTrace();
		}
		for (Row row : sheet) {
				try {
					APIDataDto dataDto = new APIDataDto();
					dataDto.setApi(row.getCell(0).getStringCellValue());
					dataDto.setCount(Math.round(row.getCell(1).getNumericCellValue()));
					dataDto.setDuration(row.getCell(2).getNumericCellValue());
					apiDataDtos.add(dataDto);
				} catch (Exception e) {
					e.printStackTrace();
					continue;
				}
				
		}

		return apiDataDtos;
	}

}
