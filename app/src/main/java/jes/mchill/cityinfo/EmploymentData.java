package jes.mchill.cityinfo;

public class EmploymentData {

    private int year;
    private float employmentRate;

    public EmploymentData(int year, float employmentRate) {
        this.year = year;
        this.employmentRate = employmentRate;
    }


    public int getYear() {
        return year;
    }
    public void setYear(int year) {
        this.year = year;
    }
    public float getEmploymentRate() {

        return employmentRate;
    }
    public void setEmploymentRate(float employmentRate) {

        this.employmentRate = employmentRate;
    }
    public float getEmployementRate() {
        return employmentRate;
    }
    public int getEmployementYear() {
        return year;
    }
}

