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
	private static final int HALT = 4; 
	private static final int N_GENERATIONS = 5;
	private static final int RESTORE_GENERATIONS = 6;

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
		printFirstRow();
		printLine();
		for (int i = 0; i < engine.getHeight(); i++) {
			for (int j = 0; j < engine.getWidth(); j++) {
				System.out.print(engine.isCellAlive(i, j) ? ALIVE_CELL : DEAD_CELL);
			}
			System.out.println("   " + i);
			printLine();
		}
		printOptions();
	}

	private void printOptions() {
		Scanner s = new Scanner(System.in);
		int option;
		System.out.println("\n \n");
		
		BeanFactory factory = new XmlBeanFactory(new FileSystemResource("spring.xml"));
		
		do {
			System.out.println("Select one of the options: \n \n"); 
			System.out.println("[1] Make a cell alive");
			System.out.println("[2] Next generation");
			System.out.println("[3] Regras");
			System.out.println("[4] Halt");
			System.out.println("[5] Generate generations automatically");
			System.out.println("[6] Restore generations");
		
			System.out.print("\n \n Option: ");
			
			option = parseOption(s.nextLine());
		}while(option == 0);
		
		switch(option) {
			case MAKE_CELL_ALIVE : makeCellAlive(); break;
			case NEXT_GENERATION : nextGeneration(); break;
			case REGRAS :
				
				List<String> string = new ArrayList<String>();
				
				ApplicationContext xml = new FileSystemXmlApplicationContext("/home/henrique/Documentos/GoL/src/br/unb/cic/lp/gol/spring.xml");
				ListaDeRegras lista = (ListaDeRegras) xml.getBean("listaderegras");
				
				for(EstrategiaDeDerivacao regrax: lista.getListaderegras()){ 
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
		int i, j = 0;
		Scanner s = new Scanner(System.in);
		
		do {
			System.out.print("\n Inform the row number (0 - " + engine.getHeight() + "): " );
			
			i = s.nextInt();
			
			System.out.print("\n Inform the column number (0 - " + engine.getWidth() + "): " );
			
			j = s.nextInt();
		}while(!validPosition(i,j));
		
		controller.makeCellAlive(i, j);
		s.close();
	}
	
	private void nextGeneration() {
		controller.nextGeneration();
	}
	
	private void halt() {
		controller.halt();
	}
	
	private void computeGenerations(){
		int generations;
		Scanner s = new Scanner(System.in);

		System.out.println("How many generations to be generated automatically? ");
		generations = Integer.parseInt(s.nextLine());
		System.out.println("Computed " + generations + " generations.");
		
		controller.computeGenerations(generations);
		
		s.close();
	}
	
	private void restoreGenerations(){
		int generations;
		Scanner s = new Scanner(System.in);

		System.out.println("How many generations to be reverted? ");
		generations = Integer.parseInt(s.nextLine());
		System.out.println("Restoring " + generations + " generations.");
		controller.restoreGenerations(generations);
		
		s.close();
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
		else if (option.equals("3")) {
			return HALT;
		}
		else if(option.equals("4")){
			return N_GENERATIONS;
		}
		else if(option.equals("5")){
			return RESTORE_GENERATIONS;
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
