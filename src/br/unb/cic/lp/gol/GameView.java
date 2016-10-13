package br.unb.cic.lp.gol;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javax.swing.*;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.core.io.FileSystemResource;

import br.unb.cic.lp.gol.regras.ListaDeRegras;



/**
 * Atua como um componente de apresentacao (view), exibindo o estado atual do
 * game com uma implementacao baseada em caracteres ASCII.
 * 
 * @author rbonifacio
 */
public class GameView {
	private static final String LINE = "+-----+";
	private static final String DEAD_CELL = "|     |";
	private static final String ALIVE_CELL = "|  o  |";
	
	private static final int INVALID_OPTION = 0;
	private static final int MAKE_CELL_ALIVE = 1;
	private static final int NEXT_GENERATION = 2;
	private static final int REGRAS = 3; 
	private static final int HALT = 99; 
	private static final int N_GENERATIONS = 4;
	private static final int RESTORE_GENERATIONS = 5;

	private GameEngine engine;
	private GameController controller;
	String teste;

	/**
	 * Construtor da classe GameBoard
	 */
	public GameView(GameController controller, GameEngine engine) {
		this.controller = controller;
		this.engine = engine;
	}

	/**
	 * Atualiza o componente view (representado pela classe GameBoard),
	 * possivelmente como uma resposta a uma atualizacao do jogo.
	 */
	public void update() {
		controller.getGui().atualizacao();
	}
	
	public void updateLoop(){
		controller.getGui().atualizacaoInfinita();
	}

	public void printOptions(String valor) {
		int option;
		
		BeanFactory factory = new XmlBeanFactory(new FileSystemResource("spring.xml"));
		
		do {
			option = parseOption(valor);
		}while(option == 0);
		
		switch(option) {
			case MAKE_CELL_ALIVE : makeCellAlive(); break;
			case NEXT_GENERATION : nextGeneration(); break;
			case REGRAS :
				
				List<String> string = new ArrayList<String>();
				
				ApplicationContext xml = new FileSystemXmlApplicationContext("spring.xml");
				ListaDeRegras lista = (ListaDeRegras) xml.getBean("listaderegras");
				
				for(EstrategiaDeDerivacao regrax: lista.getLista()){ 
					teste = regrax.getName();
					string.add(teste);
				 }
				
				 String input = (String) JOptionPane.showInputDialog(null, "Choose now...",
						 "Escolha a regra:", JOptionPane.QUESTION_MESSAGE, null, string.toArray(),
					     null); 

				 EstrategiaDeDerivacao strategy = (EstrategiaDeDerivacao)factory.getBean(input);
				 
				 engine.setStrategy(strategy); 
				 update(); break;
			
			case HALT : halt(); break;
			case N_GENERATIONS : computeGenerations(); break;
			case RESTORE_GENERATIONS : restoreGenerations();
		}
	}
	
	private void makeCellAlive() {
		String row, column;
		int i, j = 0;
		Scanner s = new Scanner(System.in);
		
		do {
			row = (String) JOptionPane.showInputDialog("Inform the row number (0 - " + engine.getHeight() + "): ");			
			i = Integer.parseInt(row);
			
			column = (String) JOptionPane.showInputDialog("Inform the column number (0 - " + engine.getWidth() + "): ");			
			j = Integer.parseInt(column);
		}while(!validPosition(i,j));
		
		controller.makeCellAlive(i, j);
		s.close();
	}
	
	private void nextGeneration() {
		controller.nextGeneration(false);
	}
	
	private void halt() {
		controller.getGui().halt();
	}
	
	private void computeGenerations(){
		controller.nextGeneration(true);		
	}
	
	private void restoreGenerations(){
		String input;
		int generations;
		
		input = (String) JOptionPane.showInputDialog("How many generations to be reverted? ");
		generations = Integer.parseInt(input);
		JOptionPane.showMessageDialog(null, "Restoring " + generations + " generations.");
		
		controller.restoreGenerations(generations);
		
	}
	
	private boolean validPosition(int i, int j) {
		System.out.println(i);
		System.out.println(j);
		return i >= 0 && i < engine.getHeight() && j >= 0 && j < engine.getWidth();
	}
/*
 * 
 * mudei a visibilidade do parseOption para modificar la no gameGUI
 */
	
	public int parseOption(String option) {
		if(option.equals("1")) {
			return MAKE_CELL_ALIVE;
		}
		else if (option.equals("2")) {
			return NEXT_GENERATION;
		}
		else if (option.equals("3")){
			return REGRAS;
		}
		else if(option.equals("4")){
			return N_GENERATIONS;
		}
		else if(option.equals("5")){
			return RESTORE_GENERATIONS;
		}
		else if (option.equals("99")) {
			return HALT;
		}
		else return INVALID_OPTION;
	}

	/* Imprime uma linha usada como separador das linhas do tabuleiro */
	private void printLine() {
		for (int j = 0; j < engine.getWidth(); j++) {
			System.out.print(LINE);
		}
		System.out.print("\n");
	}

	/*
	 * Imprime os identificadores das colunas na primeira linha do tabuleiro
	 */
	private void printFirstRow() {
		System.out.println("\n \n");
		for (int j = 0; j < engine.getWidth(); j++) {
			System.out.print("   " + j + "   ");
		}
		System.out.print("\n");
	}
}
