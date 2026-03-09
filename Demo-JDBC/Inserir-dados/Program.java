package application;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import db.DB;

public class Program {

	public static void main(String[] args) {
		
		Connection conn = null; // abrir a porta do banco
		
		Statement st = null; // Objeto usado para enviar comandos SQL para o banco, 
		// falar com o banco e dar a ordem.
		
		ResultSet rs = null; // Objeto que guarda o resultado de um SELECT.
		
		try {
			conn = DB.getConnection();
			
			st = conn.createStatement();
			
			rs = st.executeQuery("select * from department");
			
			while(rs.next()) { // enquanto existir o proximo
				System.out.println(rs.getInt("Id") + ", " + rs.getString("Name")); // pega o int que está no campo ID
				
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			DB.closeResultSet(rs);
			DB.closeStstement(st);
			DB.getConnection();
		}
	}
}
