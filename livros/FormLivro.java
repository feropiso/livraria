package capitulo_25_bd.projeto_I.livros;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
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
import capitulo_25_bd.projeto_I.autores.Autor;
import capitulo_25_bd.projeto_I.autores.DaoAutor;
import capitulo_25_bd.projeto_I.autores_livros.Autor_Livro;
import capitulo_25_bd.projeto_I.autores_livros.DaoAutor_Livro;
import capitulo_25_bd.projeto_I.editoras.DaoEditora;
import capitulo_25_bd.projeto_I.editoras.Editora;


public class FormLivro extends JDialog {
	
	private static final long serialVersionUID = 1L;
	
	private JTextField titulo;	
	private JTextField edicao;
	private JTextField ano;	
	private JTextField preco;
	
	private Livro livro;	
	
	private List<Autor> lista_Autores;
	private List<Editora> lista_Editoras;
	
	private JComboBox<String> comboAutor;
	private JComboBox<String> comboEditora;
	
	
	public FormLivro() {
		
		this(null);				
	}
	
	
	
	public FormLivro(Livro livro) {
		
		super();
		
		this.setTitle("Cadastro de Livro");
		
		if(livro==null) 
			this.livro = new Livro();
		else 
			this.livro = livro;			
			
		
		setLayout(new GridBagLayout());	
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(400, 500);
		setLocationRelativeTo(null);
		this.setModal(true);
		
		init();
	}



	public void init() {
		
		
		this.lista_Autores = new ArrayList<Autor>();
		this.lista_Editoras = new ArrayList<Editora>();	
		
		
		GridBagConstraints cons = new GridBagConstraints();
		
		
		JPanel panel = new JPanel(new GridBagLayout());
		panel.setBackground(Color.black);
		cons.fill = GridBagConstraints.BOTH;
		cons.gridwidth = GridBagConstraints.REMAINDER;
		cons.weightx = 1;
		cons.weighty = 1;
		cons.insets = new Insets(0,0,0,0);
		this.add(panel, cons);
		
		
		JLabel lb2 = new JLabel("Informe o Título do livro:");
		cons.fill = GridBagConstraints.HORIZONTAL;
		cons.gridwidth = GridBagConstraints.REMAINDER;
		cons.weightx = 1;
		cons.weighty = 0;
		cons.insets = new Insets(5,5,0,5);			
		lb2.setForeground(Color.white);
		panel.add(lb2, cons);
		
		
		titulo = new JTextField(Util.vazioOuNull(livro.getTitulo())?"":livro.getTitulo());		
		cons.fill = GridBagConstraints.HORIZONTAL;
		cons.gridwidth = GridBagConstraints.REMAINDER;
		cons.weightx = 1;
		cons.weighty = 0;
		cons.insets = new Insets(5,5,5,5);		
		panel.add(titulo, cons);
		
		
		JLabel lb3 = new JLabel("Informe a Edição do Livro:");
		cons.fill = GridBagConstraints.HORIZONTAL;
		cons.gridwidth = GridBagConstraints.REMAINDER;
		cons.weightx = 1;
		cons.weighty = 0;
		cons.insets = new Insets(5,5,0,5);			
		lb3.setForeground(Color.white);
		panel.add(lb3, cons);
		
		
		edicao = new JTextField(livro.getEdicao()<=0?"":livro.getEdicao()+"");		
		cons.fill = GridBagConstraints.HORIZONTAL;
		cons.gridwidth = GridBagConstraints.REMAINDER;
		cons.weightx = 1;
		cons.weighty = 0;
		cons.insets = new Insets(5,5,5,5);		
		panel.add(edicao, cons);
		
		
		JLabel lb4 = new JLabel("Informe o ano do Livro:");
		cons.fill = GridBagConstraints.HORIZONTAL;
		cons.gridwidth = GridBagConstraints.REMAINDER;
		cons.weightx = 1;
		cons.weighty = 0;
		cons.insets = new Insets(5,5,0,5);			
		lb4.setForeground(Color.white);
		panel.add(lb4, cons);
		
		
		ano = new JTextField(Util.vazioOuNull(livro.getAno())?"":livro.getAno());		
		cons.fill = GridBagConstraints.HORIZONTAL;
		cons.gridwidth = GridBagConstraints.REMAINDER;
		cons.weightx = 1;
		cons.weighty = 0;
		cons.insets = new Insets(5,5,5,5);		
		panel.add(ano, cons);
		
		
		JLabel lb5 = new JLabel("Informe o preço do Livro:");
		cons.fill = GridBagConstraints.HORIZONTAL;
		cons.gridwidth = GridBagConstraints.REMAINDER;
		cons.weightx = 1;
		cons.weighty = 0;
		cons.insets = new Insets(5,5,0,5);			
		lb5.setForeground(Color.white);
		panel.add(lb5, cons);
		
		
		preco = new JTextField(Util.vazioOuNull(livro.getPrice())?"":livro.getPrice());		
		cons.fill = GridBagConstraints.HORIZONTAL;
		cons.gridwidth = GridBagConstraints.REMAINDER;
		cons.weightx = 1;
		cons.weighty = 0;
		cons.insets = new Insets(5,5,5,5);		
		panel.add(preco, cons);
		
		
		JLabel lb6 = new JLabel("Selecione o(a) autor(a) do livro:");
		cons.fill = GridBagConstraints.HORIZONTAL;
		cons.gridwidth = GridBagConstraints.REMAINDER;
		cons.weightx = 1;
		cons.weighty = 0;
		cons.insets = new Insets(5,5,0,5);			
		lb6.setForeground(Color.white);
		panel.add(lb6, cons);
		
		
		comboAutor = new JComboBox<String>();		
		cons.fill = GridBagConstraints.HORIZONTAL;
		cons.gridwidth = GridBagConstraints.REMAINDER;
		cons.weightx = 1;
		cons.weighty = 0;
		cons.insets = new Insets(5,5,5,5);
		panel.add(comboAutor, cons);
		
		
		JLabel lb7 = new JLabel("Selecione a editora do livro:");
		cons.fill = GridBagConstraints.HORIZONTAL;
		cons.gridwidth = GridBagConstraints.REMAINDER;
		cons.weightx = 1;
		cons.weighty = 0;
		cons.insets = new Insets(5,5,0,5);			
		lb7.setForeground(Color.white);
		panel.add(lb7, cons);
		
		
		comboEditora = new JComboBox<String>();
		cons.fill = GridBagConstraints.HORIZONTAL;
		cons.gridwidth = GridBagConstraints.REMAINDER;
		cons.weightx = 1;
		cons.weighty = 0;
		cons.insets = new Insets(5,5,5,5);
		panel.add(comboEditora, cons);
		
		
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
						
				salvarItens();
					
			}}				
		);		
		
		carregaAutores();
		
		carregaEditoras();
		
		setVisible(true);		
	}



	private void salvarItens() {

		livro.setTitulo(this.titulo.getText());		
		livro.setEdicao(validaEdicao());
		livro.setAno(validaAno());
		livro.setPrice(validaPreco());
		
		
		if (livro.getPrice() == null || livro.getAno() == null ||
				livro.getEdicao()== 0)
			return;
		
		int index = this.comboAutor.getSelectedIndex();

		if(index == 0) {
			
			Util.mostrarMsgErro("Selecione um autor da tabela.");
			return;
		}
		
		Autor autorSelecionado = this.lista_Autores.get(index-1);	
		
		int idAutor = autorSelecionado.getIdautor();
		
		index = this.comboEditora.getSelectedIndex();
		
		if(index == 0) {
			
			Util.mostrarMsgErro("Selecione uma editora da tabela.");
			return;
		}
		
		Editora editoraSelecionada = this.lista_Editoras.get(index-1);		
		
		livro.setChav_estran_editora(editoraSelecionada.getId());
	
		if (Util.vazioOuNull(livro.getTitulo())) {
			
			Util.mostrarMsgErro("Digite o título do livro.");
			return;
		}
						
		
		if(livro.getIdLivro() > 0) {
			
			boolean retorno = new DaoLivro().atualizar(livro);
			
			if(retorno) {			
				
				DaoAutor_Livro daoautor_livro = new DaoAutor_Livro();
				
				List<Autor_Livro> listaAutores = daoautor_livro.listaAutoresComLivros(livro.getIdLivro());
				
				DaoAutor_Livro daoAutores = new DaoAutor_Livro();
				
				for(Autor_Livro autor: listaAutores)
					daoAutores.deletar(autor);
				
				Autor_Livro novo = new Autor_Livro();
				novo.setChav_estr_autor(idAutor);
				novo.setChav_estr_livro(livro.getIdLivro());
				
				daoAutores.inserir(novo);
				
				Util.mostrarMsgSucesso();
				dispose();
			}
			else
				Util.mostrarMsgErro("Não foi possível alterar o livro.");
		}	
		else {
			
			new DaoLivro().inserir(livro);
			
			if(livro.getIdLivro() <= 0) {
				
				Util.mostrarMsgErro("Não foi possível salvar o livro.");
			}
			else {
				
				Autor_Livro autor_livro = new Autor_Livro();
				
				autor_livro.setChav_estr_autor(idAutor);
				autor_livro.setChav_estr_livro(livro.getIdLivro());
				
				new DaoAutor_Livro().inserir(autor_livro);
				
				if(autor_livro.getAutores_livros_id()>0) {
					
						Util.mostrarMsgSucesso();
						dispose();
				}
				else
					Util.mostrarMsgErro("Não foi possível salvar o livro.");
			}
		}
	}
	
	
	
	private int validaEdicao() {
		
		if (Util.vazioOuNull(this.edicao.getText())) {
			
			Util.mostrarMsgErro("Digite a edição do livro.");
			return 0;
		}
		
		int aux = 0;
		
		try {
			aux = Integer.parseInt(this.edicao.getText());
			
			if (aux == 0) {
				Util.mostrarMsgErro("A edição não pode ser 0.");
				this.edicao.setText("");
			}			
		}
		
		catch (InputMismatchException inputMismatchException) {
			
			Util.mostrarMsgErro("A edição é um número inteiro. Ex: 1,2,3...");
			inputMismatchException.printStackTrace();			
		}
		
		return aux;
	}	
	
	
	
	private String validaAno() {
		
		if (Util.vazioOuNull(this.ano.getText())) {
			
			Util.mostrarMsgErro("Digite o ano do livro.");
			return null;
		}

		String ano = this.ano.getText();
		
		try {
			
			int ano_do_livro = Integer.parseInt(ano);
			int ano_corrente = 2020;
			
			if (ano_do_livro <= 0 || ano_do_livro > ano_corrente ) {			
				
				Util.mostrarMsgErro("O ano fornecido é inválido.");				
				return null;
			}			
			else
				return ano;	
			
		}
		catch (InputMismatchException | NumberFormatException inputMismatchException) {
			
			Util.mostrarMsgErro("O ano deve ser um inteiro de 4 algarismos. Ex: 2020");
			inputMismatchException.printStackTrace();
			return null;
		}
			
	}
	
	
	
	private String validaPreco() {

		if (Util.vazioOuNull(this.preco.getText())) {
			
			Util.mostrarMsgErro("Digite o preço do livro.");
			return null;
		}
		
		String preco = this.preco.getText().replace(",", ".");		
	
		try {
		
			BigDecimal preco_do_livro = new BigDecimal(preco);
					
			if (preco_do_livro.compareTo(new BigDecimal("0"))<=0 || 
					preco_do_livro.compareTo(new BigDecimal("9999.99")) >0 ) {			
			
				Util.mostrarMsgErro("O preço fornecido é inválido.");
				return null;
			}
			
			String aux = preco_do_livro.toString();
			
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
		catch (InputMismatchException | NumberFormatException inputMismatchException) {
			
			Util.mostrarMsgErro("O preço deve ser um número decimal. Ex: 12,34 ou 10,00");
			inputMismatchException.printStackTrace();
			return null;
		}					
	}
	
	
	
	private void carregaAutores() {
		
		this.lista_Autores.clear();
		
		this.comboAutor.removeAllItems();
		
		this.lista_Autores.addAll(new DaoAutor().listar());
		
		this.comboAutor.addItem("...");
		
		if(this.lista_Autores!=null || !this.lista_Autores.isEmpty()) {
			
			for (Autor autor : this.lista_Autores) 
				this.comboAutor.addItem(autor.getSobrenome()+", "+autor.getNome());			
		}
		
		if(this.livro.getIdLivro()>0) {
			
			int id_autor = new DaoAutor_Livro().autorSelecionado(this.livro.getIdLivro());
			
			if(id_autor>0) {
				
				for(int i = 0; i < this.lista_Autores.size(); i++) {
					
					if(this.lista_Autores.get(i).getIdautor() == id_autor) {
						
						this.comboAutor.setSelectedIndex(i+1);
						
						break;
					}
				}
			}
			
		}
	}

	

	private void carregaEditoras() {
		
		this.lista_Editoras.clear();
		
		this.comboEditora.removeAllItems();
		
		this.lista_Editoras.addAll(new DaoEditora().listar());
		
		this.comboEditora.addItem("...");
		
		if(this.lista_Editoras!= null || !this.lista_Editoras.isEmpty()) {
			
			for (Editora editora : this.lista_Editoras) 
				this.comboEditora.addItem(editora.getNome());			
		}
				
		
		if(this.livro.getIdLivro()>0) {
			
			int id_editora = new DaoLivro().editoraSelecionada(this.livro.getIdLivro());
			
			if(id_editora>0) {
				
				for(int i = 0; i < this.lista_Editoras.size(); i++) {
					
					if(this.lista_Editoras.get(i).getId() == id_editora) {
						
						this.comboEditora.setSelectedIndex(i+1);
						
						break;
					}
				}
			}
			
		}
	}


}
