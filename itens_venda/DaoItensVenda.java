package capitulo_25_bd.projeto_I.itens_venda;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import capitulo_25_bd.projeto_I.Dao;


public class DaoItensVenda extends Dao<ItensVenda>{
	
	@Override
	public List<ItensVenda> listar() {
		
		List<ItensVenda> lista = new ArrayList<ItensVenda>();
		
		try {
			
			Statement estamento = criaConexao().createStatement();	
			ResultSet result = estamento.executeQuery("SELECT * FROM itens_venda");
					
		
			while(result.next()) {
				
				ItensVenda itensvenda = new ItensVenda();
				
				itensvenda.setId(result.getInt("id_itens_venda"));
				itensvenda.setChav_estr_livro(result.getInt("fk_do_livro_2"));
				itensvenda.setChav_estr_venda(result.getInt("fk_venda"));
				itensvenda.setQuantidade(result.getInt("quantidade"));
				itensvenda.setValor(result.getString("valor_unitario"));
				
				lista.add(itensvenda);					
			}		
		}		
		catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return lista;
	}

	
	@Override
	public void inserir(ItensVenda iv) {
		
		try {
			
			PreparedStatement stmt = criaConexao().prepareStatement(
				"INSERT INTO itens_venda (fk_do_livro_2, fk_venda, quantidade, valor_unitario) VALUES (?,?,?,?)",
				Statement.RETURN_GENERATED_KEYS);
							
				stmt.setInt(1, iv.getChav_estr_livro());
				stmt.setInt(2, iv.getChav_estr_venda());
				stmt.setInt(3, iv.getQuantidade());
				stmt.setString(4, iv.getValor());
				
				
				stmt.executeUpdate();    
				
			    ResultSet rs = stmt.getGeneratedKeys();
			        
			    while (rs.next()) 
			    	iv.setId(rs.getInt(1)); 			             
			    stmt.close();
			
		}
		catch (SQLException e) {			
			e.printStackTrace();
		}	
		
	}

	
	@Override
	public boolean deletar(ItensVenda b) {
		// TODO Auto-generated method stub
		return false;
	}

	
	@Override
	public boolean atualizar(ItensVenda c) {
		// TODO Auto-generated method stub
		return false;
	}
	
	
	
	public List<ItensVenda> listaEspecial() {
		
		List<ItensVenda> lista = new ArrayList<ItensVenda>();
				
		try {
			
			Statement estamento = criaConexao().createStatement();	
			ResultSet result = estamento.executeQuery(
					"SELECT itens_venda.id_itens_venda, titulo, itens_venda.fk_venda, itens_venda.quantidade, itens_venda.valor_unitario FROM `itens_venda` JOIN livros ON livros.id_livro = itens_venda.fk_do_livro_2 ORDER BY itens_venda.id_itens_venda ASC");
					
		
			while(result.next()) {
				
				ItensVenda itensvenda = new ItensVenda();
				
				itensvenda.setId(result.getInt("id_itens_venda"));
				itensvenda.setTitulo(result.getString("titulo"));
				itensvenda.setChav_estr_venda(result.getInt("fk_venda"));
				itensvenda.setQuantidade(result.getInt("quantidade"));
				itensvenda.setValor(result.getString("valor_unitario"));
				
				lista.add(itensvenda);					
			}		
		}		
		catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return lista;
	}

	
	
	public List<ItensVenda> listaParticular(int v){
		
		List<ItensVenda> lista = new ArrayList<ItensVenda>();
		
		try {
			
			Statement estamento = criaConexao().createStatement();	
			ResultSet result = estamento.executeQuery(
					"SELECT itens_venda.id_itens_venda, titulo, itens_venda.fk_venda, itens_venda.quantidade, itens_venda.valor_unitario FROM `itens_venda` JOIN livros ON livros.id_livro = itens_venda.fk_do_livro_2 WHERE fk_venda="+v);
					
		
			while(result.next()) {
				
				ItensVenda itensvenda = new ItensVenda();
				
				itensvenda.setId(result.getInt("id_itens_venda"));
				itensvenda.setTitulo(result.getString("titulo"));
				itensvenda.setChav_estr_venda(result.getInt("fk_venda"));
				itensvenda.setQuantidade(result.getInt("quantidade"));
				itensvenda.setValor(result.getString("valor_unitario"));
				
				lista.add(itensvenda);					
			}		
		}		
		catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return lista;
		
	}
}
