package br.unb.cic.lp.gol;


public interface EstrategiaDeDerivacao {
	
	public String getName();
	/*
	 * Devolver o nome da regra de derivacao 
	 */
	
	public boolean shouldKeepAlive(int i, int j, GameEngine engine);
	/* Verificar se uma celula na linha i, coluna j deve ser mantida
	 * viva
	 */
	
	public boolean shouldRevive(int i, int j, GameEngine engine);
	/* Verifica se uma celula na linha i, coluna j deve ser 
	 * ressuscitada
	 */
	
}
