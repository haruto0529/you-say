package com.example.you_say_app.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.you_say_app.model.dto.RankDto;

@Repository
public class RanksDao extends SuperDao {
	//テーブルの全てを表示させるSQL
	private static String sql1 = "SELECT * FROM you_say.ranks";

	//	テーブルのレコード全てをリストで持ってくるメソッド
	public List<RankDto> getRankList() {
		List<RankDto> rankList = new ArrayList<>();

		try (Connection con = getConnection();
				PreparedStatement ps = con.prepareStatement(sql1);
				ResultSet rs = ps.executeQuery()) {

			while (rs.next()) {
				RankDto rankDto = new RankDto();
				rankDto.setRank_id(rs.getInt("rank_id"));
				rankDto.setRank_name(rs.getString("rank_name"));
				rankDto.setRequired_number(rs.getInt("required_number"));
				rankList.add(rankDto);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rankList;
	}
}