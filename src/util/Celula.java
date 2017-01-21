package util;


public class Celula {
	private int linha, coluna;
	private boolean possuiRainha;
	private boolean estaNoAlcance;
	
	public Celula(int linha, int coluna){
		this.linha = linha;
		this.coluna = coluna;
		possuiRainha = false;
		estaNoAlcance = false;
	}
	
	public void inserirRainha(){
		possuiRainha = true;
	}
	
	public boolean getPossuiRainha(){
		return possuiRainha;
	}
	
	public void inserirAlcance(){
		estaNoAlcance = true;
	}
	
	public boolean getEstado(){
		return estaNoAlcance;
	}
	
	public int getLinha(){
		return linha;
	}
	
	public int getColuna(){
		return coluna;
	}
	
	public void zeraCelula(){
		possuiRainha = false;
		estaNoAlcance = false;
	}
	
}
