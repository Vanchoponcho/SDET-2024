package ru.vancho.tests.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PopUp {
    public WebDriver driver;
    public PopUp (WebDriver driver){
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }
    @FindBy(xpath = "//div[@class='modal-content']")
    public WebElement successPopUp;
    @FindBy(id = "example-modal-sizes-title-lg")
    public WebElement headerOfPopUp;
    @FindBy(xpath = "//td[text()='Student Name']//following::td[1]")
    public WebElement studentName;
    @FindBy(xpath = "//td[text()='Student Email']//following::td[1]")
    public WebElement emailPopUp;
    @FindBy(xpath = "//td[text()='Gender']//following::td[1]")
    public WebElement genderPopUp;
    @FindBy(xpath = "//td[text()='Mobile']//following::td[1]")
    public WebElement mobilePopUp;
    @FindBy(xpath = "//td[text()='Date of Birth']//following::td[1]")
    public WebElement dateOfBirthPopUp;
    @FindBy(xpath = "//td[text()='Subjects']//following::td[1]")
    public WebElement subjectsPopUp;
    @FindBy(xpath = "//td[text()='Picture']//following::td[1]")
    public WebElement picturePopUp;
    @FindBy(xpath = "//td[text()='Address']//following::td[1]")
    public WebElement addressPopUp;
    @FindBy(xpath = "//td[text()='State and City']//following::td[1]")
    public WebElement stateAndCityPopUp;
    public String getValue(WebElement element){
        return element.getText();
    }
}
