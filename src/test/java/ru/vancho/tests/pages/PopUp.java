package ru.vancho.tests.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import static org.junit.Assert.assertEquals;

public class PopUp {
    public WebDriver driver;
    public PopUp (WebDriver driver){
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }
    @FindBy(xpath = "//div[@class='modal-content']")
    private WebElement successPopUp;
    @FindBy(id = "example-modal-sizes-title-lg")
    private WebElement popUpText;
    @FindBy(id = "example-modal-sizes-title-lg")
    private WebElement headerOfPopUp;
    @FindBy(xpath = "//td[text()='Student Name']//following::td[1]")
    private WebElement studentName;
    @FindBy(xpath = "//td[text()='Student Email']//following::td[1]")
    private WebElement emailPopUp;
    @FindBy(xpath = "//td[text()='Gender']//following::td[1]")
    private WebElement genderPopUp;
    @FindBy(xpath = "//td[text()='Mobile']//following::td[1]")
    private WebElement mobilePopUp;
    @FindBy(xpath = "//td[text()='Date of Birth']//following::td[1]")
    private WebElement dateOfBirthPopUp;
    @FindBy(xpath = "//td[text()='Subjects']//following::td[1]")
    private WebElement subjectsPopUp;
    @FindBy(xpath = "//td[text()='Picture']//following::td[1]")
    private WebElement picturePopUp;
    @FindBy(xpath = "//td[text()='Address']//following::td[1]")
    private WebElement addressPopUp;
    @FindBy(xpath = "//td[text()='State and City']//following::td[1]")
    private WebElement stateAndCityPopUp;

    public void verifyResults(String firstname
            , String lastName
            , String emailText
            , String gender
            , String mobile
            , String dateOfBirth
            , String subjects
            , String pictureName
            , String address
            , String state
            , String city) {


        assert successPopUp.isDisplayed();
        assertEquals("Thanks for submitting the form", headerOfPopUp.getText());
        assertEquals(firstname + " " + lastName, studentName.getText());
        assertEquals(emailText + "", emailPopUp.getText());
        assertEquals(gender + "", genderPopUp.getText());
        assertEquals(mobile + "", mobilePopUp.getText());
        assertEquals(dateOfBirth + "", dateOfBirthPopUp.getText());
        assertEquals(subjects + "", subjectsPopUp.getText());
        assertEquals(pictureName + "", picturePopUp.getText());
        assertEquals(address + "", addressPopUp.getText());
        assertEquals(state + " " + city, stateAndCityPopUp.getText());

    }
}
