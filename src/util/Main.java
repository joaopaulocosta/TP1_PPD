package util;

import java.util.Scanner;

public class Main {
	private static int num_Threads = 1;
	private static int num_Possibilidades = 0;
	
	public static void main(String[] args){
		Tabuleiro tabuleiro = new Tabuleiro(4);
		tabuleiro.acharCombinacao2(0,0);		
	}
}
