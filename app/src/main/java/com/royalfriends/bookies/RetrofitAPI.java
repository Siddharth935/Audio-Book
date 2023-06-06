package com.royalfriends.bookies;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface RetrofitAPI {
    @GET
    Call<NewsModal> getALLNews(@Url String url);

    @GET
    Call<NewsModal>getNewsByCategory(@Url String url);

}

