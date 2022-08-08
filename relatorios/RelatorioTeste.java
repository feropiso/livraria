package capitulo_25_bd.projeto_I.relatorios;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import capitulo_25_bd.projeto_I.Util;
import capitulo_25_bd.projeto_I.livros.Livro;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

public class RelatorioTeste extends RelatorioBase{
	
	
	
	public void gerar() {

		try {
			JasperReport jr	= null;
			Map<String, Object> parametros = null;
																
			InputStream inputStream = getClass().getResourceAsStream("/capitulo_25_bd/projeto_I/relatorios/rel_teste.jrxml");     
														
			JasperDesign jd = JRXmlLoader.load(inputStream);
			
			jr = JasperCompileManager.compileReport(jd);
			
										    
			parametros = new HashMap<String, Object>();
			
			parametros.put("nome", "Flavio Sousa");
			parametros.put("nascimento", "29/03/1989");
			
			List<Livro> lista = new ArrayList<Livro>();
			
			Livro livro = new Livro();
			
			livro.setTitulo("teste 1");
			livro.setAno("2020");
			livro.setPrice("20,00");
			lista.add(livro);
			
			livro = new Livro();
			
			livro.setTitulo("teste 2");
			livro.setAno("2012");
			livro.setPrice("49,99");
			lista.add(livro);
			
			JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource(lista);
				
			boolean retorno =  this.criaRelatorios("Relatório de Vendas", jr, parametros, ds);
			
			if(!retorno)
				Util.mostrarMsgErro("Não foi possivel gerar relatorio.");
		}
		catch (JRException e1) {
			e1.printStackTrace();
			Util.mostrarMsgErro("Não foi possivel gerar relatorio.");
		}
	}
	
}
