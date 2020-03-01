package com.serverside.application.grocerysender;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.jsoup.select.Elements;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.serverside.application.builder.JsonResultBuilder;
import com.serverside.application.config.GroceryProperties;
import com.serverside.application.enums.GroceryEndpointType;
import com.serverside.application.parser.HTMLParser;

/**
 * @author yassine.mandaris
 *
 */
@ExtendWith(MockitoExtension.class)
public class GroceryRequestSenderTest
{
	@Mock
	private GroceryProperties properties;
	@Mock
	private JsonResultBuilder jsonBuilder;
	@Mock
	private HTMLParser HTMLParser;
	@InjectMocks
	private GroceryRequestSender groceryRequestSender;

	@Test
	public void testFetchGroceryFruitItems() throws IOException
	{
		// Set up Mocking
		Elements elements = mock(Elements.class);

		// Set up returned Stubbing values
		Map<GroceryEndpointType, String> endPointMap = new HashMap<>();
		endPointMap.put(GroceryEndpointType.FRUITS, "testEndPoint");
		
		JsonArray jsonArray = new JsonArray();
		JsonObject jsonFruitObject = new JsonObject();
		jsonFruitObject.addProperty("title", "Sainsbury's Strawberries 400g");
		jsonFruitObject.addProperty("kcal_per_100g", 33);
		jsonFruitObject.addProperty("unit_price", 1.75);
		jsonFruitObject.addProperty("description", "by Sainsbury's strawberries");
		jsonArray.add(jsonFruitObject);

		// Set up stubbing.
		when(properties.getEndpoints()).thenReturn(endPointMap);
		when(HTMLParser.getElementsByClass(anyString(), anyString())).thenReturn(elements);
		when(jsonBuilder.buildResultJsonArray(any(Elements.class))).thenReturn(jsonArray);

		// Call method and assert result.
		assertNotNull(groceryRequestSender.fetchGroceryFruitItems().get("result"));
	}

	@Test
	public void testFetchGroceryFruitItems_Exception() throws IOException
	{
		// Set up returned Stubbing values
		Map<GroceryEndpointType, String> endPointMap = new HashMap<>();
		endPointMap.put(GroceryEndpointType.FRUITS, "testEndPoint");
		
		// Set up returned Stubbing values
		JsonArray jsonArray = new JsonArray();
		JsonObject jsonFruitObject = new JsonObject();
		jsonFruitObject.addProperty("title",  "Sainsbury's Strawberries 400g");
		jsonFruitObject.addProperty("kcal_per_100g", 33);
		jsonFruitObject.addProperty("unit_price", 1.75);
		jsonFruitObject.addProperty("description", "by Sainsbury's strawberries");
		jsonArray.add(jsonFruitObject);
                               	
		
		// Set up stubbing.
		when(properties.getEndpoints()).thenReturn(endPointMap);
		doThrow(IOException.class).when(HTMLParser).getElementsByClass(anyString(), anyString());
		
		// Call method and assert result.
		assertNull(groceryRequestSender.fetchGroceryFruitItems().get("result"));
	}
}
