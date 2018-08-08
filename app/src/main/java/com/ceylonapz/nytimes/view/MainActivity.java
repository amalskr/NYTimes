package com.ceylonapz.nytimes.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.ceylonapz.nytimes.NewsApplication;
import com.ceylonapz.nytimes.R;
import com.ceylonapz.nytimes.model.ApiResponse;
import com.ceylonapz.nytimes.model.News;
import com.ceylonapz.nytimes.server.ApiClient;
import com.ceylonapz.nytimes.server.ApiInterface;
import com.ceylonapz.nytimes.utility.Constant;
import com.ceylonapz.nytimes.utility.Util;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private TextView noDataTv;
    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private Type listType;
    private Gson gson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inits();

        if (Util.isOnline(this)) {
            callApi("all-sections", "1"); // for section - all-sections, sports, international
        } else {
            Toast.makeText(MainActivity.this, "No network connection. Loaded Offline data", Toast.LENGTH_LONG).show();
            getSavedData();
        }
    }

    private void inits() {
        listType = new TypeToken<List<News>>() {
        }.getType();
        gson = new Gson();

        progressBar =  findViewById(R.id.progressBar);
        noDataTv =  findViewById(R.id.textViewNoData);
        recyclerView =  findViewById(R.id.recycler_news);
    }

    private void callApi(String section, String period) {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<ApiResponse> call = apiService.getNewsDetails(section, period, Constant.API_KEY);
        call.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {

                int statusCode = response.code();

                if (statusCode == 200) {
                    List<News> newsList = response.body().getResults();
                    saveData(newsList);
                } else {
                    showError("Server Problem. Try again!");
                }
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                showError(t.getMessage());
            }
        });
    }

    private void saveData(List<News> newsList) {
        //LIST DATA CONVERT TO GSON STRING
        String gsonStr = gson.toJson(newsList, listType);

        //SAVE IN SHARED-PREFERENCE
        NewsApplication.setNewsList(this, gsonStr);

        getSavedData();
    }

    private void getSavedData() {
        //GET SAVED DATA
        String gsonList = NewsApplication.getNewsList(this);

        if (!gsonList.equals("n/a")) {
            //CONVERT TO LIST
            List<News> newsList = gson.fromJson(gsonList, listType);

            setupRecycler(newsList);
        } else {
            showError("No saved news to display...!");
        }
    }

    private void setupRecycler(List<News> dataList) {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        recyclerView.setAdapter(new NewsAdapter(this, dataList));

        assert dataList != null;
        if (dataList.size() > 0) {
            dataVisible();
        } else {
            showError("No News..!");
        }
    }

    private void showError(String message) {
        noDataTv.setText(message);

        progressBar.setVisibility(View.GONE);
        recyclerView.setVisibility(View.GONE);
        noDataTv.setVisibility(View.VISIBLE);
    }

    private void dataVisible() {
        noDataTv.setVisibility(View.GONE);
        progressBar.setVisibility(View.GONE);
        recyclerView.setVisibility(View.VISIBLE);
    }


}
