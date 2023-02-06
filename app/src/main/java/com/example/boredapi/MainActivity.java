package com.example.boredapi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.boredapi.interfaces.RetrofitAPI;
import com.example.boredapi.model.Maquina;
import com.example.boredapi.model.Registro;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    Button botaoPost;
    Button botaoGet;
    ProgressBar progressBar;

    OkHttpClient client = new OkHttpClient();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.GONE);

        botaoPost = findViewById(R.id.button);

        Registro reg = new Registro(5,9,1,1);

        botaoPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                postData(reg);
            }
        });

        botaoGet = findViewById(R.id.button2);

        botaoGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getMaquians();
            }
        });
    }

    public void postData (Registro reg) {

        progressBar.setVisibility(View.VISIBLE);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://apiapontamendes-production.up.railway.app/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RetrofitAPI retrofitAPI = retrofit.create(RetrofitAPI.class);

        Call<Registro> call = retrofitAPI.createPost(reg);

        call.enqueue(new Callback<Registro>() {
            @Override
            public void onResponse(Call<Registro> call, Response<Registro> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(MainActivity.this, "Data added to API", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Data NOT added to API", Toast.LENGTH_SHORT).show();
                }

                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<Registro> call, Throwable t) {
                Toast.makeText(MainActivity.this, "erro", Toast.LENGTH_SHORT).show();
                progressBar.setVisibility(View.GONE);
                Log.e("Erro",t.getMessage());
            }
        });
    }

    private void get() {

        ProgressDialog pd = new ProgressDialog(MainActivity.this);
        pd.setMessage("Carregando...");
        pd.setCancelable(false);
        pd.show();

        Request request = new Request.Builder()
                .url("https://apiapontamendes-production.up.railway.app/maquinas")
                .build();

        client.newCall(request).enqueue(new okhttp3.Callback() {
            @Override
            public void onFailure(@NonNull okhttp3.Call call, @NonNull IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(@NonNull okhttp3.Call call, @NonNull okhttp3.Response response) throws IOException {
                pd.dismiss();
                if (response.isSuccessful()) {


                    String arr = response.body().string();
                    JSONArray jsonArray = null;

                    try {
                        jsonArray = new JSONArray(arr);

                        for (int i = 0; i < jsonArray.length(); i++) {

                            JSONObject obj = jsonArray.getJSONObject(i);
                            Maquina maq = new Maquina(obj.getInt("ID_MAQ"),obj.getString("DESQ_MAQ"));
                            System.out.println(maq);
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                } else {
                    Log.e("erro","deu ruim");
                }
            }
        });
    }

    private void getMaquians () {

        progressBar.setVisibility(View.VISIBLE);

        Call<List<Maquina>> call = RetrofitClient.getInstancia().getApi().getMaquinas();

        call.enqueue(new Callback<List<Maquina>>() {
            @Override
            public void onResponse(Call<List<Maquina>> call, Response<List<Maquina>> response) {

                if (response.isSuccessful()) {
                    List<Maquina> maquinas = response.body();
                    String [] maq = new String[maquinas.size()];

                    for (int i = 0; i < maquinas.size(); i++) {
                        maq[i] = maquinas.get(i).getDescMaq();
                        System.out.println(maq[i]);
                    }
                } else {
                    Toast.makeText(MainActivity.this, "Erro ao buscar os dados", Toast.LENGTH_SHORT).show();
                }
                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<List<Maquina>> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
            }
        });
    }

}