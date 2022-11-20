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
	
	@Override
	public int hashCode() {
		int result = 9;
		if(apiURI !=null) {
			result = 21 * result + apiURI.hashCode();
		}
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj == this)
			return true;
		APIDataDto dataDto = (APIDataDto)obj;
		return this.apiURI.equals(dataDto.getApi());
	}

}
