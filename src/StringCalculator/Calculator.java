package StringCalculator;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Calculator {
	
	public static boolean isSingleCharDelim(List<String> in) {
		for (String x : in) {
			if (x.length() > 1) {
				return false;
			}
		}
		return true;
	}
	
	public static String stripBracket(String in) {
		if (in.charAt(0) != '[' || in.charAt(in.length() - 1) != ']') {
			return in;
		} else {
			return in.substring(1, in.length() - 1);
		}
	}
	
	public static List<String> parseDelimiters(String in) {
		List<String> ans = new ArrayList<String>();
		
		if (in.indexOf("[") == -1) {
			// Backward compatible with #4 string will contain a separate line that looks like this: “//[delimiter]\n[numbers…]” for example “//;\n1;2”
			ans.add(in);
			return ans;
		}
		
		String[] xx = in.split("]");
		
		for (String x : xx) {
			if (x.length() > 1 && x.charAt(0) == '[') {
				ans.add(x.substring(1));
			}
		}
		
		return ans;
	}
	

	public static Object add(String string) throws Exception {

		String delim = null;
		String[] in = null;
		
		// Define regex for the optional delimiter 
		Matcher m = Pattern.compile("\\/\\/(.*)\n(.*)").matcher(string);
		if (m.find()) {
			// Parse the optional delimiter
			
			String x = m.group(1);
			String y = m.group(2);
			if (x.isEmpty() || stripBracket(x).isEmpty()) {
				throw new Exception("Empty delimiter is not allowed.");
			}
			
			List<String> delims = parseDelimiters(x);
		
			StringBuilder sb = new StringBuilder();
			if (delims.size() == 1) {
				// For #7 Delimiters can be of any length with the following format: “//[delimiter]\n”
				// Use the entire string literal as the delimiter 
				for (char z : delims.get(0).toCharArray()) {
					sb.append('[');
					sb.append(z);
					sb.append(']');
				}
				delim = sb.toString();
				in = y.split(delim);
				
			} else if (isSingleCharDelim(delims)) {
				// For #8 Allow multiple delimiters like this: “//[delim1][delim2]\n”
				sb.append("[");
				for (String z : delims) {
					sb.append(z);
				}
				sb.append("]");
				delim = sb.toString();
				in = y.split(delim);
			
			} else {
				// For #9 handle multiple delimiters with length longer than one char
				// e.g. "//[***][%%&]\n1***2%%&3"
				boolean first = true;
				for (String z : delims) {
					if (first) {
						first = false;
					} else {
						sb.append("|");
					}
					sb.append(z);
				}
				delim = sb.toString();
				StringTokenizer st = new StringTokenizer(y, delim);
				List<String> xx = new ArrayList<String>();
				while(st.hasMoreTokens()) {
					xx.add(st.nextToken());
				}
				in = xx.toArray(new String[xx.size()]);
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
