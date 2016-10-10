package br.unb.cic.lp.gol.GUI;

import br.unb.cic.lp.gol.Statistics;
import br.unb.cic.lp.gol.EstrategiaDeDerivacao;
import br.unb.cic.lp.gol.GameController;
import br.unb.cic.lp.gol.GameEngine;
/*
 * Bibliotecas do java swing
 */	

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class GameGui{
	
	private JFrame tela;	
	private JPanel matrizTabuleiro;
	private JPanel botoes;
	private CelulaAct celulas[][];
	private GameController controller;
	
	/*
	 * Constrotutor GameGUI que recebe um tipo de jogo (controler)  e inicia a tela
	 */
	public GameGui(GameController controller){ 
		this.controller=controller;
		iniciandoTela();
		preparaTabuleiro();
		telaBotoes();
		painelDeBotoes();
	}
	/*
	 * tela padrao , formato padrao que será feito
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
			parte do botao errado .... 
			System.out.println("Select one of the options: \n \n"); 
			System.out.println("[1] Make a cell alive");
			System.out.println("[2] Next generation");
			System.out.println("[3] Halt");
			System.out.println("[4] Generate generations automatically");
		
	 */
	private void telaBotoes(){
		botoes = new JPanel();
		tela.add(botoes,BorderLayout.EAST);
	}
	private void  botoesDeOpcoes(String op,final String opcode){
		JButton opcao = new JButton(op);
		opcao.addActionListener( new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent event) {
				// TODO Auto-generated method stub
				tela.dispose();
				controller.getBoard().parseOption(opcode);
				}
			
			
													});
	}
	private void painelDeBotoes(){
		botoesDeOpcoes("Make a cell alive","1");
		botoesDeOpcoes("Next Generation","2");
		botoesDeOpcoes("Halt","3");
		botoesDeOpcoes("Generate generations automatically","4");
	}
	
	
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

/*	falta as atualizaçoes da matriz tabulereiro... modificar com a Injeção dependência
 * 
 */
  public void atualizacao(){
	  GameEngine Eng =controller.getEngine(); 
	  	for(int i=0;i<Eng.getHeight();i++){
	  		for(int j=0;j<Eng.getWidth();j++){
	  				 	if(Eng.isCellAlive(i, j)){
	  					celulas[i][j].Live();
	  				 	}else{
	  				 	celulas[i][j].Kill();		
	  				         }
	  				
	  		}
	   	}
	  tela.setVisible(true);
	  
  }
}