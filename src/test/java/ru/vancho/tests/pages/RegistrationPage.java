package ru.vancho.tests.pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.io.File;

public class RegistrationPage {
    public WebDriver driver;
    public RegistrationPage(WebDriver driver){
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }
    final String d = "20"; //День в календаре
    final String xp = "//*[@class='react-datepicker__week']//*[text()='" + d + "']";

    @FindBy(id = "firstName")
        private WebElement firstNameInput;
    @FindBy(id = "lastName")
        private WebElement lastNameInput;
    @FindBy(id = "userEmail")
        private WebElement emailInput;
    @FindBy(xpath = "//div[@class='custom-control custom-radio custom-control-inline']//label[@for='gender-radio-1']")
        private WebElement gender;
    @FindBy(id = "userNumber")
    private WebElement mobile;
    @FindBy(id = "dateOfBirthInput")
    private WebElement dateOfBirth;
    @FindBy(xpath = "//*[@class='react-datepicker__month-select']")
    private WebElement month;
    @FindBy(xpath = "//*[@class='react-datepicker__year-select']")
    private WebElement year;
    @FindBy(xpath = xp)
    private WebElement date;
    @FindBy(id = "subjectsInput")
    private WebElement subjects;
    @FindBy(className = "form-control-file")
    private WebElement picture;
    @FindBy(id = "currentAddress")
    private WebElement currentAddress;
    @FindBy(xpath = "//div[@id='state']//div[contains(text(), 'Select State')]")
    private WebElement state;
    @FindBy(xpath = "//div[@id='state']//div[contains(text(), 'NCR')]")
    private WebElement neededState;
    @FindBy(xpath = "//div[@id='city']//div[contains(text(), 'Select City')]")
    private WebElement city;
    @FindBy(xpath = "//div[@id='city']//div[contains(text(), 'Delhi')]")
    private WebElement neededCity;
    @FindBy(xpath = "//*[@id='submit']")
    private WebElement submit;


    public void setFirstNameInput(String firstName){
        firstNameInput.sendKeys(firstName);
    }
    public void setLastNameInput(String lastName){
        lastNameInput.sendKeys(lastName);
    }
    public void setEmailInput(String email){
        emailInput.sendKeys(email);
    }
    public void setGender(){
        gender.click();
    }
    public void setMobile(String mobileNumber){
        mobile.sendKeys(mobileNumber);
    }
    public void setDateOfBirth(String monthValue, String yearValue){
        dateOfBirth.click();
        month.click();
        month.sendKeys(monthValue);
        month.click();
        year.click();
        year.sendKeys(yearValue);
        year.click();
        date.click();
    }
    public void setSubjects(String subject){
        subjects.clear();
        subjects.sendKeys(subject);
        subjects.sendKeys(Keys.ARROW_DOWN);
        subjects.sendKeys(Keys.ENTER);
    }
    public void uploadFile(){
        File file = new File("src/test/java/resources/photo_2023-11-30_17-42-12.jpg");
        picture.sendKeys(file.getAbsolutePath());
    }

    public void setCurrentAddress(String yourAddress){
        currentAddress.sendKeys(yourAddress);
    }

    public void setState(){
        state.click();
        neededState.click();
    }

    public void setCity(){
        city.click();
        neededCity.click();
    }
    public void clickSubmit(){
        submit.click();
    }


}
