package selScenarios;

import java.net.HttpURLConnection;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

public class actionClass {
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
	void actClass(WebDriver driver) throws InterruptedException {
		driver.switchTo();
		driver.findElement(By.xpath("//a[normalize-space()='More']")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//a[normalize-space()='Links']")).click();
		Thread.sleep(1000);
		new Actions(driver)
        .sendKeys("abc")
        .perform();
		}

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		actionClass ac=new actionClass();
		WebDriver driver=ac.login();
		ac.actClass(driver);
	}

}
