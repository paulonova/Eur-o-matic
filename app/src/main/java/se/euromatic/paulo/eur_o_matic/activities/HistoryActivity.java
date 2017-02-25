package se.euromatic.paulo.eur_o_matic.activities;

import android.app.ProgressDialog;
import android.content.Context;
import android.icu.text.DateFormat;
import android.icu.text.RelativeDateTimeFormatter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import se.euromatic.paulo.eur_o_matic.R;
import se.euromatic.paulo.eur_o_matic.adapter.HistoryAdapter;
import se.euromatic.paulo.eur_o_matic.adapter.MainValueAdapter;
import se.euromatic.paulo.eur_o_matic.objects.ExchangeObject;
import se.euromatic.paulo.eur_o_matic.objects.Helper;

public class HistoryActivity extends AppCompatActivity {

    @BindView(R.id.recyclerViewHistoryValues)  RecyclerView recyclerViewHistoryValues;
    @BindView(R.id.textViewCode)  TextView textViewCode;

    private final static String CURRENCY_BASE_HISTORY = "http://api.fixer.io/";  // "http://api.fixer.io/" + date + "?" + "symbols=" + code

    ArrayList<ExchangeObject> RetroactiveDates;
    private RecyclerView.LayoutManager layoutManager;
    private HistoryAdapter historyAdapter;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ButterKnife.bind(this);

        textViewCode.setText(getBundleExtraFromIntent());
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Helper.exchangeObjectHistoricList.clear();
                getAllDates(10);
            }
        });

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage(getString(R.string.history_load));
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();

        historyAdapter = new HistoryAdapter(this);
        historyAdapter.notifyDataSetChanged();
        layoutManager = new LinearLayoutManager(this);
        recyclerViewHistoryValues.setHasFixedSize(true);
        recyclerViewHistoryValues.setLayoutManager(layoutManager);
        recyclerViewHistoryValues.setAdapter(historyAdapter);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(HistoryActivity.this, "10 more rates were added!", Toast.LENGTH_SHORT).show();
                int number = Helper.exchangeObjectHistoricList.size() + 10;
                Helper.exchangeObjectHistoricList.clear();
                getAllDates(number);
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }


    public String getBundleExtraFromIntent() {
        String getExtra = "No Value";
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            getExtra = extras.getString(MainValueAdapter.VALUE_CODE);
        }

        return getExtra;
    }


    public void getAllDates(int days) {

        for (int i = 1; i <= days; i++) {
            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.DATE, -i);
            java.text.DateFormat dateFormat = android.text.format.DateFormat.getDateFormat(this);
            final String date = dateFormat.format(cal.getTime());

            getHistoricalValuesFromCode(date);

            Log.d("DateCalendar", "date: " + date);
        }


    }


    public void getHistoricalValuesFromCode(String date) {

        if (isNetworkAvailable()) {

            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(CURRENCY_BASE_HISTORY + date + "?" + "symbols=" + getBundleExtraFromIntent())
                    .build();

            Call call = client.newCall(request);
            call.enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(HistoryActivity.this, "Something get wrong! please try again..", Toast.LENGTH_SHORT).show();
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

                            String date = jsonDataObject.getString("date");
                            Log.d("JasonDate", " Date: " + date);
                            Helper.exchangeObjectHistoricList.add(new ExchangeObject(rates.getDouble(getBundleExtraFromIntent()),date));
                            Helper.getExchangeObjectHistoricList();
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    historyAdapter.notifyDataSetChanged();
                                    if(progressDialog.isShowing()){
                                        progressDialog.dismiss();
                                    }
                                }
                            });


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });

        } else {
            Toast.makeText(HistoryActivity.this, "Network is not available! Check your connections..", Toast.LENGTH_LONG).show();
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
