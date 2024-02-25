package pages;

import helpers.ConfProperties;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MainPage extends BasePage {
    public MainPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//div[@class='center']//button[@ng-click='addCust()']")
    private WebElement customer;
    @FindBy(xpath = "//button[@ng-class='btnClass3']")
    private WebElement customerButton;

    public void open() {
        driver.get(ConfProperties.getProperty("testpage"));
    }

    public void addCustomer() {
        customer.click();
    }

    public void toCustomerList() {
        customerButton.click();
    }
}
