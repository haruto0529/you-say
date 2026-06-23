package com.example.you_say_app.model.dto;

public class RankDto {
	private int rank_id;
	private String rank_name;
	private int required_number;
	public RankDto(int rank_id, String rank_name, int required_number) {
		super();
		this.rank_id = rank_id;
		this.rank_name = rank_name;
		this.required_number = required_number;
	}
	public int getRank_id() {
		return rank_id;
	}
	public void setRank_id(int rank_id) {
		this.rank_id = rank_id;
	}
	public String getRank_name() {
		return rank_name;
	}
	public void setRank_name(String rank_name) {
		this.rank_name = rank_name;
	}
	public int getRequired_number() {
		return required_number;
	}
	public void setRequired_number(int required_number) {
		this.required_number = required_number;
	}
	@Override
	public String toString() {
		return "RankDto [rank_id=" + rank_id + ", rank_name=" + rank_name + ", required_number=" + required_number
				+ "]";
	}

}
