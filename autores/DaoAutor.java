package capitulo_25_bd.projeto_I.autores;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import capitulo_25_bd.projeto_I.Dao;
import capitulo_25_bd.projeto_I.Util;


public class DaoAutor extends Dao<Autor> {

	@Override
	public List <Autor> listar() {
		
		List <Autor> lista = new ArrayList <Autor>();	
		
		try {
			
			
			Statement estamento = criaConexao().createStatement();	
			ResultSet result = estamento.executeQuery("SELECT * FROM autores order by idautor ASC");
			
			
			while(result.next()) {
				
				Autor autor = new Autor();
				
				autor.setIdautor(result.getInt("idautor"));
				autor.setNome(result.getString("nome"));
				autor.setSobrenome(result.getString("sobrenome"));
				lista.add(autor);
			}		
		}		
		catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return lista;
	}

	@Override
	public void inserir(Autor autor) {
				
		try {
						
			PreparedStatement stmt = criaConexao().prepareStatement(
				"INSERT INTO autores (nome, sobrenome) values (?,?)",
				Statement.RETURN_GENERATED_KEYS);
			
				stmt.setString(1, autor.getNome());
				stmt.setString(2, autor.getSobrenome());					
				stmt.executeUpdate();    
				
			    ResultSet rs = stmt.getGeneratedKeys();
			        
			    while (rs.next()) 
			    	autor.setIdautor(rs.getInt(1)); 			             
			    stmt.close();									
		}
		catch (SQLException e) {			
			e.printStackTrace();
		}	
	}

	@Override
	public boolean deletar(Autor autor) {
		
		try {
			
			if(autorTemLivros(autor.getIdautor()))
				return false;
			
			PreparedStatement stmt = criaConexao().prepareStatement(
						"delete from autores WHERE idautor=?");
			
			stmt.setInt(1, autor.getIdautor());	
			
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
	public boolean atualizar(Autor autor) {
		
		try {
			
			PreparedStatement stmt = criaConexao().prepareStatement(
						"UPDATE autores SET nome=?, sobrenome=? WHERE idautor=?");
			
			stmt.setString(1, autor.getNome());	
			stmt.setString(2, autor.getSobrenome());
			stmt.setInt(3, autor.getIdautor());	
			
			stmt.executeUpdate();    

		    stmt.close();
		}
		catch (SQLException e) {			
			
			e.printStackTrace();
			return false;
		}
		
		return true;		
	}
	
	
	
	public Autor listagemPorId(int id) {
		
		Autor autor = null;
		
		try {
			
			Statement estamento = criaConexao().createStatement();	
			ResultSet result = estamento.executeQuery("SELECT * FROM autores where idautor=?");
					
			if(result.next()) {
				
				autor = new Autor();
				
				autor.setIdautor(result.getInt("idautor"));
				autor.setNome(result.getString("nome"));	
				autor.setSobrenome(result.getString("sobrenome"));
			}		
		}		
		catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return autor;	
	}



	private boolean autorTemLivros(int idautor) {
		
		try {
			
			Statement estamento = criaConexao().createStatement();	
			ResultSet result = estamento.executeQuery("SELECT count(id_autores_livros) as quantidade FROM autores_livros WHERE fk_autor="+idautor);
				
			if(result.next()) {
								
				if(result.getInt("quantidade")>0)
					return true;			
			}		
		}		
		catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return false;
	}

	
	public List<Autor> filtarPesquisa(String s) {
		
		List<Autor> lista = new ArrayList<Autor>();
		
		try {
			
			
			PreparedStatement stmt = criaConexao().
					prepareStatement("SELECT * FROM autores WHERE nome LIKE '%"+s+"%' OR sobrenome LIKE '%"+s+"%'");
			
			ResultSet result = stmt.executeQuery();
			
			while (result.next()) {
				
				Autor aux = new Autor();
				aux.setIdautor(result.getInt("idautor"));
				aux.setNome(result.getString("nome"));
				aux.setSobrenome(result.getString("sobrenome"));
				
				lista.add(aux);
			}

		    stmt.close();	
		}		
		catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return lista;	
	}
	
	
	
	public List<Autor> listaPaginada(String valor, int a, int b){
		
		List<Autor> lista = new ArrayList<Autor>();
		
		try {			
			
			PreparedStatement stmt = criaConexao().
					prepareStatement(
							"SELECT * FROM autores "+
									(Util.vazioOuNull(valor)?"":" where nome LIKE '%"+valor+"%' OR sobrenome LIKE '%"+valor+"%'")+" order by idautor ASC  LIMIT ?,?");
			
			stmt.setInt(1, a);
			stmt.setInt(2, b);
			
			ResultSet result = stmt.executeQuery();		
		
			while(result.next()) {
				
				Autor autor = new Autor();
				
				autor.setIdautor(result.getInt("idautor"));
				autor.setNome(result.getString("nome"));
				autor.setSobrenome(result.getString("sobrenome"));				
				lista.add(autor);
			}		
		}		
		catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return lista;
	}
	
	
	
	public int contAutores(String valor) {
		
		int retorno = 0;
		
		try {			
			
			Statement estamento = criaConexao().createStatement();	
			ResultSet result = estamento.executeQuery("SELECT count(*) as quant FROM autores "+
					(Util.vazioOuNull(valor)?"":" where nome LIKE '%"+valor+"%' OR sobrenome LIKE '%"+valor+"%'")+"");
					
		
			if(result.next()) 
				retorno = result.getInt("quant");		
		}		
		catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return retorno;
	}
	
	
	
	public List <Autor> listaOrdemAlfabetica(){
		
		List <Autor> lista = new ArrayList <Autor>();	
		
		try {
			
			
			Statement estamento = criaConexao().createStatement();	
			ResultSet result = estamento.executeQuery("SELECT * FROM autores order by nome ASC");
			
			
			while(result.next()) {
				
				Autor autor = new Autor();
				
				autor.setIdautor(result.getInt("idautor"));
				autor.setNome(result.getString("nome"));
				autor.setSobrenome(result.getString("sobrenome"));
				lista.add(autor);
			}		
		}		
		catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return lista;
	}

}
