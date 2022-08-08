package capitulo_25_bd.projeto_I.vendas;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import capitulo_25_bd.projeto_I.Util;
import capitulo_25_bd.projeto_I.estoque.DaoEstoque;
import capitulo_25_bd.projeto_I.estoque.ItemEstoque;
import capitulo_25_bd.projeto_I.itens_venda.DaoItensVenda;
import capitulo_25_bd.projeto_I.itens_venda.ItensVenda;
import capitulo_25_bd.projeto_I.livros.DaoLivro;
import capitulo_25_bd.projeto_I.livros.Livro;

public class FormVenda extends JDialog {
	
	private JLabel valorFinal;
	private JLabel valorUnitario;
	private JLabel valor_do_Desconto;
	
	private JTable vendaJTable;
		
	private JTextField descontoJTF;
	private JTextField quantidadeJTF;
		
	private List<Livro> listaLivros;
	private List<ItensVenda> lista_de_itens;
	
	private JComboBox<String> comboLivros;
	
	private int quantidadeTotal;
	
	private String valor_Final;
	private String valorDesconto;
	private String precoBrutoTotal;	
	
	
	public FormVenda() {
		
		super ();			
		this.setTitle("Loja");
		
		setLayout(new GridBagLayout());	
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(550, 550);
		setLocationRelativeTo(null);
		this.setModal(true);
	
		inicializador();
	}
	
	

	private void inicializador() {
		
		valor_Final = "";		
		valorDesconto = "";
		precoBrutoTotal = "";
		quantidadeTotal = 0;
		
		this.listaLivros = new ArrayList<Livro>();
		this.lista_de_itens = new ArrayList<ItensVenda>();
				
		GridBagConstraints cons = new GridBagConstraints();
		
		
		JPanel panel = new JPanel(new GridBagLayout());
		panel.setBackground(Color.black);
		cons.fill = GridBagConstraints.BOTH;
		cons.gridwidth = GridBagConstraints.REMAINDER;
		cons.weightx = 1;
		cons.weighty = 1;
		cons.insets = new Insets(0,0,0,0);
		this.add(panel, cons);
		
		
		JLabel lb1 = new JLabel("Selecione o(s) Livro(s):");
		cons.fill = GridBagConstraints.HORIZONTAL;
		cons.gridwidth = GridBagConstraints.REMAINDER;
		cons.weightx = 1;
		cons.weighty = 0;
		cons.insets = new Insets(15,5,0,5);			
		lb1.setForeground(Color.white);
		panel.add(lb1, cons);
		
		
		comboLivros = new JComboBox<String>();		
		cons.fill = GridBagConstraints.HORIZONTAL;
		cons.gridwidth = GridBagConstraints.REMAINDER;
		cons.weightx = 1;
		cons.weighty = 0;
		cons.insets = new Insets(5,5,5,5);
		panel.add(comboLivros, cons);
		
		
		valorUnitario = new JLabel();
		cons.fill = GridBagConstraints.NONE;
		cons.gridwidth = GridBagConstraints.REMAINDER;
		cons.anchor = GridBagConstraints.NORTHEAST;
		cons.weightx = 1;
		cons.weighty = 0;
		cons.insets = new Insets(5,5,5,5);
		valorUnitario.setForeground(Color.white);
		panel.add(valorUnitario, cons);
		
		
		JLabel lb2 = new JLabel("Informe a Quantidade:");
		cons.fill = GridBagConstraints.HORIZONTAL;
		cons.gridwidth = GridBagConstraints.REMAINDER;
		cons.weightx = 1;
		cons.weighty = 0;
		cons.insets = new Insets(5,5,0,5);			
		lb2.setForeground(Color.white);
		panel.add(lb2, cons);
		
		
		quantidadeJTF = new JTextField();		
		cons.fill = GridBagConstraints.HORIZONTAL;
		cons.gridwidth = GridBagConstraints.REMAINDER;
		cons.weightx = 1;
		cons.weighty = 0;
		cons.insets = new Insets(5,5,5,5);		
		panel.add(quantidadeJTF, cons);
		
		
		JButton addJButton = new JButton("Adicionar");		
		cons.fill = GridBagConstraints.NONE;
		cons.gridwidth = GridBagConstraints.REMAINDER;
		cons.anchor = GridBagConstraints.CENTER;
		cons.weightx = 0;
		cons.weighty = 0;
		cons.insets = new Insets(5,5,5,5);		
		panel.add(addJButton, cons);
		
		
		vendaJTable = new JTable();		
		vendaJTable.setModel(new DefaultTableModel(
	            new Object [][] {},
	            new String [] {"Título", "Quantidade", "Preço R$"}) {
	           
			private static final long serialVersionUID = 1L;
				
	        public boolean isCellEditable(int rowIndex, int columnIndex) {
	        		        	
	               return false;
	        }
	    });
		
		
		vendaJTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
		vendaJTable.setSurrendersFocusOnKeystroke(true);
		vendaJTable.getColumnModel().getColumn(0).setPreferredWidth(340);
		vendaJTable.getColumnModel().getColumn(1).setPreferredWidth(75);		
		vendaJTable.getColumnModel().getColumn(2).setPreferredWidth(65);
		
		
		JScrollPane barraRolagem = new JScrollPane(vendaJTable);
		cons.fill = GridBagConstraints.BOTH;
		cons.gridwidth = GridBagConstraints.REMAINDER;
		cons.weightx = 1;
		cons.weighty = 1;
		cons.insets = new Insets(5,5,5,5);
		panel.add(barraRolagem, cons);
		
		
		JLabel lb3 = new JLabel("Informe o Desconto em %:");
		cons.fill = GridBagConstraints.HORIZONTAL;
		cons.gridwidth = GridBagConstraints.REMAINDER;
		cons.weightx = 1;
		cons.weighty = 0;
		cons.insets = new Insets(5,5,0,5);			
		lb3.setForeground(Color.white);
		panel.add(lb3, cons);
		
		
		descontoJTF = new JTextField();		
		cons.fill = GridBagConstraints.HORIZONTAL;
		cons.gridwidth = GridBagConstraints.RELATIVE;
		cons.weightx = 1;
		cons.weighty = 0;
		cons.insets = new Insets(5,5,5,5);		
		panel.add(descontoJTF, cons);
		
		
		JButton addDescontoJButton = new JButton("Adicionar Desconto");		
		cons.fill = GridBagConstraints.NONE;
		cons.gridwidth = GridBagConstraints.REMAINDER;
		cons.anchor = GridBagConstraints.EAST;
		cons.weightx = 0;
		cons.weighty = 0;
		cons.insets = new Insets(5,5,5,5);		
		panel.add(addDescontoJButton, cons);
		
		
		valor_do_Desconto = new JLabel();
		valor_do_Desconto.setVisible(false);
		cons.fill = GridBagConstraints.NONE;
		cons.gridwidth = GridBagConstraints.REMAINDER;
		cons.anchor = GridBagConstraints.NORTHEAST;
		cons.weightx = 1;
		cons.weighty = 0;
		cons.insets = new Insets(5,5,5,5);
		valor_do_Desconto.setForeground(Color.white);
		panel.add(valor_do_Desconto, cons);
		
		
		valorFinal = new JLabel("Total: R$ 0,00");
		cons.fill = GridBagConstraints.NONE;
		cons.gridwidth = GridBagConstraints.REMAINDER;
		cons.anchor = GridBagConstraints.NORTHEAST;
		cons.weightx = 1;
		cons.weighty = 0;
		cons.insets = new Insets(5,5,5,5);
		valorFinal.setForeground(Color.white);
		panel.add(valorFinal, cons);
		
		
		JButton bt_finalizar = new JButton("Finalizar Compra");		
		cons.fill = GridBagConstraints.NONE;
		cons.gridwidth = GridBagConstraints.REMAINDER;
		cons.anchor = GridBagConstraints.CENTER;
		cons.weightx = 0;
		cons.weighty = 0;
		cons.insets = new Insets(5,5,15,5);		
		panel.add(bt_finalizar, cons);
		
		
		comboLivros.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent ev) {
						
				carregaValor();
					
			}}				
		);
		
		
		addJButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent ev) {			
				
				addItem();				
			
			}}				
		);
		
		
		addDescontoJButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent ev) {			
				
				addDesconto();				
			
			}}				
		);
		
		
		bt_finalizar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent ev) {
						
				finalizaCompra();
					
			}}				
		);


		carregaLivros();
		setVisible(true);		
	} 
	


	private void finalizaCompra() {
		
		
		if (quantidadeTotal == 0) {
			Util.mostrarMsgErro("Adicione o livro.");
			return;
		}
		
		Venda venda = new Venda();
		
		venda.setData(dataAtual());
		venda.setQuantidadeTotal(quantidadeTotal);		
		venda.setValorBruto(precoBrutoTotal);
		venda.setDesconto(valorDesconto);
		venda.setValorTotal(valor_Final);
		
		DaoVenda daovenda = new DaoVenda();	
		daovenda.inserir(venda);
		
		if(venda.getId()<=0)
			Util.mostrarMsgErro("Não foi possível efetuar a compra.");
		
		else {
			
			DaoItensVenda daoitensvenda = new DaoItensVenda();	
						
				for(ItensVenda iv: this.lista_de_itens) {
					
					iv.setChav_estr_venda(venda.getId());					
					daoitensvenda.inserir(iv);				
				}			
						
				DaoEstoque daoestoque = new DaoEstoque();
				ItemEstoque itemestoque = new ItemEstoque();
					
				boolean retorno3 = false;
					
					for(int i = 0; i < lista_de_itens.size(); i++) {
											
						itemestoque.setChav_estr_livro(lista_de_itens.get(i).getChav_estr_livro());
						itemestoque.setQuantidade(daoestoque.quantosLivros(lista_de_itens.get(i).getChav_estr_livro())-lista_de_itens.get(i).getQuantidade());	
							
						retorno3 = daoestoque.atualizar(itemestoque);
					}
									
					if(retorno3) {
						Util.mostrarMsgSucesso();					
						dispose();
					}
					
					else
						Util.mostrarMsgErro("Não foi possível efetuar a compra.");	
					
		}
		
	}
	
	
	
	private void carregaValor() {
		
		valorUnitario.setText("");
		int index = comboLivros.getSelectedIndex()-1;
		
		if (index >= 0) {
			Livro livroSelecionado = this.listaLivros.get(index);
			valorUnitario.setText("Preço: R$ "+livroSelecionado.getPrice());						
		}
		
	}



	private void addItem() {
		
		DefaultTableModel model = (DefaultTableModel) this.vendaJTable.getModel();
		
		
		if(validaDados()) {
			
			ItensVenda itensvenda = new ItensVenda();
			
			int quantidadeFornecida = validaQuantidadeFornecida();
		
			itensvenda.setQuantidade(quantidadeFornecida);
			
			Livro livroSelecionado = this.listaLivros.get(comboLivros.getSelectedIndex()-1);
			
			itensvenda.setChav_estr_livro(livroSelecionado.getIdLivro());
				
			String preco = livroSelecionado.getPrice().replace(",", ".");				
			BigDecimal precobigdecimal = Util.converteParaBigDecimal(preco);							
			BigDecimal precoQuant = precobigdecimal.multiply(Util.converteParaBigDecimal(""+quantidadeFornecida));
				
			itensvenda.setValor(formataPreco(precoQuant.toString()));
				
			lista_de_itens.add(itensvenda);
			
			this.limpaTabela();
			
			for(ItensVenda iv: lista_de_itens) 
				model.addRow(new String[]{retornaTitulo(iv.getChav_estr_livro()), iv.getQuantidade()+"",
							iv.getValor()});
			
			int quantidade_final = 0;
			double preco_final = 0.0;	
			
			for(int i = 0; i < lista_de_itens.size(); i++) {
					
				String valorRecuperado = lista_de_itens.get(i).getValor().replace(",", ".");
				int quantidadeRecuperada = lista_de_itens.get(i).getQuantidade();
					
				BigDecimal preco_Corrente = Util.converteParaBigDecimal(valorRecuperado);
				Double valorauxiliar = preco_Corrente.doubleValue();
				preco_final += valorauxiliar;
				quantidade_final += quantidadeRecuperada;					
			}				
				
			quantidadeTotal = quantidade_final;
			precoBrutoTotal = formataPreco(""+preco_final);					
			valorFinal.setText("Total: R$ "+precoBrutoTotal);
			
			valorDesconto = "0,00";
			valor_Final = precoBrutoTotal;
			
		}	
		
		model.fireTableDataChanged();					
	}
		
	
	
	private boolean validaDados() {
						
		int index = comboLivros.getSelectedIndex()-1;
		
		if(index < 0) {
			Util.mostrarMsgErro("Selecione um livro.");
			return false;
		}
		
		Livro livroSelecionado = this.listaLivros.get(index);
		int idllivro = livroSelecionado.getIdLivro();
			
		DaoEstoque daoestoque = new DaoEstoque();
		boolean aux = daoestoque.temRegistro(idllivro);				
			
		if (aux == false) {
			Util.mostrarMsgErro("Não há registro desse livro.");
			return aux;
		}
	
		int quantidade_estoque = daoestoque.quantosLivros(idllivro);
					
		if (quantidade_estoque == 0) {
			Util.mostrarMsgErro("Não temos esse livro no estoque no momento.");
			return false;		
		}
		
		int quantidadeFornecida = validaQuantidadeFornecida();				
		int novaQuantidade = quantidade_estoque-quantidadeFornecida;
								
		if(novaQuantidade < 0) {
			Util.mostrarMsgErro("Há somente "+quantidade_estoque+" livros no estoque.");
			return false;
		}
			
		int chav = 0;
		
		for(int i = 0; i < lista_de_itens.size(); i++) {
			
			chav = lista_de_itens.get(i).getChav_estr_livro();
			
			if (livroSelecionado.getIdLivro() == chav) {
				Util.mostrarMsgErro("Livro já adicionado!");
				return false;
			}
		}
		
		
		return true;	
	}



	private void addDesconto() {
		
		String aux = precoBrutoTotal; 
		
		BigDecimal precoBruto = Util.converteParaBigDecimal(aux.replace(",", "."));
		
		String valor_Desconto = calculaDesconto(precoBruto.doubleValue());
		
		BigDecimal precoDesconto = Util.converteParaBigDecimal(valor_Desconto.replace(",", "."));
		BigDecimal preco_Com_Desconto = precoBruto.subtract(precoDesconto);
		
		valorDesconto = calculaDesconto(precoBruto.doubleValue());
		
		valor_do_Desconto.setVisible(true);
		valor_do_Desconto.setText("Desconto: -R$ "+valorDesconto);		
		valor_Final = formataPreco(preco_Com_Desconto.toString());
		
		valorFinal.setText("Total: R$ "+valor_Final);
	}
	
	
	
	private int validaQuantidadeFornecida() {
		
		if (Util.vazioOuNull(this.quantidadeJTF.getText())) 			
			return 1;
		
		else {
			
			int aux = 0;
		
			try {	
			
			aux = Integer.parseInt(this.quantidadeJTF.getText());
			
			if (aux <= 0) 
				Util.mostrarMsgErro("Quantidade fornecida é inválida.");				
			}		
			catch (InputMismatchException inputMismatchException) {
				
				Util.mostrarMsgErro("A quantidade é um número inteiro. Ex: 1,2,3...");
				inputMismatchException.printStackTrace();			
			}		
			return aux;
		}			
	}
	
	
	
	private String formataPreco(String s) {
				
			String aux = s;
			
			if(aux.contains(".")) {				
				aux = aux.replace(".", ",");				
				if(aux.indexOf(",") == aux.length()-1)
					aux += "00";
				else if(aux.indexOf(",") == aux.length()-2)
					aux += "0";
				else if(aux.length() - aux.indexOf(",")>3)
					aux = aux.substring(0, aux.indexOf(",")+3);
			}
			else
				aux += ",00";			
			
			return aux;
	}
		


	private String calculaDesconto(double valorBruto) {
		
		String retorno = "";
		
		if (Util.vazioOuNull(this.descontoJTF.getText())) 			
			return "0,00";		
		
		else {
			
			try {
				
				int desc = Integer.parseInt(descontoJTF.getText());
				
				if (desc <= 0 || desc > 15)
					Util.mostrarMsgErro("Valor de desconto inválido.");
				
				
				double precoDesconto = valorBruto*((double)desc/100);
				
				retorno = formataPreco(""+precoDesconto);				
			}		
			catch (InputMismatchException | NumberFormatException inputMismatchException) {
				Util.mostrarMsgErro("Não foi possível efetuar a compra.");
				inputMismatchException.printStackTrace();			
			}
		return retorno;
		}			
	}
	
	
	
	private void limpaTabela() {
		
		DefaultTableModel model = (DefaultTableModel) this.vendaJTable.getModel();
		
		int num =  model.getRowCount();
		
		for(int i =0; i< num; i++)
			model.removeRow(0);
	}



	private void carregaLivros() {
		
		this.listaLivros.clear();
		
		this.comboLivros.removeAllItems();
		
		this.listaLivros.addAll(new DaoLivro().listar());
		
		this.comboLivros.addItem("...");
		
		if(this.listaLivros!= null || !this.listaLivros.isEmpty()) {
			
			for (Livro livro : this.listaLivros) 
				this.comboLivros.addItem(livro.getTitulo());			
		}		
	}
	
	
	
	public String retornaTitulo(int i) {
		
		DaoLivro daolivro = new DaoLivro();
		return daolivro.tituloSelecionada(i);
		
	}
	
	
	public java.sql.Date dataAtual() {
		
		return new java.sql.Date(new Date().getTime()); 
		
	}

}
