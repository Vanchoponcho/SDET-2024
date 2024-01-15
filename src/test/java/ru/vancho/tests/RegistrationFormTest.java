package ru.vancho.tests;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import ru.vancho.tests.pages.PopUp;
import ru.vancho.tests.pages.RegistrationPage;
import java.util.concurrent.TimeUnit;
import static org.junit.Assert.assertEquals;

public class RegistrationFormTest {
    public static RegistrationPage registrationPage;
    public static PopUp popUp;
    public static WebDriver driver;

@BeforeClass
public static void setUp(){

        System.setProperty("webdriver.chrome.driver", ConfProperties.getProperty("chromedriver"));
        driver = new ChromeDriver();
        registrationPage = new RegistrationPage(driver);
        popUp = new PopUp(driver);
        driver.manage().window().setSize(new Dimension(1920,1080));
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get(ConfProperties.getProperty("regpage"));
    }

@Test
    public void fillForm(){
        registrationPage.setFirstNameInput("Ivan");
        registrationPage.setLastNameInput("Ogurtsov");
        registrationPage.setEmailInput("ivan.ogurtsov@example.com");
        registrationPage.setGender();
        registrationPage.setMobile("8900123456");
        registrationPage.setDateOfBirth("April", "2000");
        registrationPage.setSubjects("English");
        registrationPage.uploadFile();
        registrationPage.setCurrentAddress("Russian Federation, Samara, Volzhskiy avenue, 4");
        registrationPage.setState();
        registrationPage.setCity();
        registrationPage.clickSubmit();

        popUp.verifyResults("Ivan"
        , "Ogurtsov"
        , "ivan.ogurtsov@example.com"
        , "Male"
        , "8900123456"
        , "20 April,2000"
        , "English"
        , "photo_2023-11-30_17-42-12.jpg"
        , "Russian Federation, Samara, Volzhskiy avenue, 4"
        , "NCR"
        , "Delhi");

    }
@AfterClass
    public static void close() {
    driver.quit();
    }
}

