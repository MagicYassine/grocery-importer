package com.serverside.application.util;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import com.serverside.application.util.GroceryStringUtil;

/**
 * @author yassine.mandaris
 *
 */
@ExtendWith(MockitoExtension.class)
public class CroceryStringUtilTest
{
	@InjectMocks
	private GroceryStringUtil croceryStringUtil;

	@Test
	public void test_isNullOrEmpty_StringNull()
	{
		assertTrue(croceryStringUtil.isNullOrEmpty(null));
	}

	@Test
	public void test_isNullOrEmpty_StringEmpty()
	{
		assertTrue(croceryStringUtil.isNullOrEmpty(""));
	}

	@Test
	public void test_isNullOrEmpty_Success()
	{
		assertFalse(croceryStringUtil.isNullOrEmpty("testNotEmptyOrNull"));
	}
}
