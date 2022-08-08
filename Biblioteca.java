package capitulo_25_bd.projeto_I;

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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import capitulo_25_bd.projeto_I.autores.Autor;
import capitulo_25_bd.projeto_I.autores.DaoAutor;
import capitulo_25_bd.projeto_I.autores.FormAutor;
import capitulo_25_bd.projeto_I.autores_livros.Autor_Livro;
import capitulo_25_bd.projeto_I.autores_livros.DaoAutor_Livro;
import capitulo_25_bd.projeto_I.editoras.DaoEditora;
import capitulo_25_bd.projeto_I.editoras.Editora;
import capitulo_25_bd.projeto_I.editoras.FormEditora;
import capitulo_25_bd.projeto_I.estoque.FormEstoque;
import capitulo_25_bd.projeto_I.livros.DaoLivro;
import capitulo_25_bd.projeto_I.livros.FormLivro;
import capitulo_25_bd.projeto_I.livros.Livro;
import capitulo_25_bd.projeto_I.relatorios.FormRelatorio;
import capitulo_25_bd.projeto_I.vendas.FormVenda;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;

public class Biblioteca extends JFrame {	
	
	private int limite_inferior = 0;
	private int quant = 0;
	private final int numero_maximo = 10;
	
	private JLabel status_pagina_Label;
	
	private JTable resultadoJTable;	
	
	private JComboBox<String> pesquisaJCbox;
	
	private JTextField buscaJTxtF;

	private List<Editora> listaEditoras;
	private List<Autor> listaAutores;
	private List<Livro> listaLivros;
	
	
	public Biblioteca() {
		
		super ("Consulta na Biblioteca");			
		setLayout(new GridBagLayout());					
		inicializador();
		
		this.listaEditoras = new ArrayList<Editora>();
		this.listaAutores = new ArrayList<Autor>();
		this.listaLivros = new ArrayList<Livro>();
	}
	
	
	
	public void inicializador() {
		
		GridBagConstraints cons = new GridBagConstraints();
		
		
		JPanel panel = new JPanel(new GridBagLayout());
		panel.setBackground(Color.black);
		cons.fill = GridBagConstraints.BOTH;
		cons.gridwidth = GridBagConstraints.REMAINDER;
		cons.weightx = 1;
		cons.weighty = 1;
		cons.insets = new Insets(0,0,0,0);
		this.add(panel, cons);
		
		
		JPanel panelbts = new JPanel(new GridBagLayout());
		panelbts.setBackground(Color.black);
		cons.fill = GridBagConstraints.HORIZONTAL;
		cons.gridwidth = GridBagConstraints.REMAINDER;
		cons.weightx = 1;
		cons.weighty = 0;
		cons.insets = new Insets(0,0,0,0);
		panel.add(panelbts, cons);
		
		
		JPanel panelPesquisa = new JPanel(new GridBagLayout());
		panelPesquisa.setBackground(Color.black);
		cons.fill = GridBagConstraints.HORIZONTAL;
		cons.gridwidth = GridBagConstraints.REMAINDER;
		cons.weightx = 1;
		cons.weighty = 0;
		cons.insets = new Insets(0,0,0,0);
		panel.add(panelPesquisa, cons);	
		
		
		JButton autorJButton = new JButton("Novo Autor(a)");		
		cons.fill = GridBagConstraints.HORIZONTAL;
		cons.gridwidth = 1;
		cons.weightx = 1;
		cons.weighty = 0;
		cons.insets = new Insets(5,5,5,5);		
		panelbts.add(autorJButton, cons);
		
		
		JButton editoraJButton = new JButton("Nova Editora");		
		cons.fill = GridBagConstraints.HORIZONTAL;
		cons.gridwidth = 1;
		cons.weightx = 1;
		cons.weighty = 0;
		cons.insets = new Insets(5,5,5,5);		
		panelbts.add(editoraJButton, cons);
		
		
		JButton livroJButton = new JButton("Novo Livro");		
		cons.fill = GridBagConstraints.HORIZONTAL;
		cons.gridwidth = 1;
		cons.weightx = 1;
		cons.weighty = 0;
		cons.insets = new Insets(5,5,5,5);		
		panelbts.add(livroJButton, cons);
		
		
		JButton bt_loja = new JButton("Comprar");		
		cons.fill = GridBagConstraints.HORIZONTAL;
		cons.gridwidth = 1;
		cons.weightx = 1;
		cons.weighty = 0;
		cons.insets = new Insets(5,5,5,5);		
		panelbts.add(bt_loja, cons);
		
		
		JButton bt_estoque = new JButton("Estoque");		
		cons.fill = GridBagConstraints.HORIZONTAL;
		cons.gridwidth = 1;
		cons.weightx = 1;
		cons.weighty = 0;
		cons.insets = new Insets(5,5,5,5);		
		panelbts.add(bt_estoque, cons);
		
		
		JButton bt_relatorio = new JButton("Relatórios");		
		cons.fill = GridBagConstraints.HORIZONTAL;
		cons.gridwidth = GridBagConstraints.REMAINDER;
		cons.weightx = 1;
		cons.weighty = 0;
		cons.insets = new Insets(5,5,5,5);			
		panelbts.add(bt_relatorio, cons);
		
		
		JLabel lb1 = new JLabel("Pesquisar:");
		cons.fill = GridBagConstraints.HORIZONTAL;
		cons.gridwidth = GridBagConstraints.REMAINDER;
		cons.weightx = 1;
		cons.weighty = 0;
		cons.insets = new Insets(5,5,0,5);			
		lb1.setForeground(Color.white);
		panelPesquisa.add(lb1, cons);
		
		
		buscaJTxtF = new JTextField();
		cons.fill = GridBagConstraints.HORIZONTAL;
		cons.gridwidth = 1;
		cons.weightx = 5;
		cons.weighty = 0;
		cons.insets = new Insets(5,5,5,5);						
		panelPesquisa.add(buscaJTxtF, cons);
		
		
		pesquisaJCbox = new JComboBox<String>(new String[]{"...", 
															"Lista de Autores", 
															"Lista de Editoras", 
															"Livros da Biblioteca"});
		
		cons.fill = GridBagConstraints.HORIZONTAL;
		cons.gridwidth = 1;
		cons.weightx = 5;
		cons.weighty = 0;
		cons.insets = new Insets(5,5,5,5);						
		panelPesquisa.add(pesquisaJCbox, cons);
		
		
		JButton pesquisarJButton = new JButton("Pesquisar");		
		cons.fill = GridBagConstraints.HORIZONTAL;
		cons.gridwidth = GridBagConstraints.REMAINDER;
		cons.weightx = 1;
		cons.weighty = 0;
		cons.insets = new Insets(5,5,5,5);		
		panelPesquisa.add(pesquisarJButton, cons);
				
		
		resultadoJTable = new JTable();
		resultadoJTable.setModel(new DefaultTableModel(
	            new Object [][] {},
	            new String [] {"Código", "Descrição"}) {
	           
			private static final long serialVersionUID = 1L;
				
	        public boolean isCellEditable(int rowIndex, int columnIndex) {
	               return false;
	        }
	    });
		
		
		resultadoJTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
		resultadoJTable.setSurrendersFocusOnKeystroke(true);
		
		
		JScrollPane barraRolagem = new JScrollPane(resultadoJTable);
		cons.fill = GridBagConstraints.BOTH;
		cons.gridwidth = GridBagConstraints.REMAINDER;
		cons.weightx = 1;
		cons.weighty = 1;
		cons.insets = new Insets(5,5,5,5);
		panel.add(barraRolagem, cons);
		
		
		JPanel panelPesquisaPaginada = new JPanel(new GridBagLayout());
		panelPesquisaPaginada.setBackground(Color.black);
		cons.fill = GridBagConstraints.HORIZONTAL;
		cons.gridwidth = GridBagConstraints.REMAINDER;
		cons.weightx = 1;
		cons.weighty = 0;
		cons.insets = new Insets(0,0,0,0);
		panel.add(panelPesquisaPaginada, cons);
				
		
		JButton anteriorJButton = new JButton("Anterior");
		cons.anchor = GridBagConstraints.CENTER;
		cons.fill = GridBagConstraints.NONE;				
		cons.gridwidth = 1;
		cons.weightx = 0;
		cons.weighty = 0;
		cons.insets = new Insets(5,5,5,5);
		panelPesquisaPaginada.add(anteriorJButton, cons);
		
		
		status_pagina_Label = new JLabel("Sem Resultados");
		cons.anchor = GridBagConstraints.CENTER;
		cons.fill = GridBagConstraints.NONE;			
		cons.gridwidth = 1;
		cons.weightx = 0;
		cons.weighty = 0;
		cons.insets = new Insets(5,15,5,15);				
		status_pagina_Label.setForeground(Color.white);
		panelPesquisaPaginada.add(status_pagina_Label, cons);
		
		
		JButton posteriorJButton = new JButton("Próxima");	
		cons.anchor = GridBagConstraints.CENTER;
		cons.fill = GridBagConstraints.NONE;	;		
		cons.gridwidth = GridBagConstraints.REMAINDER;
		cons.weightx = 0;
		cons.weighty = 0;
		cons.insets = new Insets(5,5,5,5);
		panelPesquisaPaginada.add(posteriorJButton, cons);
				
		
		JPanel panelRodape = new JPanel(new GridBagLayout());
		panelRodape.setBackground(Color.black);
		cons.fill = GridBagConstraints.HORIZONTAL;
		cons.gridwidth = GridBagConstraints.REMAINDER;
		cons.weightx = 1;
		cons.weighty = 0;
		cons.insets = new Insets(0,0,0,0);
		panel.add(panelRodape, cons);
		
		
		JButton alterarJButton = new JButton("Alterar");		
		cons.fill = GridBagConstraints.NONE;
		cons.anchor = GridBagConstraints.WEST;
		cons.gridwidth = 1;
		cons.weightx = 1;
		cons.weighty = 0;
		cons.insets = new Insets(5,5,5,5);		
		panelRodape.add(alterarJButton, cons);
		
		
		JButton removerJButton = new JButton("Remover");		
		cons.fill = GridBagConstraints.NONE;
		cons.gridwidth = GridBagConstraints.REMAINDER;
		cons.anchor = GridBagConstraints.EAST;
		cons.weightx = 1;
		cons.weighty = 0;
		cons.insets = new Insets(5,5,5,5);		
		panelRodape.add(removerJButton, cons);
			
		
		autorJButton.addActionListener(
				new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent ev) {
						
						novoAutor();
					
					}					
				}				
		);
		
		
		editoraJButton.addActionListener(
				new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent ev) {
						
						novoEditora();										
					}					
				}				
		);
		
		
		livroJButton.addActionListener(
				new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent ev) {
						
						novoLivro();										
					}					
				}				
		);
		
		bt_loja.addActionListener(
				new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent ev) {
						
						novaVenda();															
					}					
				}				
		);
		
		
		bt_estoque.addActionListener(
				new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent ev) {
						
						novoItemEstoque();									
					}					
				}				
		);
		
		
		bt_relatorio.addActionListener(
				new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent ev) {
						
						geraRelatorio();										
					}					
				}				
		);
		
		
		buscaJTxtF.getDocument().addDocumentListener(new  DocumentListener() {

			@Override
			public void insertUpdate(DocumentEvent e) {
				
				resetPaginacao();
				pesquisar();
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				
				resetPaginacao();
				pesquisar();
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
			}
			
			
		});
				
		
		pesquisarJButton.addActionListener(
				new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent ev) {						
						
						resetPaginacao();
						pesquisar();						
					}					
				}				
		);
		
		
		alterarJButton.addActionListener(new ActionListener() {
					
			@Override
			public void actionPerformed(ActionEvent ev) {
						
				alterarItem();		
			}					
		});
		
		
		removerJButton.addActionListener(new ActionListener() {
					
			@Override
			public void actionPerformed(ActionEvent ev) {
						
				removerItem();
			}					
		});
		
		
		anteriorJButton.addActionListener(
				new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent ev) {						
						
						anterior();
					}					
				}				
		);
		
		
		posteriorJButton.addActionListener(
				new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent ev) {
						
						proximo();
							
					}					
				}				
		);
		
	}
	
	

	private void novoAutor() {
		
		FormAutor form = new FormAutor();
		pesquisar();
	}
	
	
	
	private void novoEditora() {
		
		FormEditora form = new FormEditora();
		pesquisar();
	}
	
	
	
	private void novoLivro() {
		
		FormLivro form = new FormLivro();
		pesquisar();
	}
	
	
	
	private void novaVenda() {
		
		FormVenda form = new FormVenda();		
	}



	private void novoItemEstoque() {
		
		FormEstoque form = new FormEstoque();
		
	}



	private void resetPaginacao() {
		
		this.limite_inferior = 0;
		this.quant = 0;
	}
	
	
	private void pesquisar() {
		
		this.listaEditoras.clear();
		this.listaAutores.clear();
		this.listaLivros.clear();
		
		this.limpaTabela();
		
		
		DefaultTableModel model = (DefaultTableModel) this.resultadoJTable.getModel();
		
		
		if(this.pesquisaJCbox.getSelectedIndex()==1) {
			
			this.quant = new DaoAutor().contAutores(this.buscaJTxtF.getText());
			
			if(this.quant>0) {
				
				this.listaAutores.addAll(new DaoAutor().listaPaginada(this.buscaJTxtF.getText(), limite_inferior, this.numero_maximo));
				
				status_pagina_Label.setText((limite_inferior+1)+" a "+(limite_inferior+this.listaAutores.size())+" de "+this.quant);
			}
				
			else
				status_pagina_Label.setText("Sem resultados");
			
			for (Autor autor : this.listaAutores)  
	            model.addRow(new String[]{autor.getIdautor()+"", autor.getSobrenome()+", "+autor.getNome()});
		}		
		
		
		if(this.pesquisaJCbox.getSelectedIndex()==2) {
			
			this.quant = new DaoEditora().contEditoras(this.buscaJTxtF.getText());
			
			if(this.quant>0) {
				
				this.listaEditoras.addAll(new DaoEditora().listaPaginada(this.buscaJTxtF.getText(), limite_inferior, this.numero_maximo));
				
				status_pagina_Label.setText((limite_inferior+1)+" a "+(limite_inferior+this.listaEditoras.size())+" de "+this.quant);
			}
				
			else
				status_pagina_Label.setText("Sem resultados");
			
			for (Editora editora : this.listaEditoras)  
	            model.addRow(new String[]{editora.getId()+"", editora.getNome()});  
		}
		
		
		if(this.pesquisaJCbox.getSelectedIndex()==3) {
			
			this.quant = new DaoLivro().contLivros(this.buscaJTxtF.getText());
			
			if(this.quant>0) {
				
				this.listaLivros.addAll(new DaoLivro().listaPaginada(this.buscaJTxtF.getText(), limite_inferior, this.numero_maximo));
				
				status_pagina_Label.setText((limite_inferior+1)+" a "+(limite_inferior+this.listaLivros.size())+" de "+this.quant);
			}
			else
				status_pagina_Label.setText("Sem resultados");
			
			
			for (Livro livro : this.listaLivros)  
	            model.addRow(new String[]{livro.getIdLivro()+"", livro.getTitulo()+",  ed: "+ livro.getEdicao()+""+",  ano: "+livro.getAno()+",  preço: R$ "+livro.getPrice()});
		}
		
		
		model.fireTableDataChanged();
		model.fireTableStructureChanged();
	}
	
	
	private void anterior() {
		
		if(this.quant==0)
			return;
		
		if(limite_inferior <= 0)
			return;
			
		limite_inferior -= this.numero_maximo;
		
		pesquisar();
	}
	
	
	private void proximo() {
		
		if(this.quant==0)
			return;
		
		if((limite_inferior+this.listaAutores.size()) >= this.quant)
			return;
		
		if((limite_inferior+this.listaEditoras.size()) >= this.quant)
			return;
		
		if((limite_inferior+this.listaLivros.size()) >= this.quant)
			return;		
		
		limite_inferior+= this.numero_maximo;
		
		pesquisar();
	}
	
	
	private void alterarItem() {
		
		int index = this.resultadoJTable.getSelectedRow();
		
		if(index<0) {
			
			Util.mostrarMsgErro("Selecione uma linha da tabela.");
			return;
		}
		
		
		if(this.pesquisaJCbox.getSelectedIndex()==1) {
			
			Autor autorSelecionado = this.listaAutores.get(index);
			
			FormAutor form = new FormAutor(autorSelecionado);
			pesquisar();
		}
		
		
		if(this.pesquisaJCbox.getSelectedIndex()==2) {
			
			Editora editoraSelecionada = this.listaEditoras.get(index);
			
			FormEditora form = new FormEditora(editoraSelecionada);
			pesquisar();
		}
		
		if(this.pesquisaJCbox.getSelectedIndex()==3) {
			
			Livro livroSelecionado = this.listaLivros.get(index);				
			FormLivro form = new FormLivro(livroSelecionado);
			pesquisar();	
		}
				
	}
	
	
	
	private void removerItem() {
		
		int index = this.resultadoJTable.getSelectedRow();
		
		if(index<0) {
			
			Util.mostrarMsgErro("Selecione uma linha da tabela.");
			return;
		}
		
		
		if(this.pesquisaJCbox.getSelectedIndex()==1) {
			
			Autor autorSelecionada = this.listaAutores.get(index);
			
			boolean retorno = new DaoAutor().deletar(autorSelecionada);
			
			if(retorno) {
				
				Util.mostrarMsgSucesso();
				pesquisar();
			}
			else
				Util.mostrarMsgErro("Não foi possível deletar o autor.");
		}
		
		
		if(this.pesquisaJCbox.getSelectedIndex()==2) {
			
			Editora editoraSelecionada = this.listaEditoras.get(index);
			
			boolean retorno = new DaoEditora().deletar(editoraSelecionada);
			
			if(retorno) {
				
				Util.mostrarMsgSucesso();
				pesquisar();
			}
			else
				Util.mostrarMsgErro("Não foi possível deletar a editora.");
		}
		
		
		if(this.pesquisaJCbox.getSelectedIndex()==3) {
			
			DaoAutor_Livro daoautor_livro = new DaoAutor_Livro();
			
			Livro livroSelecionado = this.listaLivros.get(index);
			
			List<Autor_Livro> listaAutores = daoautor_livro.listaAutoresComLivros(livroSelecionado.getIdLivro());
			
			DaoAutor_Livro daoAutores = new DaoAutor_Livro();
			
			for(Autor_Livro autor: listaAutores)
				daoAutores.deletar(autor);
			
			boolean retorno = new DaoLivro().deletar(livroSelecionado);
					
			if(retorno) {
				
				Util.mostrarMsgSucesso();
				pesquisar();
			}
			else
				Util.mostrarMsgErro("Não foi possível deletar o livro.");
		}
	}
	
	
	
	private void limpaTabela() {
		
		DefaultTableModel model = (DefaultTableModel) this.resultadoJTable.getModel();
		
		int num =  model.getRowCount();
		
		for(int i =0; i< num; i++)
			model.removeRow(0);
	}
	
	
	
	private void geraRelatorio() {
		
		FormRelatorio formrelatorio = new FormRelatorio();
		
	}
	
	
	
	public static void main(String[] args) {
				
			try {
			
				for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
			        
					//System.out.println(info.getName());
					
					if ("Nimbus".equals(info.getName()))
			            UIManager.setLookAndFeel(info.getClassName());
			    }
				
			} catch (ClassNotFoundException | 
					InstantiationException | 
					IllegalAccessException |
					UnsupportedLookAndFeelException e) {
				
				e.printStackTrace();
			} 
		
		Dao.criaConexao();	
		
		if(!Dao.temConexao()) {
			
			Util.mostrarMsgErro("Não foi possível conectar ao BD.");
			return;
		}
		
		Biblioteca bdbiblioteca = new Biblioteca();
		bdbiblioteca.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		bdbiblioteca.setSize(800, 600);
		bdbiblioteca.setLocationRelativeTo(null);
		bdbiblioteca.setVisible(true);		
	}

	
	
	
}
