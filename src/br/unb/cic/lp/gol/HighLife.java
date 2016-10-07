package br.unb.cic.lp.gol;
x	
public class HighLife extends Conway {

	@Override
	public boolean shouldRevive(int i, int j, GameEngine engine) {
		return (!engine.cells[i][j].isAlive())
				&& (engine.numberOfNeighborhoodAliveCells(i, j) == 3 || engine.numberOfNeighborhoodAliveCells(i, j) == 6);
	}
	@Override
	public string getName(){
		return "HighLife";
	}
	
}
