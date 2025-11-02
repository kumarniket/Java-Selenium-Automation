package seleniumAutomation.tests;

import seleniumAutomation.base.BaseTest;
import seleniumAutomation.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {
    
    @Test(priority = 1, description = "Verify successful login with valid credentials")
    public void testSuccessfulLogin() {
        LoginPage loginPage = new LoginPage(driver);
        
        String username = configReader.getProperty("valid.username");
        String password = configReader.getProperty("valid.password");
        
        loginPage.login(username, password);
        
        Assert.assertTrue(loginPage.isLogoutButtonDisplayed(), 
                "Login failed - Logout button not displayed");
    }
    
    @Test(priority = 2, description = "Verify login fails with invalid credentials")
    public void testInvalidLogin() {
        LoginPage loginPage = new LoginPage(driver);
        
        loginPage.login("invaliduser", "invalidpass");
        
        Assert.assertTrue(loginPage.isErrorMessageDisplayed(), 
                "Error message not displayed for invalid credentials");
    }
    
    @Test(priority = 3, description = "Verify login with empty username")
    public void testEmptyUsername() {
        LoginPage loginPage = new LoginPage(driver);
        
        loginPage.login("", "password123");
        
        Assert.assertTrue(loginPage.isErrorMessageDisplayed(), 
                "Error message not displayed for empty username");
    }
    
    @Test(priority = 4, description = "Verify login with empty password")
    public void testEmptyPassword() {
        LoginPage loginPage = new LoginPage(driver);
        
        loginPage.login("testuser", "");
        
        Assert.assertTrue(loginPage.isErrorMessageDisplayed(), 
                "Error message not displayed for empty password");
    }
}