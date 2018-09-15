package com.pushtechnology.connect.diffusion.sink;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import com.pushtechnology.connect.diffusion.config.DiffusionConfig;

public class DiffusionSinkConnectorTest {
	private DiffusionSinkConnector connector = new DiffusionSinkConnector();
	private Map<String, String> props = new HashMap<>();

	@Before
	public void setup() {
		props.put(DiffusionConfig.HOST, "localhost");
		props.put(DiffusionConfig.PORT, "8080");
		props.put(DiffusionConfig.USERNAME, "admin");
		props.put(DiffusionConfig.PASSWORD, "password");
		props.put(DiffusionConfig.DIFFUSION_DESTINATION, "topic");
	}

	@Test
	public void testTaskConfigs() {
		connector.start(props);
		
		List<Map<String, String>> taskConfigs = connector.taskConfigs(1);
		
		assertEquals(taskConfigs.size(), 1);
		assertEquals(taskConfigs.get(0), props);
	}
	
	@Test
	public void testTaskConfigsWithMoreThanOne() {
		connector.start(props);
		
		List<Map<String, String>> taskConfigs = connector.taskConfigs(123);
		
		assertEquals(taskConfigs.size(), 1);
		assertEquals(taskConfigs.get(0), props);
	}

	@Test
	public void testTaskClass() {
		assertEquals(DiffusionSinkTask.class, connector.taskClass());
	}
}
