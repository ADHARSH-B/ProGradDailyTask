package Testing;

public class UnitConverter {
	private double value;
	private String unit;

	UnitConverter(double value, String unit) {
		this.value = value;
		this.unit = unit;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

	public double convertToMeter() {
		if (unit.equals("CM"))
			return value / 100;
		if (unit.equals("KM"))
			return value * 1000;
		else
			return value;

	}

	
	public double convertToCelsius() {
		if(unit.equals("F"))
		return (value - 32) * 5 / 9;
		else if(unit.equals("k"))
			return value - 273.15;
		else 
			return value;
	}

	public double convertCentimeterTokilometer() {
		return value / 100000;
	}

	public double convertKilometerToCentimeter() {
		return value * 100000;
	}

	public double convertmeterToCentimeter() {
		return value * 100;
	}

	public double convertMeterToKiloMeter() {
		return value / 1000;
	}

	public double convertToGram() {
		if (unit.equals("KG"))
			return value * 1000;
		return value;
	}

}
