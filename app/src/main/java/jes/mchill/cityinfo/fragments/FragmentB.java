package jes.mchill.cityinfo.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Locale;

import jes.mchill.cityinfo.R;
import jes.mchill.cityinfo.SharedViewModel;
import jes.mchill.cityinfo.WeatherData;

public class FragmentB extends Fragment {

    private SharedViewModel sharedViewModel;
    private TextView weatherTextView;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;


    public static FragmentB newInstance(String param1, String param2) {
        FragmentB fragment = new FragmentB();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedViewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_b, container, false);
        weatherTextView = view.findViewById(R.id.weatherTextView);

        sharedViewModel.getWeatherData().observe(getViewLifecycleOwner(), new Observer<WeatherData>() {
            @Override
            public void onChanged(WeatherData weatherData) {
                if (weatherData != null) {
                    double temperatureInCelsius = Double.parseDouble(weatherData.getTemperature()) - 273.15;

                    ImageView weatherImageView = view.findViewById(R.id.weatherImageView);
                    if (weatherData.getMain().toLowerCase(Locale.ROOT).contains("rain")) {
                        weatherImageView.setImageResource(R.drawable.rain);
                    } else if (weatherData.getMain().toLowerCase(Locale.ROOT).contains("cloud")) {
                        weatherImageView.setImageResource(R.drawable.clouds);
                    } else if (weatherData.getMain().toLowerCase(Locale.ROOT).contains("clear")) {
                        weatherImageView.setImageResource(R.drawable.clear_sky);
                    } else if (weatherData.getMain().toLowerCase(Locale.ROOT).contains("sunny")) {
                        weatherImageView.setImageResource(R.drawable.sunny);
                    } else if (weatherData.getMain().toLowerCase(Locale.ROOT).contains("snow")) {
                        weatherImageView.setImageResource(R.drawable.snow);
                    } else if (weatherData.getMain().toLowerCase(Locale.ROOT).contains("storm")) {
                        weatherImageView.setImageResource(R.drawable.thunderstorm);
                    }
                    else {
                        weatherImageView.setImageResource(R.drawable.clear_sky);
                    }

                    TextView locationTextView = view.findViewById(R.id.locationTextView);
                    locationTextView.setText("Location: " + weatherData.getName());

                    TextView weatherTextView = view.findViewById(R.id.weatherTextView);
                    weatherTextView.setText("Weather: " + weatherData.getMain());

                    TextView descriptionTextView = view.findViewById(R.id.descriptionTextView);
                    descriptionTextView.setText("Description: " + weatherData.getDescription());

                    TextView temperatureTextView = view.findViewById(R.id.temperatureTextView);
                    temperatureTextView.setText(String.format("Temperature: %.2f°C", temperatureInCelsius));

                    TextView windSpeedTextView = view.findViewById(R.id.windSpeedTextView);
                    windSpeedTextView.setText("Wind Speed: " + weatherData.getWindSpeed() + " m/s");
                }
            }
        });
        return view;
    }
}


