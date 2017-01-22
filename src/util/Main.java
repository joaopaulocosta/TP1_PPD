package util;

import java.util.Scanner;

public class Main {
	private static int numThreads = 4;
	private static int numPossibilidades = 0;
	private static int numRainhas = 8;
	
	public static void main(String[] args){
		
		Thread listaThreads[] = new Thread[numThreads];
		Tabuleiro listaTabuleiros[] = new Tabuleiro[numThreads];
		int cont_Aux = 0;
		for(int i = 0; i < numThreads; i++){
			int aux;
			if(numRainhas%numThreads != 0 && i < numRainhas%numThreads){
				aux = (numRainhas/numThreads) + 1;
				cont_Aux++;
				System.out.println("Coluna Inicial: " + (i*aux) + " Coluna Final: "+ ((i*aux) + aux));
				listaTabuleiros[i] = new Tabuleiro(numRainhas, (i*aux) , (i*aux) + aux );
				listaThreads[i] = new Thread(listaTabuleiros[i]);
				listaThreads[i].start();
			}
			else{
				aux = numRainhas/numThreads;
				System.out.println("Coluna Inicial: " + (i*aux + cont_Aux) + " Coluna Final: "+ ((i*aux + cont_Aux) + aux));
				listaTabuleiros[i] = new Tabuleiro(numRainhas, (i*aux + cont_Aux) , (i*aux + cont_Aux) + aux );
				listaThreads[i] = new Thread(listaTabuleiros[i]);
				listaThreads[i].start();
			}
		}
		
		
		
		for(int i = 0; i < numThreads; i++){
			try{
				listaThreads[i].join();
			}catch(Exception e){
				System.out.println("Erro no join");
			}
			numPossibilidades += listaTabuleiros[i].getCombinacoes();
		}
		System.out.println(numPossibilidades + "combinações");
	}
}
