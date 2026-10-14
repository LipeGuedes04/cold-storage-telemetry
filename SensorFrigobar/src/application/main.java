package application;

import java.util.Locale;
import java.util.Scanner;

public class main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);

		double[] temperaturas = new double[5];
		int diasRisco = 0;

		for (int i = 0; i < temperaturas.length; i++) {
			System.out.println("Insira a temperatura " + (i + 1) + ":");
			temperaturas[i] = sc.nextDouble();

			while (temperaturas[i] > 10 || temperaturas[i] < -40) {
				System.out.println("Valor inválido, digite novamente");
				System.out.println("Insira a temperatura " + (i + 1) + ":");
				temperaturas[i] = sc.nextDouble();
			}
		}
		
		double maior = temperaturas[0];
		
		for (int i = 0; i < temperaturas.length; i++) {
			System.out.println("Temperatura " + (i + 1) + " digitada: " + temperaturas[i]);

			if (temperaturas[i] > maior) {
				maior = temperaturas[i];
			}

			if (temperaturas[i] > -18) {
				diasRisco += 1;
			}
		}

		System.out.println("========================================");
		System.out.println("======== RELATÓRIO FINAL ===============");
		System.out.println("A maior temperatura da semana foi: " + maior);
		System.out.println("Total dos dias que a câmara correu risco: " + diasRisco);

		sc.close();

	}
}