package StringCalculator;

import java.util.ArrayList;
import java.util.List;

public class Calculator {

	public static Object add(String string) {
		
		String[] in = string.trim().split("[,\n]");
		
		List<Long> nums = new ArrayList<Long>();
		
		for (String s : in) {
			if (s.trim().length() == 0) {
				continue;
			}
			long x = Long.parseLong(s.trim());
			nums.add(x);
		}
		
		long ans = 0;
		for (long x : nums) {
			ans += x; 
		}
		
		return ans;
	}

}
