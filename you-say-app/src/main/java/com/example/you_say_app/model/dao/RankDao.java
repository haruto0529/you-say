package com.example.you_say_app.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.stereotype.Repository;

import com.example.you_say_app.model.dto.RankDto;

@Repository
public class RankDao extends SuperDao {
	// 指定された rank_id に一致するランク情報を1件取得する
	public RankDto getRank(int rank_id) {
	    // ranks テーブルから rank_id で1件検索するSQL
	    String sql = "SELECT * FROM you_say.ranks where rank_id = ?";
	    try (Connection con = getConnection();
	            PreparedStatement ps = con.prepareStatement(sql)) {
	        ps.setInt(1, rank_id); // WHERE の ? に rank_id をセット
	        try (ResultSet rs = ps.executeQuery()) { // SELECT実行
	            if (rs.next()) { // 1件見つかったら
	                RankDto rank = new RankDto();
	                rank.setRank_id(rs.getInt("rank_id"));                 // ランクID
	                rank.setRank_name(rs.getString("rank_name"));          // ランク名
	                rank.setRequired_number(rs.getInt("required_number")); // 昇格に必要な数
	                return rank; // 取得したランク情報を返す
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace(); // エラー内容をコンソールに出力
	    }
	    return null; // 見つからない or エラー時は null
	}

}
