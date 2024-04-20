package jes.mchill.cityinfo;

public class EmploymentData {

    private int year;
    private double employmentRate;
    private String municipalityCode;

    // No-argument constructor
    public EmploymentData() {
        // Initialize with default values if necessary
    }

    // Constructor with arguments
    public EmploymentData(int year, double employmentRate) {
        this.year = year;
        this.employmentRate = employmentRate;
    }

    // Getters and setters for year
    public int getEmploymentYear() {
        return year;
    }

    public void setEmploymentYear(int year) {
        this.year = year;
    }

    // Getters and setters for employment rate
    public double getEmploymentRate() {
        return employmentRate;
    }

    public void setEmploymentRate(double employmentRate) {
        this.employmentRate = employmentRate;
    }

    // Getters and setters for municipality code
    public String getMunicipalityCode() {
        return municipalityCode;
    }

    public void setMunicipalityCode(String municipalityCode) {
        this.municipalityCode = municipalityCode;
    }
}
