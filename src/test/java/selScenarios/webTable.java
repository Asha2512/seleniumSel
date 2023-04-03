package selScenarios;

import java.net.HttpURLConnection;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

public class webTable {
	static String jalaUrl="http://magnus.jalatechnologies.com/";
	static String url = "";
	static HttpURLConnection huc = null;
	static int respCode = 200;
	@Test(priority=1)
	WebDriver login() throws InterruptedException {
		ChromeOptions option = new ChromeOptions();
        option.addArguments("--remote-allow-origins=*");
		WebDriver driver=new ChromeDriver(option);
		driver.get(jalaUrl);	
		driver.manage().window().maximize();
		WebElement userName = driver.findElement(By.xpath("//input[@id='UserName']"));
		userName.sendKeys("training@jalaacademy.com");
		//System.out.println("I am userName xpaths"+userName);
		WebElement passWord = driver.findElement(By.xpath("//input[@id='Password']"));
		passWord.sendKeys("jobprogram");
		WebElement SignIN=driver.findElement(By.xpath("//button[@id='btnLogin']"));
		SignIN.click();
		Thread.sleep(1000);
		return driver;
		
	}
	@Test(priority=1)
	void webElemet(WebDriver driver) throws InterruptedException {
		driver.switchTo();
	 
        List<WebElement> emLIst= driver.findElements(By.xpath("//*[@id=\"tblEmployee\"]/tbody/tr"));
	System.out.println("no of row"+emLIst.size());	
	}
}
