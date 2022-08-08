package capitulo_25_bd.projeto_I.editoras;

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
import capitulo_25_bd.projeto_I.editoras.DaoEditora;


public class FormEditora extends JDialog {	
	
	
	private static final long serialVersionUID = 1L;
	
	private JTextField nome;
	
	private Editora editora;
	

	public FormEditora() {
	
		this(null);
	}
	
	
	
	public FormEditora(Editora editora) {

		super ();		
		
		this.setTitle("Cadastro de Editora");
		
		if(editora==null)
			this.editora = new Editora();
		else
			this.editora = editora;
		
		setLayout(new GridBagLayout());	
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(400, 200);
		setLocationRelativeTo(null);
		this.setModal(true);
	
		inicializador();
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
		
		
		JLabel lb2 = new JLabel("Informe o Nome:");
		cons.fill = GridBagConstraints.HORIZONTAL;
		cons.gridwidth = GridBagConstraints.REMAINDER;
		cons.weightx = 1;
		cons.weighty = 0;
		cons.insets = new Insets(5,5,0,5);			
		lb2.setForeground(Color.white);
		panel.add(lb2, cons);
		
		
		nome = new JTextField(Util.vazioOuNull(editora.getNome())?"":editora.getNome());		
		cons.fill = GridBagConstraints.HORIZONTAL;
		cons.gridwidth = GridBagConstraints.REMAINDER;
		cons.weightx = 1;
		cons.weighty = 0;
		cons.insets = new Insets(5,5,5,5);		
		panel.add(nome, cons);
		
		
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
		
		
		setVisible(true);
	}
	

	
	private void salvar() {
		
		editora.setNome(this.nome.getText());
		
		if (editora == null) {
			
			Util.mostrarMsgErro("Digite nome da editora.");
			return;
		}
		
		if (Util.vazioOuNull(editora.getNome())) {
			
			Util.mostrarMsgErro("Digite nome da editora.");
			return;
		}
		
		if(this.editora.getId()>0) {
			
			boolean retorno = new DaoEditora().atualizar(editora);
			
			if(retorno) {
				
				Util.mostrarMsgSucesso();
				dispose();
			}
			else
				Util.mostrarMsgErro("Não foi possível alterar a editora.");
		}	
		else {
			
			new DaoEditora().inserir(editora);
			
			if(editora.getId()<=0) {
				
				Util.mostrarMsgErro("Não foi possível salvar a editora.");
			}
			else {
				
				Util.mostrarMsgSucesso();
				dispose();
			}
		}
		
	}
}
