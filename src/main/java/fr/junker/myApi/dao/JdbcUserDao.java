package fr.junker.myApi.dao;

import java.util.List;

import fr.junker.myApi.datasource.MySQLConnector;
import fr.junker.myApi.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;




public class JdbcUserDao implements IUserDao{
	
	private MySQLConnector connector;
	
	public JdbcUserDao(){
		connector = new MySQLConnector();
	}
	
	public void update(User user) throws Exception{
		Connection conn = connector.createConnection();
		
		String sql = "update user set name = ?, age = ? where id = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, user.getName());
		ps.setInt(2, user.getAge());
		ps.setInt(3, user.getId());
		
		int result = ps.executeUpdate();
		
		if (!(result == 0 || result == 1)){
			System.err.println("erreur dans update user, nombre d'user modifiés différents de 1 ou 0");
		}
		
		ps.close();
		conn.close();
 	}
	
	public User getById(Integer id) throws Exception{
		Connection conn = connector.createConnection();
		User user = null;

		
		String sql = "select id, name, age from user where id = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, id);
		
		ResultSet rs = ps.executeQuery();
		
		if (rs.next()){
			user = new User();
			user.setId(rs.getInt("id"));
			user.setName(rs.getString("name"));
			user.setAge(rs.getInt("age"));
			
		}
		rs.close();
		ps.close();
		conn.close();
		
		return user;
	}
	
	public void delete(User user) throws Exception{
		Connection conn = connector.createConnection();
		
		String sql = "delete from user where id = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, user.getId());
		
		int result = ps.executeUpdate();
		
		if (result != 1){
			System.err.println("erreur dans delete user, nombre d'user deletés différents de 1");
		}
		
		
		ps.close();
		conn.close();
	}
	
	public User add(User user) throws Exception{
		Connection conn = connector.createConnection();
		
		String sql = "insert into user (name, age) values(?, ?);";
		PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		ps.setString(1, user.getName());
		ps.setInt(2, user.getAge());
		
		
		ps.executeUpdate();
		
		ResultSet rs = ps.getGeneratedKeys();
		
		if (rs.next()){
			user.setId(rs.getInt(1));
		}
		rs.close();
		ps.close();
		conn.close();
		
		return user;
	}
	
	public List<User> getAll() throws Exception{
		Connection conn = connector.createConnection();
		List<User> list = new ArrayList<User>();
		
		String sql = "select id, name, age from user";
		PreparedStatement ps = conn.prepareStatement(sql);
		
		ResultSet rs = ps.executeQuery();
		
		while(rs.next()){
			User user = new User();
			user.setId(rs.getInt("id"));
			user.setName(rs.getString("name"));
			user.setAge(rs.getInt("age"));
			
			list.add(user);
		}
		
		rs.close();
		ps.close();
		conn.close();
		
		return list;
		
	}
	
}
