package jes.mchill.cityinfo;

public class EmploymentData {

    private int year;
    private double employmentRate;

    public EmploymentData() {
    }

    public EmploymentData(int year, double employmentRate) {
        this.year = year;
        this.employmentRate = employmentRate;
    }

    public int getEmploymentYear() {
        return year;
    }

    public void setEmploymentYear(int year) {
        this.year = year;
    }

    public double getEmploymentRate() {
        return employmentRate;
    }

    public void setEmploymentRate(double employmentRate) {
        this.employmentRate = employmentRate;
    }


}
