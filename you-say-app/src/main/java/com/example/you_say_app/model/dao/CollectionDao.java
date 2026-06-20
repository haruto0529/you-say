package com.example.you_say_app.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

import org.springframework.stereotype.Repository;

import com.example.you_say_app.model.dto.OutputCollectionDto;

@Repository
public class CollectionDao extends SuperDao {
	public int insertCollection(int user_id, int quote_id) {
		int ret = 0;
		String sql = "INSERT INTO you_say.collecton_quotes (user_id, quote_id) VALUES (?, ?)";

		try (Connection con = getConnection();
				PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setInt(1, user_id);
			ps.setInt(2, quote_id);

			ret = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ret;
	}
	
//	問題文と答えとQuoteIdを引き出してくるメソッド
	public OutputCollectionDto collectionDisplay(int userId) {
		OutputCollectionDto collect = new OutputCollectionDto();
		String sql = "SELECT c.collection_id, c.user_id, c.quote_id, quotes.full_text, c.has_gold_medal, c.created_at, c.deleted_at FROM collecton_quotes AS c JOIN quotes ON c.quote_id = quotes.quote_id WHERE user_id = ?";
		try (Connection con = getConnection();
				PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setInt(1, userId);
			try (ResultSet rs = ps.executeQuery()) {

				while (rs.next()) {
					collect.setCollectionId(rs.getInt("collection_id"));
					collect.setUserId(rs.getInt("user_id"));
					collect.setQuoteId(rs.getInt("quote_id"));
					collect.setQuote(rs.getString("full_text"));
					collect.setHasGoldMedal(rs.getBoolean("has_gold_medal"));
					collect.setQuote(rs.getString("full_text"));
					collect.setCreatedAt(rs.getObject("created_at", LocalDateTime.class));
					collect.setDeletedAt(rs.getObject("deleted_at", LocalDateTime.class));
					
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return collect;

	}

}
