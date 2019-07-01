package pl.coderslab.db.models;

public class Order {
    private int id;
    private String dateDeliveredToRepair;
    private String datePlannedRepair;
    private String dateStartedRepair;
    private int employeeId;
    private String problemDescription;
    private String repairDescription;
    private int statusId;
    private int vehicleId;
    private double repairCostForCustommer;
    private double costOfParts;
    private double costOfmanHour;
    private int manHour;

    private String carBrand;
    private String model;
    private String registrationNumber;

    private String employeeFirstName;
    private String employeeLastName;

    private String customerFirstName;
    private String customerLastName;

    private String statusValue;

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public String getCustomerFirstName() {
        return customerFirstName;
    }

    public void setCustomerFirstName(String customerFirstName) {
        this.customerFirstName = customerFirstName;
    }

    public String getCustomerLastName() {
        return customerLastName;
    }

    public void setCustomerLastName(String customerLastName) {
        this.customerLastName = customerLastName;
    }

    public String getStatusValue() {
        return statusValue;
    }

    public void setStatusValue(String statusValue) {
        this.statusValue = statusValue;
    }

    public Order() {
    }

    public Order(String dateDeliveredToRepair, String datePlannedRepair, String dateStartedRepair, int employeeId, String problemDescription, String repairDescription, int statusId, int vehicleId, double repairCostForCustommer, double costOfParts, double costOfmanHour, int manHour) {
        this.dateDeliveredToRepair = dateDeliveredToRepair;
        this.datePlannedRepair = datePlannedRepair;
        this.dateStartedRepair = dateStartedRepair;
        this.employeeId = employeeId;
        this.problemDescription = problemDescription;
        this.repairDescription = repairDescription;
        this.statusId = statusId;
        this.vehicleId = vehicleId;
        this.repairCostForCustommer = repairCostForCustommer;
        this.costOfParts = costOfParts;
        this.costOfmanHour = costOfmanHour;
        this.manHour = manHour;
    }

    public String getCarBrand() {
        return carBrand;
    }

    public void setCarBrand(String carBrand) {
        this.carBrand = carBrand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getEmployeeFirstName() {
        return employeeFirstName;
    }

    public void setEmployeeFirstName(String employeeFirstName) {
        this.employeeFirstName = employeeFirstName;
    }

    public String getEmployeeLastName() {
        return employeeLastName;
    }

    public void setEmployeeLastName(String employeeLastName) {
        this.employeeLastName = employeeLastName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDateDeliveredToRepair() {
        return dateDeliveredToRepair;
    }

    public void setDateDeliveredToRepair(String dateDeliveredToRepair) {
        this.dateDeliveredToRepair = dateDeliveredToRepair;
    }

    public String getDatePlannedRepair() {
        return datePlannedRepair;
    }

    public void setDatePlannedRepair(String datePlannedRepair) {
        this.datePlannedRepair = datePlannedRepair;
    }

    public String getDateStartedRepair() {
        return dateStartedRepair;
    }

    public void setDateStartedRepair(String dateStartedRepair) {
        this.dateStartedRepair = dateStartedRepair;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getProblemDescription() {
        return problemDescription;
    }

    public void setProblemDescription(String problemDescription) {
        this.problemDescription = problemDescription;
    }

    public String getRepairDescription() {
        return repairDescription;
    }

    public void setRepairDescription(String repairDescription) {
        this.repairDescription = repairDescription;
    }

    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }

    public int getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(int vehicleId) {
        this.vehicleId = vehicleId;
    }

    public double getRepairCostForCustommer() {
        return repairCostForCustommer;
    }

    public void setRepairCostForCustommer(double repairCostForCustommer) {
        this.repairCostForCustommer = repairCostForCustommer;
    }

    public double getCostOfParts() {
        return costOfParts;
    }

    public void setCostOfParts(double costOfParts) {
        this.costOfParts = costOfParts;
    }

    public double getCostOfmanHour() {
        return costOfmanHour;
    }

    public void setCostOfmanHour(double costOfmanHour) {
        this.costOfmanHour = costOfmanHour;
    }

    public int getManHour() {
        return manHour;
    }

    public void setManHour(int manHour) {
        this.manHour = manHour;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", dateDeliveredToRepair='" + dateDeliveredToRepair + '\'' +
                ", datePlannedRepair='" + datePlannedRepair + '\'' +
                ", dateStartedRepair='" + dateStartedRepair + '\'' +
                ", employeeId=" + employeeId +
                ", problemDescription='" + problemDescription + '\'' +
                ", repairDescription='" + repairDescription + '\'' +
                ", status=" + statusId +
                ", vehicleId=" + vehicleId +
                ", repairCostForCustommer=" + repairCostForCustommer +
                ", costOfParts=" + costOfParts +
                ", costOfmanHour=" + costOfmanHour +
                ", manHour=" + manHour +
                '}';
    }
}
