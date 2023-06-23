package fr.junker.myApi.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.junker.myApi.datasource.MySQLConnector;
import fr.junker.myApi.model.Animal;

public class JdbcAnimalDao implements IAnimalDao{
	
	private MySQLConnector connector;
	
	public JdbcAnimalDao(){
		connector = new MySQLConnector();
	}
	
	public void update(Animal animal) throws Exception{
		Connection conn = connector.createConnection();
		
		String sql = "update animal set name = ?, type = ?, weight = ? where id = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, animal.getName());
		ps.setString(2, animal.getType());
		ps.setInt(3, animal.getWeight());
        ps.setInt(4, animal.getId());
		
		int result = ps.executeUpdate();
		
		if (!(result == 0 || result == 1)){
			System.err.println("erreur dans update animal, nombre d'animal modifiés différents de 1 ou 0");
		}
		
		ps.close();
		conn.close();
 	}
	
    @Override
	public Animal getById(Integer id) throws Exception{
		Connection conn = connector.createConnection();
		Animal animal = null;

		
		String sql = "select id, name, type, weight from animal where id = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, id);
		
		ResultSet rs = ps.executeQuery();
		
		if (rs.next()){
			animal = new Animal();
			animal.setId(rs.getInt("id"));
			animal.setName(rs.getString("name"));
			animal.setType(rs.getString("type"));
			animal.setWeight(rs.getInt("weight"));
			
		}
		rs.close();
		ps.close();
		conn.close();
		
		return animal;
	}
	
	public void delete(Animal animal) throws Exception{
		Connection conn = connector.createConnection();
		
		String sql = "delete from animal where id = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, animal.getId());
		
		int result = ps.executeUpdate();
		
		if (result != 1){
			System.err.println("erreur dans delete animal, nombre d'animal deletés différents de 1");
		}
		
		
		ps.close();
		conn.close();
	}
	
	public Animal add(Animal animal) throws Exception{
		Connection conn = connector.createConnection();
		
		String sql = "insert into animal (name, type, weight) values(?, ?, ?);";
		PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		ps.setString(1, animal.getName());
		ps.setString(2, animal.getType());
		ps.setInt(3, animal.getWeight());
		
		
		ps.executeUpdate();
		
		ResultSet rs = ps.getGeneratedKeys();
		
		if (rs.next()){
			animal.setId(rs.getInt(1));
		}
		rs.close();
		ps.close();
		conn.close();
		
		return animal;
	}
	
	public List<Animal> getAll() throws Exception{
		Connection conn = connector.createConnection();
		List<Animal> list = new ArrayList<Animal>();
		
		String sql = "select id, name, type, weight from animal";
		PreparedStatement ps = conn.prepareStatement(sql);
		
		ResultSet rs = ps.executeQuery();
		
		while(rs.next()){
			Animal animal = new Animal();
			animal.setId(rs.getInt("id"));
			animal.setName(rs.getString("name"));
			animal.setType(rs.getString("type"));
			animal.setWeight(rs.getInt("weight"));
			
			list.add(animal);
		}
		
		rs.close();
		ps.close();
		conn.close();
		
		return list;
		
	}
	
}

