package com.webkorps.data.processor.dto;

public class ProcessedAPIDataDto {

	private String apiURI;
	private Long countDifference;
	private Double durationDifference;
	
	public ProcessedAPIDataDto() {
	}
	
	
	
	public ProcessedAPIDataDto(String apiURI, Long countDifference, Double durationDifference) {
		super();
		this.apiURI = apiURI;
		this.countDifference = countDifference;
		this.durationDifference = durationDifference;
	}



	public String getApiURI() {
		return apiURI;
	}
	public void setApiURI(String apiURI) {
		this.apiURI = apiURI;
	}
	public Long getCountDifference() {
		return countDifference;
	}
	public void setCountDifference(Long countDifference) {
		this.countDifference = countDifference;
	}
	public Double getDurationDifference() {
		return durationDifference;
	}
	public void setDurationDifference(Double durationDifference) {
		this.durationDifference = durationDifference;
	}
	
	
}
