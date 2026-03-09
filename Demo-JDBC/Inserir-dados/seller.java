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
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Connection conn = null;
		PreparedStatement st = null;
		
		try {
			conn = DB.getConnection();
			
			st = conn.prepareStatement(
					"INSERT INTO seller " // seller: onde os dados estão armazenados no JDBC.
					+ "(Name, Email, BirthDate, BaseSalary, DepartmentId) "
					+ "VALUES "
					+ "(?, ?, ?, ?, ?)",
					Statement.RETURN_GENERATED_KEYS);
			
			st.setString(1, "Carl Purple");
			st.setString(2, "carl@gmail.com");
			st.setDate(3, new java.sql.Date(sdf.parse("01/01/2026").getTime())); // use java.sql.Date no JDBC, 
			// e use java.util.Date no javaEE
			st.setDouble(4, 3000.0);
			st.setInt(5, 4);
			
			int linhasAlteradas = st.executeUpdate(); // chame ele quando for executar os dados.
			
			if (linhasAlteradas > 0) {
				ResultSet rs = st.getGeneratedKeys(); // rs. = mostra os ids.
				
				while (rs.next()) { // percorre o ResultSet.
					int id = rs.getInt(1); // pega o primeiro id.
					System.out.println("Done! Id = " + id);
				}
				
			} else {
				System.out.println("Nenhuma linha foi afetada no banco!");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
		finally {
			DB.closeStatement(st);
			DB.closeConnection();
		}
	}
}
