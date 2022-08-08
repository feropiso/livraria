package capitulo_25_bd.projeto_I.relatorios;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import capitulo_25_bd.projeto_I.Util;
import capitulo_25_bd.projeto_I.autores.Autor;
import capitulo_25_bd.projeto_I.autores.DaoAutor;
import capitulo_25_bd.projeto_I.autores.RelatorioAutores;
import capitulo_25_bd.projeto_I.autores_livros.RelatorioAutorLivro;
import capitulo_25_bd.projeto_I.editoras.DaoEditora;
import capitulo_25_bd.projeto_I.editoras.Editora;
import capitulo_25_bd.projeto_I.editoras.RelatorioEditoraLivros;
import capitulo_25_bd.projeto_I.editoras.RelatorioEditoras;
import capitulo_25_bd.projeto_I.estoque.RelatorioEstoque;
import capitulo_25_bd.projeto_I.itens_venda.RelatorioItensVenda;
import capitulo_25_bd.projeto_I.livros.DaoLivro;
import capitulo_25_bd.projeto_I.livros.Livro;
import capitulo_25_bd.projeto_I.vendas.RelatorioVenda;
import capitulo_25_bd.projeto_I.vendas.RelatorioVendaDetalhada;


public class FormRelatorio extends JDialog {
	
	private JLabel lb3;
	private JLabel lb4;
	
	private JComboBox<String> JCbox_relatorios;
	private JComboBox<String> JCbox_autores;
	private JComboBox<String> JCbox_editoras;
	
	private List<Autor> listaAutores;
	private List<Editora> listaEditoras;
	
	
	public FormRelatorio() {
		
		super ();		
		
		this.setTitle("Relatórios da Biblioteca");
		
		setLayout(new GridBagLayout());	
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(400, 300);
		setLocationRelativeTo(null);
		this.setModal(true);
		
		inicializador();
	}
	
	

	public void inicializador() {
		
		this.listaAutores = new ArrayList<Autor>();
		this.listaEditoras = new ArrayList<Editora>();	
		
		GridBagConstraints cons = new GridBagConstraints();
		
		
		JPanel panel = new JPanel(new GridBagLayout());
		panel.setBackground(Color.black);
		cons.fill = GridBagConstraints.BOTH;
		cons.gridwidth = GridBagConstraints.REMAINDER;
		cons.weightx = 1;
		cons.weighty = 1;
		cons.insets = new Insets(0,0,0,0);
		this.add(panel, cons);
		
		
		JLabel lb2 = new JLabel("Selecione o tipo de relatório:");
		cons.fill = GridBagConstraints.HORIZONTAL;
		cons.gridwidth = GridBagConstraints.REMAINDER;
		cons.weightx = 1;
		cons.weighty = 0;
		cons.insets = new Insets(5,5,0,5);			
		lb2.setForeground(Color.white);
		panel.add(lb2, cons);
		
		
		JCbox_relatorios = new JComboBox<String>(new String[]{"...", 
				"Relatório Geral de Autores", "Relatório Geral de Editoras",
				"Relatório Livros por Autor(a)", "Relatório Livros por Editora", 
				"Relatório Itens-Venda", "Relatório de Estoque", 
				"Relatório sintético de Vendas", 
				"Relatório analítico de Vendas"});
		
		cons.fill = GridBagConstraints.HORIZONTAL;
		cons.gridwidth = GridBagConstraints.REMAINDER;
		cons.weightx = 1;
		cons.weighty = 0;
		cons.insets = new Insets(5,5,5,5);						
		panel.add(JCbox_relatorios, cons);
		
		
		lb3 = new JLabel("Selecione o autor:");
		lb3.setVisible(false);
		cons.fill = GridBagConstraints.HORIZONTAL;
		cons.gridwidth = GridBagConstraints.REMAINDER;
		cons.weightx = 1;
		cons.weighty = 0;
		cons.insets = new Insets(5,5,0,5);			
		lb3.setForeground(Color.white);
		panel.add(lb3, cons);
		
		
		JCbox_autores = new JComboBox<String>();	
		JCbox_autores.setVisible(false);
		cons.fill = GridBagConstraints.HORIZONTAL;
		cons.gridwidth = GridBagConstraints.REMAINDER;
		cons.weightx = 1;
		cons.weighty = 0;
		cons.insets = new Insets(5,5,5,5);
		panel.add(JCbox_autores, cons);
		
		
		lb4 = new JLabel("Selecione a editora:");
		lb4.setVisible(false);
		cons.fill = GridBagConstraints.HORIZONTAL;
		cons.gridwidth = GridBagConstraints.REMAINDER;
		cons.weightx = 1;
		cons.weighty = 0;
		cons.insets = new Insets(5,5,0,5);			
		lb4.setForeground(Color.white);
		panel.add(lb4, cons);
		
		
		JCbox_editoras = new JComboBox<String>();
		JCbox_editoras.setVisible(false);
		cons.fill = GridBagConstraints.HORIZONTAL;
		cons.gridwidth = GridBagConstraints.REMAINDER;
		cons.weightx = 1;
		cons.weighty = 0;
		cons.insets = new Insets(5,5,5,5);
		panel.add(JCbox_editoras, cons);
		
		
		JButton bt_gerar = new JButton("Gerar");		
		cons.fill = GridBagConstraints.NONE;
		cons.gridwidth = GridBagConstraints.REMAINDER;
		cons.anchor = GridBagConstraints.CENTER;
		cons.weightx = 0;
		cons.weighty = 0;
		cons.insets = new Insets(5,5,5,5);		
		panel.add(bt_gerar, cons);
		
		
		JCbox_relatorios.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent ev) {
						
				if(JCbox_relatorios.getSelectedIndex()==3) {
					
					lb4.setVisible(false);
					JCbox_editoras.setVisible(false);
					
					lb3.setVisible(true);
					JCbox_autores.setVisible(true);			
				}
				
				if(JCbox_relatorios.getSelectedIndex()==4) {
					
					lb3.setVisible(false);
					JCbox_autores.setVisible(false);
					
					lb4.setVisible(true);
					JCbox_editoras.setVisible(true);
					
				}
			}}				
		);
		
		
		bt_gerar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent ev) {
						
				gerar();
					
			}}				
		);
		
		carregaAutores();
		carregaEditoras();
		setVisible(true);		
	}



	private void gerar() {
		
		
		int index = this.JCbox_relatorios.getSelectedIndex();
		
		if(index==0) {
			
			Util.mostrarMsgErro("Selecione um tipo de relatório.");
			return;
		}
		
		if(index==1) {
						
			RelatorioAutores relatorioautores = new RelatorioAutores();
			boolean retorno = relatorioautores.gerarRelatorio();
			
			if(retorno)
				dispose();
		}
		
		if(index==2) {
			
			RelatorioEditoras relatorioeditores = new RelatorioEditoras();
			
			boolean retorno = relatorioeditores.gerarRelatorio();			
			
			if(retorno)
				dispose();
		}
		
		if(index==3) {
			
			int aux = JCbox_autores.getSelectedIndex()-1;
			
			if (aux < 0) {
				
				Util.mostrarMsgErro("Selecione um autor.");
				return;
			}			
			
			Autor autorselecionado = this.listaAutores.get(aux);	
			
			String autor = autorselecionado.getSobrenome()+", "+autorselecionado.getNome();
			
			DaoLivro daolivro = new DaoLivro();			
			List<Livro> lista = daolivro.livrosAutor(autorselecionado.getIdautor());
						
			RelatorioAutorLivro relatorioautorlivro = new RelatorioAutorLivro();
			boolean retorno = relatorioautorlivro.gerarRelatorio(lista, autor);			
			
			if(retorno)
				dispose();
			
		}
		
		if(index==4) {
			
			int aux = JCbox_editoras.getSelectedIndex()-1;
			
			if (aux < 0) {
				
				Util.mostrarMsgErro("Selecione uma editora.");
				return;
			}
			
			Editora editoraselecionada = this.listaEditoras.get(aux);
			String editora = editoraselecionada.getNome();
			DaoLivro daolivro = new DaoLivro();			
			List<Livro> lista = daolivro.livrosEditora(editoraselecionada.getId());
						
			RelatorioEditoraLivros relatorioeditoralivros = new RelatorioEditoraLivros();
			
			boolean retorno = relatorioeditoralivros.gerarRelatorio(lista, editora);			
			
			if(retorno)
				dispose();
			
		}
		
		if(index==5) {
			
			RelatorioItensVenda relatorioitensvenda = new RelatorioItensVenda();
			boolean retorno = relatorioitensvenda.gerarRelatorio();			
			
			if(retorno)
				dispose();
		}
		
		if(index==6) {
			
			RelatorioEstoque relatorioestoque = new RelatorioEstoque();
			boolean retorno = relatorioestoque.gerarRelatorio();			
			
			if(retorno)
				dispose();
		}
		
		if(index==7) {
			
			RelatorioVenda relatoriovenda = new RelatorioVenda();
						
			boolean retorno = relatoriovenda.gerarRelatorio();			
			
			if(retorno)
				dispose();
		}
		
		if(index==8) {
			
			RelatorioVendaDetalhada relatoriovenda = new RelatorioVendaDetalhada();
			
			boolean retorno = relatoriovenda.gerarRelatorio();			
			
			if(retorno)
				dispose();
		}
	}

	
	private void carregaAutores() {
		
		this.listaAutores.clear();
		
		this.JCbox_autores.removeAllItems();
		
		this.listaAutores.addAll(new DaoAutor().listar());
		
		this.JCbox_autores.addItem("...");
		
		if(this.listaAutores!=null || !this.listaAutores.isEmpty()) {
			
			for (Autor autor : this.listaAutores) 
				this.JCbox_autores.addItem(autor.getSobrenome()+", "+autor.getNome());			
		}
				
	}
	
	
	private void carregaEditoras() {
		
		this.listaEditoras.clear();
		
		this.JCbox_editoras.removeAllItems();
		
		this.listaEditoras.addAll(new DaoEditora().listar());
		
		this.JCbox_editoras.addItem("...");
		
		if(this.listaEditoras!= null || !this.listaEditoras.isEmpty()) {
			
			for (Editora editora : this.listaEditoras) 
				this.JCbox_editoras.addItem(editora.getNome());			
		}		
	}
}
