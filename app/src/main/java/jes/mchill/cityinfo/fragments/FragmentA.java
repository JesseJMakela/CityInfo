package jes.mchill.cityinfo.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Locale;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import jes.mchill.cityinfo.MunicipalityData;
import jes.mchill.cityinfo.MunicipalityDataRetriever;
import jes.mchill.cityinfo.R;
import jes.mchill.cityinfo.SharedViewModel;
import jes.mchill.cityinfo.WeatherData;
import jes.mchill.cityinfo.WeatherDataRetriever;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentA#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentA extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private TextView txtPopulationData;
    private TextView txtWeatherData;
    private EditText editTextLocation;
    private SharedViewModel sharedViewModel;

    public FragmentA() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentA.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentA newInstance(String param1, String param2) {
        FragmentA fragment = new FragmentA();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_a, container, false);
        editTextLocation = view.findViewById(R.id.txtEditLocation);
        Button findBtn = view.findViewById(R.id.findBtn);
        sharedViewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);



        findBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onFindBtnClick(v);
            }
        });
        return view;
    }
    public void onFindBtnClick(View view) {
        Log.d("Lut", "Nappula toimii");
        String location = editTextLocation.getText().toString();

        MunicipalityDataRetriever mr = new MunicipalityDataRetriever();
        WeatherDataRetriever wr = new WeatherDataRetriever();

        ExecutorService service = Executors.newSingleThreadExecutor();
        service.execute(new Runnable() {
            @Override
            public void run() {
                Context context = getContext();
                final ArrayList<MunicipalityData> populationData = mr.getData(context, location);
                final WeatherData weatherData = wr.getWeatherData(location);

                if (getActivity() == null || getActivity().isFinishing())
                    return;

                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        sharedViewModel.setPopulationData(populationData);
                        sharedViewModel.setWeatherData(weatherData);
                        if (txtPopulationData != null) {

                            String s = "";
                            for (MunicipalityData data : populationData) {
                                s += data.getYear() + " : " + data.getPopulation() + "\n";
                        }
                        txtPopulationData.setText(s);
                    } else {
                        Log.e("FragmentA", "TextView txtPopulationData is null.");
                    }

                    if (txtWeatherData != null && weatherData != null) {
                        String weatherInfo = String.format(Locale.getDefault(),
                                "Location: %s\nWeather: %s\nDescription: %s\nTemperature: %s K\nWind speed: %s m/s",
                                weatherData.getName(),
                                weatherData.getMain(),
                                weatherData.getDescription(),
                                weatherData.getTemperature(),
                                weatherData.getWindSpeed());
                        txtWeatherData.setText(weatherInfo);
                    } else {
                        Log.e("FragmentA", "TextView txtWeatherData is null.");
                    }
                Log.d("Lut", "Data haettu");

            }
        });
    }
}); }
}