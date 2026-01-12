package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Model.ProdutoM;

public class ProdutoDao {
	
	
	public void adicionar(ProdutoM produto) throws SQLException {
		
		try {
			Connection connection = new ConnectionFactory().getConnection();
			String sql = "INSERT INTO tbproduto(nomeProduto,valorProduto,quantiProduto,codCategoria) values (?,?,?,?)";
			PreparedStatement stmt = connection.prepareStatement(sql);
			
			stmt.setString(1, produto.getNomeProduto());
			stmt.setDouble(2, produto.getValorProduto());
			stmt.setInt(3, produto.getQuantiProduto());
			stmt.setInt(4, produto.getCodCategoria());
			
			stmt.execute();
			stmt.close();
			connection.close();
			System.out.println("Produto cadastrado com sucesso");
		}
		catch(SQLException e){
			System.out.println("Erro: "+e);
		}

	}
	
	public List<ProdutoM> getLista() throws SQLException{
		try {
			List<ProdutoM> produtos = new ArrayList<ProdutoM>();
			
			Connection connection = new ConnectionFactory().getConnection();
			
			PreparedStatement stmt = connection.prepareStatement("SELECT * FROM tbproduto");
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				ProdutoM produto = new ProdutoM();
				
				produto.setCodProduto(rs.getInt(1));
				produto.setNomeProduto(rs.getString(2));
				produto.setValorProduto(rs.getDouble(3));
				produto.setQuantiProduto(rs.getInt(4));
				produto.setCodCategoria(rs.getInt(5));
				
				produtos.add(produto);
			}
			rs.close();
			stmt.close();
			
			return produtos;
		}
		catch(SQLException e) {
			throw new RuntimeException();
		}
	}
	
	public void alterar(ProdutoM produto) throws SQLException{

		try {
			Connection connection = new ConnectionFactory().getConnection();
			
			String sql = "UPDATE tbproduto SET nomeProduto = (?), valorProduto = (?), quantiProduto = (?), codCategoria = (?) WHERE codProduto = (?)";
			
			PreparedStatement stmt = connection.prepareStatement(sql);
			
			stmt.setString(1, produto.getNomeProduto());
			stmt.setDouble(2, produto.getValorProduto());
			stmt.setDouble(3, produto.getQuantiProduto());
			stmt.setInt(4, produto.getCodCategoria());
			stmt.setInt(5, produto.getCodProduto());
			
			stmt.execute();
			stmt.close();
			
			connection.close();
			System.out.println("Dados alterados com sucesso");
		}
		catch(SQLException e) {
			throw new RuntimeException();	
		}
	}
	
	public void excluir(ProdutoM produto) throws SQLException{
		
		String sql = "DELETE FROM tbproduto WHERE tbproduto.codProduto = (?)";
		
		try {
			Connection connection = new ConnectionFactory().getConnection();
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setInt(1,produto.getCodProduto());
			stmt.execute();
			stmt.close();
			
			connection.close();
			
			System.out.println("Dados Excluidos com Sucesso");
		}
		catch(SQLException e) {
			throw new RuntimeException();	
		}
	}
}
