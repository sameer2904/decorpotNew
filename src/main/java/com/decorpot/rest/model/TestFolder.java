package com.decorpot.rest.model;

import java.io.File;

public class TestFolder {

	public static void main(String[] args) {
		
		File folder = new File("/Users/sameer/Downloads/Senorita1/aaa1");
		
		File [] files = folder.listFiles();
		for(File f : files) {
			System.out.println(f.getName());
		}
	}

}
