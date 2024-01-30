import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.AssertJUnit;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.AssertJUnit;
import static org.testng.Assert.assertEquals;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class CengageTest {

	static WebDriver driver;
	static WebDriverWait wait ;
	static Select select;
	static SoftAssert softAssert;

	@BeforeClass
	private void launchBrowser() {
		driver= new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		wait = new WebDriverWait(driver,Duration.ofSeconds(15));
		
		

	}

	@Test(priority = 1, description = "Verify Ebook button clickable")
	private void testEBook() {
		try {
		Reporter.log("TESTCASE" + getClass().getSimpleName() + "(1/10)", true);
		driver.get("https://www.cengage.co.in/category/gale");
		driver.findElement(By.xpath("//h2[text()='GALE E-BOOKS']/../../descendant::a[contains(@href,'ebook')]")).click();
		String parent = driver.getWindowHandle();
		Set<String> windowHandles = driver.getWindowHandles();// All opened window ID
		Iterator<String> window = windowHandles.iterator();
		while(window.hasNext())
		{
			String child = window.next();
			if(!parent.equals(child))
			{
				driver.switchTo().window(child);
			}

		}
		driver.findElement(By.xpath("//a[@class='btn green-btn green-btn--transparent btn-block']")).click();
		List<WebElement> findElements = driver.findElements(By.xpath("//li[@class='header-tab-nav__tab']/a"));
		String text = driver.findElement(By.xpath("//strong[text()='Print And Ebooks']")).getText();
		
	}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Test(priority = 2, description = "Verify user can able verify catalog button")
	private void checkingCatalog() {
		try
		{
		
		Reporter.log("TESTCASE" + getClass().getSimpleName() + "(2/10)", true);
		driver.findElement(By.xpath("//p[text()='Library Type']/../descendant::label[contains(text(),'Academic')]/preceding-sibling::input")).click();
		WebElement academicButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@data-value='Academic']")));
		String name = academicButton.getText();
		String academic = "academic";
		Assert.assertEquals(name, academic);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
		@Test(priority = 3, description = "User able to verify menu Check Box")
		private void verifyMenuCheckBox() {
			try
			{
			softAssert = new SoftAssert();	
			Reporter.log("TESTCASE" + getClass().getSimpleName() + "(3/10)", true);
			driver.findElement(By.xpath("//input[@class='catalog-search__product-checkbox'][@value='']")).click();	
			List<WebElement> menuCheckBox = driver.findElements(By.xpath("//input[@class='catalog-search__product-checkbox'][@value!='']"));
			for (WebElement webElement : menuCheckBox) {
				Assert.assertTrue(webElement.isSelected());
			}
			softAssert.assertAll();
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
		@Test(priority = 4, description = "Verify User can click page button and go to that page")
		private void verifyPageButton() {
			try {
				Reporter.log("TESTCASE" + getClass().getSimpleName() + "(4/10)", true);
				driver.findElement(By.xpath("//a[text()='Pages']")).click();
				String text = driver.findElement(By.xpath("//strong[text()='Pages']")).getText();
				Assert.assertEquals(text, "Pages");
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		@Test(priority = 5, description = "Verify user can check home Menu")
		private void verifyhomeMenu() {
			try
			{
				Reporter.log("TESTCASE" + getClass().getSimpleName() + "(5/10)", true);
				SoftAssert softAssert = new SoftAssert();
				String[] homeMenuExpected = {"Gale Primary Sources","Gale Databases","Gale eBooks","Support","Blog"};
				List<WebElement> homeMenuActual = driver.findElements(By.xpath("//span[@class='main-nav__section-text']"));
				for (int i = 0; i < homeMenuExpected.length; i++) {
					String actualMenu = homeMenuActual.get(i).getText();
					Assert.assertEquals(actualMenu, homeMenuExpected[i]);
				}
				softAssert.assertAll();
			
			
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
		@Test(priority = 6, description = "User can verify clear filter Button")
		private void verifyClearFilter() {
			try {
			softAssert = new SoftAssert();
			Reporter.log("TESTCASE" + getClass().getSimpleName() + "(6/10)", true);	
			driver.findElement(By.xpath("//li[@class='header-tab-nav__tab']/a[text()='Products']")).click();
			driver.findElement(By.xpath("//label[contains(text(),'Academic')]/preceding-sibling::input")).click();
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//label[contains(text(),'Public')]/preceding-sibling::input)[1]"))).click();
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//label[contains(text(),'K-12')]/preceding-sibling::input)[1]"))).click();
			driver.findElement(By.xpath("//a[@class='catalog-search__filters-tags-clear js-clear-all-filters']")).click();
			List<WebElement> findElements = driver.findElements(By.xpath("//p[text()='Audience']/../ul/li/input"));
			for (WebElement webElement : findElements) {
			softAssert.assertFalse(webElement.isSelected());
			}
			}
			catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		@Test(priority = 7, description = "User can verify check box by Entering valid Input and get relevant result ")
		private void verifySearchBox() {
			try
			{
			Reporter.log("TESTCASE" + getClass().getSimpleName() + "(7/10)", true);	
			String validData = "Seventeenth and Eighteenth Century Nichols Newspapers Collection";
			String title="";
			driver.findElement(By.xpath("//div[@class='c-header__search-box c-search-box c-search-box--desktop']/descendant::input[@placeholder='Search']")).sendKeys(validData);
			driver.findElement(By.xpath("//div[@class='c-header__search-box c-search-box c-search-box--desktop']/descendant::input[@placeholder='Search']/following-sibling::button")).click();
			List<WebElement> searchResult = driver.findElements(By.xpath("//div[@class='catalog-search__product-detail-content']/p/a"));
			for (WebElement webElement : searchResult) {
				 title = webElement.getText();
				Assert.assertTrue(title.equalsIgnoreCase(validData));
			}
			driver.findElement(By.xpath("//i[@class='fa fa-close']")).click();
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
		@Test(priority = 8, description = "Verify user can able to add to wishList", enabled = true)
		private void verifyAddToWishList() {
			try
			{
			Reporter.log("TESTCASE" + getClass().getSimpleName() + "(8/10)", true);		
			driver.findElement(By.xpath("//a[text()='Print And Ebooks']")).click();
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//a[contains(text(),'Business Plans Handbook')])[1]"))).click();
			String parent = driver.getWindowHandle();
			Set<String> windowHandles = driver.getWindowHandles();
			Iterator<String> window = windowHandles.iterator();
			while(window.hasNext())
			{
				String child = window.next();
				if(!parent.equals(child))
				{
					driver.switchTo().window(child);
				}
			}
			driver.findElement(By.xpath("//span[text()='Add To Wishlist']/..")).click();
			String wishList = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()='Please log in to add this product to the wishlist']"))).getText();
			driver.findElement(By.xpath("//span[text()='×']")).click();
			driver.switchTo().window(parent);
			AssertJUnit.assertEquals(wishList, "Please log in to add this product to the wishlist");
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		@Test(priority = 9, description = "User can verify Request trail", enabled = true)
		private void verifyRequestTrailTest() {
			try
			{
			Reporter.log("TESTCASE" + getClass().getSimpleName() + "(9/10)", true);	
			driver.findElement(By.xpath("//a[text()='Print And Ebooks']")).click();
			driver.findElement(By.xpath("(//a[contains(text(),'Business Plans Handbook')])[1]/ancestor::div[@class='catalog-search__product has-controls is-list']/div/input")).click();
			driver.findElement(By.xpath("//button[contains(text(),'Add selected to cart')]")).click();
			String cartText =	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()='Please log in to add this product to the cart']"))).getText();
			driver.findElement(By.xpath("(//span[@class='c-modal__close-icon'])[2]")).click();
			Assert.assertEquals(cartText, "Please log in to add this product to the cart");
			}
			catch (Exception e) {
			e.printStackTrace();
			}
			
		}
		@Test(priority = 10, description = "Verify the contact representative", enabled = true)
		private void verifyContactRepresentative() {
			try {
			Reporter.log("TESTCASE" + getClass().getSimpleName() + "(10/10)", true);	
			driver.get("https://www.gale.com/intl/s?result-type=ebooks");
			driver.findElement(By.xpath("(//a[text()='Contact representative'])[1]")).click();
			driver.findElement(By.xpath("//label[text()='First Name']/following-sibling::input")).sendKeys("Venkatesh");
			driver.findElement(By.xpath("//label[text()='Last Name']/following-sibling::input")).sendKeys("G");
			driver.findElement(By.xpath("//label[text()='Institution']/following-sibling::input")).sendKeys("Anna University");
			driver.findElement(By.xpath("//label[text()='Email']/following-sibling::input")).sendKeys("venkatesh.guna@globallogic.com");
			driver.findElement(By.xpath("//label[text()='Tell us more about how we can help']/following-sibling::textarea")).sendKeys("Please provide relevant Information on this course");
			select = new Select(driver.findElement(By.id("country")));
			select.selectByValue("India");
			driver.findElement(By.xpath("//input[@type='checkbox'][@name='FutureOptOut']")).click();
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='recaptcha-checkbox-border']"))).click();
			driver.findElement(By.xpath("//input[@type='submit'][@class='gale-btn green-btn ']")).click();
			String submissionSuccessText = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[text()='Successfully Submitted the Form!']"))).getText();
			Assert.assertEquals(submissionSuccessText, "Successfully Submitted the Form!");
			}
			catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		
		
	
	

	@AfterClass(enabled = false)
	private void tearDown() {
		driver.quit();
	}
}
