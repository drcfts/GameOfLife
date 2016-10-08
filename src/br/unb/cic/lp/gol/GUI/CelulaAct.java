package br.unb.cic.lp.gol.GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import br.unb.cic.lp.gol.GameEngine;

/*
 *  Implementa action listener 
 */
public class CelulaAct extends JButton implements ActionListener {
	private boolean Alive; // booleano que diz se a celula está viva nao 
	//matriz[alt][larg]
	public int alt; 
	public int larg;
	
	private GameEngine engine;


	/*
	 * construtor do botao da celula
	 */
	public	CelulaAct(int alt , int larg,GameEngine eng){
		addActionListener(this);
		this.alt =alt;
		this.larg =larg;
		this.engine =eng;
		// falta botar as imagens referente a vivo e morto
	}
	/*
	 * Açoes da celula morrer e viver(criar)
	 * 
	 */
	
	public void Kill(){
	setAlive(false);
	}

	public void Live(){
	setAlive(true);
	}
	
	
	public void actionPerformed(ActionEvent arg0) {
		engine.makeCellAlive(alt,larg);
		
		if(Alive){
			//tornar life vivo 
			
		}else{
			// tornar life dead
		}
		
		
		
	}

	public void setAlive(boolean alive) {
		Alive = alive;
	}
	
	
	
}
