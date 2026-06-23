package com.example.you_say_app.model.dto;

import java.time.LocalDateTime;

public class OutputCollectionDto {
	private int likeCount;
	private boolean isLiked;
	private int collectionId;
	private int userId;
	private int quoteId;
	private String quote;
	private Boolean hasGoldMedal;
	private LocalDateTime createdAt;
	private LocalDateTime deletedAt;

	public OutputCollectionDto(int likeCount, boolean isLiked, int collectionId, int userId, int quoteId, String quote,
			Boolean hasGoldMedal, LocalDateTime createdAt, LocalDateTime deletedAt) {
		super();
		this.likeCount = likeCount;
		this.isLiked = isLiked;
		this.collectionId = collectionId;
		this.userId = userId;
		this.quoteId = quoteId;
		this.quote = quote;
		this.hasGoldMedal = hasGoldMedal;
		this.createdAt = createdAt;
		this.deletedAt = deletedAt;
	}

	public OutputCollectionDto() {
		// TODO 自動生成されたコンストラクター・スタブ
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

	public int getQuoteId() {
		return quoteId;
	}

	public void setQuoteId(int quoteId) {
		this.quoteId = quoteId;
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

	public int getLikeCount() {
		return likeCount;
	}

	public void setLikeCount(int likeCount) {
		this.likeCount = likeCount;
	}

	public boolean isLiked() {
		return isLiked;
	}

	public void setLiked(boolean isLiked) {
		this.isLiked = isLiked;
	}

	@Override
	public String toString() {
		return "OutputCollectionDto [likeCount=" + likeCount + ", isLiked=" + isLiked + ", collectionId=" + collectionId
				+ ", userId=" + userId + ", quoteId=" + quoteId + ", quote=" + quote + ", hasGoldMedal=" + hasGoldMedal
				+ ", createdAt=" + createdAt + ", deletedAt=" + deletedAt + "]";
	}

}
