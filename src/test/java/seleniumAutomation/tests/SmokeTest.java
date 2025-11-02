package seleniumAutomation.tests;

import seleniumAutomation.base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class SmokeTest extends BaseTest {
    
    @Test(priority = 1, description = "Verify application URL is accessible")
    public void testApplicationAccessible() {
        String expectedUrl = configReader.getProperty("app.url");
        String actualUrl = driver.getCurrentUrl();
        
        Assert.assertTrue(actualUrl.contains(expectedUrl), 
                "Application URL is not accessible");
    }
    
    @Test(priority = 2, description = "Verify page title")
    public void testPageTitle() {
        String expectedTitle = configReader.getProperty("app.title", "");
        String actualTitle = driver.getTitle();
        
        Assert.assertTrue(actualTitle.contains(expectedTitle), 
                "Page title mismatch. Expected: " + expectedTitle + ", Actual: " + actualTitle);
    }
    
    @Test(priority = 3, description = "Verify critical elements are present")
    public void testCriticalElements() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        
        // Wait for page to load
        WebElement bodyElement = wait.until(
            ExpectedConditions.presenceOfElementLocated(By.tagName("body"))
        );
        
        Assert.assertTrue(bodyElement.isDisplayed(), "Page body not loaded");
    }
}