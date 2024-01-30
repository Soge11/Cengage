package NGL_Science;


import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import io.github.bonigarcia.wdm.WebDriverManager;

import org.testng.Assert; 



public class NGL_Science {
	
	private WebDriver driver;
	SoftAssert softAssert = new SoftAssert(); 
	static  WebDriverWait wait;
	
	
@BeforeTest(description = "Launch National geographic learning Application")
	public void launchNgl()
	{   
	
	    WebDriverManager.chromiumdriver().setup();
	    driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		driver.get("https://ngl.cengage.com/");
	}

@Test(priority = 1, description = "verify Tab Names on NGL page " )
public void verifyTabsNamesOnNglPage()
{
	
	try
    { 
    Reporter.log("Test Case 1 = " + getClass().getSimpleName() + "(1/10)", true);
    String[] tabNames ={"PRODUCTS", "SALES & ORDERS", "RESOURCES","SUPPORT"};
    ArrayList<String> expectedTabNames = new ArrayList(Arrays.asList(tabNames));
    Collections.sort(expectedTabNames);
    List<WebElement> nglTabs = driver.findElements(By.xpath("//button[@class ='css-1uuf2mc-BaseStyledButton-StyledIconButton e183qit0']|//button[@class ='css-1ud1h7i-BaseStyledButton e183qit0']"));
    ArrayList<String> actualTabNames = new ArrayList<String>();   
    for (int i = 0; i < nglTabs.size(); i++) 
    {
    	actualTabNames.add(nglTabs.get(i).getText());
    }
    Collections.sort(actualTabNames);
    Assert.assertEquals(actualTabNames, expectedTabNames);   
    Reporter.log("All Tabs are presents is passed", true);   
    }
	catch (Exception e) 
	{
    Reporter.log("All Tabs are presents is Failed", true);
    Reporter.log(e.getMessage(), true);
    
    }
	
}

@Test(priority = 2, description = "Click on Products Dropdown and Verify the list the elements under products" )
	public void clikcOnProductsTabAndVerifyList()
	{
		try
	       { 
	    Reporter.log("Test Case 2 = " + getClass().getSimpleName() + "(2/10)", true);
	    WebElement clickProductsTab = driver.findElement(By.xpath("//span[contains(text(),'PRODUCTS')]"));
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
	    wait.until(ExpectedConditions.elementToBeClickable(clickProductsTab));
	    JavascriptExecutor js=(JavascriptExecutor)driver;
	    js.executeScript("arguments[0].click();", clickProductsTab);
	    String[] headings ={"Science", "Social Studies","English Language Learning","Mathematics","English Language Arts","World Languages","Advanced, Honors, and Electives","Career and Technical Eduction (CTE)","Online Learning Platforms","Catalog Brochures (View & Download)"};
	    ArrayList<String> expectedHeadings = new ArrayList(Arrays.asList(headings));
	    Collections.sort(expectedHeadings);
	    List<WebElement> headingElements = driver.findElements(By.xpath("//a[@class = 'css-1v8z6jt-MenuItemStyles']"));
	    ArrayList<String> actualHeadings = new ArrayList<String>();   
	    for (int i = 0; i < headings.length; i++) 
	    {
	        actualHeadings.add(headingElements.get(i).getText());
	    }
	    Collections.sort(actualHeadings);
	    Assert.assertEquals(actualHeadings, expectedHeadings);
	    Reporter.log("Click on Products Tab and Verify the List of Products is Passed", true);
	    }
		catch (Exception e) 
		{
	    Reporter.log("Click on Products Tab and Verify the List of Products  is Failed", true);
	    Reporter.log(e.getMessage(), true);
	    e.printStackTrace();
	    }
		
}

@Test(priority = 3, description = "Click on Science product and Verify the title Name " )
public void verifyTitleNameOfScienceProduct()
{
try {
	
	Reporter.log("Test Case 3 = " + getClass().getSimpleName() + "(3/10)", true);
	driver.findElement(By.xpath("//a[@href='/programs/science']")).click(); 
	String actualProductName = driver.findElement(By.xpath("//div[@class='css-186pv92-StyledFlex-StyledFlex']")).getText();
	String expectedProductName = "Science"; 
    Assert.assertEquals(actualProductName, expectedProductName);

    Reporter.log("Click on Science product and Verify the title Name is passed", true);
   }            
   catch (Exception e) {
    Reporter.log("Click on Science and Verify the title Name is Failed", true);
    Reporter.log(e.getMessage(), true);
    e.printStackTrace();
    }
}


@Test(priority = 4, description = "Verify Categories Present On Science Page" )
public void VerifyCategoriesOnScience()
{
try {
	
	Reporter.log("Test case 4 = " + getClass().getSimpleName() + "(4/10)", true);
	String[] category ={"National Geographic Learning Difference", "High School Grades 9 - 12","Elementary School Grades K - 5"};
    ArrayList<String> expectedCategory = new ArrayList<String>(Arrays.asList(category));
    Collections.sort(expectedCategory);
    List<WebElement> CategoryUnderScience = driver.findElements(By.xpath("//p[@class = 'accordionTitle css-727n8w']"));
    ArrayList<String> actualCategories = new ArrayList<String>();  
    for (int i = 0; i < CategoryUnderScience.size(); i++) 
    {
    	actualCategories.add(CategoryUnderScience.get(i).getText());
    }
    Collections.sort(actualCategories);
    Assert.assertEquals(actualCategories, expectedCategory);
    Reporter.log("Verify categories under science page is Passed", true);
    }
               
   catch (Exception e) {
    Reporter.log("Verify Categories under science page is Failed", true);
    Reporter.log(e.getMessage(), true);
    e.printStackTrace();
    
    }
}

@Test(priority = 5, description = "Click on view all titles URL present on Science page" )
public void verifyViewAllTitlesURLAndFilterTypes() 
{
try {
	
	Reporter.log("Test case 5 = " + getClass().getSimpleName() + "(5/10)", true);
    driver.findElement(By.xpath("//a[@href='/catalog/science']")).click(); 
    WebElement filterText = driver.findElement(By.xpath("//h3[normalize-space()='Filters']")); 
    softAssert.assertEquals(filterText.getText(), "Filters");
    String[] filterOptions ={"Subject","Grade Level","Format","Reading Level","Language","Series"};
    ArrayList<String> expectedFiltertypes = new ArrayList<String>(Arrays.asList(filterOptions));
    Collections.sort(expectedFiltertypes);
    List<WebElement> listsFilterType = driver.findElements(By.xpath("//button[@class='css-3e8k1j']"));
    ArrayList<String> actualFiltertypes = new ArrayList<String>();  
    for (int i = 0; i < listsFilterType .size(); i++) 
    {
    	actualFiltertypes.add(listsFilterType .get(i).getText());
    }
    Collections.sort(actualFiltertypes);
    softAssert.assertEquals(actualFiltertypes, expectedFiltertypes);
    softAssert.assertAll();
    Reporter.log("Click on view all titles URL and Verify Filter Types is passed", true);
   }            
   catch (Exception e) 
{
    Reporter.log("Click on view all titles URL and Verify Filter Types is Failed", true);
    Reporter.log(e.getMessage(), true);
    e.printStackTrace();
    
    }
}

@Test(priority = 6, description = "Applied filter in science page" )
public void appliedFilterforBiologySubject() 
{
   try 
  {
	Reporter.log("Test case 6 = " + getClass().getSimpleName() + "(6/10)", true);
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[normalize-space()='Biology (9)']"))).click();
	WebElement checkAppliedFilterText = driver.findElement(By.cssSelector("span.css-pszfx5"));
	Assert.assertEquals(checkAppliedFilterText.getText(), "Biology");
	Reporter.log("Applied filter in science page  is passed", true);
   }            
   catch (Exception e) 
   {
    Reporter.log("Applied filter in science page is Failed", true);
    Reporter.log(e.getMessage(), true);
    e.printStackTrace();
    
    }
}


@Test(priority = 7, description = "Verify Per Page dropdown on filter page" )
public void verifyPerPageDropdown() 
{
try { 
	Reporter.log("Test case 7 = " + getClass().getSimpleName() + "(7/10)", true);
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),'10')]")));
	driver.findElement(By.xpath("//span[contains(text(),'10')]")).click();
	String[] perPageDropDownList ={"10","20","50"};
    ArrayList<String> expectedPerPageList = new ArrayList<String>(Arrays.asList(perPageDropDownList));
    Collections.sort(expectedPerPageList);
    List<WebElement> listOfPerPage = driver.findElements(By.xpath("//li[contains(text(),'50')]|//li[contains(text(),'20')]|//li[contains(text(),'10')]"));
    ArrayList<String> actualPerPageList = new ArrayList<String>();  
    for (int i = 0; i < listOfPerPage .size(); i++) 
    {
    	actualPerPageList.add(listOfPerPage .get(i).getText());
    }
    Collections.sort(actualPerPageList);
    Assert.assertEquals(actualPerPageList, expectedPerPageList);
    Reporter.log("Verify Per Page dropdown on filter page is passed", true);
   }            
   catch (Exception e) 
{
    Reporter.log("Verify Per Page dropdown on filter page is Failed", true);
    Reporter.log(e.getMessage(), true);
    e.printStackTrace();
    
    }
}


@Test(priority = 8, description = "Select book and Verify Tilte of the book" )
public void selectBookAndVerifyTilteOfTheBook() 
{
try {
	Reporter.log("Test case 8 = " + getClass().getSimpleName() + "(8/10)", true);
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//img[@alt='National Geographic Biology Student Edition']"))).click();
	String actualTitle = driver.findElement(By.xpath("//h2[normalize-space()='National Geographic Biology Student Edition']")).getText();
    String expectedTitle = "National Geographic Biology Student Edition";           
    Assert.assertEquals(actualTitle,expectedTitle); 
    Reporter.log("Select book and Verify Tilte of the book is passed", true);
   }            
   catch (Exception e) 
{
    Reporter.log("Select book and Verify Tilte of the book is Failed", true);
    Reporter.log(e.getMessage(), true);
    e.printStackTrace();
    
    }
}


@Test(priority = 9, description = "Click on Order Button and Verify order Information Title Order page" )
public void VerifyOrderButtonAndTitle() 
{
try {
	
	
	Reporter.log("Test case 9 = " + getClass().getSimpleName() + "(9/10)", true);
    driver.findElement(By.xpath("//button[@class='track_cta_primary css-ylem4i-BaseStyledButton e183qit0']")).click(); 
    WebElement orderInformationText = driver.findElement(By.xpath("//div[@class='css-186pv92-StyledFlex-StyledFlex']"));
    Assert.assertEquals(orderInformationText.getText(),"Order Information");
	Reporter.log("Click on Order Button and Verify order Information Title is passed", true);
   }            
   catch (Exception e) 
{
    Reporter.log("Click on Order Button and Verify order Information Title is Failed", true);
    Reporter.log(e.getMessage(), true);
    e.printStackTrace();
    
    }
}

@Test(priority = 10, description = "Click on Contact your sales representative button and Verify title" )
public void verifyContactYourSalesRepresentativeURL () 
{
   try 
   {
   Reporter.log("Test case 10 = " + getClass().getSimpleName() + "(10/10)", true);
   WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
   WebElement ContactYourSalesUrl  = wait.until(ExpectedConditions.elementToBeClickable((By.xpath("//a[normalize-space()='Contact your sales representative']"))));
   ContactYourSalesUrl.click();
   String parent = driver.getWindowHandle();
   Set<String> windowHandles = driver.getWindowHandles();
	Iterator<String> window = windowHandles.iterator();
	while(window.hasNext())
	{
		String child = window.next();
		if(!parent.equals(child))
		{
			driver.switchTo().window(child);
			 WebElement orderInformationText = driver.findElement(By.xpath("//h1[contains(text(),'Grades K-12 School: National Geographic Learning (')]"));
			 Assert.assertEquals(orderInformationText.getText(),"Grades K-12 School: National Geographic Learning (U.S.) & Gale, A Cengage Company (International)");
			 WebElement addSchoolName = driver.findElement(By.xpath("//input[@id='ceng-repfinder_account']"));
			 addSchoolName.sendKeys("Af");
			 driver.close();
		}
        driver.switchTo().window(parent);
	}
	
	Reporter.log("Click on Contact your sales representative button and Verify titleis passed", true);
   }            
   catch (Exception e) 
{
    Reporter.log("Click on Contact your sales representative button and Verify title is Failed", true);
    Reporter.log(e.getMessage(), true);
    e.printStackTrace();
    
    }
}

@Test(priority = 11, description = "Click on Order form button and Verify title" )
public void verifyOrderFormURL () 
{
   try 
   {
   Reporter.log("Test case 11 = " + getClass().getSimpleName() + "(11/11)", true);
   WebElement clickTrackYourOrder = driver.findElement(By.xpath("//a[normalize-space()='Track your order with order details']"));
   WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));
   wait.until(ExpectedConditions.elementToBeClickable(clickTrackYourOrder));
   JavascriptExecutor js=(JavascriptExecutor)driver;
   js.executeScript("arguments[0].scrollIntoView(true);", clickTrackYourOrder);
   js.executeScript("arguments[0].click();", clickTrackYourOrder);
   String parent = driver.getWindowHandle();
   Set<String> windowHandles = driver.getWindowHandles();
	Iterator<String> window = windowHandles.iterator();
	while(window.hasNext())
	{
		String child = window.next();
		if(!parent.equals(child))
		{
			driver.switchTo().window(child);
			 WebElement orderInformationText = driver.findElement(By.xpath("//div[@class='css-sxh77v-Container eqvr3ss0']//div//h2[@class='css-1ep73i1'][normalize-space()='Order Tracking']"));
			 Assert.assertEquals(orderInformationText.getText(),"Order Tracking");
			
		}
        driver.switchTo().window(parent);
	}
	
	Reporter.log("Click on Order Form button and Verify titleis passed", true);
   }            
   catch (Exception e) 
{
    Reporter.log("Click on Order Form button and Verify title is Failed", true);
    Reporter.log(e.getMessage(), true);
    e.printStackTrace();
    
    }
}


@AfterTest(description = "Closing the Application" )
public void closingApplication()
{
	
	driver.quit();
}

}
