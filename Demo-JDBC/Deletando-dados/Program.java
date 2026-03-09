package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import db.DB;
import db.DbIntegrityException;

public class Program {

	public static void main(String[] args) {
		
		Connection conn = null;
		PreparedStatement st = null;
		try {
			conn = DB.getConnection();
			
			st = conn.prepareStatement(
					"DELETE FROM Department "
					+ "WHERE "
					+ "Id = ?");
			st.setInt(1, 2);
					
			
			int linhasAfetadas = st.executeUpdate();
			System.out.println("Done! Quantas linhas foram afetadas: " + linhasAfetadas);
		} catch (SQLException e) {
			throw new DbIntegrityException(e.getMessage());
		} 
		finally {
			DB.closeStatement(st);
			DB.closeConnection();
		}
		
	}
}
