package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Model.CategoriaM;

public class CategoriaDao {
	
	public void adicionar(CategoriaM categoria) throws SQLException {
		
		try {
			Connection connection = new ConnectionFactory().getConnection();			
			String sql = "INSERT INTO tbcategoria(nomeCategoria) values (?)";
			PreparedStatement stmt = connection.prepareStatement(sql);
			
			stmt.setString(1, categoria.getNomeCategoria());
			
			stmt.execute();
			stmt.close();
			connection.close();
			System.out.println("Produto cadastrado com sucesso");
		}
		catch(SQLException e){
			System.out.println("Erro: "+e);
		}

	}
	
	public List<CategoriaM> getLista() throws SQLException{
		try {
			List<CategoriaM> categorias = new ArrayList<CategoriaM>();
			
			Connection connection = new ConnectionFactory().getConnection();
			
			PreparedStatement stmt = connection.prepareStatement("SELECT * FROM tbcategoria");
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				CategoriaM categoria = new CategoriaM();
				
				categoria.setCodCategoria(rs.getInt(1));
				categoria.setNomeCategoria(rs.getString(2));
				
				
				categorias.add(categoria);
			}
			rs.close();
			stmt.close();
			
			return categorias;
		}
		catch(SQLException e) {
			throw new RuntimeException();
		}
	}
	
	public void alterar(CategoriaM categoria) throws SQLException{
		try {
			Connection connection = new ConnectionFactory().getConnection();
			String sql = "UPDATE tbcategoria SET nomeCategoria = (?) WHERE codCategoria = (?)";
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, categoria.getNomeCategoria());
			stmt.setInt(2, categoria.getCodCategoria());
			
			
			stmt.execute();
			stmt.close();
			connection.close();
			System.out.println("Dados alterados com sucesso");
		}
		catch(SQLException e) {
			throw new RuntimeException();	
		}
	}
	
	public void excluir(CategoriaM categoria) throws SQLException{
		
		
		
		try {
			Connection connection = new ConnectionFactory().getConnection();
			String sql = "DELETE FROM tbcategoria WHERE codCategoria = (?)";
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setInt(1,categoria.getCodCategoria());
			stmt.execute();
			stmt.close();
			connection.close();
			System.out.println("Dados Excluidos com Sucesso");
		}
		catch(SQLException e) {
			throw new RuntimeException();	
		}
	}
	
	public List<CategoriaM> pegaCategoriaPorCod(int cod) throws SQLException{
		try {
			List<CategoriaM> categorias = new ArrayList<CategoriaM>();
			
			Connection connection = new ConnectionFactory().getConnection();
			
			PreparedStatement stmt = connection.prepareStatement("SELECT * FROM tbcategoria WHERE codCategoria = " + cod);
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				CategoriaM categoria = new CategoriaM();
				
				categoria.setCodCategoria(rs.getInt(1));
				categoria.setNomeCategoria(rs.getString(2));
				
				
				categorias.add(categoria);
			}
			rs.close();
			stmt.close();
			
			return categorias;
		}
		catch(SQLException e) {
			throw new RuntimeException();
		}
	}
	
	
}
