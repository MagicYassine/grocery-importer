/**
 * 
 */
package com.serverside.application.tax;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 * @author yassine.mandaris
 *
 */
@ExtendWith(MockitoExtension.class)
public class TaxCalculatorTest
{
	@InjectMocks
	private TaxCalculator taxCalculator;

	@Test
	public void test_CalculateVatAmount()
	{
		// Set up method's params
		BigDecimal vatRate = new BigDecimal(20.00);
		BigDecimal totalAfterVat = new BigDecimal(100.00);
		
		// Set up Expected result
		BigDecimal expectedVatResul = new BigDecimal(16.67).divide(new BigDecimal(1), 2, RoundingMode.HALF_UP);

		//Call the method and assert the result
		assertEquals(expectedVatResul, taxCalculator.calculateVatAmount(totalAfterVat, vatRate));
	}
	
	@Test
	public void test_CalculateTotalBeforeVAT()
	{
		// Set up method's params
		BigDecimal vatRate = new BigDecimal(20.00);
		BigDecimal totalAfterVat = new BigDecimal(100.00);
		
		// Set up Expected result
		BigDecimal expectedVatResul = new BigDecimal(83.33).divide(new BigDecimal(1), 2, RoundingMode.HALF_UP);

		//Call the method and assert the result
		assertEquals(expectedVatResul, taxCalculator.calculateTotalBeforeVAT(totalAfterVat, vatRate));
	}
}
