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
		
		List<Product> list = new ArrayList<>();
		
		System.out.println("Enter file path: ");
		String sourceFileStr = sc.nextLine();											 //É INFORMADO O DESTINO DO ARQUIVO NA PASTA, ATRAVEZ DO TECLADO COMO ENTRADA
		
		File sourceFile = new File(sourceFileStr);
		String sourceFolderStr = sourceFile.getParent();                                 //OBETEM O CAMINHO DE ORIGEM DESPRESANDO O ARQUIVO.
		
		boolean sucess = new File(sourceFile + "\\out").mkdir();						 // CRIA UMA PASTA CHAMADA OUT

		
		System.out.println("Folder Created: " + sucess);									// IMPRIME TRUE OU FALSE SE FOI CRIADO OU NÃO
		
		String targetFileStr = sourceFileStr + "\\out\\summary.csv";						// CRIA UM ARQUIVO COM A ORIGEM DO ARQUIVO SOURCE
		
		try (BufferedReader br = new BufferedReader(new FileReader(sourceFileStr))){        //TRY-WITH-RESOURCE/ UTILIZADO PARA LER E ESCREVER ARQUIVOS
			
			String itemCsv = br.readLine();													//CRIA O ITEM APARTIR DE UMA STRING ATRAVES DA LEITURA BR.READLINE													
			while (itemCsv != null) {														//ENQUANTO NÃO CHEGAR NO FINAL DO ARQUIVO ELE CONTINUARÁ LENDO//DIFERENTE DE NULO
				String[] fields = itemCsv.split(",");                                       // ESSA STRING SEPARA O QUE SERÁ LIDO POR "," OU VALOR DIFERENTE CASO QUEIRA ENTRE OS PARENTESES
				String name = fields[0];                                                    // COM BASE NESSA SEPARAÇÃO, PEGA O PRIMEIRO VALOR JÁ ATRIBUINDO A NAME
				double price = Double.parseDouble(fields[1]);								// PEGA O SEGUNDO VALOR, COMO A ORIGEM É STRING PRECISAMOS USAR O METODO QUE CONVERTE NO TIPO DESEJADO(PARSE)
				int quantity = Integer.parseInt(fields[2]);									// PEGA O TERCEIRO VALOR, COMO A ORIGEM É STRING PRECISAMOS USAR O METODO QUE CONVERTE NO TIPO DESEJADO(PARSE)
				
				list.add(new Product(name, price, quantity));								// COM O VALOR OBTIDO CRIAMOS O OBJETO JÁ INCLUINDO A LISTA
				
				itemCsv = br.readLine();													// PULAMOS PRA PROXIMA LINHA
			}
			
			try(BufferedWriter bw = new BufferedWriter(new FileWriter(targetFileStr))) {     //PARA CRIAR O NOVO ARQUIVO PEGO O VALOR QUE CRIEI COM BASE NO ARQUIVO ORIGEM
				for(Product item: list) {													 // PARA CADA ITEM PRODUCT NA LISTA
					bw.write(item.getName() + ", " + String.format("%.2f", item.totalValue()));// SERÁ ESCRITO O NOME DO ITEM + O RESULTADO DO METODO UTILIZADO
					bw.newLine();
				}
				System.out.println(targetFileStr + " CREATED");								 // SE NÃO DEU ERRO, É IMPRIMIDO A MENSAGEM CREATED
				
			}catch (IOException e) {														 // AQUI TRATA O ERRO
				System.out.println("Error: " + e.getMessage());
			}
	
		}catch (IOException e) {															  // AQUI TRATA O ERRO
		
			System.out.println("Error: " + e.getMessage());
		}
		sc.close();
	}
}
