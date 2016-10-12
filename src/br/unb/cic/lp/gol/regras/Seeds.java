package br.unb.cic.lp.gol.regras;

import br.unb.cic.lp.gol.EstrategiaDeDerivacao;
import br.unb.cic.lp.gol.GameEngine;

public class Seeds implements EstrategiaDeDerivacao{
	
	@Override
	public boolean shouldKeepAlive(int i, int j, GameEngine engine) {
		return false;
	}

	@Override
	public boolean shouldRevive(int i, int j, GameEngine engine) {
		return (!engine.cells[i][j].isAlive())
				&& (engine.numberOfNeighborhoodAliveCells(i, j) == 2);
	}
	@Override
	public String getName(){
		return "Seeds";
	}

}
