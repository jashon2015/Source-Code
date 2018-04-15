package com.sorting.www;

import java.util.Arrays;

/**
 * 字典序算法
 * 寻找最近的换位数
 * 步骤：
 * 1. 从后向前查看逆序区域，找到逆序区域的前一位，也就是数字置换的边界
 * 2. 把逆序区域的前一位和逆序区域中刚刚大于它的数字交换位置
 * 3. 把原来的逆序区域转为顺序
 * 
 * 1. From backward to forward, look at the reversed-order region and find the 
 * previous one in the reversed-order region, which is the boundary of the numeric substitution.
 * 2. Put the number exchange position in the previous and reversed regions of the reversed region just above it
 * 3. Turn the original reversed region into sequence
 *
 * @author Jashon
 *
 */
public class Lexicographical {
	
	// 主流程，返回最近一个大于自身的相同数字组成的整数.
	public static int[] findNearestNumber(int[] numbers) {
		// 拷贝入参，避免直接修改入参
		int [] numbersCopy = Arrays.copyOf(numbers, numbers.length);
		// 1. 从后向前查看逆序区域，找到逆序区域的前一位，也就是数字置换的边界
		int index = findTransferPoint(numbersCopy);
		// 如果数字置换边界是 0，说明整个数组已经逆序，无法得到更大的相同数字组成的整数，返回自身
		if (index == 0) {
			return null;
		}
		// 2. 把逆序区域的前一位和逆序区域中刚刚大于它的数字交换位置
		exchangeHead(numbersCopy, index);
		// 3. 把原来的逆序区域转为顺序
		reverse(numbersCopy, index);
		
		return numbersCopy;
		
	}
	
	private static int findTransferPoint(int[] numbers) {
		for(int i = numbers.length-1; i>0; i--) {
			if(numbers[i] > numbers[i-1]) {
				return i;
			}
		}
		return 0;
	}

	private static int[] exchangeHead(int[] numbersCopy, int index) {
		int head = numbersCopy[index-1];
		
		for(int i = numbersCopy.length-1; i>0; i--) {
			if (head < numbersCopy[i]) {
				numbersCopy[index-1] = numbersCopy[i];
				numbersCopy[i] = head;
				break;
			}
		}
		return numbersCopy;
	}

	private static int[] reverse(int[] num, int index) {
		for (int i = index, j = num.length-1; i<j; i++,j--) {
			int temp = num[i];
			num[i] = num[j];
			num[j] = temp;
		}
		return num;
	}


	public static void main(String[] args) {
		int[] numbers = {1,2,3,4,5};
		for (int i=0; i<10; i++) {
			numbers = findNearestNumber(numbers);
			outputNumbers(numbers);
		}
	}

	// 输出数组
	private static void outputNumbers(int[] numbers) {
		for(int i : numbers) {
			System.out.print(i);
		}
		System.out.println();
	}

}
