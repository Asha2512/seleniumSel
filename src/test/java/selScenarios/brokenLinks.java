package selScenarios;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Test;

public class brokenLinks {
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
	void checkbrokenLink(WebDriver driver) throws InterruptedException {
		
		driver.switchTo();
		driver.findElement(By.xpath("//a[normalize-space()='More']")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//a[normalize-space()='Links']")).click();
		Thread.sleep(1000);
		List<WebElement> links = driver.findElements(By.tagName("a"));
		//to determine the no of iframe elements
		System.out.println("No. fo links are: "+links.size());
		Thread.sleep(1000);
		//switching to frame 1
		for(int i=0;i<links.size();i++) {
			System.out.println(links.get(i).getText());
			WebElement ele = links.get(i);
			String url = ele.getAttribute("href");
			testverifyLinks(url);
		}
		Thread.sleep(2000);
		
        driver.quit();
		 }
	@Test(priority=2)
	public void testverifyLinks(String linkUrl) {
		//creating a try catch block
		try {
			
			//creating an object of url class and giving the url as it's argument
			URL url = new URL(linkUrl);
			
			//creating an object of HttpURLConnection to open the url
			HttpURLConnection httpURLConnect = (HttpURLConnection)url.openConnection();
			
			//checking the connection.If not responded within 3 seconds it will time out
			httpURLConnect.setConnectTimeout(3000);
			
			//connecting the url
			httpURLConnect.connect();
			
			//IF the response code is equal to 200 then the site is fine
			if(httpURLConnect.getResponseCode()==200) {
				System.out.println(linkUrl+"-"+httpURLConnect.getResponseMessage());
			}else {
			
			//this condition will be true for the links which are broken
			if(httpURLConnect.getResponseCode()==HttpURLConnection.HTTP_NOT_FOUND) {
				System.out.println(linkUrl+"-"+httpURLConnect.getResponseMessage()+"-"+HttpURLConnection.HTTP_NOT_FOUND);
			}
			}
			//If exception occurs catch block will catch it
		}catch(Exception e) {
		}
	}

		
	
	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		brokenLinks bl =new brokenLinks();
		WebDriver driver=bl.login();
		bl.checkbrokenLink(driver);

	}

}
