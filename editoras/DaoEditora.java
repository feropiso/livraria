package capitulo_25_bd.projeto_I.editoras;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import capitulo_25_bd.projeto_I.Dao;
import capitulo_25_bd.projeto_I.Util;


public class DaoEditora extends Dao<Editora> {
	
	@Override
	public List<Editora> listar() {
		
		List<Editora> lista = new ArrayList<Editora>();	
		
		try {
			
			Statement estamento = criaConexao().createStatement();	
			ResultSet result = estamento.executeQuery("SELECT * FROM editoras order by ideditora ASC");
					
		
			while(result.next()) {
				
				Editora editora = new Editora();
				
				editora.setId(result.getInt("ideditora"));
				editora.setNome(result.getString("nome"));
				lista.add(editora);					
			}		
		}		
		catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return lista;
	}

	
	@Override
	public void inserir(Editora editora) {
		
		try {
			
			PreparedStatement stmt = criaConexao().prepareStatement(
						"INSERT INTO editoras (nome) VALUES (?)",
						Statement.RETURN_GENERATED_KEYS);
			
			stmt.setString(1, editora.getNome());								
				
			stmt.executeUpdate();    
				
		    ResultSet rs = stmt.getGeneratedKeys();
		        
		    while (rs.next()) 
		    	editora.setId(rs.getInt(1));  
		             
		    stmt.close();
		}
		catch (SQLException e) {			
			e.printStackTrace();
		}	
	}

	
	@Override
	public boolean deletar(Editora editora) {
		
		try {
			
			if(editoraTemLivros(editora.getId()))
				return false;
			
			PreparedStatement stmt = criaConexao().prepareStatement(
						"delete from editoras WHERE ideditora=?");
			
			stmt.setInt(1, editora.getId());	
			
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
	public boolean atualizar(Editora editora) {
		
		try {
			
			PreparedStatement stmt = criaConexao().prepareStatement(
						"UPDATE editoras SET nome=? WHERE ideditora=?");
			
			stmt.setString(1, editora.getNome());								
			stmt.setInt(2, editora.getId());	
			
			stmt.executeUpdate();    

		    stmt.close();
		}
		catch (SQLException e) {			
			
			e.printStackTrace();
			return false;
		}
		
		return true;
	}

	
	
	public Editora listarPorId(int id) {
		
		Editora editora = null;
		
		try {
			
			Statement estamento = criaConexao().createStatement();	
			ResultSet result = estamento.executeQuery("SELECT * FROM editoras where ideditora=?");
					
			if(result.next()) {
				
				editora = new Editora();
				
				editora.setId(result.getInt("ideditora"));
				editora.setNome(result.getString("nome"));				
			}		
		}		
		catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return editora;	
	}



	public boolean editoraTemLivros(int id) {
		
		try {
			
			Statement estamento = criaConexao().createStatement();	
			ResultSet result = estamento.executeQuery("SELECT count(id_livro) as quant FROM livros where fk_editora="+id);
				
			if(result.next()) {
				
				System.out.print("quant livros editora: "+result.getInt("quant"));
				
				if(result.getInt("quant")>0)
					return true;			
			}		
		}		
		catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return false;	
	}


	

	public List<Editora> filtarPesquisa(String s) {
		
		List<Editora> lista = new ArrayList<Editora>();
		
		try {
			
			PreparedStatement stmt = criaConexao().
					prepareStatement("SELECT * FROM editoras WHERE nome LIKE '%"+s+"%'");
			
			ResultSet result = stmt.executeQuery();
			
			while (result.next()) {
				
				Editora aux = new Editora();
				aux.setId(result.getInt("ideditora"));
				aux.setNome(result.getString("nome"));
				
				lista.add(aux);
			}

		    stmt.close();	
		}		
		catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return lista;	
	}

	
	
	public List<Editora> listaPaginada (String valor, int a, int b) {
		
		List<Editora> lista = new ArrayList<Editora>();
		
		try {			
			
			PreparedStatement stmt = criaConexao().
					prepareStatement(
							"SELECT * FROM editoras "+
									(Util.vazioOuNull(valor)?"":" where nome LIKE '%"+valor+"%'")+" order by ideditora ASC  LIMIT ?,?");
			
			stmt.setInt(1, a);
			stmt.setInt(2, b);
			
			ResultSet result = stmt.executeQuery();		
		
			while(result.next()) {
				
				Editora editora = new Editora();
				
				editora.setId(result.getInt("ideditora"));
				editora.setNome(result.getString("nome"));								
				lista.add(editora);
			}		
		}		
		catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return lista;
	}
	
	
	
	public int contEditoras(String valor) {
		
		int retorno = 0;
		
		try {			
			
			Statement estamento = criaConexao().createStatement();	
			ResultSet result = estamento.executeQuery("SELECT count(*) as quant FROM editoras "+
					(Util.vazioOuNull(valor)?"":" where nome LIKE '%"+valor+"%'")+"");
					
		
			if(result.next()) 
				retorno = result.getInt("quant");		
		}		
		catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return retorno;
	}
	
	
	
	public List<Editora> listaOrdemAlfabetica(){
		
		List<Editora> lista = new ArrayList < Editora >();	
		
		try {
			
			Statement estamento = criaConexao().createStatement();	
			ResultSet result = estamento.executeQuery("SELECT * FROM editoras order by nome ASC");
					
		
			while(result.next()) {
				
				Editora editora = new Editora();
				
				editora.setId(result.getInt("ideditora"));
				editora.setNome(result.getString("nome"));
				lista.add(editora);					
			}		
		}		
		catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return lista;
	}
		
}
