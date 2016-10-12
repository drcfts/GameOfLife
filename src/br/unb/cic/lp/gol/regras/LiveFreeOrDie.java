package br.unb.cic.lp.gol.regras;

import br.unb.cic.lp.gol.EstrategiaDeDerivacao;
import br.unb.cic.lp.gol.GameEngine;

public class LiveFreeOrDie implements EstrategiaDeDerivacao{

	@Override
	public String getName() {
		return "LiveFreeOrDie";
	}

	@Override
	public boolean shouldKeepAlive(int i, int j, GameEngine engine) {
		return engine.numberOfNeighborhoodAliveCells(i, j) == 0;
	}

	@Override
	public boolean shouldRevive(int i, int j, GameEngine engine) {
		return engine.numberOfNeighborhoodAliveCells(i, j) == 2;
	}

}
