package jes.mchill.cityinfo;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import jes.mchill.cityinfo.fragments.FragmentA;
import jes.mchill.cityinfo.fragments.FragmentB;
import jes.mchill.cityinfo.fragments.FragmentC;

public class MainActivity extends AppCompatActivity {
    private TextView txtPopulationData;
    private TextView txtWeatherData;
    private EditText editTextLocation;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtPopulationData = findViewById(R.id.txtPopulation);
        txtWeatherData = findViewById(R.id.txtWeather);
        editTextLocation = findViewById(R.id.txtEditLocation);

        Button fragmentA = findViewById(R.id.btnA);
        Button fragmentB = findViewById(R.id.btnB);
        Button fragmentC = findViewById(R.id.btnC);

        fragmentA.setOnClickListener(listener);
        fragmentB.setOnClickListener(listener);
        fragmentC.setOnClickListener(listener);


    }

    private View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Fragment fragment;

            if (view.getId() == R.id.btnA) {
                fragment = new FragmentA();
            } else if (view.getId() == R.id.btnB) {
                fragment = new FragmentB();
            } else if (view.getId() == R.id.btnC) {
                fragment = new FragmentC();
            } else {
                fragment = new FragmentA();
            }
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.frame, fragment)
                    .commit();
        }
    };
    public void onFindBtnClick(View view) {
        Log.d("Lut", "Nappula toimii");
        Context context = this;

        MunicipalityDataRetriever mr = new MunicipalityDataRetriever();
        WeatherDataRetriever  wr = new WeatherDataRetriever();

        String location = editTextLocation.getText().toString();

        ExecutorService service = Executors.newSingleThreadExecutor();
        service.execute(new Runnable() {
            @Override
            public void run() {
                ArrayList<MunicipalityData> populationData = mr.getData(context, location);
                WeatherData weatherData = wr.getWeatherData(location);
                if(populationData == null) {
                    return;
                }

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        String s ="";
                        for(MunicipalityData data : populationData) {
                            s += data.getYear() + " : " + data.getPopulation() + "\n";
                        }
                        txtPopulationData.setText(s);
                    }
                });

                Log.d("Lut", "Data haettu");

            }
});




    }
}
