package capitulo_25_bd.projeto_I;

import java.math.BigDecimal;

import javax.swing.JOptionPane;

public class Util {
	
	
	public static void mostrarMsgErro(String mensagem) {
		
		JOptionPane.showMessageDialog(null, mensagem, "Um erro Ocorreu", JOptionPane.ERROR_MESSAGE);
	}
	
	
	
	public static void mostrarMsgSucesso() {
		
		JOptionPane.showMessageDialog(null, "Operação Realizada Com Sucesso.", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
	}

	
	
	public static boolean vazioOuNull(String valor) {
		
		return valor==null || valor.length()==0;
	}

	
	
	public static BigDecimal converteParaBigDecimal(String s) {
		
		return new BigDecimal(s);		
	}
	
	
	public static int mostrarMsgSimOuNao(String mensagem) {
		
		int i = JOptionPane.showConfirmDialog(null, mensagem, "Um erro Ocorreu", JOptionPane.YES_NO_OPTION);
		//Fechar i = -1; Sim = 0; Não = 1;
		return i;
	}
	
}
