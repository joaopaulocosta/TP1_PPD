package util;

public class Tabuleiro implements Runnable {
	private int numRainhas;
	private Celula matrizCelulas[][];
	private int combinacoes;
	private int posicoesRainhas[];
	private int cont;
	private int colunaInicial, colunaFinal;
	private StringBuffer stringCombinacoes;
	
	public Tabuleiro(int numRainhas, int colunaInicial, int colunaFinal){
		this.colunaInicial = colunaInicial;
		this.colunaFinal = colunaFinal;
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
		stringCombinacoes = new StringBuffer("");
		
	}
	
	public void zerarTabuleiro(){
		for(int i = 0; i < numRainhas; i++){
			for(int j = 0; j < numRainhas; j++){
				matrizCelulas[i][j].zeraCelula();
			}
		}
	}
	
	public int getCombinacoes(){
		return combinacoes;
	}
	
	public String getStringCombinacoes(){
		return stringCombinacoes.toString();
	}
	
	
	//imprimi o tabuleiro, colocando R para uma celula que possui rainha, 1 para uma celula
	//que esta sob campo de ataque de uma rainha e 0 para uma celula vazia e fora do campo de ataque
	public void imprimirTabuleiro(){
		for(Celula linha[] : this.matrizCelulas){
			for(Celula celula : linha){
				if(celula.getPossuiRainha()){
					stringCombinacoes.append("R ");
				}
				else if(celula.getEstado()){
					stringCombinacoes.append("1 ");
				}
				else{
					stringCombinacoes.append("0 ");
				}
			}
			stringCombinacoes.append("\r\n");
		}
		stringCombinacoes.append("\r\n");
	}
	
	
	public void run(){
		
		int linha = 0;
		int coluna = colunaInicial;
		
		while(true){
	
			//condiçao de parada do while, so acontece quando todo o tabuleiro for percorrido
			if(linha == 0 && coluna == colunaFinal)
				break;
			
			int i = coluna;
			if( linha == 0){	
				for(; i< colunaFinal; i++){
					if(matrizCelulas[linha][i].getEstado() == false){
						this.inserirRainha(i);
						this.preencherAlcance(linha, i);
						coluna = 0;
						linha++;
						break;
					}
				}
			}
			else{ 
				//percorre as colunas da linha a procura de uma celula livre para inserir a rainha	
				for(; i< numRainhas; i++){
					if(matrizCelulas[linha][i].getEstado() == false){
						this.inserirRainha(i);
						this.preencherAlcance(linha, i);
						coluna = 0;
						linha++;
						break;
					}
				}
			}
			
			/*caso o contador de rainhas inseridas verificar que foram inseridas N-Rainhas, uma nova
			 * combinação foi formada*/
			if(posicoesRainhas[0] == numRainhas){
				combinacoes++;
				imprimirTabuleiro();
				//System.out.println("Combinacoes: "+ combinacoes);
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
