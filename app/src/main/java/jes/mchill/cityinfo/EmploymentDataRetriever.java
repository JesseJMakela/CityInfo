package jes.mchill.cityinfo;

import android.content.Context;
import android.util.Log;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

public class EmploymentDataRetriever {
    private static final String TAG = "EmploymentData";

    public ArrayList<EmploymentData> getData(Context context, String municipality) {
        Log.d(TAG, "Starting data retrieval for municipality: " + municipality);
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode areas = null;
        URL url;

        try {
            url = new URL("https://pxdata.stat.fi:443/PxWeb/api/v1/fi/StatFin/tyokay/statfin_tyokay_pxt_115x.px");
            areas = objectMapper.readTree(url);
            Log.i(TAG, "Data fetched successfully from URL.");
        } catch (Exception e) {
            Log.e(TAG, "Error fetching data from URL: ", e);
            return null;
        }

        ArrayList<String> keys = new ArrayList<>();
        ArrayList<String> values = new ArrayList<>();
        if (areas != null) {
            for (JsonNode node : areas.get("variables").get(1).get("values")) {
                values.add(node.asText());
            }
            for (JsonNode node : areas.get("variables").get(1).get("valueTexts")) {
                keys.add(node.asText());
            }
        } else {
            Log.e(TAG, "No area data available.");
            return null;
        }

        HashMap<String, String> municipalityCodes = new HashMap<>();
        for (int i = 0; i < keys.size(); i++) {
            municipalityCodes.put(keys.get(i).toLowerCase().trim(), values.get(i));
            Log.d(TAG, "HashMap Entry: " + keys.get(i).toLowerCase().trim() + " = " + values.get(i));
        }

        String normalizedMunicipality = municipality.toLowerCase().trim();
        String code = municipalityCodes.get(normalizedMunicipality);
        if (code == null) {
            Log.e(TAG, "No code found for municipality: " + normalizedMunicipality);
            return null;
        }
        Log.d(TAG, "Municipality code retrieved: " + code);

        try {
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "application/json; utf-8");
            con.setRequestProperty("Accept", "application/json");
            con.setDoOutput(true);

            JsonNode jsonInputString = objectMapper.readTree(context.getResources().openRawResource(R.raw.query3));
            ((ObjectNode) jsonInputString.get("query").get(0).get("selection")).putArray("values").add(code);

            byte[] input = objectMapper.writeValueAsBytes(jsonInputString);
            OutputStream os = con.getOutputStream();
            os.write(input, 0, input.length);
            Log.i(TAG, "Request posted to server with code: " + code);

            BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "utf-8"));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                response.append(line.trim());
            }
            Log.d(TAG, "Response received from server: " + response);

            JsonNode municipalityData = objectMapper.readTree(response.toString());
            ArrayList<EmploymentData> employmentData = new ArrayList<>();
            for (JsonNode node : municipalityData.get("dimension").get("Vuosi").get("category").get("label")) {
                int year = Integer.parseInt(node.asText());
                float employmentRate = Float.parseFloat(municipalityData.get("value").get(node.asText()).asText());
                employmentData.add(new EmploymentData(year, employmentRate));
                Log.d(TAG, "Processed data - Year: " + year + ", Employment Rate: " + employmentRate);
            }
            return employmentData;
        } catch (IOException e) {
            Log.e(TAG, "IOException during POST request: ", e);
            return null;
        }
    }
}
