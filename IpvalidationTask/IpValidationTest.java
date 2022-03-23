package IpValidation;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.BeforeClass;
import org.junit.Test;

public class IpValidationTest {
	static IpValidation validator = null;

	@BeforeClass
	public static void beforeClass() {
		validator = new IpValidation();
	}

	@Test(expected = IpFormatInvalidException.class)
	public void verifyTest1() throws NumberFormatException, IpFormatInvalidException {
		validator.setIpValidationString("10.2.2");
		assertFalse(validator.validateIpAddress());

	}

	@Test(expected = NumberFormatException.class)
	public void verifyTest2() throws NumberFormatException, IpFormatInvalidException {
		validator.setIpValidationString("10.2.aa.aa");
		assertFalse(validator.validateIpAddress());

	}

	@Test()
	public void verifyTest3() throws NumberFormatException, IpFormatInvalidException {
		validator.setIpValidationString("1.1.1.1");
		assertTrue(validator.validateIpAddress());

	}

	@Test()
	public void verifyTest4() throws NumberFormatException, IpFormatInvalidException {
		validator.setIpValidationString("192.168.1.1");
		assertTrue(validator.validateIpAddress());

	}

	@Test()
	public void verifyTest5() throws NumberFormatException, IpFormatInvalidException {
		validator.setIpValidationString("10.0.0.1");
		assertTrue(validator.validateIpAddress());

	}

	@Test()
	public void verifyTest6() throws NumberFormatException, IpFormatInvalidException {
		validator.setIpValidationString("127.0.0.1");
		assertTrue(validator.validateIpAddress());

	}

	@Test()
	public void verifyTest7() throws NumberFormatException, IpFormatInvalidException {
		validator.setIpValidationString("0.0.0.0");
		assertFalse(validator.validateIpAddress());

	}

	@Test()
	public void verifyTest8() throws NumberFormatException, IpFormatInvalidException {
		validator.setIpValidationString("255.255.255.255");
		assertFalse(validator.validateIpAddress());

	}

	@Test()
	public void verifyTest9() throws NumberFormatException, IpFormatInvalidException {
		validator.setIpValidationString("1.1.1.0");
		assertFalse(validator.validateIpAddress());

	}

}
