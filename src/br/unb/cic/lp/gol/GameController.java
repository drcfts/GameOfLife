package br.unb.cic.lp.gol;

import java.security.InvalidParameterException;

import br.unb.cic.lp.gol.GUI.GameGui;

/**
 * Classe que atua como um controlador do 
 * padrao MVC, separando os componentes da 
 * camada de apresentacao e model. 
 * 
 * @author rbonifacio
 */
public class GameController {

	private GameEngine engine;
	private GameView board;
	private Statistics statistics;
	private GameGui gui;
	
	
	public GameGui getGui() {
		return gui;
	}

	public void setGui(GameGui gui) {
		this.gui = gui;
	}

	public GameEngine getEngine() {
		return engine;
	}
	
	public void setEngine(GameEngine engine) {
		this.engine = engine;
	}
	
	public GameView getBoard() {
		return board;
	}
	
	public void setBoard(GameView board) {
		this.board = board;
	}
	
	public void setStatistics(Statistics statistics) {
		this.statistics = statistics;
	}
	
	public Statistics getStatistics() {
		return statistics;
	}

	public void start() {
		gui.telapadrao();
		board.update();
	}
	
	public void halt() {
		//oops, nao muito legal fazer sysout na classe Controller
		System.out.println("\n \n");
		statistics.display();
		System.exit(0);
	}
	
	public void makeCellAlive(int i, int j) {
		try {
			engine.makeCellAlive(i, j);
			board.update();
		}
		catch(InvalidParameterException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void nextGeneration(boolean loop) {
		engine.nextGeneration();
		
//		caso seja geracao automatica
		if(loop){
			board.updateLoop();
		}
//		se for apenas uma geracao
		else{
			board.update();
		}
	}
	
//	public void computeGenerations(int generations){
//
//		for(int i=0; i<generations; i++){
//			engine.nextGeneration();
//		}
//		board.update();
//	}
//	
	public void restoreGenerations(int generations){
		
		engine.restoreGenerations(generations);
		board.update();
		
	}
	
}
