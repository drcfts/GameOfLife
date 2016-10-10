package br.unb.cic.lp.gol.GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
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

	ImageIcon vivo,morto;
	/*
	 * construtor do botao da celula
	 */
	public	CelulaAct(int alt , int larg,GameEngine eng){
		addActionListener(this);
		this.alt =alt;
		this.larg =larg;
		this.engine =eng;
		vivo = new ImageIcon(this.getClass().getResource("imagens/verde.png")); 
		morto = new ImageIcon(this.getClass().getResource("imagens/vermelho.png")); 
		
	}
	/*
	 * Açoes da celula morrer e viver(criar)
	 * 
	 */
	
	public void Kill(){
	setAlive(false);
	setIcon(morto);
	}

	public void Live(){
	setAlive(true);
	setIcon(vivo);	
	}
	
	
	public void actionPerformed(ActionEvent event) {
		engine.makeCellAlive(alt,larg);
		
		if(Alive){
			setIcon(vivo);
			
			
		}else{
			
			setIcon(morto);
			// pintar vermelho tornar life dead
		}
		
		
		
	}

	public void setAlive(boolean alive) {
		Alive = alive;
	}
	
	
	
}
