package pl.coderslab.db.models;

public class Vehicle {
    private int id;
    private String model;
    private String carBrand;
    private String productionYear;
    private String registrationNumber;
    private String nextTechnicalInspection;
    private int customerId;
    private String inspectionNotify;

    public Vehicle() {
    }

    public Vehicle(String model, String carBrand, String productionYear, String registrationNumber, String nextTechnicalInspection, int customerId) {
        this.model = model;
        this.carBrand = carBrand;
        this.productionYear = productionYear;
        this.registrationNumber = registrationNumber;
        this.nextTechnicalInspection = nextTechnicalInspection;
        this.customerId = customerId;
    }

    public String getInspectionNotify() {
        return inspectionNotify;
    }

    public void setInspectionNotify(String inspectionNotify) {
        this.inspectionNotify = inspectionNotify;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getCarBrand() {
        return carBrand;
    }

    public void setCarBrand(String carBrand) {
        this.carBrand = carBrand;
    }

    public String getProductionYear() {
        return productionYear;
    }

    public void setProductionYear(String productionYear) {
        this.productionYear = productionYear;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public String getNextTechnicalInspection() {
        return nextTechnicalInspection;
    }

    public void setNextTechnicalInspection(String nextTechnicalInspection) {
        this.nextTechnicalInspection = nextTechnicalInspection;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "id=" + id +
                ", model='" + model + '\'' +
                ", carBrand='" + carBrand + '\'' +
                ", productionYear='" + productionYear + '\'' +
                ", registrationNumber='" + registrationNumber + '\'' +
                ", nextTechnicalInspection='" + nextTechnicalInspection + '\'' +
                ", customerId=" + customerId +
                '}';
    }
}
