package StringCalculator;

import java.util.ArrayList;
import java.util.List;

public class Calculator {

	public static Object add(String string) throws Exception {
		
		// Parse the optional delimiter
		boolean delimDetected = false;
		char delim = 0;
		int idx = string.indexOf("\n");
		
		if (idx != -1) {
			String head = string.substring(0, idx);
			if (head.length() == 3 && head.charAt(0) == '/' && head.charAt(1) == '/') {
				delimDetected = true;
				delim = head.charAt(2);
			}
		}
		
		String[] in = null;
		
		if (delimDetected) {
			// Parse by customized delimiter
			in = string.substring(string.indexOf("\n")).trim().split(Character.toString(delim));
		} else {
			// Parse by default delimiters (comma or new line) 
			in = string.trim().split("[,\n]");
		}
		
		List<Long> nums = new ArrayList<Long>();
		List<Long> negs = new ArrayList<Long>();
		for (String s : in) {
			if (s.trim().length() == 0) {
				continue;
			}
			long x = Long.parseLong(s.trim());
			if (x < 0) {
				negs.add(x);
			} else {
				if (x <= 1000) {
					nums.add(x);
				}
			}
		}
		
		if (! negs.isEmpty()) {
			StringBuilder sb = new StringBuilder();
			for (long x : negs) {
				sb.append(" " + x);
			}
			throw new Exception("Negative not allowed :" + sb.toString());
		}
		
		long ans = 0;
		for (long x : nums) {
			ans += x; 
		}
		
		return ans;
	}

}
