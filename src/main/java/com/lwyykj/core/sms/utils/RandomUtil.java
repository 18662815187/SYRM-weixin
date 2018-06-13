package com.lwyykj.core.sms.utils;
import java.util.Random;

public class RandomUtil {

	public static String randomFor6() {
		Random random = new Random();
		String result = "";
		for (int i = 0; i < 6; i++) {
			result += random.nextInt(10);
		}
//		System.out.print(result);
		return result;
	}
	
}
