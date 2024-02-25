package pages;

import Models.Customer;
import helpers.ConfProperties;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.*;

public class CustomersPage extends BasePage {
    public CustomersPage(WebDriver driver) {
        super(driver);
    }

    public void open() throws InterruptedException {
        driver.get(ConfProperties.getProperty("customerspage"));
    }

    @FindBy(xpath = "//a[@ng-click=\"sortType = 'fName'; sortReverse = !sortReverse\"]")
    public WebElement firstNameColumn;

    public void clickToSortByFirstName() {
        firstNameColumn.click();
    }

    public List<Customer> getCustomersList() {
        List<Customer> customers = new ArrayList<>();
        for (WebElement row : driver.findElements(By.xpath("//table/tbody/tr"))) {
            String firstName = row.findElements(By.tagName("td")).get(0).getText();
            String lastName = row.findElements(By.tagName("td")).get(1).getText();
            String postCode = row.findElements(By.tagName("td")).get(2).getText();
            String accountNumber = row.findElements(By.tagName("td")).get(3).getText();
            Customer customer = new Customer(firstName, lastName, postCode, accountNumber);
            customers.add(customer);
        }
        return customers;
    }

    public boolean isCustomersSortedByFirstName(List<Customer> customers) {
        for (int i = 0; i < customers.size() - 1; i++) {
            char firstLetterOfFirstName = customers.get(i).getFirstName().toLowerCase().charAt(0);
            char firstLetterOfNextFirstName = customers.get(i + 1).getFirstName().toLowerCase().charAt(0);
            if (firstLetterOfFirstName > firstLetterOfNextFirstName) {
                return false;
            }
        }
        return true;
    }

    public double getAvgFirstNameLength(List<Customer> customers) {
        if (customers.isEmpty()) {
            return 0;
        }
        int totalLength = customers.stream().mapToInt(customer -> customer.getFirstName().length()).sum();
        return (double) totalLength / customers.size();
    }

    public Customer getCustomerWithFirstNameLengthCloseToAvg(List<Customer> customers, double length) {
        Optional<Customer> closeToAvgCustomer = customers.stream().min(Comparator.comparingDouble(c -> Math.abs(c.getFirstName().length() - length)));

        return closeToAvgCustomer.orElse(null);
    }

    public void deleteCustomerByFirstName(String firstName) {
        driver.findElement(By.xpath("//td[contains(text(), '" + firstName + "')]/following-sibling::td/button[contains(@ng-click, 'deleteCust')]")).click();
    }
}
