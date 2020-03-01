package com.serverside.application.factory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.serverside.application.api.ApiProcesssor;
import com.serverside.application.api.impl.ApiProcessorFruitImpl;
import com.serverside.application.factory.ApiProcessorFactory;
import com.serverside.application.grocerysender.GroceryRequestSender;

/**
 * @author yassine.mandaris
 *
 */
@ExtendWith(MockitoExtension.class)
public class ApiProcessorFactoryTest
{
	@Mock
	private List<ApiProcesssor> apiProcessors;
	@Mock
	private GroceryRequestSender groceryRequestSender;
	@InjectMocks
	private ApiProcessorFactory apiProcessorFactory;

	@Test
	public void testCreateInstance()
	{
		List<ApiProcesssor> apiProcessor = Stream.of(new ApiProcessorFruitImpl(groceryRequestSender))
						.collect(Collectors.toList());
		when(apiProcessors.spliterator()).thenReturn(apiProcessor.spliterator());
		when(apiProcessors.stream()).thenCallRealMethod();
		ApiProcesssor api = apiProcessorFactory.createInstance("fruits");
		assertNotNull(api);
		assertEquals(ApiProcessorFruitImpl.class, api.getClass());
	}

	@Test
	public void testGetInstance_Null()
	{
		List<ApiProcesssor> apiProcessor = Stream.of(new ApiProcessorFruitImpl(groceryRequestSender))
						.collect(Collectors.toList());
		when(apiProcessors.spliterator()).thenReturn(apiProcessor.spliterator());
		when(apiProcessors.stream()).thenCallRealMethod();
		ApiProcesssor api = apiProcessorFactory.createInstance("other");
		assertNull(api);
	}
}
