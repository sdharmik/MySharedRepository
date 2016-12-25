package com.core.rough;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class GF {
	By loc;
	public By getLocator(String locType, String locValue){
		
		switch (locType) {
		case "id":
			loc = By.id(locValue);
			System.out.println(loc);
			break;
		}

		return loc;
	}

	public static void main(String[] args) {
		WebDriver obj = new FirefoxDriver();
		obj.get("http://fb.com");
		GF obj1 = new GF();
		
		By loc = obj1.getLocator("id", "email");
		
		obj.findElement(loc).sendKeys("test");

	}

}
