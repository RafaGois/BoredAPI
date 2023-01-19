package com.example.boredapi;

import android.os.AsyncTask;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class BuscaDados extends AsyncTask<Void,Void,String> {

    TextView txtAtividades;
    ProgressBar progressBar;

    public BuscaDados(TextView txtAtividades, ProgressBar progressBar) {
        this.txtAtividades = txtAtividades;
        this.progressBar = progressBar;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

    }

    @Override
    protected String doInBackground(Void... voids) {
        try {
            URL url = new URL("https://www.boredapi.com/api/activity");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            InputStream inputStream = connection.getInputStream();

            if (inputStream == null) {
                return "Dados não encontrados";
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            StringBuilder stringBuilder = new StringBuilder();

            while ((line=br.readLine()) != null) {
                stringBuilder.append(line);
            }

            return stringBuilder.toString();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);

        progressBar.setVisibility(View.INVISIBLE);

        if (s.equalsIgnoreCase("Dados não encontrados")) {
            txtAtividades.setText("Dados não retorados por alguma razão");
        } else {

            try {
                JSONObject root = new JSONObject(s);
                String activity = root.getString("activity");
                txtAtividades.setText(activity);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
