package application;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import db.DB;
import db.DbException;

public class Program {

	public static void main(String[] args) {
		
		Connection conn = null;
		Statement st = null;
		try {
			conn = DB.getConnection();
			
			conn.setAutoCommit(false); // não confirmar as alterações automaticamente, vão ficar pendentes.
			
			st = conn.createStatement();
			
			int linhasAfetadas1 = st.executeUpdate("UPDATE seller SET BaseSalary = 2090 WHERE DepartmentId = 1");
			
			// int x  = 1;
			//if (x < 2) {
			//	throw new SQLException("Fake error");
			// }
			
			int linhasAfetadas2 = st.executeUpdate("UPDATE seller SET BaseSalary = 3090 WHERE DepartmentId = 2");
			conn.commit(); // transação terminou aqui
			
			System.out.println("Linhas afetadas 1: " + linhasAfetadas1);
			System.out.println("Linhas afetadas 2: " + linhasAfetadas2);

			
			
		} catch (SQLException e) {
			try {
				conn.rollback();
				throw new DbException("Voltando a transação, causado por: " + e.getMessage());
			} catch (SQLException e1) {
				throw new DbException("ERRO ao tentar voltar a transação, causado por: " + e1.getMessage());
			} 
		} 
		finally {
			DB.closeStatement(st);
			DB.closeConnection();
		}
		
	}
}
