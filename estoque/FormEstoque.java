package capitulo_25_bd.projeto_I.estoque;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import capitulo_25_bd.projeto_I.Util;
import capitulo_25_bd.projeto_I.livros.DaoLivro;
import capitulo_25_bd.projeto_I.livros.Livro;


public class FormEstoque extends JDialog {
	
	private JTextField quatidade;
	
	private List<Livro> listaLivros;
	
	private ItemEstoque itemEstoque;
	
	private JComboBox<String> comboLivros;
		
	
	public FormEstoque() {		

		this(null);
	}
	
	
	
	public FormEstoque(ItemEstoque itemEstoque) {

		super ();		
		
		this.setTitle("Inserir no Estoque");
		
		if(itemEstoque==null)
			this.itemEstoque = new ItemEstoque();
		else
			this.itemEstoque = itemEstoque;
		
		setLayout(new GridBagLayout());	
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(400, 300);
		setLocationRelativeTo(null);
		this.setModal(true);
	
		inicializador();
	}



	private void inicializador() {
		
		this.listaLivros = new ArrayList<Livro>();
		
		
		GridBagConstraints cons = new GridBagConstraints();
		
		
		JPanel panel = new JPanel(new GridBagLayout());
		panel.setBackground(Color.black);
		cons.fill = GridBagConstraints.BOTH;
		cons.gridwidth = GridBagConstraints.REMAINDER;
		cons.weightx = 1;
		cons.weighty = 1;
		cons.insets = new Insets(0,0,0,0);
		this.add(panel, cons);
		
		
		JLabel lb2 = new JLabel("Selecione o Livro para Inserir:");
		cons.fill = GridBagConstraints.HORIZONTAL;
		cons.gridwidth = GridBagConstraints.REMAINDER;
		cons.weightx = 1;
		cons.weighty = 0;
		cons.insets = new Insets(5,5,0,5);			
		lb2.setForeground(Color.white);
		panel.add(lb2, cons);
		
		
		comboLivros = new JComboBox<String>();		
		cons.fill = GridBagConstraints.HORIZONTAL;
		cons.gridwidth = GridBagConstraints.REMAINDER;
		cons.weightx = 1;
		cons.weighty = 0;
		cons.insets = new Insets(5,5,5,5);
		panel.add(comboLivros, cons);
		
		
		JLabel lb3 = new JLabel("Informe a Quantidade:");
		cons.fill = GridBagConstraints.HORIZONTAL;
		cons.gridwidth = GridBagConstraints.REMAINDER;
		cons.weightx = 1;
		cons.weighty = 0;
		cons.insets = new Insets(5,5,0,5);			
		lb3.setForeground(Color.white);
		panel.add(lb3, cons);
		
		
		quatidade = new JTextField();		
		cons.fill = GridBagConstraints.HORIZONTAL;
		cons.gridwidth = GridBagConstraints.REMAINDER;
		cons.weightx = 1;
		cons.weighty = 0;
		cons.insets = new Insets(5,5,5,5);		
		panel.add(quatidade, cons);
		
		
		JButton bt_salvar = new JButton("Salvar");		
		cons.fill = GridBagConstraints.NONE;
		cons.gridwidth = GridBagConstraints.REMAINDER;
		cons.anchor = GridBagConstraints.CENTER;
		cons.weightx = 0;
		cons.weighty = 0;
		cons.insets = new Insets(5,5,5,5);		
		panel.add(bt_salvar, cons);
		
		
		bt_salvar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent ev) {
						
				salvar();
					
			}}				
		);
		
		carregaLivros();
		setVisible(true);
	}

	

	private void salvar() {
		
		int index = this.comboLivros.getSelectedIndex()-1;

		if(index < 0) {			
			Util.mostrarMsgErro("Selecione um livro.");
			return;
		}
		
		if (Util.vazioOuNull(this.quatidade.getText())) {
			
			Util.mostrarMsgErro("Digite a quantidade.");
			return;
		}
		
		
		int quant = obtemQuantidade();
		
		if (quant <= 0) {
			
			Util.mostrarMsgErro("Quantidade inválida.");
			return;
		}
		
		int chav_livro = obtemIdLivro(index);
		
		itemEstoque.setQuantidade(quant);
		itemEstoque.setChav_estr_livro(chav_livro);
		
		DaoEstoque daoestoque = new DaoEstoque();
		boolean r = daoestoque.temRegistro(chav_livro);
		int estoque = daoestoque.quantosLivros(chav_livro);
		if(r) {
			itemEstoque.setQuantidade(quant+estoque);
			boolean retorno = new DaoEstoque().atualizar(itemEstoque);
			
			if(retorno) {				
				Util.mostrarMsgSucesso();
				dispose();
			}
			else
				Util.mostrarMsgErro("Não foi possível alterar o estoque.");
		}		
		else {
			
			new DaoEstoque().inserir(itemEstoque);
			
			if(itemEstoque.getId()<0) {
				
				Util.mostrarMsgErro("Não foi possível salvar o item no estoque.");
			}
			else {
				
				Util.mostrarMsgSucesso();
				dispose();
			}
		}		
	}
	
	
	
	private int obtemQuantidade() {
				
		int aux = 0;
		
		try {
			aux = Integer.parseInt(this.quatidade.getText());
						
		}
		
		catch (InputMismatchException inputMismatchException) {
			
			Util.mostrarMsgErro("A quantidade é um número inteiro. Ex: 1,2,3...");
			inputMismatchException.printStackTrace();			
		}
		
		return aux;				
	}
	
	
	
	private int obtemIdLivro(int index) {
		
		Livro livroSelecionado = this.listaLivros.get(index);		
		
		return livroSelecionado.getIdLivro();		
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
}
