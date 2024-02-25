package pages;

import helpers.ConfProperties;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AddCustomerPage extends BasePage {
    public AddCustomerPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//input[@placeholder='First Name']")
    public WebElement firstNameInput;
    @FindBy(xpath = "//input[@placeholder='Last Name']")
    public WebElement lastNameInput;
    @FindBy(xpath = "//input[@placeholder='Post Code']")
    public WebElement postCodeInput;
    @FindBy(xpath = "//button[@type='submit']")
    public WebElement addCustomerButton;

    public void open() {
        driver.get(ConfProperties.getProperty("addcustomerpage"));
    }

    public void setFirstName(String firstName) {
        firstNameInput.sendKeys(firstName);
    }

    public void setLastName(String lastName) {
        lastNameInput.sendKeys(lastName);
    }

    public void setPostCode(String postCode) {
        postCodeInput.sendKeys(postCode);
    }

    public void addCustomer() {
        addCustomerButton.click();
    }

    public void acceptAction() {
        driver.switchTo().alert().accept();
    }
}
