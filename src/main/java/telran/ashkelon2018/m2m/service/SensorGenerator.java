package telran.ashkelon2018.m2m.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.integration.annotation.InboundChannelAdapter;
import org.springframework.integration.annotation.Poller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import telran.ashkelon2018.m2m.dto.Sensor;

@EnableBinding(Source.class)
// class is connected with interface Source, so we can use its methods
public class SensorGenerator {
	ObjectMapper mapper = new ObjectMapper();
	@Value("${minId}")
	// this information will be in configuration - no need to write in by hand in code
	int minId;
	@Value("${maxId}")
	int maxId;
	@Value("${minData}")
	int minData;
	@Value("${maxData}")
	int maxData;
	
	@InboundChannelAdapter(value = Source.OUTPUT, poller = @Poller(fixedDelay="100",maxMessagesPerPoll="1"))
	public String sendSensorData() throws JsonProcessingException {
		int id = getRandomId();
		long timestamp = System.currentTimeMillis();
		int data = getRandomData();
		Sensor sensor = new Sensor(id, timestamp, data);
		String sensorData = mapper.writeValueAsString(sensor);
		return sensorData;
	}

	private int getRandomData() {
		return getRandomNumber(minData, maxData);
	}

	private int getRandomId() {
		return getRandomNumber(minId, maxId);
	}

	private int getRandomNumber(int min, int max) {
		return (int) (min + Math.random() * (max + 1 - min));
	}

}
