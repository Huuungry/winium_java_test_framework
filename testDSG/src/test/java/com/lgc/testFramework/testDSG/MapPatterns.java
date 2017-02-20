package com.lgc.testFramework.testDSG;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.sikuli.script.Pattern;

public interface MapPatterns {

	public default Map<String, Pattern> getPatterns(String path) {

	    File folder = new File(path);
	    File[] listOfFiles = folder.listFiles();

//Count number of files
	    int size1 = listOfFiles.length;

//Creating array of objects - class Pattern
		Pattern[] pattern = new Pattern[size1];

//Creating new Map for objects
		Map<String, Pattern> map = new HashMap<String, Pattern>();

//Filling map
		for(int i = 0; i < size1; i++) {
			String key = null;

//Creating Objects from the path
			if (i < listOfFiles.length) {
			pattern[i] = new Pattern(listOfFiles[i].getPath());
			if (pattern[i].getFilename() != null) {
				key = pattern[i].getFilename().replace(path+'\\', "").replace(".png", "");
							}
//Filling Map with objects
				map.put(key, pattern[i]);
			}
		}
		return map;
	}

}
