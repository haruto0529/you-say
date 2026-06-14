package com.example.you_say_app.model.dto;

public class QuotesCollectionsDto {

	private int collection_id;
	private int user_id;
	private int quote_id;
	private int has_gold_medal;
	private String created_at;
	private String deleted_at;
	
	public QuotesCollectionsDto(int collection_id, int user_id, int quote_id, int has_gold_medal, String created_at,
			String deleted_at) {
		super();
		this.collection_id = collection_id;
		this.user_id = user_id;
		this.quote_id = quote_id;
		this.has_gold_medal = has_gold_medal;
		this.created_at = created_at;
		this.deleted_at = deleted_at;
	}
	
	public QuotesCollectionsDto() {
		
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

	public int getHas_gold_medal() {
		return has_gold_medal;
	}

	public void setHas_gold_medal(int has_gold_medal) {
		this.has_gold_medal = has_gold_medal;
	}

	public String getCreated_at() {
		return created_at;
	}

	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}

	public String getDeleted_at() {
		return deleted_at;
	}

	public void setDeleted_at(String deleted_at) {
		this.deleted_at = deleted_at;
	}

	@Override
	public String toString() {
		return "QuotesCollectionsDto [collection_id=" + collection_id + ", user_id=" + user_id + ", quote_id="
				+ quote_id + ", has_gold_medal=" + has_gold_medal + ", created_at=" + created_at + ", deleted_at="
				+ deleted_at + "]";
	}
	
	
	
}
