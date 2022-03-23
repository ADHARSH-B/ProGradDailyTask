package IpValidation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class IpValidation {

	private String ipValidationString;


	public String getIpValidationString() {
		return ipValidationString;
	}
	public void setIpValidationString(String ipValidationString) {
		this.ipValidationString=ipValidationString;
	}

	public boolean validateIpAddress() throws NumberFormatException, IpFormatInvalidException{
		List<String> inputString=Arrays.asList(ipValidationString.split("\\."));
		if(inputString.size()<4 || inputString.size()>4)
			throw new IpFormatInvalidException("Input String is Not in proper format !! Ip Address should"
					+ "be in this Format  #.#.#.#   ");
		else if(Integer.parseInt(inputString.get(3))>=255 || Integer.parseInt(inputString.get(3))<=0)
			return false;
		return true; 
	}

}
