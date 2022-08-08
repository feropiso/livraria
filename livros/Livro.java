package capitulo_25_bd.projeto_I.livros;

public class Livro {
	
	private int idLivro;
	private int edicao;
	private int chav_estran_editora;
	
	private String ano;
	private String price;
	private String titulo;
	
	
	public int getIdLivro() {
		return idLivro;
	}
	public void setIdLivro(int idLivro) {
		this.idLivro = idLivro;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public int getEdicao() {
		return edicao;
	}
	public void setEdicao(int edicao) {
		this.edicao = edicao;
	}
	public String getAno() {
		return ano;
	}
	public void setAno(String nome) {
		this.ano = nome;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}	
	public int getChav_estran_editora() {
		return chav_estran_editora;
	}
	public void setChav_estran_editora(int chav_estran_editora) {
		this.chav_estran_editora = chav_estran_editora;
	}
	
}
