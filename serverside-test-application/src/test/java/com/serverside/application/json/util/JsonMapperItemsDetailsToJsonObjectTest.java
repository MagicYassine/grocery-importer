/**
 * 
 */
package com.serverside.application.json.util;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.google.gson.JsonObject;
import com.serverside.application.pojo.GroceryItemDetails;
import com.serverside.application.util.GroceryStringUtil;

/**
 * @author yassine.mandaris
 *
 */
@ExtendWith(MockitoExtension.class)
public class JsonMapperItemsDetailsToJsonObjectTest
{
	@Mock
	private GroceryStringUtil stringUtil;
	@InjectMocks
	private JsonMapperItemsDetailsToJsonObject mapper;

	@Test
	public void test_MapItemDetailsToJsonObject()
	{
		// Set up method's params
		GroceryItemDetails itemDetails = new GroceryItemDetails();
		itemDetails.setDescription("descriptionTest");
		itemDetails.setTitle("testTitle");
		itemDetails.setKcal("30");
		itemDetails.setUnitPrice("10.00");

		// Set up stubbing
		when(stringUtil.isNullOrEmpty(anyString())).thenReturn(false);

		// Call method
		JsonObject jsonObject = mapper.mapItemDetailsToJsonObject(itemDetails);

		// Assert result
		assertNotNull(jsonObject.getAsJsonPrimitive("title"));
		assertNotNull(jsonObject.get("kcal_per_100g"));
		assertNotNull(jsonObject.get("description"));
		assertNotNull(jsonObject.get("unit_price"));
	}

	@Test
	public void test_MapItemDetailsToJsonObject_EmptyKcal()
	{
		// Set up method's params
		GroceryItemDetails itemDetails = new GroceryItemDetails();
		itemDetails.setDescription("descriptionTest");
		itemDetails.setTitle("testTitle");
		itemDetails.setKcal("");
		itemDetails.setUnitPrice("10.00");

		// Set up stubbing
		when(stringUtil.isNullOrEmpty(anyString())).thenReturn(true);

		// Call method
		JsonObject jsonObject = mapper.mapItemDetailsToJsonObject(itemDetails);

		// Assert result
		assertNotNull(jsonObject.getAsJsonPrimitive("title"));
		assertNull(jsonObject.get("kcal_per_100g"));
		assertNotNull(jsonObject.get("description"));
		assertNotNull(jsonObject.get("unit_price"));
	}
}
