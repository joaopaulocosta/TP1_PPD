package util;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import java.util.Scanner;

public class Main {
	private static int numThreads;
	private static int numPossibilidades = 0;
	private static int numRainhas;
	
	public static void main(String[] args){
		
//Checanco os par�metros de entrada--------------------------------------------------
		if(args.length != 2){
			System.out.println("Entrada de par�metros n�o � v�lida, primeiro entre com o numero de rainhas seguido"
					+ " de espa�o e o n�mero de rainhas por threads");
			return;
		}
		try{
			numRainhas = Integer.parseInt(args[0]);
			numThreads = Integer.parseInt(args[1]);
			int aux;
			aux = (numRainhas/numThreads);
			if(numRainhas%numThreads != 0){
				numThreads = aux+1;
			}
		}catch(Exception e){
			System.out.println("Os dois par�metros devem ser do tipo inteiro");
			return;
		}
		
		if(numRainhas < 4){
			System.out.println("O n�mero m�nimo de rainhas � 4");
			return;
		}

//--------------------------------------------------------------------------------------
//Criando as threads e os tabuleiros que ser�o usados para a paraleliza��o		
		
		
		Thread listaThreads[] = new Thread[numThreads];
		Tabuleiro listaTabuleiros[] = new Tabuleiro[numThreads];
		int cont_Aux = 0;
		
	    System.out.println("");
		
		for(int i = 0; i < numThreads; i++){
			int aux;
			if(numRainhas%numThreads != 0 && i < numRainhas%numThreads){
				aux = (numRainhas/numThreads) + 1;
				cont_Aux++;
				System.out.println("Thread "+ i +" Coluna Inicial: " + (i*aux) + " Coluna Final: "+ ((i*aux) + aux));
				listaTabuleiros[i] = new Tabuleiro(numRainhas, (i*aux) , (i*aux) + aux );
			}
			else{
				aux = numRainhas/numThreads;
				System.out.println("Thread "+ i +" Coluna Inicial: " + (i*aux + cont_Aux) + " Coluna Final: "+ ((i*aux + cont_Aux) + aux));
				listaTabuleiros[i] = new Tabuleiro(numRainhas, (i*aux + cont_Aux) , (i*aux + cont_Aux) + aux );
			}
			
			listaThreads[i] = new Thread(listaTabuleiros[i]);
			listaThreads[i].start();
		}
		
		System.out.println("");
//-----------------------------------------------------------------------------------------
//Gerando sa�da, todas as combina��es s�o salvas no arquivo texto
		try{
			FileWriter arq = new FileWriter("combinacoes.txt");
		    PrintWriter gravarArq = new PrintWriter(arq);
		    
		    for(int i = 0; i < numThreads; i++){
				try{
					listaThreads[i].join();
				}catch(Exception e){
					System.out.println("Erro no join");
				}
				numPossibilidades += listaTabuleiros[i].getCombinacoes();
				
				gravarArq.println("\nCombina��es da Thread "+i+"\r\n");
				gravarArq.println(listaTabuleiros[i].getStringCombinacoes());
			}
		    
		    gravarArq.println("Total de "+numPossibilidades + " combina��es");
		    System.out.println("Total de "+numPossibilidades + " combina��es");
		    System.out.println("\nTodas as combina��es poss�veis foram gravados no arquivo combinacoes.txt\n");
		    
		    gravarArq.close();
		    arq.close();
		    
		}catch(Exception e){
			System.out.println("N�o foi poss�vel gravar o arquivo");
			return;
		}
		
		
	}
}
