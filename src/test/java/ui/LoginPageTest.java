package ui;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;

public class LoginPageTest {
    private static final String EMAIL = System.getProperty("email", "dummy@gmail.com");
    private static final String PASSWORD = System.getProperty("password", "1212121");
    private static final String DOMAIN = System.getProperty("domain", "companyx");
    private static final String LOGIN_PAGE = "https://st2016.inv.bg";
    private WebDriver chrome;

    @BeforeEach
    public void beforeEach() {
        //Do this before every test
        ChromeOptions options = new ChromeOptions();
        //options.addArguments("--headless=new"); //set headless execution
        chrome = new ChromeDriver(options); // Start new chrome instance
        chrome.get(LOGIN_PAGE); //Navigate to login page (st2016.inv.bg)
        chrome.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @AfterEach
    public void afterEach() {
        //Do this after every test
        chrome.quit(); // Close chrome instance
    }

    @Test
    @Tag("ui")
    @DisplayName("Can login with valid credentials")
    public void canLoginWithValidCredentials() {
        //Find email field
        WebElement emailField = chrome.findElement(By.id("loginusername"));
        //Populate email with karamfilovs@gmail.com
        emailField.sendKeys(EMAIL);
        //Find password field
        WebElement passwordField = chrome.findElement(By.id("loginpassword"));
        //Populate password
        passwordField.sendKeys(PASSWORD);
        //Find Login button
        WebElement loginButton = chrome.findElement(By.id("loginsubmit"));
        //Click Login button
        loginButton.click();
        //Check that user is logged in
        //Find user panel header
        WebElement userPanelHeader = chrome.findElement(By.cssSelector("div.userpanel-header"));
        String actualUser = userPanelHeader.getText();
        Assertions.assertEquals(EMAIL, actualUser);
    }

    @Test
    @Tag("ui")
    @DisplayName("Can't login with invalid password")
    public void cantLoginWithInvalidPassword() {
        //Find email field
        WebElement emailField = chrome.findElement(By.id("loginusername"));
        //Populate email with karamfilovs@gmail.com
        emailField.sendKeys(EMAIL);
        //Find password field
        WebElement passwordField = chrome.findElement(By.id("loginpassword"));
        //Populate wrong password
        passwordField.sendKeys("1111111212");
        //Find Login button
        WebElement loginButton = chrome.findElement(By.id("loginsubmit"));
        //Click Login button
        loginButton.click();
        //Find error message
        WebElement loginError = chrome.findElement(By.cssSelector(".selenium-error-block"));
        Assertions.assertEquals("Грешно потребителско име или парола. Моля, опитайте отново.", loginError.getText().strip());
    }

    @Test
    @Tag("ui")
    @DisplayName("Can't login with blank credentials")
    public void cantLoginWithBlankCredentials() {
        //Find Login button
        WebElement loginButton = chrome.findElement(By.id("loginsubmit"));
        //Click Login button
        loginButton.click();
        //Find error message
        WebElement loginError = chrome.findElement(By.cssSelector(".selenium-error-block"));
        Assertions.assertEquals("Моля, попълнете вашия email", loginError.getText().strip());
    }

    @Test
    @Tag("ui")
    @DisplayName("Can logout from the system")
    public void canLogoutFromTheSystem() {
        //Find email field
        WebElement emailField = chrome.findElement(By.id("loginusername"));
        //Populate email with karamfilovs@gmail.com
        emailField.sendKeys(EMAIL);
        //Find password field
        WebElement passwordField = chrome.findElement(By.id("loginpassword"));
        //Populate password
        passwordField.sendKeys(PASSWORD);
        //Find Login button
        WebElement loginButton = chrome.findElement(By.id("loginsubmit"));
        //Click Login button
        loginButton.click();
        //Check that user is logged in
        //Find user panel header
        WebElement userPanelHeader = chrome.findElement(By.xpath("//div[@class='userpanel-header']"));
        String actualUser = userPanelHeader.getText();
        Assertions.assertEquals(EMAIL, actualUser);
        //Find logout link
        userPanelHeader.click(); //clicks on user panel (logger user name)
        WebElement logoutLink = chrome.findElement(By.cssSelector("a.selenium-button-logout"));
        //Click logout link
        logoutLink.click();
        //Find logout success message
        WebElement logoutSuccess = chrome.findElement(By.id("okmsg"));
        //Check logout success message
        Assertions.assertEquals("Вие излязохте от акаунта си.", logoutSuccess.getText().strip());
        //Check navigation
        Assertions.assertEquals("Вход - QA Ground", chrome.getTitle());
    }
}
