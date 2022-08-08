package capitulo_25_bd.projeto_I.vendas;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import capitulo_25_bd.projeto_I.Dao;


public class DaoVenda extends Dao<Venda>  {

	@Override
	public List<Venda> listar() {
		
		List<Venda> lista = new ArrayList<Venda>();
		
		try {			
			
			Statement estamento = criaConexao().createStatement();	
			ResultSet result = estamento.executeQuery("SELECT * FROM venda");
					
		
			while(result.next()) {
				
				Venda venda = new Venda();
				
				venda.setId(result.getInt("id_venda"));
				venda.setData(result.getDate("data"));
				venda.setQuantidadeTotal(result.getInt("quantidade_total"));
				venda.setValorBruto(result.getString("valor_bruto"));
				venda.setDesconto(result.getString("valor_desconto"));
				venda.setValorTotal(result.getString("valor_total"));
				
				lista.add(venda);
			}		
		}		
		catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return lista;
	}

	
	@Override
	public void inserir(Venda venda) {
		
		try {
			
			PreparedStatement stmt = criaConexao().prepareStatement(
				"INSERT INTO venda (data, quantidade_total, valor_bruto, valor_desconto, valor_total) VALUES (?,?,?,?,?)",
				Statement.RETURN_GENERATED_KEYS);
							
				stmt.setDate(1, venda.getData());
				stmt.setInt(2, venda.getQuantidadeTotal());
				stmt.setString(3, venda.getValorBruto());
				stmt.setString(4, venda.getDesconto());
				stmt.setString(5, venda.getValorTotal()); 
				
				stmt.executeUpdate();    
				
			    ResultSet rs = stmt.getGeneratedKeys();
			        
			    while (rs.next()) 
			    	venda.setId(rs.getInt(1)); 			             
			    stmt.close();
			
		}
		catch (SQLException e) {			
			e.printStackTrace();
		}	
		
	}

	
	@Override
	public boolean deletar(Venda b) {
		// TODO Auto-generated method stub
		return false;
	}

	
	@Override
	public boolean atualizar(Venda c) {
		// TODO Auto-generated method stub
		return false;
	}
	
	
	
	public boolean inserirLista(List<Venda> venda) {
		
		try {
			
			PreparedStatement stmt = criaConexao().prepareStatement(
				"INSERT INTO venda (data, quantidade_total, valor_bruto, valor_desconto, valor_total) VALUES (?,?,?,?,?)",
				Statement.RETURN_GENERATED_KEYS);
				
			for(Venda v: venda) {
				stmt.setDate(1, v.getData());
				stmt.setInt(2, v.getQuantidadeTotal());
				stmt.setString(3, v.getValorBruto());
				stmt.setString(4, v.getDesconto());
				stmt.setString(5, v.getValorTotal());
				
				ResultSet rs = stmt.getGeneratedKeys();
		        
			    while (rs.next()) 
			    	v.setId(rs.getInt(1));
			}							
				stmt.executeUpdate();					             
			    stmt.close();			
		}
		catch (SQLException e) {			
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	
	
	public List<Venda> listaEspecial() {
		
		List<Venda> lista = new ArrayList<Venda>();
		
		try {			
			
			Statement estamento = criaConexao().createStatement();	
			ResultSet result = estamento.executeQuery("SELECT * FROM `venda` ORDER BY data ASC ");
					
		
			while(result.next()) {
				
				Venda venda = new Venda();
				
				venda.setId(result.getInt("id_venda"));
				venda.setData_aux(converteData(result.getDate("data")));
				venda.setQuantidadeTotal(result.getInt("quantidade_total"));
				venda.setValorBruto(result.getString("valor_bruto"));
				venda.setDesconto(result.getString("valor_desconto"));
				venda.setValorTotal(result.getString("valor_total"));
				
				lista.add(venda);
			}		
		}		
		catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return lista;
	}


	private String converteData(Date date) {
		
		SimpleDateFormat formatador = new SimpleDateFormat("dd /MM/ yyyy");  
		 
		Date dataDoSistema = new Date(date.getTime());
		
		String dataEmTexto = formatador.format(dataDoSistema);  
		 
		return dataEmTexto;
	}
		
}
