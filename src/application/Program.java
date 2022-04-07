package application;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import entities.Product;

public class Program {

	public static void main(String[] args) {
		
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		
		List<Product> lista = new ArrayList<>();
		
		System.out.println("Entre com o local do arquivo: ");
		
		String localDoArquivo = sc.nextLine();
		File path = new File(localDoArquivo);
		
		boolean sucesso = new File(path, "\\OUT").mkdir();
		String destinoDoArquivo = localDoArquivo + "C:\\Users\\VINICIUS RIBEIRO\\eclipse-workspace\\ProjetoArquivo\\sumario.csv";
		
		try (BufferedReader br = new BufferedReader(new FileReader(localDoArquivo))) {

			String itemCsv = br.readLine();
			while (itemCsv != null) {

				String[] fields = itemCsv.split(",");//itens separados por virgula
				String name = fields[0];
				double price = Double.parseDouble(fields[1]);
				int quantity = Integer.parseInt(fields[2]);

				lista.add(new Product(name, price, quantity));

				itemCsv = br.readLine();
			}
			try (BufferedWriter bw = new BufferedWriter(new FileWriter(destinoDoArquivo))) {

				for (Product item : lista) {
					bw.write(item.getName() + "," + String.format("%.2f", item.getTotal()));
					bw.newLine();
				}

				System.out.println(destinoDoArquivo + " CREATED!");
				
			} catch (IOException e) {
				System.out.println("Error writing file: " + e.getMessage());
			}

		} catch (IOException e) {
			System.out.println("Error reading file: " + e.getMessage());
		}
		
		sc.close();

	}

}
