package com.ch.model1.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

//전국 100산 관광정보 오픈 api 응답결과 중 가장 안쪽 들어있는 item정보담기위한 DTO
@Data
@JsonIgnoreProperties(ignoreUnknown = true) 
public class Item {
	private String placeNm; 
	private double lat; 
	private double lot; 
	private String frtrlNm; 
}
