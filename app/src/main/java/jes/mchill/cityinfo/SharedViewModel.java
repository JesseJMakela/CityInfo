package jes.mchill.cityinfo;

import androidx.lifecycle.ViewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;

public class SharedViewModel extends ViewModel {
    private MutableLiveData<String> selectedCity = new MutableLiveData<>();
    private MutableLiveData<WeatherData> weatherData = new MutableLiveData<>();
    private MutableLiveData<ArrayList<MunicipalityData>> populationData = new MutableLiveData<>();

    // Setter methods
    public void setSelectedCity(String city) {
        selectedCity.setValue(city);
    }

    public void setWeatherData(WeatherData data) {
        weatherData.setValue(data);
    }

    public void setPopulationData(ArrayList<MunicipalityData> data) {
        populationData.setValue(data);
    }

    // Getter methods
    public LiveData<String> getSelectedCity() {
        return selectedCity;
    }

    public LiveData<WeatherData> getWeatherData() {
        return weatherData;
    }

    public LiveData<ArrayList<MunicipalityData>> getPopulationData() {
        return populationData;
    }
}

