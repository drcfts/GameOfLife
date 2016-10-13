package br.unb.cic.lp.gol;

import br.unb.cic.lp.gol.GUI.GameGui;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.FileSystemResource;

public class Main {

	public static void main(String args[]) {
		
		GameController controller = new GameController();
		Statistics statistics = new Statistics();
		GameEngine engine = new GameEngine(40, 40, statistics);	
		
		BeanFactory factory = new XmlBeanFactory(new FileSystemResource("spring.xml"));
		EstrategiaDeDerivacao Conway =(EstrategiaDeDerivacao)factory.getBean("Conway");
		
		/* estrategia inicial */
		engine.setStrategy(Conway);
		
		GameView board = new GameView(controller, engine);
		
		controller.setBoard(board);
		controller.setEngine(engine);
		controller.setStatistics(statistics);
		
		GameGui gui = new GameGui(controller);
		
		controller.setGui(gui);
		controller.start();
	}
}
