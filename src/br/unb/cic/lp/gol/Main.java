package br.unb.cic.lp.gol;

import java.util.Scanner;


public class Main {

	public static void main(String args[]) {
		GameController controller = new GameController();
		Scanner scan = new Scanner(System.in);
		Statistics statistics = new Statistics();
		GameEngine engine = new GameEngine(10, 10, statistics);	
		int regra;
		
		GameView board = new GameView(controller, engine);
		int i = 1;
		System.out.println("Selcione a regra a ser utilizada: ");
		System.out.println("[" + i++ + "] Conway");
		System.out.println("[" + i++ + "] HighLife");
		regra = scan.nextInt();
		
		
		switch(regra){
			case 1: 
				engine.setStrategy(new Conway());
				System.out.println("Regra a ser utilizada: Conway");
				break;
			case 2: 
				engine.setStrategy(new HighLife());
				System.out.println("Regra a ser utilizada: HighLife");
				break;
			default: 
				engine.setStrategy(new Conway());
		}
		
		controller.setBoard(board);
		controller.setEngine(engine);
		controller.setStatistics(statistics);
		
		controller.start();
	}
}
