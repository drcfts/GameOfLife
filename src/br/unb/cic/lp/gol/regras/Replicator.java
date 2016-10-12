package br.unb.cic.lp.gol.regras;

import br.unb.cic.lp.gol.EstrategiaDeDerivacao;
import br.unb.cic.lp.gol.GameEngine;

public class Replicator implements EstrategiaDeDerivacao{
	
	/* mantem viva ou revive se numero de vizinhos for impar */
	@Override
	public boolean shouldKeepAlive(int i, int j, GameEngine engine) {
		return (engine.cells[i][j].isAlive())
				&& ((engine.numberOfNeighborhoodAliveCells(i, j)%2)!=0);
	}

	@Override
	public boolean shouldRevive(int i, int j, GameEngine engine) {
		return (!engine.cells[i][j].isAlive())
				&& ((engine.numberOfNeighborhoodAliveCells(i, j)%2)!=0);
	}
	@Override
	public String getName(){
		return "Replicator";
	}
}
