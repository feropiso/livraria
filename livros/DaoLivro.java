package capitulo_25_bd.projeto_I.livros;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import capitulo_25_bd.projeto_I.Dao;
import capitulo_25_bd.projeto_I.Util;


public class DaoLivro extends Dao <Livro> {
	
	@Override
	public List<Livro> listar() {
		
		List <Livro> lista = new ArrayList <Livro>();	
		
		try {			
			
			Statement estamento = criaConexao().createStatement();	
			ResultSet result = estamento.executeQuery("SELECT * FROM livros order by id_livro ASC");
					
		
			while(result.next()) {
				
				Livro livro = new Livro();
				
				livro.setIdLivro(result.getInt("id_livro"));
				livro.setTitulo(result.getString("titulo"));
				livro.setEdicao(result.getInt("edicao"));
				livro.setAno(result.getString("copyright"));
				livro.setPrice(result.getString("preco"));
				livro.setChav_estran_editora(result.getInt("fk_editora"));
				lista.add(livro);
			}		
		}		
		catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return lista;
	}

	
	@Override
	public void inserir(Livro livro) {
		
		try {
						
			PreparedStatement stmt = criaConexao().prepareStatement(
				"INSERT INTO livros (titulo, edicao, copyright, preco, fk_editora) VALUES (?,?,?,?,?)",
				Statement.RETURN_GENERATED_KEYS);
							
				stmt.setString(1, livro.getTitulo());
				stmt.setInt(2, livro.getEdicao());
				stmt.setString(3, livro.getAno());
				stmt.setString(4, livro.getPrice());
				stmt.setInt(5, livro.getChav_estran_editora()); 
				
				stmt.executeUpdate();    
				
			    ResultSet rs = stmt.getGeneratedKeys();
			        
			    while (rs.next()) 
			    	livro.setIdLivro(rs.getInt(1)); 			             
			    stmt.close();
			
		}
		catch (SQLException e) {			
			e.printStackTrace();
		}	
		
	}

	
	@Override
	public boolean deletar(Livro livro) {
		
		try {
			
			PreparedStatement stmt = criaConexao().prepareStatement(
						"DELETE FROM livros WHERE id_livro=?");
			
			stmt.setInt(1, livro.getIdLivro());	
			
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
	public boolean atualizar(Livro livro) {

		try {
			
			PreparedStatement stmt = criaConexao().prepareStatement(
						"UPDATE livros SET titulo=?, edicao=?, copyright=?, preco=?, fk_editora=? WHERE id_livro=?");
						
			stmt.setString(1, livro.getTitulo());
			stmt.setInt(2, livro.getEdicao());
			stmt.setString(3, livro.getAno());
			stmt.setString(4, livro.getPrice());
			stmt.setInt(5, livro.getChav_estran_editora());
			stmt.setInt(6, livro.getIdLivro());
			stmt.executeUpdate();    

		    stmt.close();
		}
		catch (SQLException e) {			
			
			e.printStackTrace();
			return false;
		}
		
		return true;	
	}
	
	
	
	public int editoraSelecionada(int id_livro) {
		
		
		int aux = 0;
		try {
			
			PreparedStatement stmt = criaConexao().prepareStatement("SELECT fk_editora FROM livros WHERE id_livro=?");
			
			stmt.setInt(1, id_livro);	
			
			ResultSet result = stmt.executeQuery();
			
			if (result.next()) 
				aux = result.getInt("fk_editora");

		    stmt.close();				
		}		
		catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return aux;
	}

	
	
	public List<Livro> listaPaginada(String valor, int a, int b){
		
		List <Livro> lista = new ArrayList <Livro>();	
		
		try {			
			
			PreparedStatement stmt = criaConexao().
					prepareStatement(
							"SELECT * FROM livros "+
									(Util.vazioOuNull(valor)?"":" where titulo LIKE '%"+valor+"%'")+" order by id_livro ASC  LIMIT ?,?");
			
			stmt.setInt(1, a);
			stmt.setInt(2, b);
			
			ResultSet result = stmt.executeQuery();		
		
			while(result.next()) {
				
				Livro livro = new Livro();
				
				livro.setIdLivro(result.getInt("id_livro"));
				livro.setTitulo(result.getString("titulo"));
				livro.setEdicao(result.getInt("edicao"));
				livro.setAno(result.getString("copyright"));
				livro.setPrice(result.getString("preco"));
				livro.setChav_estran_editora(result.getInt("fk_editora"));
				lista.add(livro);
			}		
		}		
		catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return lista;
	}
	
	

	public int contLivros(String valor) {
		
		int retorno = 0;
		
		try {			
			
			Statement estamento = criaConexao().createStatement();	
			ResultSet result = estamento.executeQuery("SELECT count(*) as quant FROM livros "+
					(Util.vazioOuNull(valor)?"":" where titulo LIKE '%"+valor+"%'")+"");
					
		
			if(result.next()) 
				retorno = result.getInt("quant");		
		}		
		catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return retorno;
	}
	
	
	
	public List<Livro> livrosAutor(int chave_autor){
		
		List <Livro> lista = new ArrayList <Livro>();
		
		try {
			
			PreparedStatement stmt = criaConexao().
					prepareStatement("Select * from livros inner join autores_livros on id_livro = fk_livro where fk_autor = ?");
			
			stmt.setInt(1, chave_autor);			

			ResultSet result = stmt.executeQuery();
			
			while(result.next()) {
				
				Livro livro = new Livro();
				
				livro.setIdLivro(result.getInt("id_livro"));
				livro.setTitulo(result.getString("titulo"));
				livro.setEdicao(result.getInt("edicao"));
				livro.setAno(result.getString("copyright"));
				livro.setPrice(result.getString("preco"));
				//livro.setChav_estran_editora(result.getInt("fk_editora"));
				lista.add(livro);
			}		
		}		
		catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return lista;
	}

	
	
	public List<Livro> livrosEditora(int chave_editora){
		
		List <Livro> lista = new ArrayList <Livro>();
		
		try {
			
			PreparedStatement stmt = criaConexao().
					prepareStatement("Select * from livros WHERE fk_editora = ?");
			
			stmt.setInt(1, chave_editora);			

			ResultSet result = stmt.executeQuery();
			
			while(result.next()) {
				
				Livro livro = new Livro();
				
				livro.setIdLivro(result.getInt("id_livro"));
				livro.setTitulo(result.getString("titulo"));
				livro.setEdicao(result.getInt("edicao"));
				livro.setAno(result.getString("copyright"));
				livro.setPrice(result.getString("preco"));
				//livro.setChav_estran_editora(result.getInt("fk_editora"));
				lista.add(livro);
			}		
		}		
		catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return lista;
	}
	
	
	public String tituloSelecionada(int id_livro) {
				
		String aux = "";
		
		try {
			
			PreparedStatement stmt = criaConexao().prepareStatement("SELECT titulo FROM livros WHERE id_livro=?");
			
			stmt.setInt(1, id_livro);	
			
			ResultSet result = stmt.executeQuery();
			
			if (result.next()) 
				aux = result.getString("titulo");

		    stmt.close();				
		}		
		catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return aux;
	}
}
