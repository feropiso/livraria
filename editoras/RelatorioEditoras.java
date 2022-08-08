package capitulo_25_bd.projeto_I.editoras;

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

public class RelatorioEditoras extends RelatorioBase {
	
	public boolean gerarRelatorio() {

		try {
			
			JasperReport jr	= null;
			
			Map<String, Object> parametros = null;
																
			InputStream inputStream = getClass().getResourceAsStream("/capitulo_25_bd/projeto_I/relatorios/rel_editoras.jrxml");     
														
			JasperDesign jd = JRXmlLoader.load(inputStream);
			
			jr = JasperCompileManager.compileReport(jd);
													    
			parametros = new HashMap<String, Object>();
			
			DaoEditora daoeditora = new DaoEditora();
			
			List<Editora> lista = daoeditora.listaOrdemAlfabetica();
			
			int total = lista.size();
			
			String dataAtual = dataAtual();			
			
			parametros.put("data", dataAtual);
			parametros.put("total", ""+total);
						
			JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource(lista);
				
			boolean retorno =  this.criaRelatorios("RELAT�RIO GERAL DE EDITORAS", jr, parametros, ds);
			
			if(!retorno) {
				
				Util.mostrarMsgErro("N�o foi possivel gerar relatorio.");
				return false;
			}
			
		}		
		catch (JRException e1) {
			e1.printStackTrace();
			Util.mostrarMsgErro("N�o foi possivel gerar relatorio.");
			return false;
		}
		
		return true;
	}

}
