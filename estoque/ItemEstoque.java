package capitulo_25_bd.projeto_I.estoque;

public class ItemEstoque {
	
	private int id;
	private int quantidade;
	private int chav_estr_livro;
	
	private String titulo;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	public int getChav_estr_livro() {
		return chav_estr_livro;
	}
	public void setChav_estr_livro(int chav_estr_livro) {
		this.chav_estr_livro = chav_estr_livro;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}	
	

}
