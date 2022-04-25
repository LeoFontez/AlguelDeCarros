package model.services;

import model.entities.Aluguel;
import model.entities.Fatura;

public class ServicoDeAluguel {

	private Double precoPorDia;
	private Double precoPorHora;
	
	private TaxaDeServicos taxaDeServico;

	public ServicoDeAluguel(Double precoPorDia, Double precoPorHora, TaxaDeServicos taxa) {
		this.precoPorDia = precoPorDia;
		this.precoPorHora = precoPorHora;
		this.taxaDeServico = taxa;
	}
	
	public void processoDeFatura(Aluguel aluguel) {
		//nota de pagamento
		
		long t1 = aluguel.getStart().getTime();
		long t2 = aluguel.getFinish().getTime();
		
		double horas = (double)(t2 - t1) / 1000 / 60 / 60;
		
		double pagamentoBasico;
		if(horas <= 12.0) {
			pagamentoBasico = Math.ceil(horas) * precoPorHora;
		}
		else {
			pagamentoBasico = Math.ceil(horas / 24) * precoPorDia;
		}
		
		double taxa = taxaDeServico.taxa(pagamentoBasico);
		
		aluguel.setFatura(new Fatura(pagamentoBasico,taxa));
	}
	
}
