package se.euromatic.paulo.eur_o_matic.activities;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import se.euromatic.paulo.eur_o_matic.R;
import se.euromatic.paulo.eur_o_matic.adapter.MainValueAdapter;
import se.euromatic.paulo.eur_o_matic.objects.ExchangeObject;
import se.euromatic.paulo.eur_o_matic.objects.Helper;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.recyclerViewMainValues)  RecyclerView recyclerViewMainValues;

    private MainValueAdapter valueAdapter;
    private RecyclerView.LayoutManager layoutManager;

    private final static String CURRENCY_URL = "http://api.fixer.io/latest?base=eur";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        getExchangeValues();

        valueAdapter = new MainValueAdapter(this);
        valueAdapter.notifyDataSetChanged();
        layoutManager = new LinearLayoutManager(this);
        recyclerViewMainValues.setHasFixedSize(true);
        recyclerViewMainValues.setLayoutManager(layoutManager);
        recyclerViewMainValues.setAdapter(valueAdapter);

    }


    public void getExchangeValues() {

        if (isNetworkAvailable()) {

            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(CURRENCY_URL)
                    .build();

            Call call = client.newCall(request);

            call.enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(MainActivity.this, "Something get wrong! please try again..", Toast.LENGTH_SHORT).show();
                        }
                    });

                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {

                    String jsonData = response.body().string();

                    if (response.isSuccessful()) {

                        Log.d("exchange_info", "SUCCESS: " + jsonData);
                        try {

                            JSONObject jsonDataObject = new JSONObject(jsonData);
                            final JSONObject rates = jsonDataObject.getJSONObject("rates");

//                            final String base = jsonDataObject.getString("base");
//                            final String date = jsonDataObject.getString("date");

                            Helper.saveJsonRatesInArrayList(rates);
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    valueAdapter.notifyDataSetChanged();
                                }
                            });


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
        }else {
            Toast.makeText(MainActivity.this, "Network is not available! Check your connections..", Toast.LENGTH_LONG).show();
        }
    }


    /**
     * To check if the device is currently connected to the Internet
     */
    private boolean isNetworkAvailable() {
        ConnectivityManager manager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();

        boolean isAvailable = false;
        if (networkInfo != null && networkInfo.isConnected()) {
            isAvailable = true;
        }

        return isAvailable;
    }

}
