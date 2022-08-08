package capitulo_25_bd.projeto_I.vendas;

import java.sql.Date;

public class Venda {
	
	private int id;
	private int quantidadeTotal;
	
	private String desconto;
	private String valorBruto;
	private String valorTotal;
	private String data_aux;
	
	private Date data;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getQuantidadeTotal() {
		return quantidadeTotal;
	}

	public void setQuantidadeTotal(int quantidadeTotal) {
		this.quantidadeTotal = quantidadeTotal;
	}

	public String getDesconto() {
		return desconto;
	}

	public void setDesconto(String desconto) {
		this.desconto = desconto;
	}

	public String getValorBruto() {
		return valorBruto;
	}

	public void setValorBruto(String valorBruto) {
		this.valorBruto = valorBruto;
	}

	public String getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(String valorTotal) {
		this.valorTotal = valorTotal;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public String getData_aux() {
		return data_aux;
	}

	public void setData_aux(String data_aux) {
		this.data_aux = data_aux;
	}
	
	
	

}
