package jes.mchill.cityinfo;

public class PopulationData {

    private int year;
    private int population;

    public PopulationData(int year, int population) {
        this.year = year;
        this.population = population;
    }


    public int getPopulationYear() {
        return year;
    }
    public void setYear(int year) {
        this.year = year;
    }
    public int getPopulation() {

        return population;
    }
    public void setPopulation(int population) {

        this.population = population;
    }

}
