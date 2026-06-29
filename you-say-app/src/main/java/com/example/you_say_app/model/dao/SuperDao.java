package com.example.you_say_app.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * SuperDao - データベース接続の取得を担当する基底クラス
 */
public class SuperDao {

    // 接続情報は本来外部ファイル化すべきですが、教育上の簡略化のため直接記述します
    private static final String DB_URI =
    		"jdbc:mysql://localhost:3306/you_say?characterEncoding=utf8&"
            + "useSSL=false&serverTimezone=GMT%2B9&"
            + "rewriteBatchedStatements=true";
    private static final String DB_USER = "root";
    private static final String DB_PASS = "";

    /**
     * データベース接続を取得する
     * 呼び出し元で try-with-resources を使用して確実に close することを想定
     * @return データベース接続オブジェクト
     * @throws SQLException 接続失敗時にスロー
     */
    protected Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URI, DB_USER, DB_PASS);
    }
    
    public static void main(String[] args) {
SuperDao dao = new SuperDao();
        
        System.out.println("テストを開始します...");

        // try-with-resources 文を使用して接続テストを実施
        try (Connection con = dao.getConnection()) {
            
            if (con != null && !con.isClosed()) {
                System.out.println("成功：正常に接続されました！");
                System.out.println("接続先: " + DB_URI);
            }

        } catch (SQLException e) {
            System.err.println("失敗：エラーが発生しました。");
            System.err.println("原因: " + e.getMessage());
            e.printStackTrace();
        }
        
        // tryブロックを抜けると自動的に con.close() が呼ばれる
        System.out.println("接続は自動的に切断されました");
    }
}