package br.unb.cic.lp.gol.memento;

import br.unb.cic.lp.gol.Cell;

class Originator {
	private Cell[][] state;
	private static Originator uniqueInstance;
	
	// Padrao Singleton
	private Originator(){
		
	}
	
	public static Originator Instance(){
		if(uniqueInstance == null){
			uniqueInstance = new Originator();
		}
		return uniqueInstance;
	}
	public Cell[][] getState() {
		return state;
	}

	public void setState(Cell[][] state) {
		this.state = state;
	}
	
	public Memento saveToMemento(){
		return new Memento(state);
	}
	
	public void restoreFromMemento(Memento memento){
		state = memento.getSavedState();
	}
	
	public static class Memento {
		
		private final Cell[][] state; 
		
		private Memento (Cell[][] stateToSave){
			state = stateToSave;
		}
		
		private Cell[][] getSavedState(){
			return state;
		}
		
	}
}
