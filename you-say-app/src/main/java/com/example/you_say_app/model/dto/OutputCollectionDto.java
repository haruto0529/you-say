package com.example.you_say_app.model.dto;

import java.time.LocalDateTime;

public class OutputCollectionDto {
	private int collectionId;
	private int userId;
	private String quote;
	private Boolean hasGoldMedal;
	private LocalDateTime createdAt;
	private LocalDateTime deletedAt;
	public OutputCollectionDto(int collectionId, int userId, String quote, Boolean hasGoldMedal,
			LocalDateTime createdAt, LocalDateTime deletedAt) {
		super();
		this.collectionId = collectionId;
		this.userId = userId;
		this.quote = quote;
		this.hasGoldMedal = hasGoldMedal;
		this.createdAt = createdAt;
		this.deletedAt = deletedAt;
	}
	public int getCollectionId() {
		return collectionId;
	}
	public void setCollectionId(int collectionId) {
		this.collectionId = collectionId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getQuote() {
		return quote;
	}
	public void setQuote(String quote) {
		this.quote = quote;
	}
	public Boolean getHasGoldMedal() {
		return hasGoldMedal;
	}
	public void setHasGoldMedal(Boolean hasGoldMedal) {
		this.hasGoldMedal = hasGoldMedal;
	}
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
	public LocalDateTime getDeletedAt() {
		return deletedAt;
	}
	public void setDeletedAt(LocalDateTime deletedAt) {
		this.deletedAt = deletedAt;
	}
	@Override
	public String toString() {
		return "OutputCollectionDto [collectionId=" + collectionId + ", userId=" + userId + ", quote=" + quote
				+ ", hasGoldMedal=" + hasGoldMedal + ", createdAt=" + createdAt + ", deletedAt=" + deletedAt + "]";
	}
	
	

}
