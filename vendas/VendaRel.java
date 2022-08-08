package capitulo_25_bd.projeto_I.vendas;

import java.util.List;

import capitulo_25_bd.projeto_I.itens_venda.ItensVenda;

public class VendaRel {
	
	private Venda venda;
	private List<ItensVenda> itens;
	
	
	public Venda getVenda() {
		return venda;
	}
	public void setVenda(Venda venda) {
		this.venda = venda;
	}
	public List<ItensVenda> getItens() {
		return itens;
	}
	public void setItens(List<ItensVenda> itens) {
		this.itens = itens;
	}
	

}
