package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomNumber {

	private static Random generator = new Random(1L);
	private static List<Integer> list = new ArrayList<Integer>();
	
	public static int newRandomNumber(int max) {
		int r = generator.nextInt(max);
		list.add(r);
		return r;
	}
	
	public static List<Integer> getRandomNumberList() {
		return list;
	}
	
	public static void resetRandomCounter() {
        list.clear();
        generator = new Random(1L);
    } 
}
