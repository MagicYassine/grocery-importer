package com.serverside.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @author yassine.mandaris
 *
 */
@SpringBootApplication
@AllArgsConstructor
@Slf4j
public class Application
{
	public static void main(final String[] args)
	{
		log.debug("Start running the application");
		SpringApplication.run(Application.class, args);
	}
}
