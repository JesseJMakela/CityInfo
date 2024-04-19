package jes.mchill.cityinfo;

import androidx.lifecycle.ViewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;

public class SharedViewModel extends ViewModel {
    private MutableLiveData<String> selectedCity = new MutableLiveData<>();
    private MutableLiveData<WeatherData> weatherData = new MutableLiveData<>();
    private MutableLiveData<ArrayList<SelfsufficiencyData>> selfSufficiencyData = new MutableLiveData<>();

    private MutableLiveData<ArrayList<PopulationData>> populationData = new MutableLiveData<>();

    // Setter methods
    public void setSelectedCity(String city) {
        selectedCity.setValue(city);
    }

    public void setWeatherData(WeatherData data) {
        weatherData.setValue(data);
    }

    public void setSelfSufficiencyData(ArrayList<SelfsufficiencyData> data) {
        selfSufficiencyData.setValue(data);
    }

    // Getter methods
    public LiveData<String> getSelectedCity() {
        return selectedCity;
    }

    public LiveData<WeatherData> getWeatherData() {
        return weatherData;
    }

    public LiveData<ArrayList<SelfsufficiencyData>> getSelfSufficiencyData() {
        return selfSufficiencyData;
    }

    public void setPopulationData(ArrayList<PopulationData> data) {
        populationData.setValue(data);
    }
    public LiveData<ArrayList<PopulationData>> getPopulationData() {
        return populationData;
    }
}



