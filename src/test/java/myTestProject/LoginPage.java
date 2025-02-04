package myTestProject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.ConfigUtils;

import java.util.Properties;

public class LoginPage {
    WebDriver driver;
    By username = new By.ByXPath("//*[contains(@name,'username')]");
    By password = new By.ByXPath("//*[contains(@name,'password')]");
    By loginbtn = new By.ByXPath("//button[normalize-space()='Login']");
    By menu = new By.ByXPath("//i[@class='oxd-icon bi-caret-down-fill oxd-userdropdown-icon']");
    By logoutlink = new By.ByLinkText("Logout");
    public LoginPage(WebDriver driver){
        this.driver = driver;
    }
    public void login(){
        Properties prop = ConfigUtils.getProps("data");

        driver.findElement(username).sendKeys(prop.getProperty("USERNAME"));
        driver.findElement(password).sendKeys(prop.getProperty("PASSWORD"));
        driver.findElement(loginbtn).click();
    }

    public void logout() throws InterruptedException {
        driver.findElement(menu).click();
        Thread.sleep(2000);
        driver.findElement(logoutlink).click();
    }
}
