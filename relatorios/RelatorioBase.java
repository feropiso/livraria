package capitulo_25_bd.projeto_I.relatorios;

import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import javax.swing.JFrame;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

public abstract class RelatorioBase {

	
	public final boolean criaRelatorios(String titulo, JasperReport jr, Map<String, Object> parametros, JRDataSource ds ){
		
			try{
	
			JasperPrint jp = JasperFillManager.fillReport(jr, parametros, ds);
			
			this.viewReportFrame( titulo, jp);
			}
			catch (Exception e) { e.printStackTrace();return false;}
			
		return true;
	}
	
	
		
	private final void viewReportFrame( String titulo, JasperPrint print ) {
		
		JasperViewer viewer = new JasperViewer( print, false );
		
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();  
		Rectangle screenRect = ge.getMaximumWindowBounds();  

		viewer.setSize(screenRect.width, screenRect.height);   	
		viewer.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		viewer.setLocationRelativeTo(null);
		viewer.setTitle("Relatório");
		
		viewer.setVisible(true);
	}

	
	
	public String dataAtual() {
		
		SimpleDateFormat formatador = new SimpleDateFormat("dd /MM/ yyyy");  
		 
		Date dataDoSistema = new Date();
		
		String dataEmTexto = formatador.format(dataDoSistema);  
		 
		return dataEmTexto;
	}	
		
	
	
}
