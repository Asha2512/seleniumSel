package selScenarios;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;


public class implicitWaitExplicitWait {
	
		
		@Test(priority=1)
		WebDriver login() throws InterruptedException {
			ChromeOptions option = new ChromeOptions();
	        option.addArguments("--remote-allow-origins=*");
			WebDriver driver=new ChromeDriver(option);
			driver.get("http://magnus.jalatechnologies.com/");	
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
			WebElement userName = driver.findElement(By.xpath("//input[@id='UserName']"));
			userName.sendKeys("training@jalaacademy.com");
			//System.out.println("I am userName xpaths"+userName);
			WebElement passWord = driver.findElement(By.xpath("//input[@id='Password']"));
			passWord.sendKeys("jobprogram");
			WebElement SignIN=driver.findElement(By.xpath("//button[@id='btnLogin']"));
			SignIN.click();
			WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(30));
			wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//a[normalize-space()='More']"))));
			return driver;
			
		}
		@Test(priority=1)
		void enterIFrame(WebDriver driver) throws InterruptedException {
			
			driver.switchTo();
			driver.findElement(By.xpath("//a[normalize-space()='More']")).click();
			Thread.sleep(1000);
			driver.findElement(By.xpath("//a[normalize-space()='iFrames']")).click();
			Thread.sleep(1000);
			List<WebElement> iframeelements = driver.findElements(By.tagName("iframe"));
			//to determine the no of iframe elements
			System.out.println("No. fo iframes are: "+iframeelements.size());
			Thread.sleep(1000);
			//switching to frame 1
			driver.switchTo().frame(1);
			Thread.sleep(1000);
			//clicking the button inside the iframe
			driver.findElement(By.xpath("/html/body/div[2]/header/nav/div/ul/li/a")).click();
			Thread.sleep(1000);
		}
		public static void main(String[] args) throws InterruptedException {
			implicitWaitExplicitWait ifra= new implicitWaitExplicitWait();
			WebDriver driver=ifra.login();
			ifra.enterIFrame(driver);
			
		}

}