package br.unb.cic.lp.gol.GUI;


import br.unb.cic.lp.gol.GameController;
import br.unb.cic.lp.gol.GameEngine;

/*
 * Bibliotecas do java swing
 */	

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GameGui{
	
	private JFrame tela;	
	private JPanel matrizTabuleiro;
	private JPanel botoes;
	private CelulaAct celulas[][];
	private GameController controller;
	
	public GameGui(GameController controller){ 
		this.controller=controller;
		iniciandoTela();
		preparaTabuleiro();
		telaBotoes();
		painelDeBotoes();
	}
	
	public void telapadrao(){
		tela.setSize(720,640);
		tela.setResizable(false);
		tela.setVisible(true);
			
	}
	
	private void iniciandoTela(){
		
		tela = new JFrame("Game Of Life - Por: Davi,Henrique e Marcos");
		tela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		tela.setLayout(new BorderLayout());
		
	}

	private void telaBotoes(){
		botoes = new JPanel();
		tela.add(botoes,BorderLayout.NORTH);
	}
	
	private void  botoesDeOpcoes(String op,final String opcode){
		JButton opcao = new JButton(op);
		opcao.addActionListener( new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent event) {
				// TODO Auto-generated method stub
				tela.dispose();
				controller.getBoard().printOptions(opcode);
				}
			
													});
		botoes.add(opcao);
	}
	private void painelDeBotoes(){
		botoesDeOpcoes("Make a cell alive","1");
		botoesDeOpcoes("Next Generation","2");
		botoesDeOpcoes("Rules","3");
		botoesDeOpcoes("Auto Generate","4");
		botoesDeOpcoes("Restore generations","5");
		botoesDeOpcoes("Halt","99");
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
  
  public void atualizacaoInfinita(){
	 
	  botoes.setVisible(false); //Torna os botes do menu principal invisiveis
	  JPanel controle = new JPanel();
	  tela.add(controle,BorderLayout.SOUTH);
	  final JButton pausa = new JButton("Pause"); //Cria um botao que ira pausar o loop
	  
	  controle.add(pausa);
	  
	  // Definicao das acoes a serem executadas pelo timer
	  ActionListener actionListener = new ActionListener() {
			public void actionPerformed(ActionEvent evento) {
				atualizacao();
				controller.getBoard().printOptions("2");
			}    
	  };
	  final Timer timer = new Timer(500, actionListener);
	  timer.setInitialDelay(0);
	  timer.start();	
	  
	  //Definicao das acoes executadas pelo botao de pausa
	  pausa.addActionListener( new ActionListener(){
		  
			@Override
			public void actionPerformed(ActionEvent event) {
				timer.stop();
				pausa.setVisible(false);
				botoes.setVisible(true);
				
			}
	  
});
	  
	  
  }
  
  //Funcao halt a ser executada no GUI
  public void halt() {
	  JOptionPane.showMessageDialog(null,"Revived cells: " + controller.getStatistics().getRevivedCells() + 
			  "\nKilled cells: " + controller.getStatistics().getKilledCells());
	  System.exit(0);
  }
  

}