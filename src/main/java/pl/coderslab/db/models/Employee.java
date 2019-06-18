package pl.coderslab.db.models;

public class Employee {
    private int id;
    private String firstName;
    private String lastName;
    private String address;
    private String phoneNumber;
    private String note;
    private Double hourCost;

    public Employee() {
    }

    public Employee(String firstName, String lastName, String address, String phoneNumber, String note, Double hourCost) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.note = note;
        this.hourCost = hourCost;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Double getHourCost() {
        return hourCost;
    }

    public void setHourCost(Double hourCost) {
        this.hourCost = hourCost;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", address='" + address + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", note='" + note + '\'' +
                ", hourCost=" + hourCost +
                '}';
    }
}
