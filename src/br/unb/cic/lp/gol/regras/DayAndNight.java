package br.unb.cic.lp.gol.regras;

import br.unb.cic.lp.gol.EstrategiaDeDerivacao;
import br.unb.cic.lp.gol.GameEngine;

public class DayAndNight implements EstrategiaDeDerivacao{
	@Override
	public boolean shouldKeepAlive(int i, int j, GameEngine engine) {
		return (engine.cells[i][j].isAlive())
				&& (engine.numberOfNeighborhoodAliveCells(i, j) >= 3 && engine.numberOfNeighborhoodAliveCells(i, j) != 5);
	}

	@Override
	public boolean shouldRevive(int i, int j, GameEngine engine) {
		return (!engine.cells[i][j].isAlive())
				&& (engine.numberOfNeighborhoodAliveCells(i, j) == 3 || engine.numberOfNeighborhoodAliveCells(i, j) > 5);
	}
	@Override
	public String getName(){
		return "DayAndNight";
	}
}
