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

    private static final String TAG = "EmploymentRateData";

    public ArrayList<EmploymentData> getData(Context context, String municipality) {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode areas = null;
        ArrayList<EmploymentData> employmentDataList = new ArrayList<>();

        try {
            areas = objectMapper.readTree(new URL("https://pxdata.stat.fi/PxWeb/api/v1/fi/StatFin/tyokay/statfin_tyokay_pxt_115x.px"));
            Log.d(TAG, "Areas data: " + areas.toPrettyString());
            if (areas == null) {
                Log.e(TAG, "Failed to load data from API.");
                return null;
            }
        } catch (Exception e) {
            Log.e(TAG, "Error fetching API data", e);
            return null;
        }

        ArrayList<String> keys = new ArrayList<>();
        ArrayList<String> values = new ArrayList<>();

        for (JsonNode node : areas.get("variables").get(0).get("values")) {
            values.add(node.asText());
        }
        for (JsonNode node : areas.get("variables").get(0).get("valueTexts")) {
            keys.add(node.asText());
        }




        Log.d(TAG, "Keys: " + keys.toString());
        Log.d(TAG, "Values: " + values.toString());

        if (keys.isEmpty() || values.isEmpty()) {
            Log.e(TAG, "No data found in 'values' or 'valueTexts'.");
            return null;
        }

        HashMap<String, String> municipalityCodes = new HashMap<>();
        for (int i = 0; i < keys.size(); i++) {
            municipalityCodes.put(keys.get(i), values.get(i));
        }

        Log.d(TAG, "Municipality codes: " + municipalityCodes.toString());

        String code = null;
        code = municipalityCodes.get(municipality);
        if (code == null) {
            Log.e(TAG, "Municipality code not found.");
            return null;
        }
        Log.d(TAG, "Data code retrieved: " + code);

        try {
            URL url = new URL("https://pxdata.stat.fi/PxWeb/api/v1/fi/StatFin/tyokay/statfin_tyokay_pxt_115x.px");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "application/json; utf-8");
            con.setRequestProperty("Accept", "application/json");
            con.setDoOutput(true);

            JsonNode jsonInputString = objectMapper.readTree(context.getResources().openRawResource(R.raw.query3));
            ((ObjectNode) jsonInputString.get("query").get(1).get("selection")).putArray("values").add(code);

            byte[] input = objectMapper.writeValueAsBytes(jsonInputString);
            OutputStream os = con.getOutputStream();
            os.write(input, 0, input.length);
            Log.i(TAG, "Posted data to server.");

            BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "utf-8"));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                response.append(line.trim());
            }

            JsonNode municipalityData = objectMapper.readTree(response.toString());
            if (municipalityData == null) {
                Log.e(TAG, "Failed to parse response data.");
                return null;
            }

            ArrayList<String> years = new ArrayList<>();
            ArrayList<String> employmentRates = new ArrayList<>();
            JsonNode yearsNode = municipalityData.path("dimension").path("Vuosi").path("category").path("label");
            JsonNode ratesNode = municipalityData.path("value");

            if (yearsNode.isMissingNode() || ratesNode.isMissingNode()) {
                Log.e(TAG, "Missing year or rate data in the response.");
                return null;
            }

            yearsNode.forEach(node -> years.add(node.asText()));
            ratesNode.forEach(node -> employmentRates.add(node.asText()));

            for (int i = 0; i < years.size(); i++) {
                employmentDataList.add(new EmploymentData(Integer.valueOf(years.get(i)), Float.parseFloat(employmentRates.get(i))));
                Log.d(TAG, "Year: " + years.get(i) + ", Employment rate: " + employmentRates.get(i) + "%");
            }
            return employmentDataList;
        } catch (IOException e) {
            Log.e(TAG, "Error in API communication", e);
            return null;
        }
    }
}
