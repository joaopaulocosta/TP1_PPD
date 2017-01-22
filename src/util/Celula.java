/*
 * Trabalho 1 de PPD
 * João Paulo Costa
 * Renato Carvalho Alvarenga
 * Vinícius Salles Pereira 
 */
package util;



/**
 * A Classe Celula, responsavel por representar a unidade que forma o tabuleiro.
 */
public class Celula {
	
	/** linha e coluna representam a localizacao da celula. */
	private int linha, coluna;
	
	/** O booleano possuiRainha verifica se a celula ja possui ou nao uma rainha. */
	private boolean possuiRainha;
	
	/** O booleano estaNoAlcance verifica se a celula esta no alcance do ataque de alguma rainha ou nao. */
	private boolean estaNoAlcance;
	
	/**
	 * construtor para a celula.
	 *
	 * @param linha da celula
	 * @param coluna da celula
	 */
	public Celula(int linha, int coluna){
		this.linha = linha;
		this.coluna = coluna;
		possuiRainha = false;
		estaNoAlcance = false;
	}
	
	/**
	 * Insere a rainha na celula, com isso modifica o valor de possuiRainha.
	 */
	public void inserirRainha(){
		possuiRainha = true;
	}
	
	/**
	 * Retorna o valor de possuiRainha.
	 *
	 * @return o valor de possuiRainha
	 */
	public boolean getPossuiRainha(){
		return possuiRainha;
	}
	
	/**
	 * Inserir alcance na celula, com isso modifica o valor de estaNoAlcance.
	 */
	public void inserirAlcance(){
		estaNoAlcance = true;
	}
	
	/**
	 * Retorna o valor de estaNoAlcance.
	 *
	 * @return o valor de estaNoAlcance
	 */
	public boolean getEstado(){
		return estaNoAlcance;
	}
	
	/**
	 * Retorna a linha correspondente da celula.
	 *
	 * @return o valor de linha
	 */
	public int getLinha(){
		return linha;
	}
	
	/**
	 * Retorna a coluna correspondente da celula.
	 *
	 * @return o valor de coluna
	 */
	public int getColuna(){
		return coluna;
	}
	
	/**
	 * Zera celula, tornando falsa as suas propriedades.
	 */
	public void zeraCelula(){
		possuiRainha = false;
		estaNoAlcance = false;
	}
	
}
