package com.ceylonapz.nytimes.server;

import com.ceylonapz.nytimes.model.ApiResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by AMAL on 2018-08-08.
 */

public interface ApiInterface {

    @GET("mostpopular/v2/mostviewed/{section}/{period}.json")
    Call<ApiResponse> getNewsDetails(@Path("section") String section, @Path("period") String period,
                                     @Query("api-key") String apiKey);
}
