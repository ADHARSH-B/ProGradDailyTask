package Testing;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class MeasurementResultAnalyzerTest {

	@Test
	public void testMeasurementExpressionIsValid()
			throws InvalidExpressionException, InvalidUnitExcpetion, InvalidOperationsException {
		assertTrue(MeasurementsResultAnalyzer.checkExpressionIsValid("1 kg", "1000 g", "2000 g", '+'));
		assertTrue(MeasurementsResultAnalyzer.checkExpressionIsValid("1 m", "100 cm", "2 m", '+'));
		assertFalse(MeasurementsResultAnalyzer.checkExpressionIsValid("200 cm", "1 km", "10200 cm", '+'));
		assertTrue(MeasurementsResultAnalyzer.checkExpressionIsValid("1 m", "50 cm", "0.5 m", '-'));
		assertFalse(MeasurementsResultAnalyzer.checkExpressionIsValid("2000"
				+ " cm", "1 m", "190 cm", '-'));
		assertTrue(MeasurementsResultAnalyzer.checkExpressionIsValid("10 g", "1 kg", "1010 g", '+'));
		assertTrue(MeasurementsResultAnalyzer.checkExpressionIsValid("1.5 kg", "500 g", "1 kg", '-'));
	}

	@Test
	public void testEqualityOfTwoMeasurements() throws InvalidExpressionException, InvalidUnitExcpetion {
		assertTrue(MeasurementsResultAnalyzer.checkEqualityOfTwoMeasurements("1 cm", "1 cm"));
		assertTrue(MeasurementsResultAnalyzer.checkEqualityOfTwoMeasurements("1 m", "100 cm"));
		assertTrue(MeasurementsResultAnalyzer.checkEqualityOfTwoMeasurements("0.001 km", "100 cm"));
		assertTrue(MeasurementsResultAnalyzer.checkEqualityOfTwoMeasurements("0 C","32 F"));

	}

//	@Test(expected = InvalidExpressionException.class)
//	public void ExcpectedInvalidExpression()
//			throws InvalidExpressionException, InvalidUnitExcpetion, InvalidOperationsException {
//		MeasurementsResultAnalyzer.checkExpressionIsValid("1 kg", "1000 cm", "2000 g", '+');
//	}

	@Test(expected = InvalidOperationsException.class)
	public void ExcpectedInvalidOperationException()
			throws InvalidExpressionException, InvalidUnitExcpetion, InvalidOperationsException {
		MeasurementsResultAnalyzer.checkExpressionIsValid("1 kg", "1000 g", "2000 g", '/');
	}

	@Test(expected = InvalidUnitExcpetion.class)
	public void ExcpectedInvalidUnitException()
			throws InvalidExpressionException, InvalidUnitExcpetion, InvalidOperationsException {
		MeasurementsResultAnalyzer.checkExpressionIsValid("1 lg", "1000 g", "2000 g", '/');

	}

}




//1.5 kg - 500 g = 1 kg.
		// 200 cm + 1 km = 100200 cm.
		// 1 m - 50 cm = 0.5 m
//1 cm is equal to 1 cm.
//	 1 m is equal to 100 cm, and 100 cm is equal to 0.001 km.
		// 2000 cm – 1 m = 1900 cm.
		// 10 g + 1 kg = 1010 g