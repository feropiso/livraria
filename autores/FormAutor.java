package capitulo_25_bd.projeto_I.autores;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import capitulo_25_bd.projeto_I.Util;

public class FormAutor extends JDialog{
	
	private static final long serialVersionUID = 1L;
	
	private JTextField nome;	
	private JTextField sobrenome;
	
	private Autor autor;
		
	
	public FormAutor() {
		
		this(null);
	}
	
	
	
	public FormAutor(Autor autor) {
		
		super ();		
		
		this.setTitle("Cadastro de Autor(a)");
		
		if(autor==null)
			this.autor = new Autor();
		else
			this.autor = autor;
		
		setLayout(new GridBagLayout());	
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(400, 300);
		setLocationRelativeTo(null);
		this.setModal(true);
		
		initiator();
	}

	
	
	public void initiator() {
		
		GridBagConstraints cons = new GridBagConstraints();
		
		JPanel panel = new JPanel(new GridBagLayout());
		panel.setBackground(Color.black);
		cons.fill = GridBagConstraints.BOTH;
		cons.gridwidth = GridBagConstraints.REMAINDER;
		cons.weightx = 1;
		cons.weighty = 1;
		cons.insets = new Insets(0,0,0,0);
		this.add(panel, cons);
		
		
		JLabel lb2 = new JLabel("Informe o Nome do(a) Autor(a):");
		cons.fill = GridBagConstraints.HORIZONTAL;
		cons.gridwidth = GridBagConstraints.REMAINDER;
		cons.weightx = 1;
		cons.weighty = 0;
		cons.insets = new Insets(5,5,0,5);			
		lb2.setForeground(Color.white);
		panel.add(lb2, cons);
		
		
		nome = new JTextField(Util.vazioOuNull(autor.getNome())?"":autor.getNome());		
		cons.fill = GridBagConstraints.HORIZONTAL;
		cons.gridwidth = GridBagConstraints.REMAINDER;
		cons.weightx = 1;
		cons.weighty = 0;
		cons.insets = new Insets(5,5,5,5);		
		panel.add(nome, cons);
		
		
		JLabel lb3 = new JLabel("Informe o Sobrenome do(a) Autor(a):");
		cons.fill = GridBagConstraints.HORIZONTAL;
		cons.gridwidth = GridBagConstraints.REMAINDER;
		cons.weightx = 1;
		cons.weighty = 0;
		cons.insets = new Insets(5,5,0,5);			
		lb3.setForeground(Color.white);
		panel.add(lb3, cons);
		
		
		sobrenome = new JTextField(Util.vazioOuNull(autor.getSobrenome())?"":autor.getSobrenome());		
		cons.fill = GridBagConstraints.HORIZONTAL;
		cons.gridwidth = GridBagConstraints.REMAINDER;
		cons.weightx = 1;
		cons.weighty = 0;
		cons.insets = new Insets(5,5,5,5);		
		panel.add(sobrenome, cons);
		
		
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
						
				toSave();
					
			}}				
		);
			
		setVisible(true);
		
	}

	
	
	private void toSave() {

		autor.setNome(this.nome.getText());
		autor.setSobrenome(this.sobrenome.getText());
		
		if (autor == null) {
			
			Util.mostrarMsgErro("Digite nome do autor.");
			return;
		}
		
		if (Util.vazioOuNull(autor.getNome())) {
			
			Util.mostrarMsgErro("Digite nome do autor.");
			return;
		}
		
		if (Util.vazioOuNull(autor.getSobrenome())) {
			
			Util.mostrarMsgErro("Digite o sobrenome do autor.");
			return;
		}

		if(this.autor.getIdautor()>0) {
			
			boolean retorno = new DaoAutor().atualizar(autor);
			
			if(retorno) {
				
				Util.mostrarMsgSucesso();
				dispose();
			}
			else
				Util.mostrarMsgErro("Não foi possível alterar o autor.");
		}	
		else {
			
			new DaoAutor().inserir(autor);
			
			if(autor.getIdautor()<=0) {
				
				Util.mostrarMsgErro("Não foi possível salvar o autor.");
			}
			else {
				
				Util.mostrarMsgSucesso();
				dispose();
			}
		}
		
	}

}
