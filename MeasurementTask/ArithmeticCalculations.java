package Testing;

public class ArithmeticCalculations {

	public static double performOperationOnNumbers(double numberOne, double numberTwo, char operationType) {
		if (operationType == '+')
			return numberOne + numberTwo;
		else
			return Math.abs(numberOne - numberTwo);
	}
}
