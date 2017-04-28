package com.compraventa.entity;

import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.Properties;

public class Test {
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub		
		
		String properti_name = "messages_en.properties";		
		
		Test carga = new Test();
		carga.cargaFichero(properti_name);
		
	}
	
	public  void cargaFichero(String properti_name){
			
		InputStream input = null;
		Properties prop;
		
		input = getClass().getClassLoader().getResourceAsStream(properti_name);
		
		prop = new Properties();
		
		try {
			prop.load(input);
			Enumeration enu =    prop.keys();
			
			while(enu.hasMoreElements()){
				System.out.println(enu.nextElement().toString());
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
