package Testing;

public class Main {

	public static void main(String[] args) {
		// System.out.println(ArchitectUseCase.checkEquality("HJ ","500 g"));
		try {
			//System.out.println(MeasurementsResultAnalyzer.checkEqualityOfTwoMeasurements("0 C", "32 F"));
			System.out.println(MeasurementsResultAnalyzer.checkExpressionIsValid("1 kg", "1000 g", "2000 g", '+'));
		} catch (InvalidExpressionException e) {
			System.out.println(e.getMessage());
		} catch (NumberFormatException e) {
			System.out.println("Only Numbers are allowed from [0-9]");
		}
		catch (InvalidUnitExcpetion e) {
			System.out.println(e.getMessage());
		} catch (InvalidOperationsException e) {
			System.out.println(e.getMessage());
		}

	}

}
