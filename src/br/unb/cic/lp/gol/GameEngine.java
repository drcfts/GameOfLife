package br.unb.cic.lp.gol;

import java.security.InvalidParameterException;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

import javax.swing.event.CellEditorListener;

import br.unb.cic.lp.gol.memento.Caretaker;

/**
 * Representa um ambiente (environment) do jogo GameOfLife.
 * 
 * Essa implementacao eh nao inifinita, ou seja, nem todas as celulas possuem
 * oito celulas vizinhas. Por exemplo, a celula de coordenada (0,0) possui
 * apenas tres celulas vizinhas, (0,1), (1,0) e (1,1). (Fixed it!)
 * 
 * Um ambiente eh representado como um array bidimensional de celulas, com
 * altura (height) e comprimento (width).
 * 
 * @author rbonifacio
 */
public class GameEngine {
	private int height;
	private int width;
	public Cell[][] cells;
	private Statistics statistics;
	private Caretaker states;
	
	private EstrategiaDeDerivacao strategy;
	
	/**
	 * Construtor da classe Environment.
	 * 
	 * @param height
	 *            dimensao vertical do ambiente
	 * @param width
	 *            dimentsao horizontal do ambiente
	 */
	public GameEngine(int height, int width, Statistics statistics) {
		this.height = height;
		this.width = width;

		cells = new Cell[height][width];
		states = new Caretaker();

		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				cells[i][j] = new Cell();
			}
		}
		
		this.statistics = statistics;
	}
	
	/* Getters e setters de strategy */
	public EstrategiaDeDerivacao getStrategy() {
		return strategy;
	}

	public void setStrategy(EstrategiaDeDerivacao strategy) {
		this.strategy = strategy;
	}

	/**
	 * Calcula uma nova geracao do ambiente. Essa implementacao utiliza o
	 * algoritmo do Conway, ou seja:
	 * 
	 * a) uma celula morta com exatamente tres celulas vizinhas vivas se torna
	 * uma celula viva.
	 * 
	 * b) uma celula viva com duas ou tres celulas vizinhas vivas permanece
	 * viva.
	 * 
	 * c) em todos os outros casos a celula morre ou continua morta.
	 */
	public void nextGeneration() {
		List<Cell> mustRevive = new ArrayList<Cell>();
		List<Cell> mustKill = new ArrayList<Cell>();
		
		//armazena o "tabuleiro" no conjunto de estados do jogo
		Cell[][] cellState = new Cell[height][width];
		
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				cellState[i][j] = new Cell();
				cellState[i][j].setAlive(cells[i][j].isAlive());
			}
		}
		
		states.addState(cellState);
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				if (strategy.shouldRevive(i, j, this)) {
					mustRevive.add(cells[i][j]);
				} 
				else if ((!strategy.shouldKeepAlive(i, j, this)) && cells[i][j].isAlive()) {
					mustKill.add(cells[i][j]);
				}
			}
		}
		
		for (Cell cell : mustRevive) {
			cell.revive();
			statistics.recordRevive();
		}
		
		for (Cell cell : mustKill) {
			cell.kill();
			statistics.recordKill();
		}
		
	}
	
	public void restoreGenerations(int generations){
		try{
			cells = (states.restore(generations));
		}
		catch (InvalidParameterException e){
			cells = new Cell[height][width];
			for (int i = 0; i < height; i++) {
				for (int j = 0; j < width; j++) {
					cells[i][j] = new Cell();
				}
			}
			
		}
	}
	
	/**
	 * Torna a celula de posicao (i, j) viva
	 * 
	 * @param i posicao vertical da celula
	 * @param j posicao horizontal da celula
	 * 
	 * @throws InvalidParameterException caso a posicao (i, j) nao seja valida.
	 */
	public void makeCellAlive(int i, int j) throws InvalidParameterException {
		if(validPosition(i, j)) {
			cells[i][j].revive();
			statistics.recordRevive();
		}
		else {
			new InvalidParameterException("Invalid position (" + i + ", " + j + ")" );
		}
	}
	
	/**
	 * Verifica se uma celula na posicao (i, j) estah viva.
	 * 
	 * @param i Posicao vertical da celula
	 * @param j Posicao horizontal da celula
	 * @return Verdadeiro caso a celula de posicao (i,j) esteja viva.
	 * 
	 * @throws InvalidParameterException caso a posicao (i,j) nao seja valida. 
	 */
	public boolean isCellAlive(int i, int j) throws InvalidParameterException {
		if(validPosition(i, j)) {
			return cells[i][j].isAlive();
		}
		else {
			throw new InvalidParameterException("Invalid position (" + i + ", " + j + ")" );
		}
	}

	/**
	 * Retorna o numero de celulas vivas no ambiente. 
	 * Esse metodo eh particularmente util para o calculo de 
	 * estatisticas e para melhorar a testabilidade.
	 * 
	 * @return  numero de celulas vivas.
	 */
	public int numberOfAliveCells() {
		int aliveCells = 0;
		for(int i = 0; i < height; i++) {
			for(int j = 0; j < width; j++) {
				if(isCellAlive(i,j)) {
					aliveCells++;
				}
			}
		}
		return aliveCells;
	}

	/* verifica se uma celula deve ser mantida viva */
//	private boolean shouldKeepAlive(int i, int j) {
//		return (cells[i][j].isAlive())
//				&& (numberOfNeighborhoodAliveCells(i, j) == 2 || numberOfNeighborhoodAliveCells(i, j) == 3);
//	}
//
	/* verifica se uma celula deve (re)nascer */
//	private boolean shouldRevive(int i, int j) {
//		return (!cells[i][j].isAlive())
//				&& (numberOfNeighborhoodAliveCells(i, j) == 3);
//	}

	/*
	 * Computa o numero de celulas vizinhas vivas, dada uma posicao no ambiente
	 * de referencia identificada pelos argumentos (i,j).
	 * A partir da implementação modificada, tem-se que todas as células do tabuleiro 
	 * possuem 8 vizinhos. Os vizinhos das células da extrema direita se tornam
	 * as células da extrema esquerda, assim como os vizinhos extremos superiores se tornam 
	 * as células extremas inferiores.
	 */
	
	public int numberOfNeighborhoodAliveCells(int i, int j) {
		int alive = 0;
		 /* caso o valor dos vizinhos anteriores seja superior aos vizinhos 
		 * posteriores.
		 */
		int contA = 0, contB = 0;
		
		for (int a = (height + i - 1)%height; contA <3; contA++) {
			contB = 0;
			for (int b = (width + j - 1)%width; contB < 3; contB++) {
				if (validPosition(a, b)  && (!(a==i && b == j)) && cells[a][b].isAlive()) {
					alive++;
				}
				b = (b+1)%width;				
			}
			
			a = (a+1)%height;
		}
		return alive;
	} 

	/*
	 * Verifica se uma posicao (a, b) referencia uma celula valida no tabuleiro.
	 */
	private boolean validPosition(int a, int b) {
		return a >= 0 && a < height && b >= 0 && b < width;
	}
	private int[] setPosition(int a , int b){
		if(validPosition(a,b)){
			return new int[]{a,b};
				
		}
		int rA =a;
		int rB=b;
		if(a==-1){
			 rA = height -1;
		}else if(a== height){
			rA=0;
		}
		if(b==-1){
			 rB = width -1;
		}else if(a== width){
			rB=0;
		}
		
		
		return new int[]{rA,rB};
	}
	

	
	
	/* Metodos de acesso as propriedades height e width */
	
	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}
}
