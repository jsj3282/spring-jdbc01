package com.care.jdbc_dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;

import com.care.jdbc_dto.JdbcDTO;
import com.care.template.Constant;

public class JdbcDAO {
	/*
	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@192.168.0.42:1521:xe";
	private String user="jsp", pwd="1234";
	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;
	*/
	private JdbcTemplate template;

	public JdbcDAO() {
		this.template = Constant.template;
		//Constant.template는 MemberController에서 만들어진다.
		/*
		try {
			Class.forName(driver);
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		*/
	}
	
	public ArrayList<JdbcDTO> list(){
		String sql = "select * from test_jdbc";
		
		ArrayList<JdbcDTO> list = (ArrayList<JdbcDTO>)template.query(sql, new BeanPropertyRowMapper<JdbcDTO>(JdbcDTO.class));
		//여러개의 값을 가져올 때는 template.query를 사용한다.
		
		return list;
		
		//return (ArrayList<JdbcDTO>)template.query(sql, new BeanPropertyRowMapper<JdbcDTO>(JdbcDTO.class));
		/*
		ArrayList<JdbcDTO> list = new ArrayList<JdbcDTO>();
		try {
			con = DriverManager.getConnection(url, user, pwd);
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				JdbcDTO dto = new JdbcDTO();
				dto.setId(rs.getInt("id"));
				dto.setName(rs.getString("name"));
				list.add(dto);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return list;
		*/
	}
	
	public void save(final String id, final String name) {
		//final : 오버라이드 되는 메소드 까지 갈때 변화가 없어야 한다.
		String sql = "insert into test_jdbc(id, name) values(?,?)";
//		template.update(sql, new PreparedStatementSetter() {
//
//			@Override
//			public void setValues(PreparedStatement ps) throws SQLException {
//				ps.setString(1,  id);
//				ps.setString(2,  name);
//				
//			}
//			
//		});	
		
		template.update(sql, ps->{
			ps.setString(1,  id);
			ps.setString(2,  name);
		});
		
		/*
		try {
			con = DriverManager.getConnection(url, user, pwd);
			ps = con.prepareStatement(sql);
			ps.setString(1,  id);
			ps.setString(2,  name);
			ps.executeUpdate();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		*/
	}
	
	public JdbcDTO modify(String id) {
		//String sql = "select * from test_jdbc where id = ?";
		String sql = "select * from test_jdbc where id = " + id;
		JdbcDTO dto = null;
		return template.queryForObject(sql, new BeanPropertyRowMapper<JdbcDTO>(JdbcDTO.class));
		//한개만 되돌려줄때는 queryForObject
		
		/*
		try {
			con = DriverManager.getConnection(url, user, pwd);
			ps = con.prepareStatement(sql);
			ps.setString(1,  id);
			rs = ps.executeQuery();
			if(rs.next()) {
				dto = new JdbcDTO();
				dto.setId(rs.getInt("id"));
				dto.setName(rs.getString("name"));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return dto;
		*/
	}
	
	public void modifySave(String id, String name) {
		String sql = "update test_jdbc set name = '" + name + "' where id = " + id;
		template.update(sql);
		
		/*
		try {
			con = DriverManager.getConnection(url, user, pwd);
			ps = con.prepareStatement(sql);
			ps.setString(1,  name);
			ps.setString(2,  id);
			ps.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		*/
		
	}
	
	public void delete(String id) {
		String sql = "delete from test_jdbc where id = " + id;
		template.update(sql);
		/*
		try {
			con = DriverManager.getConnection(url, user, pwd);
			ps = con.prepareStatement(sql);
			ps.setString(1, id);
			ps.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		*/
	}
	
	public int count() {
		String sql = "select count(*) from test_jdbc";
		return template.queryForObject(sql,  Integer.class);
	}
}
