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
    @FindBy(xpath = "//*[@id=\"genterWrapper\"]/div[2]/div[1]/label")
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
    @FindBy(xpath = "//div[@class='modal-content']")
    private WebElement successPopUp;
    @FindBy(id = "example-modal-sizes-title-lg")
    private WebElement popUpText;
    @FindBy(id = "example-modal-sizes-title-lg")
    private WebElement headerOfPopUp;
    @FindBy(xpath = "/html/body/div[4]/div/div/div[2]/div/table/tbody/tr[1]/td[2]")
    private WebElement studentName;
    @FindBy(xpath = "/html/body/div[4]/div/div/div[2]/div/table/tbody/tr[2]/td[2]")
    private WebElement emailPopUp;
    @FindBy(xpath = "/html/body/div[4]/div/div/div[2]/div/table/tbody/tr[3]/td[2]")
    private WebElement genderPopUp;
    @FindBy(xpath = "/html/body/div[4]/div/div/div[2]/div/table/tbody/tr[4]/td[2]")
    private WebElement mobilePopUp;
    @FindBy(xpath = "/html/body/div[4]/div/div/div[2]/div/table/tbody/tr[5]/td[2]")
    private WebElement dateOfBirthPopUp;
    @FindBy(xpath = "/html/body/div[4]/div/div/div[2]/div/table/tbody/tr[6]/td[2]")
    private WebElement subjectsPopUp;
    @FindBy(xpath = "/html/body/div[4]/div/div/div[2]/div/table/tbody/tr[8]/td[2]")
    private WebElement picturePopUp;
    @FindBy(xpath = "/html/body/div[4]/div/div/div[2]/div/table/tbody/tr[9]/td[2]")
    private WebElement addressPopUp;
    @FindBy(xpath = "/html/body/div[4]/div/div/div[2]/div/table/tbody/tr[10]/td[2]")
    private WebElement stateAndCityPopUp;

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

    public void setStateAndCity(){
        state.click();
        neededState.click();
        city.click();
        neededCity.click();
    }
    public void clickSubmit(){
        submit.click();
    }

    public void verifyResults(String firstname
            , String lastName
            , String email
            , String gender
            , String mobile
            , String dateOfBirth
            , String subjects
            , String pictureName
            , String address
            , String state
            , String city) {

        assert successPopUp.isDisplayed();
        assert headerOfPopUp.getText().equals("Thanks for submitting the form");
        assert studentName.getText().equals(firstname + " " + lastName);
        assert emailPopUp.getText().equals(email);
        assert genderPopUp.getText().equals(gender);
        assert mobilePopUp.getText().equals(mobile);
        assert dateOfBirthPopUp.getText().equals(dateOfBirth);
        assert subjectsPopUp.getText().equals(subjects);
        assert picturePopUp.getText().equals(pictureName);
        assert addressPopUp.getText().equals(address);
        assert stateAndCityPopUp.getText().equals(state + " " + city);

    }

}
