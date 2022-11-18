package com.webkorps.data.processor.dto;

public class APIDataDto {
	
	private String apiURI;
	private Long count;
	private Double duration;
	public String getApi() {
		return apiURI;
	}
	public void setApi(String api) {
		this.apiURI = api;
	}
	public Long getCount() {
		return count;
	}
	public void setCount(Long count) {
		this.count = count;
	}
	public Double getDuration() {
		return duration;
	}
	public void setDuration(Double duration) {
		this.duration = duration;
	}
	
	

}
