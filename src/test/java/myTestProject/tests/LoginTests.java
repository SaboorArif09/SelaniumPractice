package myTestProject.tests;

import myTestProject.LoginPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utils.ConfigUtils;

import java.time.Duration;
import java.util.Properties;

public class LoginTests {
    LoginPage loginPg;
    WebDriver driver;
    @BeforeClass
    public void openPage() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver","C:\\Users\\PureLogics-469\\Downloads\\chromedriver_win32\\chromedriver.exe");
        driver = new ChromeDriver();

        Properties prop = ConfigUtils.getProps("data");

        //driver.manage().window().maximize();
        driver.get(prop.getProperty("URL"));
        Thread.sleep(2000);
    }
    @Test(description = "Login Test", priority = 1)
    public void login(){
        loginPg = new LoginPage(driver);
        loginPg.login();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//i[@class='oxd-icon bi-caret-down-fill oxd-userdropdown-icon']")));

        //Verification
        String text = driver.findElement(By.xpath("//h6[normalize-space()='Dashboard']")).getText();
//        if(text.contains("Dashboard"))
//            System.out.println("login successfully");
        Assert.assertEquals(text, "Dashboard");
    }
    @Test(description = "Logout Test", priority = 2)
    public void logout() throws InterruptedException {
        loginPg.logout();
        //driver.navigate().refresh();
        //String title = driver.getTitle();
        //System.out.println(title);

        //Verification
        String url = driver.getCurrentUrl();
       // if(url.contains("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login"))
       //     System.out.println("logout successfully");
        Assert.assertTrue(url.contains("/login"));
    }

    @AfterClass
    public void closeWindow(){
        driver.quit();
    }
}
