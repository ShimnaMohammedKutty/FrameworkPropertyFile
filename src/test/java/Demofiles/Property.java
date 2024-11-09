package Demofiles;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class Property {

	public static void main(String[] args) throws IOException {

		//Loading  the property file

		Properties prop=new Properties();
		File propFile=new File(System.getProperty("user.dir")+"\\src\\test\\java\\Demofiles\\data.properties");
		FileReader fr=new FileReader(propFile);
		prop.load(fr);
		
		//Reading from the property file
		
		System.out.println(prop.getProperty("firstname"));
		System.out.println(prop.getProperty("lastname"));
		System.out.println(prop.getProperty("location"));
		System.out.println(prop.getProperty("experience"));
	
		
		
	}

}
