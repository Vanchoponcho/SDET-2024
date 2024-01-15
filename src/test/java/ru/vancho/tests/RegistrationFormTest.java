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
        registrationPage.setGender(); //локатор установлен на Male
        registrationPage.setMobile("8900123456");
        registrationPage.setDateOfBirth("April", "2000");
        registrationPage.setSubjects("English");
        registrationPage.uploadFile();
        registrationPage.setCurrentAddress("Russian Federation, Samara, Volzhskiy avenue, 4");
        registrationPage.setState(); //локатор установлен на NCR
        registrationPage.setCity(); //локатор установлен на Delhi
        registrationPage.clickSubmit();

        assert popUp.successPopUp.isDisplayed();
        assertEquals("Thanks for submitting the form", popUp.getValue(popUp.headerOfPopUp));
        assertEquals("Ivan Ogurtsov", popUp.getValue(popUp.studentName));
        assertEquals("ivan.ogurtsov@example.com", popUp.getValue(popUp.emailPopUp));
        assertEquals("Male", popUp.getValue(popUp.genderPopUp));
        assertEquals("8900123456", popUp.getValue(popUp.mobilePopUp));
        assertEquals("20 April,2000", popUp.getValue(popUp.dateOfBirthPopUp));
        assertEquals("English", popUp.getValue(popUp.subjectsPopUp));
        assertEquals("photo_2023-11-30_17-42-12.jpg", popUp.getValue(popUp.picturePopUp));
        assertEquals("Russian Federation, Samara, Volzhskiy avenue, 4", popUp.getValue(popUp.addressPopUp));
        assertEquals("NCR Delhi", popUp.getValue(popUp.stateAndCityPopUp));

    }

@AfterClass
    public static void close() {
    driver.quit();
    }
}

