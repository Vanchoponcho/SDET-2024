package Models;

public class Customer {
    private String firstName;
    private String lastName;
    private String postCode;
    private String accountNumber;

    public Customer(String firstName, String lastName, String postCode) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.postCode = postCode;
    }

    public Customer(String firstName, String lastName, String postCode, String accountNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.postCode = postCode;
        this.accountNumber = accountNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumbers(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", postCode='" + postCode + '\'' +
                ", accountNumber=" + accountNumber +
                '}';
    }
}
