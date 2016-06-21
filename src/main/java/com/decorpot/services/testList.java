package com.decorpot.services;

import com.decorpot.utils.DecorpotConstants;

public class testList {

	public static void main(String[] args) {

		DecorpotConstants.wardrobes.subList(0, DecorpotConstants.wardrobes.indexOf("6-door") + 1).forEach(s -> System.out.println(s));
	}

}
