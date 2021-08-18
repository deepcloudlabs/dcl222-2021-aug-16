package com.example.banking.dto;

public class TransferResponse {
	private String status;
	
	public TransferResponse(String status) {
		this.status = status;
	}

	public String getStatus() {
		return status;
	}

}
