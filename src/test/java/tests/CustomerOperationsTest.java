package tests;

import Models.Customer;
import helpers.ConfProperties;
import helpers.TestDataGenerator;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.AddCustomerPage;
import pages.CustomersPage;
import pages.MainPage;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class CustomerOperationsTest {
    public static WebDriver driver;
    public static MainPage mainPage;
    public static AddCustomerPage addCustomerPage;
    public static CustomersPage customersPage;
    String postCode = TestDataGenerator.generateNumber(10);
    String firstName = TestDataGenerator.convertNumberToStringByAlphabet(postCode);
    String lastName = "Malfoy";

    @BeforeAll
    public static void setUp() {
        System.setProperty("webdriver.chrome.driver", ConfProperties.getProperty("chromedriver"));
        driver = new ChromeDriver();
        mainPage = new MainPage(driver);
        addCustomerPage = new AddCustomerPage(driver);
        customersPage = new CustomersPage(driver);
        driver.manage().window().setSize(new Dimension(1920, 1080));
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get(ConfProperties.getProperty("testpage"));
    }

    @Test
    public void openMainPage() {
        mainPage.open();
        mainPage.addCustomer();
        addCustomerPage.setPostCode(postCode);
        addCustomerPage.setLastName(lastName);
        addCustomerPage.setFirstName(firstName);
        addCustomerPage.addCustomer();
        addCustomerPage.acceptAction();
        mainPage.toCustomerList();
        List<Customer> customers = customersPage.getCustomersList();
        assertNotNull(customers.stream().filter(customer -> customer.getFirstName().equals(firstName) &&
                customer.getLastName().equals(lastName) &&
                customer.getPostCode().equals(postCode)).findAny().orElse(null));

    }

    @Test
    public void sortByFirstName() throws InterruptedException {
        customersPage.open();
        customersPage.clickToSortByFirstName();
        customersPage.clickToSortByFirstName();
        List<Customer> customers = customersPage.getCustomersList();
        assertTrue(customersPage.isCustomersSortedByFirstName(customers));
    }

    @Test
    public void deleteCustomerByAvgLength() throws InterruptedException {

        customersPage.open();
        List<Customer> customersBeforeDeleteCustomer = customersPage.getCustomersList();
        double avgFirstNameLength = customersPage.getAvgFirstNameLength(customersBeforeDeleteCustomer);
        Customer customerWithAvgFirstName = customersPage.getCustomerWithFirstNameLengthCloseToAvg(customersBeforeDeleteCustomer, avgFirstNameLength);
        String avgFirstName = customerWithAvgFirstName.getFirstName();
        customersPage.deleteCustomerByFirstName(avgFirstName);
        List<Customer> customersAfterDeleteCustomer = customersPage.getCustomersList();
        assertNull(customersAfterDeleteCustomer.stream()
                .filter(customer -> customer.getFirstName().equals(customerWithAvgFirstName.getFirstName()) &&
                        customer.getLastName().equals(customerWithAvgFirstName.getLastName()) &&
                        customer.getPostCode().equals(customerWithAvgFirstName.getPostCode()))
                .findAny()
                .orElse(null));
    }
}
