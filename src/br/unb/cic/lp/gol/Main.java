package br.unb.cic.lp.gol;

import java.util.Scanner;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.FileSystemResource;

public class Main {

	public static void main(String args[]) {
		GameController controller = new GameController();
		Scanner scan = new Scanner(System.in);
		Statistics statistics = new Statistics();
		GameEngine engine = new GameEngine(10, 10, statistics);	
		
		BeanFactory factory = new XmlBeanFactory(new FileSystemResource("spring.xml"));
		EstrategiaDeDerivacao HighLife =(EstrategiaDeDerivacao)factory.getBean("HighLife");
		engine.setStrategy(HighLife);
		
		GameView board = new GameView(controller, engine);
		
		controller.setBoard(board);
		controller.setEngine(engine);
		controller.setStatistics(statistics);
		
		controller.start();
	}
}
