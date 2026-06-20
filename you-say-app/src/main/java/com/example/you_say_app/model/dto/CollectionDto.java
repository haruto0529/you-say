package com.example.you_say_app.model.dto;

import java.time.LocalDateTime;

public class CollectionDto {
	private int collection_id;
	private int user_id;
	private int quote_id;
	private boolean has_gold_medal;
	private LocalDateTime created_at;
	private LocalDateTime deleted_at;
	
	
	public CollectionDto(int collection_id, int user_id, int quote_id, boolean has_gold_medal, LocalDateTime created_at,
			LocalDateTime deleted_at) {
		super();
		this.collection_id = collection_id;
		this.user_id = user_id;
		this.quote_id = quote_id;
		this.has_gold_medal = has_gold_medal;
		this.created_at = created_at;
		this.deleted_at = deleted_at;
	}


	public CollectionDto() {
		super();
	}


	public int getCollection_id() {
		return collection_id;
	}


	public void setCollection_id(int collection_id) {
		this.collection_id = collection_id;
	}


	public int getUser_id() {
		return user_id;
	}


	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}


	public int getQuote_id() {
		return quote_id;
	}


	public void setQuote_id(int quote_id) {
		this.quote_id = quote_id;
	}


	public boolean isHas_gold_medal() {
		return has_gold_medal;
	}


	public void setHas_gold_medal(boolean has_gold_medal) {
		this.has_gold_medal = has_gold_medal;
	}


	public LocalDateTime getCreated_at() {
		return created_at;
	}


	public void setCreated_at(LocalDateTime created_at) {
		this.created_at = created_at;
	}


	public LocalDateTime getDeleted_at() {
		return deleted_at;
	}


	public void setDeleted_at(LocalDateTime deleted_at) {
		this.deleted_at = deleted_at;
	}


	@Override
	public String toString() {
		return "CollectionDto [collection_id=" + collection_id + ", user_id=" + user_id + ", quote_id=" + quote_id
				+ ", has_gold_medal=" + has_gold_medal + ", created_at=" + created_at + ", deleted_at=" + deleted_at
				+ "]";
	}
	
	
	
	

}
