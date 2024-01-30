package TestProject.Cengage;

import java.net.HttpURLConnection;
import java.net.URL;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class NGL_SocialStudies {
	WebDriver driver;
@BeforeTest
	public void initializeBrowser()
	{
		WebDriverManager.chromedriver().setup();
		driver=new ChromeDriver();
	    driver.manage().window().maximize();
	    driver.get("https://ngl.cengage.com/");
	    driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
	}
  @Test(priority=1,description="Click on Products menu and verify that product list appears")
  public void verifyProductDropdownList() throws Exception {
	  try
	  {
		  Reporter.log("TESTCASE 1 :" + getClass().getSimpleName() + "(1/10)", true);
	      WebElement productMenu=driver.findElement(By.xpath("//span[contains(text(),'PRODUCTS')]"));
	      WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(100));
	      wait.until(ExpectedConditions.elementToBeClickable(productMenu));
	      JavascriptExecutor js=(JavascriptExecutor)driver;
	      js.executeScript("arguments[0].click();", productMenu);	  
	      String productList[]= {"Science","Social Studies", "English Language Learning", "Mathematics",
	    		  "English Language Arts","World Languages","Advanced, Honors, and Electives",
	    		  "Career and Technical Eduction (CTE)","Online Learning Platforms","Catalog Brochures (View & Download)"};
	      List<String> expectedProductDropdownList = new ArrayList<String>();
	      expectedProductDropdownList= Arrays.asList(productList);
	      Collections.sort(expectedProductDropdownList);
	  	  List<WebElement> productDropdownList=driver.findElements(By.xpath("//*[@role='menuitem']"));
	      ArrayList<String> actualProductDropdownList=new ArrayList<String>();
	      for(int i=0;i<productList.length;i++)
	    	  actualProductDropdownList.add(productDropdownList.get(i).getText()); 
	      Collections.sort(actualProductDropdownList);
	      for(int i=0;i<productList.length;i++)
	    	  Assert.assertEquals(actualProductDropdownList.get(i), expectedProductDropdownList.get(i));
          Reporter.log("All dropdownlist are present", true);
      }	  
	  catch(Exception e)
	  {
		  Reporter.log("Click on Products menu and verify that product list appears is failed");
          e.printStackTrace(); 
	  }
  }
  
  @Test(priority=2,description="Click on Social studies and verify that user navigates to Social Studies page")
  public void verifyNavigationToSocialStudiesPage()
  {
	 try
	  {
		  Reporter.log("TESTCASE 2 :" + getClass().getSimpleName() + "(2/10)", true);
		  driver.findElement(By.xpath("//a[contains(text(),'Social Studies')]")).click();
		  Assert.assertEquals(driver.findElement(By.cssSelector("div.css-186pv92-StyledFlex-StyledFlex")).getText(),
				  "Social Studies");
		  Reporter.log("Clicked on social studies and verified the page title", true);
	  }
	  catch(Exception e)
	  {
		  Reporter.log("Click on Social studies and verify that user navigates to Social Studies page is failed");
          e.printStackTrace(); 
	  } 
  }
  
  @Test(priority=3,description="Click on High School category and select 'See more titles' button to expand the list of books and verify that list of books appears")
  public void verifyListOfBooksAppearedAfterSelectingHighSchooolGrade()
  {
	 try
	  {
		  Reporter.log("TESTCASE 3 :" + getClass().getSimpleName() + "(3/10)", true);
		  driver.findElement(By.xpath("//p[contains(text(),'High School Grades 9 - 12')]")).click();
		  WebElement seeMoreTitlesButton=driver.findElement(By.cssSelector("div.css-x8x7mr-HiddenStyles"));
		  WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(100));
		  wait.until(ExpectedConditions.elementToBeClickable(seeMoreTitlesButton));
		  JavascriptExecutor js=(JavascriptExecutor)driver;
		  js.executeScript("arguments[0].click();", seeMoreTitlesButton);
		  WebElement filterText=driver.findElement(By.xpath("//h3[contains(text(),'Filters')]"));
		  wait.until(ExpectedConditions.visibilityOf(filterText));
		  Assert.assertEquals(filterText.getText(),"Filters");
		  Reporter.log("Clicked on High School category and select 'See more titles' button to expand the list of books and verified that list of books appears", true);
	  }
	  catch(Exception e)
	  {
		  Reporter.log("Click on High School category and select 'See more tiles' button to expand the list of books and verify that list of books appears is failed");
          e.printStackTrace(); 
	  } 
  }
  
  @Test(priority=4,description="Add a book to Cart and verify that the product is added in to cart")
  public void verifyAnOrderOfABook()
  {
    try
	  {
    	 Reporter.log("TESTCASE 4 :" + getClass().getSimpleName() + "(4/10)", true);
		 SoftAssert softAssert=new SoftAssert();
	     driver.findElement(By.cssSelector("h3.css-1nklabj")).click();
	     WebElement orderButton=driver.findElement(By.cssSelector("div.css-x8x7mr-HiddenStyles"));
	     WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(10));
	     wait.until(ExpectedConditions.elementToBeClickable(orderButton));
	     softAssert.assertEquals(orderButton.getText(), "ORDER");
	     JavascriptExecutor js=(JavascriptExecutor)driver;
	     js.executeScript("arguments[0].click();", orderButton);
	     WebElement orderInformationText=driver.findElement(By.cssSelector("div.css-186pv92-StyledFlex-StyledFlex"));
	     softAssert.assertEquals(orderInformationText.getText(),"Order Information");
	     softAssert.assertAll();
	     Reporter.log("A book is added to Cart and verified that the product is added in to cart", true);
	  }
	  catch(Exception e)
	  {
		  Reporter.log("Add a book to Cart and verify that the product is added in to cart is failed");
          e.printStackTrace(); 
	  }  
  }
  
  @Test(priority=5,description="Search any subject in Search Bar and verify that related books are listed")
  public void verifySearchedItemsListed()
  {
	 try
	  {
	  Reporter.log("TESTCASE 5 :" + getClass().getSimpleName() + "(5/10)", true);
	  WebElement searchBar=driver.findElement(By.cssSelector("input.css-1w25djl"));
	  searchBar.sendKeys("algebra");
	  searchBar.sendKeys(Keys.ENTER);
	  WebElement resultText=driver.findElement(By.cssSelector("h2.css-yaemxs"));
	  Assert.assertEquals(resultText.getText(),"Results matching \"algebra\"");
	  Reporter.log("Searched a subject in Search Bar and verified that related books are listed", true);
	  }
	  catch(Exception e)
	  {
		  Reporter.log("Search any subject in Search Bar and verify that related books are listed is failed");
          e.printStackTrace(); 
	  }  
  }
  @Test(priority=6,description="Apply Programs and disciplines filter on the searched results")
  public void verifyFilterAppliedOnSearchedResults()
  {
	 try
	  {
	  Reporter.log("TESTCASE 6 :" + getClass().getSimpleName() + "(6/10)", true);
	  driver.findElement(By.xpath("//label[contains(text(),'CTE - Health Science')]")).click();
	  WebElement appliedFilterText=driver.findElement(By.cssSelector("span.css-pszfx5"));
	  Assert.assertEquals(appliedFilterText.getText(),"CTE - Health Science");
	  Reporter.log("Applied Programs and disciplines filter on the searched results", true);
	  }
	  catch(Exception e)
	  {
		  Reporter.log("Apply Programs and disciplines filter on the searched results is failed");
          e.printStackTrace(); 
	  }  
  }
  
  @Test(priority=7,description="Navigate to Resourcse and click on 'Sign in to Products'")
  public void verifyNavigateToSignInToProducts()
  {
	  try
	  {
		  Reporter.log("TESTCASE 7 :" + getClass().getSimpleName() + "(7/10)", true);
		  WebElement resourcesMenu=driver.findElement(By.xpath("//span[contains(text(),'RESOURCES')]"));
		  WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(100));
		  wait.until(ExpectedConditions.elementToBeClickable(resourcesMenu));
		  JavascriptExecutor js=(JavascriptExecutor)driver;
		  js.executeScript("arguments[0].click();", resourcesMenu);
		  driver.findElement(By.xpath("//a[contains(text(),'Sign In to Products')]")).click();
		  WebElement signInToProductsText=driver.findElement(By.cssSelector("div.css-186pv92-StyledFlex-StyledFlex"));
		  Assert.assertEquals(signInToProductsText.getText(), "Sign In To Products");
		  Reporter.log("Navigated to Resourcse and clicked on 'Sign in to Products'", true);
	  }
      catch(Exception e)
      {
	     Reporter.log("Navigate to Resourcse and click on 'Sign in to Products' is failed");
         e.printStackTrace(); 
      }  
  }
  @Test(priority=8,description="Count the number of links present and find the broken links")
  public void verifybrokenLinksInSignInToProductsPage()
  {
	  
	  try
	  {
	  Reporter.log("TESTCASE 8 :" + getClass().getSimpleName() + "(8/10)", true);
	  SoftAssert softAssert=new SoftAssert();
	  List<WebElement> links=driver.findElements(By.tagName("a"));
	  System.out.println("Number of links present in the page are: "+links.size());
	  for(WebElement link : links)
      {  
          String url= link.getAttribute("href");
          HttpURLConnection   connection= (HttpURLConnection)new URL(url).openConnection();
          connection.setRequestMethod("HEAD");
          connection.connect();
          int responseCode = connection.getResponseCode();
          System.out.println(responseCode);
          if(responseCode==200)
          {
        	  System.out.println("Link with text: "+link.getText()+" is not broken with response code: "+responseCode);
              softAssert.assertTrue(responseCode==200, "The link with Text: "+link.getText()+" is NOT broken with code: " + responseCode);
          }
          else
          {
        	  System.out.println("Link with text: "+link.getText()+" is broken with response code: "+responseCode);
        	  softAssert.assertFalse(responseCode>200, "The link with Text: "+link.getText()+" and URL: "+url+"is broken with code: " +responseCode);
        	  
          }
      } 
	  softAssert.assertAll();
	  Reporter.log("Broken links are found", true);
	  }
	  catch(Exception e)
      {
	     Reporter.log("Verification of Broken link is failed");
         e.printStackTrace(); 
      }    
  }
  
  @Test(priority=9,description="Click on 'Sign In' link and navigate to other window")
  public void verifyNavigationToChildWindow()
  {
	  try
	  {
		  Reporter.log("TESTCASE 9 :" + getClass().getSimpleName() + "(9/10)", true);
		  SoftAssert softAssert=new SoftAssert();
		  driver.findElement(By.linkText("Sign in")).click();
		  Set<String> windowTabs=driver.getWindowHandles();
		  Iterator<String> itr=windowTabs.iterator();
		  String parentTab=itr.next();
		  String childTab=itr.next();
		  driver.switchTo().window(childTab);
		  driver.findElement(By.linkText("Register Now.")).click();
		  WebElement registrationText=driver.findElement(By.xpath("//h2[contains(text(),'Welcome to your Course Registration')]"));
		  softAssert.assertEquals(registrationText.getText(), "Welcome to your Course Registration");
		  driver.switchTo().window(parentTab);
		  WebElement signInToProductsText=driver.findElement(By.cssSelector("div.css-186pv92-StyledFlex-StyledFlex"));
		  softAssert.assertEquals(signInToProductsText.getText(), "Sign In To Products");
		  WebElement supportMenu= driver.findElement(By.xpath("//span[contains(text(),'SUPPORT')]"));
		  WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(100));
		  wait.until(ExpectedConditions.elementToBeClickable(supportMenu));
		  JavascriptExecutor js=(JavascriptExecutor)driver;
		  js.executeScript("arguments[0].click();", supportMenu);
		  WebElement supportText=driver.findElement(By.xpath("//div[contains(text(),'Support')]"));
		  softAssert.assertEquals(supportText.getText(), "Support");
		  softAssert.assertAll();
		  Reporter.log("Clicked on 'Sign In' link and navigated to other window", true);
	  }
	  catch(Exception e)
      {
	     Reporter.log("Click on 'Sign In' link and navigate to other window is failed");
         e.printStackTrace(); 
      }    
  }
  
  @Test(priority=10,description="Click on training link present in Support category")
  public void verifyClickOnTrainingLinkFromSupportPage()
  {
	  try
	  {
		  Reporter.log("TESTCASE 10 :" + getClass().getSimpleName() + "(10/10)", true);
		  WebElement searchTheTrainingLibraryLink=driver.findElement(By.linkText("Search the training library"));
	      JavascriptExecutor js=(JavascriptExecutor)driver;
		  js.executeScript("arguments[0].click();", searchTheTrainingLibraryLink);
	      WebElement trainingLibraryText=driver.findElement(By.cssSelector("div.css-186pv92-StyledFlex-StyledFlex"));
	      Assert.assertEquals(trainingLibraryText.getText(), "Training Library");
	      Reporter.log("Clicked on training link present in Support category", true);
	  }
	  catch(Exception e)
      {
	     Reporter.log("Click on training link present in Support category is failed");
         e.printStackTrace(); 
      }    
  }
  @AfterTest
  public void closeBrowser()
  {
	  driver.quit();
  }
}
