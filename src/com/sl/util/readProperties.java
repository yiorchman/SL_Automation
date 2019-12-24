package com.sl.util;

import java.io.FileInputStream;
//import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class readProperties {

	
	
	public String getData(String a) throws IOException {
		//this function get the property file that is required in a parameter.
		Properties prop=new Properties();
		FileInputStream property= new FileInputStream("etc//config.properties");
		prop.load(property);
		return prop.getProperty(a);
	}

}
