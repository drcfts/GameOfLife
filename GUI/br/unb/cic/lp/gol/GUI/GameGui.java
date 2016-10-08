package br.unb.cic.lp.gol.GUI;

//import br.unb.cic.lp.gol.EstrategiaDeDerivacao;
import br.unb.cic.lp.gol.GameController;
import br.unb.cic.lp.gol.GameEngine;
/*
 * Bibliotecas do java swing
 */	
import java.awt.BorderLayout;
import java.awt.GridLayout;


import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class GameGui{
	
	private JFrame tela;	
	private JPanel matrizTabuleiro;
	private JButton botoes;
	private CelulaAct celulas[][];
	private GameController controller;
	
	/*
	 * Constrotutor GameGUI que recebe um tipo de jogo (controler)  e inicia a tela
	 */
	public GameGui(GameController controller){ 
		this.controller=controller;
		iniciandoTela();
		preparaTabuleiro();
		
	}
	/*
	 * tela padrao , formato padrao que ser� feito
	 */
	
	public void telapadrao(){
		tela.setSize(500,500);
		tela.setResizable(false);
		tela.setVisible(true);
			
	}
	/*
	 * Cria a tela "Game of Life" e default close para fechar
	 */
	private void iniciandoTela(){
		tela = new JFrame("Game Of Life - Por :Davi,Henrique e Marcos");
		tela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	/*
	 * parte do botao errado .... 
	 */
	
	
	private void preparaTabuleiro(){
		matrizTabuleiro = new JPanel();
		int altura  = controller.getEngine().getHeight(); // buscando altura dado pelo controller 
		int largura = controller.getEngine().getWidth(); // buscando largura .... '''''
		
		matrizTabuleiro.setLayout(new GridLayout(altura,largura));  // Faz um novo layout com o tamanho da altura e largura
		celulas = new CelulaAct[altura][largura];// instancia uma celulas referente CellButton
	
		for(int i = 0;i<altura;i++){
			for(int j = 0;j<largura;j++){
					celulas[i][j]= new 	CelulaAct(i,j,controller.getEngine());//celulas[i][j] -> busca no CellButton referente ao i e j e ao controller engine
					matrizTabuleiro.add(celulas[i][j]);//adiciona na matrizTab a celula
		}
	
	}
		tela.add(matrizTabuleiro,BorderLayout.CENTER); // poe no centro da tela a matrizTab
	}
}