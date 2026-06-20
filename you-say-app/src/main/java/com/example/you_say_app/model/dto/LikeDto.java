package com.example.you_say_app.model.dto;

import java.time.LocalDateTime;

public class LikeDto {
	int collectionId;
	LocalDateTime createdAt;
	LocalDateTime updatedAt;
	LocalDateTime deletedAt;

	public LikeDto() {

	}

	public LikeDto(int collectionId, LocalDateTime createdAt, LocalDateTime updatedAt, LocalDateTime deletedAt) {
		super();
		this.collectionId = collectionId;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.deletedAt = deletedAt;
	}

	public int getCollectionId() {
		return collectionId;
	}

	public void setCollectionId(int collectionId) {
		this.collectionId = collectionId;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

	public LocalDateTime getDeletedAt() {
		return deletedAt;
	}

	public void setDeletedAt(LocalDateTime deletedAt) {
		this.deletedAt = deletedAt;
	}

	@Override
	public String toString() {
		return "LikeDto [collectionId=" + collectionId + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt
				+ ", deletedAt=" + deletedAt + "]";
	}

}
