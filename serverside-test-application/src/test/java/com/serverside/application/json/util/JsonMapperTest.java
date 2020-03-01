package com.serverside.application.json.util;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.serverside.application.json.util.JsonMapperToGroceryItems;
import com.serverside.application.pojo.GroceryItems;

/**
 * @author yassine.mandaris
 *
 */
@ExtendWith(MockitoExtension.class)
class JsonMapperTest
{
	@Mock
	private ObjectMapper objectMapper;
	@InjectMocks
	private JsonMapperToGroceryItems jsonMapper;

	@Test
	public void testJsonToGroceryItems() throws JsonProcessingException
	{
		String json = "testJson";

		GroceryItems groceryItems = mock(GroceryItems.class);
		when(objectMapper.readValue(json,  GroceryItems.class)).thenReturn(groceryItems);
		assertNotNull(jsonMapper.jsonToGroceryItems(json));
	}
	
	@Test
	public void testJsonToGroceryItems_Null_Response() throws JsonProcessingException
	{
		String json = null;

		assertNull(jsonMapper.jsonToGroceryItems(json));
	}
	
	@Test
	public void testJsonToGroceryItems_Exception() throws JsonProcessingException
	{
		String json = "testJson";

		when(objectMapper.readValue(json,  GroceryItems.class)).thenThrow(JsonParseException.class);
		assertNull(jsonMapper.jsonToGroceryItems(json));
	}
}
