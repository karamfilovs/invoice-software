package ui;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class LoginPage {
    //Different methods for working with Login page

    public void login(String username, String password){
        //I will use  selenium here
        //Find email field
       // WebElement emailField = chrome.findElement(By.id("loginusername"));
        //Populate email with karamfilovs@gmail.com
       // emailField.sendKeys(EMAIL);
        //Find password field
       // WebElement passwordField = chrome.findElement(By.id("loginpassword"));
        //Populate password
       // passwordField.sendKeys(PASSWORD);
        //Find Login button
       // WebElement loginButton = chrome.findElement(By.id("loginsubmit"));
        //Click Login button
       // loginButton.click();
    }

    public void resetPassword(String oldPassword, String newPassword){
        //It will perform 10 steps
    }

    public static void main(String[] args) {
        LoginPage loginPage = new LoginPage();
        loginPage.login("dummy@gmail.com", "111111");
        loginPage.resetPassword("1111111", "222222");
    }
}
