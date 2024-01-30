package com.launch;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class CengageHigherEducation {

	WebDriver driver;

	@BeforeTest
	public void setUp() {
		driver = new ChromeDriver();		
		driver.get("https://www.cengage.co.in/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
	}

	@Test(priority = 1, description = "Verify Cengage page title")
	public void verifyPageTitle() {
		try {
			Reporter.log("TESTCASE 1 :" + getClass().getSimpleName() + "(1/10)", true);
			String expectedTitle = "Cengage India";
			String actualTitle = driver.getTitle();
			Assert.assertEquals(actualTitle, expectedTitle, "Page title verification failed!");
			Reporter.log("Verify Cengage page title is Passed", true);
		} catch (Exception e) {
			Reporter.log("Verify Cengage page title is Failed", true);
			e.printStackTrace();
			
		}
	}

	@Test(priority = 2, description = "Verify Cengage logo presence")
	public void verifyLogoPresence() {
		try {
			Reporter.log("TESTCASE 2 :" + getClass().getSimpleName() + "(2/10)", true);
			WebElement logoElement = driver.findElement(By.cssSelector("a[class='navbar-brand'] img[class='img-fluid']"));
			Assert.assertTrue(logoElement.isDisplayed(), "Logo is not displayed on the page");
			Reporter.log("Verify Cengage logo presence is Passed", true);
		} catch (Exception e) {
			Reporter.log("Verify Cengage logo presence is Failed", true);
			e.printStackTrace();
			
		}
	}

	@Test(priority = 3, description = "Verify Catalog Menus and Submenus")
	public void verifyCatalogMenus() {
		try {
			Reporter.log("TESTCASE 3 :" + getClass().getSimpleName() + "(3/10)", true);
			SoftAssert softAssert = new SoftAssert();
			WebElement catalogMenu = driver.findElement(By.cssSelector(".nav-link.dropdown-toggle"));
			Actions actions = new Actions(driver);
			actions.moveToElement(catalogMenu).perform();
			ArrayList<String> expectedMenus = new ArrayList<>(
					Arrays.asList("Higher Education", "Test Prep", "ELT/NGL", "GALE"));
			Collections.sort(expectedMenus);
			List<WebElement> headingElements = driver.findElements(By.cssSelector("li a.dropdown-item"));
			ArrayList<String> actualMenus = new ArrayList<>();
			for (WebElement headingElement : headingElements) {
				actualMenus.add(headingElement.getText());
			}
			Collections.sort(actualMenus);
			softAssert.assertEquals(actualMenus, expectedMenus, "Actual and expected headings do not match.");
			softAssert.assertAll();
			Reporter.log("Verify Catalog Menus and Submenus is Passed", true);
		} catch (Exception e) {
			Reporter.log("Verify Catalog Menus and Submenus is Failed", true);
			e.printStackTrace();
		}
	}
		
	@Test(priority = 4, description = "Verify Higher Education link")
	public void verifyHigherEducationLink() {
		try {
        	Reporter.log("TESTCASE 4 :" + getClass().getSimpleName() + "(4/10)", true);
            WebElement catalogMenu = driver.findElement(By.cssSelector(".nav-link.dropdown-toggle"));
            Actions actions = new Actions(driver);
            actions.moveToElement(catalogMenu).build().perform();
            driver.findElement(By.xpath("//a[contains(text(),'Higher Education')]")).click();                     
            String expectedText = "Higher Education";            
            WebElement nextPageTextElement = driver.findElement(By.cssSelector("div[class='page_name_last'] p"));
            String actualText = nextPageTextElement.getText();
            Assert.assertEquals(actualText,expectedText , "Assertion failed: Expected text not found on the page.");
            Reporter.log("Verify Higher Education link is clickable is Passed", true);
        } catch (Exception e) {
        	Reporter.log("Verify Higher Education link is clickable is Failed", true);
            e.printStackTrace();
           
        }
	}

	@Test(priority = 5, description = "Verify catalog Search And Validate")
	public void verifySearchAndValidate() {
		try {
			Reporter.log("TESTCASE 5 :" + getClass().getSimpleName() + "(5/10)", true);
			driver.findElement(By.id("tag")).sendKeys("Education");
			driver.findElement(By.cssSelector(".fa-search")).click();
			String expectedSearchResults = "SEARCH RESULTS";
			String searchResults = driver.findElement(By.cssSelector(
					"div[class='book_search_result_content'] h2")).getText();
			Assert.assertEquals(searchResults,expectedSearchResults);			
			Reporter.log("Verify catalog Search And Validate is Passed", true);
		} catch (Exception e) {
			Reporter.log("Verify catalog Search And Validate is Failed", true);
			e.printStackTrace();
			
		}
	}

	@Test(priority = 6, description = "Verify Add to cart functionality")
	public void verifyAddtocart() {
		try {
			Reporter.log("TESTCASE 6 :" + getClass().getSimpleName() + "(6/10)", true);
			driver.findElement(By.xpath("//a[contains(@href,'model-curriculum-2018-po')]")).click();
			driver.findElement(By.cssSelector(".buying_option_btn")).click();
			driver.findElement(By.cssSelector(".add-to-cart-btn")).click();
			String cartCount = driver.findElement(By.xpath("//div[@id='navbarNavAltMarkup']//span[@class='cartno'][normalize-space()='1']")).getText();
			Assert.assertEquals(cartCount, "1");
			Reporter.log("Verify Add to cart functionality is Passed", true);
		} catch (Exception e) {
			Reporter.log("Verify Add to cart functionality is Failed", true);
			e.printStackTrace();
			
		}
	}

	@Test(priority = 7, description = "Verify book author")
	public void verifyBookAuthor() {

		try {
			Reporter.log("TESTCASE 7 :" + getClass().getSimpleName() + "(7/10)", true);
			driver.getCurrentUrl();
			WebElement authorElement = driver
					.findElement(By.xpath("//span[contains(text(),'ITL Education Solutions Limited')]"));
			String authorName = authorElement.getText();
			Assert.assertEquals(authorName, "ITL Education Solutions Limited");			
			Reporter.log("Verify book author is Passed", true);
		} catch (Exception e) {
			Reporter.log("Verify book author is Failed", true);
			e.printStackTrace();
		}
	}

	@Test(priority = 8, description = "Verify details page tabs")
	public void verifyTabs() {

		try {
			Reporter.log("TESTCASE 8 :" + getClass().getSimpleName() + "(8/10)", true);
			SoftAssert softAssert = new SoftAssert();			
			driver.getCurrentUrl();
			List<WebElement> tabs = driver.findElements(By.xpath("//a[@data-toggle=\"pill\"]"));
			String[] expectedTabNames = { "Overview", "Features", "Table of contents", "about the author(s)" };
			assert tabs.size() == expectedTabNames.length : "Number of tabs mismatch";
			for (int i = 0; i < tabs.size(); i++) {
				WebElement tab = tabs.get(i);
				String actualTabName = tab.getText();
				String expectedTabName = expectedTabNames[i].toUpperCase();
				softAssert.assertEquals(actualTabName.toUpperCase(), expectedTabName, "Assertion failed: Tab text mismatch");
			}
			softAssert.assertAll();
			Reporter.log("Verify details page tabs is Passed", true);
			
		} catch (Exception e) {
			Reporter.log("Verify details page tabs is Failed", true);
			e.printStackTrace();
		}
	}

	@Test(priority = 9, description = "Verify Removecart functionality")
	public void verifyRemovecart() {
		try {
			Reporter.log("TESTCASE 9 :" + getClass().getSimpleName() + "(9/10)", true);
			driver.navigate().to("https://www.cengage.co.in/cart");
			driver.findElement(By.cssSelector(".fa.fa-trash.delete_cart_data_page")).click();
			WebElement cartremoveMessage = driver.findElement(By.xpath("//h2[normalize-space()='CART EMPTY']"));
			Assert.assertTrue(cartremoveMessage.isDisplayed(), "Item is not successfully removed from the cart");
			Reporter.log("Verify Removecart functionality is Passed", true);
		} catch (Exception e) {
			Reporter.log("Verify Removecart functionality is Failed", true);
			e.printStackTrace();			
		}
	}

	@Test(priority = 10, description = "Verify broken links")
	public void verifyBrokenLinks() {
		try {
			Reporter.log("TESTCASE 10 :" + getClass().getSimpleName() + "(10/10)", true);
			SoftAssert softAssert = new SoftAssert();
			List<WebElement> links = driver.findElements(By.cssSelector("a"));
			for (int i = 0; i < links.size(); i++) {
				WebElement link = links.get(i);
				driver.getCurrentUrl();
				String url = link.getAttribute("href");
				if (url == null || url.isEmpty()) {
					continue;
				}
				HttpURLConnection httpURLConnection;
				if (url.startsWith("http://") || url.startsWith("https://")) {
					httpURLConnection = (HttpURLConnection) new URL(url).openConnection();
				} else {
					System.out.println("Skipping link with unsupported protocol: " + url);
					continue;
				}
				httpURLConnection.setRequestMethod("HEAD");
				httpURLConnection.connect();
				int responseCode = httpURLConnection.getResponseCode();
				System.out.println("URL: " + url + " | Response Code: " + responseCode);
				softAssert.assertTrue(responseCode == 200, "Broken link found: " + url);
				links = driver.findElements(By.tagName("a"));				
			}
			softAssert.assertAll();
			Reporter.log("Verify broken links is Passed", true);
		} catch (Exception e) {
			Reporter.log("Verify broken links is Failed", true);
			e.printStackTrace();
		}
	}

	@AfterTest
	public void tearDown() {
		
		driver.quit();
	}
}
