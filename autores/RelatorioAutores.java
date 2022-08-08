package capitulo_25_bd.projeto_I.autores;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import capitulo_25_bd.projeto_I.Util;
import capitulo_25_bd.projeto_I.relatorios.RelatorioBase;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

public class RelatorioAutores extends RelatorioBase {
	
	
	public boolean gerarRelatorio() {

		try {
			JasperReport jr	= null;
			Map<String, Object> parametros = null;
																
			InputStream inputStream = getClass().getResourceAsStream("/capitulo_25_bd/projeto_I/relatorios/rel_autores.jrxml");     
														
			JasperDesign jd = JRXmlLoader.load(inputStream);
			
			jr = JasperCompileManager.compileReport(jd);
													    
			parametros = new HashMap<String, Object>();
					
			DaoAutor dauautor = new DaoAutor();
			
			List<Autor> lista = dauautor.listaOrdemAlfabetica();
			
			int total = lista.size();
			
			String dataAtual = dataAtual();			
			
			parametros.put("data", dataAtual);
			parametros.put("total", ""+total);
						
			JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource(lista);
				
			boolean retorno =  this.criaRelatorios("RELATÓRIO GERAL DE AUTORES", jr, parametros, ds);
			
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
	

}
