package com.serverside.application.config;

import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;

import com.serverside.application.enums.GroceryEndpointType;

import lombok.Getter;
import lombok.Setter;

/**
 * @author yassine.mandaris
 *
 */
@Getter
@Setter
@ConfigurationProperties("servside.test")
@Validated
@Configuration
public class GroceryProperties
{
	private Map<GroceryEndpointType, String> endpoints;
}
