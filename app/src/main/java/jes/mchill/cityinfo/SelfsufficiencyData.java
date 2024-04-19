package jes.mchill.cityinfo;

public class SelfsufficiencyData {

    private int year;
    private float workplaceSelfSufficiency;

    public SelfsufficiencyData(int year, float workplaceSelfSufficiency) {
        this.year = year;
        this.workplaceSelfSufficiency = workplaceSelfSufficiency;
    }


    public int getYear() {
        return year;
    }
    public void setYear(int year) {
        this.year = year;
    }
    public float getWorkplaceSelfSufficiency() {

        return workplaceSelfSufficiency;
    }
    public void setWorkplaceSelfSufficiency(float workplaceSelfSufficiency) {

        this.workplaceSelfSufficiency = workplaceSelfSufficiency;
    }

}
