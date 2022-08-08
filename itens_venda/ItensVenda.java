package capitulo_25_bd.projeto_I.itens_venda;

public class ItensVenda {
	
	private int id;
	private int chav_estr_livro;
	private int chav_estr_venda;
	private int quantidade;
	
	private String valor;
	private String titulo;
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getChav_estr_livro() {
		return chav_estr_livro;
	}

	public void setChav_estr_livro(int chav_estr_livro) {
		this.chav_estr_livro = chav_estr_livro;
	}

	public int getChav_estr_venda() {
		return chav_estr_venda;
	}

	public void setChav_estr_venda(int chav_estr_venda) {
		this.chav_estr_venda = chav_estr_venda;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	
	
	

}
