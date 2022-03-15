package com.stablesort.util;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class Rand {
	public enum Option {
		positive,
		unique
	}
	
	static boolean optIncluded(Option opt, Option...opts) {
		for (Option o : opts) {
			if (o == opt) return true;
		}
		return false;
	}
	
	/**
	 * creates an array of specified length, populated with random numbers in the range from 0 to len.
	 * @param len
	 * @param opt 
	 * 	- if Option.positive is passed in, then the range is from 1 to len+1
	 * 	- if Option.unique is passed in, then the range is from 1 to (len+1)*2
	 * @return
	 */
	public static int[] getRandIntAr(int len, Option...opts) {
		Random r = new Random();
		int[] ar = new int[len];
		
		boolean posOnly = optIncluded(Option.positive, opts);
		boolean uniqueOnly = optIncluded(Option.unique, opts);
		
		Set<Integer> set = new HashSet<>();
		
		if (posOnly) {

			if (uniqueOnly) {
				for (int i = 0; i < ar.length; i++) {
					int next = r.nextInt(len*2) + 1;
					
					while (set.contains(next)) {
						next = r.nextInt(len) + 1;
					}

					ar[i] = next;
					set.add(next);
				}	

			} else {
				
				// don't care about uniquness
				for (int i = 0; i < ar.length; i++) {
					int next = r.nextInt(len) + 1;
					ar[i] = next;
				}					
			}
			
		} else {
			for (int i = 0; i < ar.length; i++) {
				ar[i] = r.nextInt(len); // cap the size just for easier readability	
			}
		}
		
		return ar;
	}

	/**
	 * creates a List of specified length, populated with random numbers in the range from 0 to len.
	 * @param len
	 * @return
	 */
	public static List<Integer> getRandIntList(int len) {
		Random r = new Random();
		List<Integer> ar = new ArrayList<>(len);
		
		for (int i = 0; i < len; i++) {
			ar.add(r.nextInt(len)); // cap the size just for easier readability
		}
		return ar;
	}
}
