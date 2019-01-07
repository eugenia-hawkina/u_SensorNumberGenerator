package telran.ashkelon2018;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class SensorNumberGeneratorApplication {

	private static final long timeout = 60000;
	
	public static void main(String[] args) {
		ConfigurableApplicationContext contex = 
				SpringApplication.run(SensorNumberGeneratorApplication.class, args);
		try {
			Thread.sleep(timeout);
		} catch(InterruptedException e) {
			e.printStackTrace();
		} finally {
			contex.close();
		}
	}

}

