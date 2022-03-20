package com.stablesort.util;

import java.util.Iterator;
import java.util.List;

public class NumUtils {
	public static int compareFloats(float f1, float f2, float delta) {
	    if (Math.abs(f1 - f2) < delta) {
	         return 0;
	    } else {
	        if (f1 < f2) {
	            return -1;
	        } else {
	            return 1;
	        }
	    }
	}

	/**
	 * Uses <code>0.00001f</code> for delta.
	 */
	public static int compareFloats(float f1, float f2)	{
	     return compareFloats(f1, f2, 0.00001f);
	}
	
	public static int[] toIntArray(List<Integer> integers){
	    int[] ret = new int[integers.size()];
	    Iterator<Integer> iterator = integers.iterator();
	    for (int i = 0; i < ret.length; i++)
	    {
	        ret[i] = iterator.next().intValue();
	    }
	    return ret;
	}
	
	/**
	 * https://leetcode.com/problems/power-of-two/solution/
	 * @param n
	 * @return
	 */
	public static boolean isPowerOfTwo(int n) {
		if (n == 0) return false;
		long x = (long) n;
		
		/*
		 * a power of two has only a single 1 bit set. For example:
		 * 2 = 0010
		 * 4 = 0100
		 * 8 = 1000
		 * 
		 * -x has all of the bits flipped, plus 1. That's because of 2's compliment.
		 * So x & (-x) to keep the rightmost 1-bit and to set all the others bits to zero. For the power of two it would result in x itself, 
		 * since a power of two contains just one 1-bit. Example for 4:	0100 & 1100 == 0100
		 */
		return (x & (-x)) == x;
	}
}
