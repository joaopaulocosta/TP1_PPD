package util;

public class Tabuleiro {
	private int numRainhas;
	private Celula matrizCelulas[][];
	private int combinacoes;
	private int posicoesRainhas[];
	private int cont;
	
	public Tabuleiro(int numRainhas){
		cont = 0;
		this.numRainhas = numRainhas;
		this.matrizCelulas = new Celula[numRainhas][numRainhas];
		for(int i = 0; i < numRainhas; i++){
			for(int j = 0; j < numRainhas; j++){
				matrizCelulas[i][j] = new Celula(i,j);
			}
		}
		this.combinacoes = 0;
		
		this.posicoesRainhas = new int[numRainhas + 1];
		posicoesRainhas[0] = 0;
		
	}
	
	public void zerarTabuleiro(){
		for(int i = 0; i < numRainhas; i++){
			for(int j = 0; j < numRainhas; j++){
				matrizCelulas[i][j].zeraCelula();
			}
		}
	}
	
	public int getNumRainhas(){
		return numRainhas;
	}
	
	//imprimi o tabuleiro, colocando R para uma celula que possui rainha, 1 para uma celula
	//que esta sob campo de ataque de uma rainha e 0 para uma celula vazia e fora do campo de ataque
	public void imprimirTabuleiro(){
		System.out.println("");
		for(Celula linha[] : this.matrizCelulas){
			for(Celula celula : linha){
				if(celula.getPossuiRainha()){
					System.out.print("R ");
				}
				else if(celula.getEstado()){
					System.out.print("1 ");
				}
				else{
					System.out.print("0 ");
				}
			}
			System.out.println("");
		}
	}
	
	
	public void acharCombinacao(int linha, int coluna){
		
		while(true){
			//condiçao de parada do while, so acontece quando todo o tabuleiro for percorrido
			if(linha == 0 && coluna == numRainhas)
				break;
			
			//percorre as colunas da linha a procura de uma celula livre para inserir a rainha
			int i = coluna;
			for(; i< numRainhas; i++){
				if(matrizCelulas[linha][i].getEstado() == false){
					this.inserirRainha(i);
					this.preencherAlcance(linha, i);
					coluna = 0;
					linha++;
					break;
				}
			}
			/*caso o contador de rainhas inseridas verificar que foram inseridas N-Rainhas, uma nova
			 * combinação foi formada*/
			if(posicoesRainhas[0] == numRainhas){
				combinacoes++;
				imprimirTabuleiro();
				System.out.println("Combinacoes: "+ combinacoes);
				posicoesRainhas[0]--;
				zerarTabuleiro();
				preencherTabuleiro();
				linha--;
				coluna = posicoesRainhas[posicoesRainhas[0] + 1]+ 1;
				
			/*caso a linha seja percorrida e não seja possivel inserir nenhuma rainha, a rainha da linha
			 * anterior é removida e uma nova inserçao e tentada */
			}else if(i == numRainhas){
				posicoesRainhas[0]--;
				zerarTabuleiro();
				preencherTabuleiro();
				coluna = posicoesRainhas[posicoesRainhas[0] + 1]+ 1;
				linha--;
			} 	
		}
		
	}
	
	
	//inseri a rainha em uma determinada coluna, a linha é relacionada ao vetor de posicoes
	public void inserirRainha(int coluna){
		posicoesRainhas[0]++;
		posicoesRainhas[posicoesRainhas[0]] = coluna;
		matrizCelulas[posicoesRainhas[0]-1][coluna].inserirRainha();
		
	}
	
	//preenche o tabuleiro de acordo com o vetor de posicoes das rainhas
	public void preencherTabuleiro(){
		for(int i = 0; i < posicoesRainhas[0]; i++){
			matrizCelulas[i][posicoesRainhas[i+1]].inserirRainha();
			preencherAlcance(i,posicoesRainhas[i+1]);
		}
	}
	
	
	//Função que preenche as celulas que estao no campo de ataque de uma rainha
	public void preencherAlcance(int linha, int coluna){
		
		//preenchendo coluna e linha
		for(int i = 0; i < numRainhas; i++){
			matrizCelulas[linha][i].inserirAlcance();
			matrizCelulas[i][coluna].inserirAlcance();
		}
		
		//preenchendo diagonais
		
		int linhaAux = linha - 1;
		int colunaAux = coluna - 1;
		
		while(linhaAux >= 0 && colunaAux >= 0){
			matrizCelulas[linhaAux--][colunaAux--].inserirAlcance();
		}
		
		linhaAux = linha + 1;
		colunaAux = coluna + 1;
		
		while(linhaAux < numRainhas && colunaAux < numRainhas){
			matrizCelulas[linhaAux++][colunaAux++].inserirAlcance();
		}
		
		linhaAux = linha - 1;
		colunaAux = coluna + 1;
		
		while(linhaAux >= 0 && colunaAux < numRainhas){
			matrizCelulas[linhaAux--][colunaAux++].inserirAlcance();
		}
		
		linhaAux = linha + 1;
		colunaAux = coluna - 1;
		
		while(linhaAux < numRainhas && colunaAux >= 0){
			matrizCelulas[linhaAux++][colunaAux--].inserirAlcance();
		}
		
	}
	
}
