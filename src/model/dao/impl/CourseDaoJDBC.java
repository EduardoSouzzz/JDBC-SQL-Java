package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import db.DB;
import db.DbException;
import model.dao.CourseDao;
import model.entities.Course;

public class CourseDaoJDBC implements CourseDao{
	
	private Connection conn;
	
	public CourseDaoJDBC(Connection conn) {
		this.conn = conn;
	}
	
	public void insert(Course obj) {
		
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			st = conn.prepareStatement(
					"INSERT INTO course "
					+ "(name, workload, price) "
					+ "VALUES "
					+ "(?, ?, ?)",
					Statement.RETURN_GENERATED_KEYS
					);
			st.setString(1, obj.getName());
			st.setInt(2, obj.getWorkload());
			st.setDouble(3, obj.getPrice());
			
			
			int linhasAfect = st.executeUpdate();
			if (linhasAfect > 0) {
				rs = st.getGeneratedKeys();
				if (rs.next()) { 
					int id = rs.getInt(1);
					obj.setId(id);
				}
			} 
			else { 
				throw new DbException("Erro inesperado, nenhuma linha foi afetada.");
			}		
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
			
		}
	}
	
	public void update(Course obj) {
		
		PreparedStatement st = null;
		
		try {
			st = conn.prepareStatement(
					"UPDATE course "
					+ "SET name = ?, workload = ?, price = ? "
					+ "WHERE id = ?");
			
			st.setString(1, obj.getName());
			st.setInt(2, obj.getWorkload());
			st.setDouble(3, obj.getPrice());
			st.setInt(4, obj.getId());
			
			st.executeUpdate();
			
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
			
		}
	}
	
	public void deleteById(Course id) {
		
		PreparedStatement st = null;
		
		try {
			st = conn.prepareStatement(
					"SELECT FROM course WHERE id = ?");
			
			st.setInt(1, id.getId());
			st.executeUpdate();
			
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
		}
	}
	
	public Course findById(Integer id) {
		
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			st = conn.prepareStatement(
					"SELECT * FROM course WHERE id = ?");
			
			st.setInt(1, id);
			rs = st.executeQuery();
			
			if (rs.next()) {
				Course c = new Course();
				c.setId(rs.getInt("id"));
				c.setName(rs.getString("name"));
				c.setWorkload(rs.getInt("workload"));
				c.setPrice(rs.getDouble("price"));
				return c;
			}
			return null;
			
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}

	@Override
	public void deleteById(Integer id) {
		PreparedStatement st = null;
		
		try {
			st = conn.prepareStatement(
					"DELETE FROM course WHERE id = ?");
			st.setInt(1, id);
			st.executeUpdate();
			
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
		}
	}

	@Override
	public List<Course> findAll() {
		
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			st = conn.prepareStatement(
					"SELECT * FROM course ORDER BY id");
			rs = st.executeQuery();
			
			List<Course> list = new ArrayList<>();
			
			while (rs.next()) {
				Course obj = new Course();
				obj.setId(rs.getInt("id"));
				obj.setName(rs.getString("name"));
				obj.setWorkload(rs.getInt("workload"));
				obj.setPrice(rs.getDouble("price"));
				list.add(obj);
			}
			return list;
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}
}
