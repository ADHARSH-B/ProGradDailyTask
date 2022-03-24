package Testing;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class MeasurementsValidator {
	static Set<String> supportedUnits = new HashSet<String>(Arrays.asList("CM", "M", "KM", "M", "G", "KG","K","C","F"));
	static Set<Character> supportedOperations = new HashSet<Character>(Arrays.asList('+', '-'));

	public static void validateUnits(String unitOne, String unitTwo, String unitThree)
			throws InvalidUnitExcpetion, InvalidExpressionException {
		if (!supportedUnits.contains(unitOne) || !supportedUnits.contains(unitTwo))
			throw new InvalidUnitExcpetion("Entered Unit is Not Supported !!");

		boolean check = unitThree == null;
		if (!check) {
			if (!supportedUnits.contains(unitThree))
				throw new InvalidUnitExcpetion("Entered Unit is Not Supported !!");
		}

	}

	public static void validateNumbers(double numberOne, double numberTwo) throws InvalidExpressionException {
		if (numberOne < 0 || numberTwo < 0)
			throw new InvalidExpressionException("Negative Numbers are not allowed !!");
	}

	public static void validateOperationType(char operationType) throws InvalidOperationsException {
		if (!supportedOperations.contains(operationType))
			throw new InvalidOperationsException(
					"Operation type is not supported  currently supported operation types are  '+,-' ");

	}

}
