package com.serverside.application.config;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import javax.xml.parsers.ParserConfigurationException;

import org.junit.jupiter.api.Test;

import com.serverside.application.config.Config;

/**
 * @author yassine.mandaris
 *
 */
class ConfigTest
{
	private final Config config = new Config();
	
	@Test
	public void testObjectMapper() throws ParserConfigurationException
	{
		assertNotNull(config.objectMapper());
	}
}
