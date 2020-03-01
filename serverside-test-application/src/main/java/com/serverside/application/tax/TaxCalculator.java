/**
 * 
 */
package com.serverside.application.tax;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.springframework.stereotype.Component;

/**
 * @author yassine.mandaris
 *
 */
@Component
public class TaxCalculator
{

	public BigDecimal calculateVatAmount(BigDecimal totalAfterVat, BigDecimal vatRate)
	{
		BigDecimal totalBeforeVat = calculateTotalBeforeVAT(totalAfterVat, vatRate);
		return totalAfterVat.subtract(totalBeforeVat);
	}
	
	public BigDecimal calculateTotalBeforeVAT(BigDecimal totalAfterVAT, BigDecimal vatRate)
	{
		BigDecimal hundred = new BigDecimal("100.00");
		BigDecimal divisor = vatRate.add(hundred).divide(hundred, 4, RoundingMode.HALF_UP);
		return totalAfterVAT.divide(divisor, 2, RoundingMode.HALF_UP);
	}
}
