package capitulo_25_bd.projeto_I.vendas;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import capitulo_25_bd.projeto_I.Util;
import capitulo_25_bd.projeto_I.itens_venda.DaoItensVenda;
import capitulo_25_bd.projeto_I.relatorios.RelatorioBase;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

public class RelatorioVendaDetalhada extends RelatorioBase {
	
	public boolean gerarRelatorio() {

		try {
			
			JasperReport jr	= null;
			
			Map<String, Object> parametros = null;
																
			InputStream inputStream = getClass().getResourceAsStream("/capitulo_25_bd/projeto_I/relatorios/rel_venda_analitica.jrxml");     
														
			JasperDesign jd = JRXmlLoader.load(inputStream);
			
			jr = JasperCompileManager.compileReport(jd);
													    
			parametros = new HashMap<String, Object>();
						
			List<VendaRel> lista = criaLista();
			
			if(lista == null)
				return false;
			
			int total = lista.size();
			
			String dataAtual = dataAtual();		
			
			parametros.put("data", dataAtual);
			parametros.put("total", ""+total);
			
			
			JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource(lista);
				
			boolean retorno =  this.criaRelatorios("RELATÓRIO GERAL DE VENDAS", jr, parametros, ds);
			
			if(!retorno) {
				
				Util.mostrarMsgErro("Não foi possivel gerar relatorio.");
				return false;
			}
			
		}		
		catch (JRException e1) {
			e1.printStackTrace();
			Util.mostrarMsgErro("Não foi possivel gerar relatorio.");
			return false;
		}
		
		return true;
	}
	
	
	
	private List<VendaRel> criaLista(){
		
		List<VendaRel> lista = new ArrayList<VendaRel>();
				
		DaoVenda daovenda = new DaoVenda();			
		List<Venda> l1 = daovenda.listaEspecial();
		
		DaoItensVenda daoitensvenda = new DaoItensVenda();
						
		for(int i = 0; i < l1.size(); i++ ) {
			
			VendaRel v = new VendaRel();
			
			v.setItens(daoitensvenda.listaParticular(l1.get(i).getId()));			
			v.setVenda(l1.get(i));	
			lista.add(v);
			
		}
		
		
		return lista;		
	}

}
