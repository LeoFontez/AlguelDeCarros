package aplicacao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

import model.entities.Aluguel;
import model.entities.Veiculo;
import model.services.ServicoDeAluguel;
import model.services.TaxaDeServicoBrasil;

public class Program {

	public static void main(String[] args) throws ParseException {
		
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:ss");
		
		System.out.println("Entre com a data do Aluguel :");
		System.out.print("Modelo do Carro: ");
		String modeloDoCarro = sc.nextLine();
		
		System.out.print("Data de Retirada (dd/MM/yyyy HH:ss): ");
		Date start = sdf.parse(sc.nextLine());
		
		System.out.print("Data de Retorno (dd/MM/yyyy HH:ss): ");
		Date finish = sdf.parse(sc.nextLine());
		
		Aluguel aluguel = new Aluguel(start, finish, new Veiculo(modeloDoCarro));
		
		System.out.print("Preço por Hora: ");
		double precoPorHora = sc.nextDouble();
		
		System.out.print("Preço por Dia: ");
		double precoPorDia = sc.nextDouble();
		
		ServicoDeAluguel servicoDeAluguel = new ServicoDeAluguel(precoPorDia, precoPorHora, new TaxaDeServicoBrasil());
		
		servicoDeAluguel.processoDeFatura(aluguel);
		
		System.out.println("\nFATURA: ");
		System.out.println("Pagamento Basico: " + String.format("%.2f", aluguel.getFatura().getPagamentoBasico()));
		System.out.println("Taxa: " + String.format("%.2f", aluguel.getFatura().getTaxa()));
		System.out.println("Pagamento Total: " + String.format("%.2f", aluguel.getFatura().getPagamentoTotal()));
		
		
		sc.close();
	}

}
