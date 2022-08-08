package capitulo_25_bd.projeto_I.estoque;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import capitulo_25_bd.projeto_I.Dao;


public class DaoEstoque extends Dao<ItemEstoque> {

	@Override
	public List<ItemEstoque> listar() {
		List<ItemEstoque> lista = new ArrayList<ItemEstoque>();
		
		try {
			
			Statement estamento = criaConexao().createStatement();	
			ResultSet result = estamento.executeQuery("SELECT * FROM estoque");
					
		
			while(result.next()) {
				
				ItemEstoque itemestoque = new ItemEstoque();
				
				itemestoque.setId(result.getInt("id_estoque"));
				itemestoque.setQuantidade(result.getInt("quantidade"));
				itemestoque.setChav_estr_livro(result.getInt("fk_do_livro"));
				lista.add(itemestoque);					
			}		
		}		
		catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return lista;
	}

	
	@Override
	public void inserir(ItemEstoque item) {
		
		try {
			
			PreparedStatement stmt = criaConexao().prepareStatement(
						"INSERT INTO estoque (quantidade, fk_do_livro) VALUES (?,?)",
						Statement.RETURN_GENERATED_KEYS);
			
			stmt.setInt(1, item.getQuantidade());								
			stmt.setInt(2, item.getChav_estr_livro());
			
			stmt.executeUpdate();    
				
		    ResultSet rs = stmt.getGeneratedKeys();
		        
		    while (rs.next()) 
		    	item.setId(rs.getInt(1));  
		             
		    stmt.close();
		}
		catch (SQLException e) {			
			e.printStackTrace();
		}	
		
	}

	
	@Override
	public boolean deletar(ItemEstoque item) {
		
		try {
			
			PreparedStatement stmt = criaConexao().prepareStatement(
						"delete from estoque WHERE fk_do_livro=?");
			
			stmt.setInt(1, item.getChav_estr_livro());	
			
			stmt.executeUpdate();    

		    stmt.close();
		}
		catch (SQLException e) {			
			
			e.printStackTrace();
			return false;
		}
		
		return true;
	}

	
	@Override
	public boolean atualizar(ItemEstoque item) {
		
		try {
			
			PreparedStatement stmt = criaConexao().prepareStatement(
						"UPDATE estoque SET quantidade=? WHERE fk_do_livro=?");
			
			stmt.setInt(1, item.getQuantidade());								
			stmt.setInt(2, item.getChav_estr_livro());	
			
			stmt.executeUpdate();    

		    stmt.close();
		}
		catch (SQLException e) {			
			
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
	
	
	
	public boolean temRegistro(int i) {
		
		try {
			
			Statement estamento = criaConexao().createStatement();	
			ResultSet result = estamento.executeQuery("SELECT quantidade FROM estoque WHERE fk_do_livro="+i);
				
			if(result.next()) {
								
				if(result.getInt("quantidade")>= 0)
					return true;			
			}		
		}		
		catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return false;		
	}
	
	
	
	public int quantosLivros(int i) {
		
		int retorno = 0;
		
		try {
			
			Statement estamento = criaConexao().createStatement();	
			ResultSet result = estamento.executeQuery("SELECT quantidade FROM estoque WHERE fk_do_livro="+i);
				
			if(result.next()) 								
				retorno = result.getInt("quantidade");					
		}		
		catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return retorno;		
	}

	
	
	public List<ItemEstoque> listaEspecial(){
		
		List<ItemEstoque> lista = new ArrayList<ItemEstoque>();
		
		try {
			
			Statement estamento = criaConexao().createStatement();	
			ResultSet result = estamento.executeQuery("SELECT estoque.id_estoque, estoque.quantidade, titulo FROM `estoque` JOIN livros ON livros.id_livro = estoque.fk_do_livro ORDER BY estoque.id_estoque ASC");
					
		
			while(result.next()) {
				
				ItemEstoque itemestoque = new ItemEstoque();
				
				itemestoque.setId(result.getInt("id_estoque"));
				itemestoque.setQuantidade(result.getInt("quantidade"));
				itemestoque.setTitulo(result.getString("titulo"));;
				lista.add(itemestoque);					
			}		
		}		
		catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return lista;
	}
}
