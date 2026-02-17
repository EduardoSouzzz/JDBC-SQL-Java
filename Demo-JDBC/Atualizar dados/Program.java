package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import db.DB;

public class Program {

	public static void main(String[] args) {
		
		Connection conn = null;
		PreparedStatement st = null;
		try {
			conn = DB.getConnection();
			
			st = conn.prepareStatement(
					"UPDATE seller " // atualize os vendedores
					+ "SET BaseSalary = BaseSalary + ? " // mudando o salario para salario + 
					// o salario(?) que eu for informar
					+ "WHERE " // onde
					+ "(DepartmentId = ?)"); // departmentId for igual a um valor que eu informar
			
			// atribuir valor para o ?
			st.setDouble(1, 200.0);
			st.setInt(2, 2);
			
			int linhasAfetadas = st.executeUpdate();
			System.out.println("Done! Quantas linhas foram afetadas: " + linhasAfetadas);
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		finally {
			DB.closeStatement(st);
			DB.closeConnection();
		}
		
	}
}
