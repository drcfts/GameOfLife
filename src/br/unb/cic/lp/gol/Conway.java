package br.unb.cic.lp.gol;
import br.unb.cic.lp.gol.GameEngine;


public class Conway implements EstrategiaDeDerivacao{
		
	public Conway() {
		
	}

	@Override
	public boolean shouldKeepAlive(int i, int j, GameEngine engine) {
		return (engine.cells[i][j].isAlive())
				&& (engine.numberOfNeighborhoodAliveCells(i, j) == 2 || engine.numberOfNeighborhoodAliveCells(i, j) == 3);
	}

	@Override
	public boolean shouldRevive(int i, int j, GameEngine engine) {
		return (!engine.cells[i][j].isAlive())
				&& (engine.numberOfNeighborhoodAliveCells(i, j) == 3);
	}
	@Override
	public String getName(){
		return "Conway";
	}
	
}
