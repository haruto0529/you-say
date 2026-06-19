package com.example.you_say_app.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CollectionDao extends SuperDao {
	public int insartCollection(int user_id, int quote_id) {
		int ret = 0;
		String sql = "INSERT INTO `you_say`.`collecton_quotes` (user_id, `quote_id, has_gold_medal) VALUES (?, ?, 0)";

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

}
