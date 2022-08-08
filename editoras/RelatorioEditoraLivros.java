package capitulo_25_bd.projeto_I.editoras;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import capitulo_25_bd.projeto_I.Util;
import capitulo_25_bd.projeto_I.livros.Livro;
import capitulo_25_bd.projeto_I.relatorios.RelatorioBase;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

public class RelatorioEditoraLivros extends RelatorioBase{
	
	public boolean gerarRelatorio(List<Livro> lista2, String editora) {

		try {
			JasperReport jr	= null;
			Map<String, Object> parametros = null;
																
			InputStream inputStream = getClass().getResourceAsStream("/capitulo_25_bd/projeto_I/relatorios/rel_livros_editora.jrxml");     
														
			JasperDesign jd = JRXmlLoader.load(inputStream);
			
			jr = JasperCompileManager.compileReport(jd);
													    
			parametros = new HashMap<String, Object>();
						
			int total = lista2.size();
			
			String dataAtual = dataAtual();			
			
			parametros.put("data", dataAtual);
			parametros.put("editora", editora);
			parametros.put("total", ""+total);
						
			JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource(lista2);
				
			boolean retorno =  this.criaRelatorios("RELATÓRIO LIVROS POR EDITORA", jr, parametros, ds);
			
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
