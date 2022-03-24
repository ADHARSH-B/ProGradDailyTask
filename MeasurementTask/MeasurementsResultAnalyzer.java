package Testing;

public class MeasurementsResultAnalyzer {

	private static final String CENTIMETER = "CM";
	private static final String KILOMETER = "KM";
	private static final String METER = "M";
	private static final String KILOGRAM = "KG";
	private static final String GRAM = "G";
	private static final String FAHRENHEIT = "F";
	private static final String KELVIN = "K";
	private static final String CELSIUS = "C";


	public static boolean checkExpressionIsValid(String numberOne, String numberTwo, String result, char operationType)
			throws InvalidExpressionException, InvalidUnitExcpetion, InvalidOperationsException {
		String input1[] = numberOne.split("\\s+");
		String input2[] = numberTwo.split("\\s+");
		String input3[] = result.split("\\s+");

		double valueOne = Double.parseDouble(input1[0]);
		String unitOne = input1[1].toUpperCase();

		double valueTwo = Double.parseDouble(input2[0]);
		String unitTwo = input2[1].toUpperCase();

		double result_ = Double.parseDouble(input3[0]);
		String resultUnit = input3[1].toUpperCase();

		MeasurementsValidator.validateUnits(unitOne, unitTwo, resultUnit);
		MeasurementsValidator.validateNumbers(valueOne, valueTwo);
		MeasurementsValidator.validateOperationType(operationType);

		if (resultUnit.equals(KILOGRAM) || resultUnit.equals(GRAM)) {
			valueOne = new UnitConverter(valueOne, unitOne).convertToGram();
			valueTwo = new UnitConverter(valueTwo, unitTwo).convertToGram();
			if (resultUnit.equals(KILOGRAM))
				return (ArithmeticCalculations.performOperationOnNumbers(valueOne, valueTwo, operationType)) / 1000 == result_;
			else
				return ArithmeticCalculations.performOperationOnNumbers(valueOne, valueTwo, operationType) == result_;
		} else {

			valueOne = new UnitConverter(valueOne, unitOne).convertToMeter();
			valueTwo = new UnitConverter(valueTwo, unitTwo).convertToMeter();
			if (resultUnit.equals(METER))
				return (ArithmeticCalculations.performOperationOnNumbers(valueOne, valueTwo, operationType)) == result_;
			else if (resultUnit.equals(KILOMETER))
				return new UnitConverter(ArithmeticCalculations.performOperationOnNumbers(valueOne, valueTwo, operationType), "")
						.convertMeterToKiloMeter() == result_;
			else
				return new UnitConverter(ArithmeticCalculations.performOperationOnNumbers(valueOne, valueTwo, operationType), "")
						.convertmeterToCentimeter() == result_;
		}
	}

	public static boolean checkEqualityOfTwoMeasurements(String measurementOne, String measurementTwo)
			throws InvalidExpressionException, InvalidUnitExcpetion {
		String input1[] = measurementOne.split("\\s+");
		String input2[] = measurementTwo.split("\\s+");

		double valueOne = Double.parseDouble(input1[0]);
		String unitOne = input1[1].toUpperCase();
		double valueTwo = Double.parseDouble(input2[0]);
		String unitTwo = input2[1].toUpperCase();

		MeasurementsValidator.validateUnits(unitOne, unitTwo, null);
		MeasurementsValidator.validateNumbers(valueOne, valueTwo);

		if (unitOne.equals(CENTIMETER) || unitOne.equals(KILOMETER) || unitOne.equals(METER)) {
			valueOne = new UnitConverter(valueOne, unitOne).convertToMeter();
			valueTwo = new UnitConverter(valueTwo, unitTwo).convertToMeter();
		} else if (unitOne.equals(FAHRENHEIT) || unitOne.equals(KELVIN) || unitOne.equals(CELSIUS)) {
			valueOne = new UnitConverter(valueOne, unitOne).convertToCelsius();
			valueTwo = new UnitConverter(valueTwo, unitTwo).convertToCelsius();
		} else {
			valueOne = new UnitConverter(valueOne, unitOne).convertToGram();
			valueTwo = new UnitConverter(valueTwo, unitTwo).convertToGram();
		}

		return valueOne == valueTwo;
	}
}





















//As an architect, I want to be able to check if 1 cm is equal to 1 cm.
//As an architect, I want to be able to check if 1 m is equal to 100 cm, and 100 cm is equal to 0.001 km.
//As an architect, I want to be able to know that 1 m + 100 cm = 2 m, or 200 cm + 1 km = 100200 cm.
//As an architect, I want to be able to know that 1 m - 50 cm = 0.5 m, 2000 cm – 1 m = 1900 cm.
//As an architect I want to be able to check if 1 g is equal to 1 g, and 0.1 kg = 100 g, and to know that 10 g + 1 kg = 1010 g, and 1.5 kg - 500 g = 1 kg.
//As an architect, I want to be able to check if 0 Celsius is equal to 32 Fahrenheit, and 0 Kelvin is equal to -273 Celsius.
