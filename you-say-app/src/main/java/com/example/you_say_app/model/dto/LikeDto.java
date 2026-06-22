package com.example.you_say_app.model.dto;

import java.time.LocalDateTime;

public class LikeDto {
	private int collectionId;
	private LocalDateTime createdAt;
	private LocalDateTime updateAt;
	private LocalDateTime deletedAt;

	public LikeDto() {

	}

	public LikeDto(int collectionId, LocalDateTime createdAt, LocalDateTime updateAt, LocalDateTime deletedAt) {
		super();
		this.collectionId = collectionId;
		this.createdAt = createdAt;
		this.updateAt = updateAt;
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

	public LocalDateTime getUpdateAt() {
		return updateAt;
	}

	public void setUpdateAt(LocalDateTime updateAt) {
		this.updateAt = updateAt;
	}

	public LocalDateTime getDeletedAt() {
		return deletedAt;
	}

	public void setDeletedAt(LocalDateTime deletedAt) {
		this.deletedAt = deletedAt;
	}

	@Override
	public String toString() {
		return "likeDto [collectionId=" + collectionId + ", createdAt=" + createdAt + ", updateAt=" + updateAt
				+ ", deletedAt=" + deletedAt + "]";
	}

}
