package HAS;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

//Good Code by Himanshu Obviously
public class Test_1 
{
	WebDriver driver = new FirefoxDriver();
	By gDrive = By.cssSelector("div.section-centered:nth-child(2) > div:nth-child(1) > a:nth-child(3)");
	By email = By.id("Email");
	By next = By.id("next");
	By pass = By.xpath(".//*[@id='Passwd']");
	By signIn = By.id("signIn");
	
	@Test(priority=0)
	public void setUp()
	{
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("https://www.google.com/drive/");	
		driver.findElement(gDrive).click();
		
	}	
	@Test(priority=1)
	public void loginToGdrive() throws InterruptedException
	{
		driver.findElement(email).sendKeys("xyz@gmail.com");
		driver.findElement(next).click();		
		Thread.sleep(2000);
		
		if(!driver.findElements(By.partialLinkText("Sorry, Google doesn't recognize that email")).isEmpty())
			System.out.println("Username does not exist !!! Please enter a valid Username...");
			else
			{
				driver.findElement(pass).sendKeys("password");
				Thread.sleep(2000);
				driver.findElement(signIn).click();
				
				if(!driver.findElements(By.partialLinkText("The email and password you entered don't match")).isEmpty())
					System.out.println("Username & Password do not match !!! Please enter a valid Username & Password...");
				else
				{
					System.out.println("Logged In to the System...");
				}
			}
	}
	@Test(dependsOnMethods = { "loginToGdrive" })
	 public void uploadFile() throws InterruptedException
	 {
		Thread.sleep(10000);
		 WebElement uploadButton=driver.findElement(By.xpath("//*[@id='drive_main_page']/div[4]/div[1]/div[2]/div/div/div[1]/div/div/div[1]/div"));
		 uploadButton.click();
   	  	System.out.println("Clicked new Button");
   	  	WebElement fileUpload = driver.findElement(By.xpath("//div[text()='File upload']"));
   	 	fileUpload.click();
   	  	Thread.sleep(2000);
   	  	driver.switchTo().activeElement().sendKeys("C:\\Him.txt");
   	  	
   	  	Actions action = new Actions(driver);
   	 	action.sendKeys(Keys.RETURN);
   		action.perform();
   	 
	 }
	
	//No Network – Turn off the network and try to upload a file – Assert if no network message is displayed.
	@Test
	public void noNetwork()
	{
		try
	      {
	   	  WebElement uploadButton=driver.findElement(By.xpath("UploadBtn"));
	   	  uploadButton.click();
	   	  if(driver.findElements(By.xpath("UploadPopUp")).isEmpty())
	   	  {
	   	  //Upload File Code.
	   	  }
	   	  else
	   	  {
	   	  String noInternetMessage=driver.findElement(By.xpath("//*[@id='main-message']/h1")).getText();
	   	  
	   	  if (noInternetMessage.equals("There is no Internet connection"))
	          {
	              System.out.println("Correct message is displayed");
	          }
	          else
	          {
	              System.out.println("Incorrect message is displayed");
	          } 
	   	  }
	   	  
	   	  
	      }
	      catch(Exception ex)
	      {
	    	  
	      }
	}
}
