package week5.day3;

import java.time.Duration;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class LearnWaits {

	public static void main(String[] args) {
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		
		//LeafGround Appear wait
		driver.get("http://www.leafground.com/pages/appear.html");
		driver.manage().window().maximize();
		WebElement element = driver.findElement(By.xpath("//button[@id='btn']"));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOf(element));
		System.out.println(element.isDisplayed());
		
		//LeafGround Disappear  wait
		driver.switchTo().newWindow(WindowType.TAB);
		driver.get("http://www.leafground.com/pages/disapper.html");
		WebElement element2 = driver.findElement(By.xpath("//button[@id='btn']"));
		WebDriverWait wait2 = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait2.until(ExpectedConditions.invisibilityOf(element2));
		System.out.println(element2.isDisplayed());
		
		//LeafGround TextChange wait
		driver.switchTo().newWindow(WindowType.TAB);
		driver.get("http://www.leafground.com/pages/TextChange.html");
		WebElement element3 = driver.findElement(By.xpath("//button[@id='btn']"));
		WebDriverWait wait3 = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait3.until(ExpectedConditions.textToBePresentInElement(element3, "Click ME!"));
		element3.click();
		Alert alert = driver.switchTo().alert();
		alert.accept();
		
		//LeafGround Alert wait
		driver.switchTo().newWindow(WindowType.TAB);
		driver.get("http://www.leafground.com/pages/alertappear.html");
		WebElement element4 = driver.findElement(By.xpath("//button[@id='alert']"));
		element4.click();
		WebDriverWait wait4 = new WebDriverWait(driver, Duration.ofSeconds(20));
		Alert until = wait4.until(ExpectedConditions.alertIsPresent());
		until.accept();
		System.out.println("Alert is handeled");
		
		driver.quit();
	}
}
