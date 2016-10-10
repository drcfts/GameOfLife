package br.unb.cic.lp.gol.memento;

import java.util.List;

import br.unb.cic.lp.gol.Cell;
import br.unb.cic.lp.gol.memento.Originator.Memento;

import java.util.ArrayList;
import java.security.InvalidParameterException;

/* Acessa apenas o Originator, n possui
 * acesso total ao Memento. Serve como "porta"
 * a classes de outros pacotes que precisam acessar
 * o Memento.
 */

public class Caretaker {
	
	List<Originator.Memento> savedStates;
	Originator originator;
	
	public Caretaker(){
		originator = Originator.Instance();
		savedStates = new ArrayList<Originator.Memento>();
	}
	
	
	public void addState(Cell[][] table){
		originator.setState(table);
		savedStates.add(originator.saveToMemento());
		
	}
	
	public Cell[][] restore(int generations) throws InvalidParameterException{
		int i = 0;
		int cont;
		
		
		for (i=0; i<generations; i++){
			cont = savedStates.size() - 1;
			if(savedStates.size()<=0){
				throw new InvalidParameterException("Restored back to a state that doesn't exist! " );
			}
			originator.restoreFromMemento(savedStates.get(cont));
			savedStates.remove(cont);
		}
		
		return originator.getState();
			
	}
	
}

