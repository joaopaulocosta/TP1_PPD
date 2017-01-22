/*
 * 
 */
package util;


import java.util.Scanner;

// TODO: Auto-generated Javadoc
/**
 * The Class Main.
 */
public class Main {
	
	/** The num threads. */
	private static int numThreads = 4;
	
	/** The num possibilidades. */
	private static int numPossibilidades = 0;
	
	/** The num rainhas. */
	private static int numRainhas = 8;
	
	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
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
		
		
		for(int i = 0; i < numThreads; i++){
			try{
				listaThreads[i].join();
			}catch(Exception e){
				System.out.println("Erro no join");
			}
			numPossibilidades += listaTabuleiros[i].getCombinacoes();
			System.out.println("\nCombinações da Thread "+i+"\n");
			System.out.println(listaTabuleiros[i].getStringCombinacoes());
		}
		
		
		System.out.println("Total de "+numPossibilidades + " combinações");
	}
}
