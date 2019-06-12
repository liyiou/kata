package StringCalculator;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Calculator {

	public static Object add(String string) throws Exception {
		
		// Parse the optional delimiter
		String delim = null;
		String[] in = null;
		
		if (string.startsWith("//")) {
			Matcher m = Pattern.compile("\\/\\/(.*)\n(.*)").matcher(string);
			m.find();
			delim = m.group(1);
			// Strip the enclosing "[]" in delimiter -- to reconcile with requirement #4 ...delimiter looks like this: “//[delimiter]\n[numbers…]” for example “//;\n1;2” ...
			if (delim.length() >=2 && delim.charAt(0) == '[' && delim.charAt(delim.length()-1) == ']') {
				delim = delim.substring(1, delim.length() - 1);  
			} 
			
			if (delim.isEmpty()) {
				throw new Exception("Empty delimiter is not allowed.");
			} else {
				// Use the entire string literal as the delimiter
				StringBuilder sb = new StringBuilder();
				for (char x : delim.toCharArray()) {
					sb.append('[');
					sb.append(x);
					sb.append(']');
				}
				delim = sb.toString();
				String y = m.group(2);
				in = y.split(delim);
			}
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
			long x = 0;
			try {
				x = Long.parseLong(s.trim());
			} catch (NumberFormatException e) {
				throw new Exception("Illegal number format detected in inputs: " + s.trim());
			}
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
