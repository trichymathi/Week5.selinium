package week5.day3;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class LearnWindowhandling {

	public static void main(String[] args) {
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.get("http://www.leafground.com/pages/Window.html");
		driver.manage().window().maximize();
		
		//Click button to open home page in New Window
		
		String parentWindow = driver.getWindowHandle();
		//System.out.println(driver.switchTo().window(parentWindow));
		System.out.println("PARENTWINDOW :"+parentWindow);
		driver.findElement(By.xpath("//button[@id='home']")).click();
	    Set<String> windowHandles = driver.getWindowHandles();
	    windowHandles.add(parentWindow);
		List<String> window = new 	ArrayList<String>(windowHandles);
		driver.switchTo().window(window.get(1));
		//String childofhome = driver.getWindowHandle();
		//windowHandles.add(childofhome);
		System.out.println(window);
		driver.manage().window().maximize();
		driver.findElement(By.xpath("//h5[text()='Edit']//..")).click();
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("abcdefghij@gmail.com");
		driver.close();
		
		//Find the number of opened windows
		
		driver.switchTo().window(parentWindow);
		driver.findElement(By.xpath("//button[text()='Open Multiple Windows']")).click();
		Set<String> windowHandles1 = driver.getWindowHandles();
		List<String> window1 = new 	ArrayList<String>(windowHandles1);
		System.out.println("No Of Windows : "+window1.size());
		driver.switchTo().window(window1.get(1));
		//String multipleWindow1 = driver.getWindowHandle();
		//windowHandles1.add(multipleWindow1);
		driver.manage().window().maximize();
		driver.close();
		driver.switchTo().window(window1.get(2));
		//String multipleWindow2 = driver.getWindowHandle();
		//windowHandles1.add(multipleWindow2);
		driver.manage().window().maximize();
		driver.close();
		System.out.println(window1);
		
		//Close all except this window
		
		driver.switchTo().window(parentWindow);
        driver.findElement(By.xpath("//button[text()='Do not close me ']")).click();
        Set<String> windowHandles2 = driver.getWindowHandles();
		List<String> window2 = new 	ArrayList<String>(windowHandles2);
		System.out.println(window2.size());
		for(int i =1;i<window2.size();i++) {
			driver.switchTo().window(window2.get(i)).close();
			}
	
		//Wait for 2 new Windows to open
		
		driver.switchTo().window(parentWindow);
		WebElement element = driver.findElement(By.xpath("//button[text()='Wait for 5 seconds']"));
		element.click();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.numberOfWindowsToBe(3));
		Set<String> windowHandles3 = driver.getWindowHandles();
		List<String> window3 = new 	ArrayList<String>(windowHandles3);
		driver.switchTo().window(window3.get(0)).close();
		driver.switchTo().window(window3.get(2));
		driver.manage().window().maximize();
		driver.close();
		driver.switchTo().window(window3.get(1));
		driver.manage().window().maximize();
		driver.findElement(By.linkText("Go to Home Page")).click();
		System.out.println(driver.getTitle());
		driver.close();
		}
	}