package com.efacil.connector;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.phantomjs.PhantomJSDriver;

/**
 * DOMConnector Class.
 * A test class to access to a website DOM.
 * @version 1.0 04 Mar 2017
 * @author Moatez Ben Abdallah
 **/
public class DOMConnector {
	
	public String writeToDOM(){
		
		String title = ""; 
		
		System.setProperty( "phantomjs.binary.path", "C:/Program Files (x86)/phantomjs-1.9.2-windows/phantomjs.exe" );
    	
        WebDriver driver = new PhantomJSDriver();
        driver.get("http://aavtrain.com");
 
        WebElement username = driver.findElement(By.name("user_name"));
        WebElement password = driver.findElement(By.name("password"));
        WebElement submit = driver.findElement(By.name("Submit"));
        username.sendKeys("root");
        password.sendKeys("root");
        submit.click();
        //element.submit();
        
        System.out.println("\n"+driver.findElement(By.className("errortextB")).getText()+"\n	");

        title = "Page title is: " + driver.getTitle();
        //System.out.println("Page title is: " + driver.getTitle());
        driver.quit();
        
        return title;
        
	}

}
