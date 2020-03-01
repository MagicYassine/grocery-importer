/**
 * 
 */
package com.serverside.application.processor;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.google.gson.JsonObject;
import com.serverside.application.api.ApiProcesssor;
import com.serverside.application.factory.ApiProcessorFactory;

/**
 * @author yassine.mandaris
 *
 */
@ExtendWith(MockitoExtension.class)
public class GroceryProcessorTest
{
	@Mock
	private ApiProcessorFactory apiProcessorFactory;

	@InjectMocks
	private GroceryProcessor groceryProcessor;

	@Test
	public void test_LoadGrocery()
	{
		// Set up result
		JsonObject jsonFinalResult = new JsonObject();
		jsonFinalResult.addProperty("result", "testResult");
		jsonFinalResult.addProperty("total", "testTotal");

		// Set up mocking
		ApiProcesssor apiProcesssor = mock(ApiProcesssor.class);

		// Set up method's params
		String groceryCategoryToLoad = "fruits";

		// Set up stubbing
		when(apiProcessorFactory.createInstance(anyString())).thenReturn(apiProcesssor);
		when(apiProcesssor.process()).thenReturn(jsonFinalResult);

		// Call method
		jsonFinalResult = groceryProcessor.run(groceryCategoryToLoad);

		// Assert result
		assertNotNull(jsonFinalResult.get("result"));
		assertNotNull(jsonFinalResult.get("total"));
	}

	@Test
	public void test_LoadGrocery_Null_Processor()
	{
		// Set up result
		ApiProcesssor apiProcesssor = null;
		JsonObject jsonFinalResult = new JsonObject();

		// Set up method's params
		String groceryCategoryToLoad = "fruits";

		// Set up stubbing
		when(apiProcessorFactory.createInstance(anyString())).thenReturn(apiProcesssor);

		// Call method
		jsonFinalResult = groceryProcessor.run(groceryCategoryToLoad);

		// Assert result
		assertNull(jsonFinalResult);
	}
}
