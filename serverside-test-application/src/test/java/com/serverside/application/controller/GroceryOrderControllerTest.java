/**
 * 
 */
package com.serverside.application.controller;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import javax.xml.parsers.ParserConfigurationException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.google.gson.JsonObject;
import com.serverside.application.builder.TotalBuilder;
import com.serverside.application.json.util.JsonMapperToGroceryItems;
import com.serverside.application.pojo.GroceryItems;
import com.serverside.application.pojo.Total;
import com.serverside.application.processor.GroceryProcessor;

/**
 * @author yassine.mandaris
 *
 */
@ExtendWith(MockitoExtension.class)
public class GroceryOrderControllerTest
{
	@Mock
	private GroceryProcessor groceryProcessor;
	@Mock
	private TotalBuilder totalBuilder;
	@Mock
	private JsonMapperToGroceryItems jsonUtil;
	@InjectMocks
	private GroceryOrderController groceryOrderController;

	@Test
	public void test_GetFruit() throws ParserConfigurationException
	{
		// Set up mocking
		GroceryItems items = mock(GroceryItems.class);
		Total total = mock(Total.class);

		// Set up method's params
		JsonObject jsonFruits = new JsonObject();
		jsonFruits.addProperty("result", "fruitResultTest");

		// Set up stubbing
		when(groceryProcessor.run(anyString())).thenReturn(jsonFruits);
		when(jsonUtil.jsonToGroceryItems(anyString())).thenReturn(items);
		when(totalBuilder.calculateTotal(any(GroceryItems.class))).thenReturn(total);

		// Call method and assert the result
		assertNotNull(groceryOrderController.getFruit());
	}
}
