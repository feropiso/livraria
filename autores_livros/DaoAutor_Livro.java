package capitulo_25_bd.projeto_I.autores_livros;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import capitulo_25_bd.projeto_I.Dao;


public class DaoAutor_Livro extends Dao <Autor_Livro> {

	@Override
	public List<Autor_Livro> listar() {
		// TODO Auto-generated method stub
		return null;
	}

	
	@Override
	public void inserir(Autor_Livro auto_livro) {
		
		try {
			
			PreparedStatement stmt = criaConexao().prepareStatement(
					"INSERT INTO autores_livros (fk_autor, fk_livro) VALUES (?,?)",
				Statement.RETURN_GENERATED_KEYS);
							
				stmt.setInt(1, auto_livro.getChav_estr_autor());
				stmt.setInt(2, auto_livro.getChav_estr_livro());
				
				stmt.executeUpdate();    
				
			    ResultSet rs = stmt.getGeneratedKeys();
			        
			    while (rs.next()) 
			    	auto_livro.setAutores_livros_id(rs.getInt(1)); 			             
			    stmt.close();			
			
		}
		catch (SQLException e) {			
			e.printStackTrace();
		}	
		
	}

	
	@Override
	public boolean deletar(Autor_Livro autor_livro) {
		
		try {
			
			PreparedStatement stmt = criaConexao().prepareStatement(
						"DELETE FROM autores_livros WHERE id_autores_livros=?");
			
			stmt.setInt(1, autor_livro.getAutores_livros_id());	
			
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
	public boolean atualizar(Autor_Livro c) {
		// TODO Auto-generated method stub
		return false;
	}
	
	
	
	public List<Autor_Livro> listaAutoresComLivros(int chave_livro) {
		
		List<Autor_Livro> lista = new ArrayList<Autor_Livro>();
		
		try {
			
			PreparedStatement stmt = criaConexao().
					prepareStatement("SELECT * FROM autores_livros WHERE fk_livro=?");
			
			stmt.setInt(1, chave_livro);			

			ResultSet result = stmt.executeQuery();
			
			while (result.next()) {
				
				Autor_Livro aux = new Autor_Livro();
				aux.setAutores_livros_id(result.getInt("id_autores_livros"));
				aux.setChav_estr_autor(result.getInt("fk_autor"));
				
				lista.add(aux);
			}

		    stmt.close();	
		}		
		catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return lista;
	}

	
	
	public int autorSelecionado(int id_livro) {
		
		
		int aux = 0;
		try {
			
			PreparedStatement stmt = criaConexao().prepareStatement("SELECT fk_autor FROM autores_livros WHERE fk_livro=?");
			
			stmt.setInt(1, id_livro);	
			
			ResultSet result = stmt.executeQuery();
			
			if (result.next()) 
				aux = result.getInt("fk_autor");

		    stmt.close();				
		}		
		catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return aux;
	}

}
