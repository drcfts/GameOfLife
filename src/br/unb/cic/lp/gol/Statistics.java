package br.unb.cic.lp.gol;

/**
 * Essa tambem eh uma classe com baixa coesao, 
 * pois mustura caracteristicas de Model (as propriedades) 
 * com caracteristicas de view (metodo display())
 * 
 * Nao eh uma boa implementacao.
 * 
 * @author rodrigobonifacio
 */
public class Statistics {
	private int revivedCells;
	private int killedCells;
	
	public Statistics() {
		revivedCells = 0;
		killedCells = 0;
	}

	public int getRevivedCells() {
		return revivedCells;
	}
	public void setRevivedCells(int revivedCells){
		this.revivedCells = revivedCells;
		
	}

	public void recordRevive() {
		this.revivedCells++;
	}

	public int getKilledCells() {
		return killedCells;
	}

 public void setKilledCells(int killedCells){
	 this.killedCells = killedCells;
	 
 }
	public void recordKill() {
		this.killedCells++;
	}
// N�o � uma boa maneira !!	
	public void display() {
		System.out.println("=================================");
		System.out.println("           Statistics            ");
		System.out.println("=================================");
		System.out.println("Revived cells: " + revivedCells);
		System.out.println("Killed cells: " + killedCells);
		System.out.println("=================================");
	}
//
}
