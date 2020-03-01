package com.serverside.application.json.util;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.serverside.application.pojo.GroceryItems;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @author yassine.mandaris
 *
 */
@AllArgsConstructor
@Slf4j
@Component
public class JsonMapperToGroceryItems
{
	private final ObjectMapper objectMapper;

	public GroceryItems jsonToGroceryItems(String responseJson)
	{
		log.debug("converting json response to GroceryItems Object");
		GroceryItems groceryItems = null;
		if (!StringUtils.isEmpty(responseJson))
		{
			try
			{
				objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
				groceryItems = objectMapper.readValue(responseJson, GroceryItems.class);
			}
			catch (Exception e)
			{
				log.error("Error converting JSON to GroceryItems response: {}", e.getMessage(), e);
			}
		}
		return groceryItems;
	}
}
