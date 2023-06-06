package com.royalfriends.bookies;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NewsActivity extends AppCompatActivity {

    private RecyclerView newsRV, categoryRV;
    private ProgressBar loadingPB;
    private ArrayList<Articles> articlesArrayList;
    private ArrayList<CategoryRVModal> categoryRVModalArrayList;
    private CategoryRVAdaptor categoryRVAdaptor;
    private NewsRVAdaptor newsRVAdaptor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        newsRV = findViewById(R.id.idRVNews);
        categoryRV = findViewById(R.id.idRVCategories);
        loadingPB = findViewById(R.id.idPBLoading);
        articlesArrayList = new ArrayList<>();
        categoryRVModalArrayList = new ArrayList<>();
        newsRVAdaptor = new NewsRVAdaptor(articlesArrayList, this);
        categoryRVAdaptor = new CategoryRVAdaptor(categoryRVModalArrayList, this, this::onCategoryClick);
        newsRV.setLayoutManager(new LinearLayoutManager(this));
        newsRV.setAdapter(newsRVAdaptor);
        categoryRV.setAdapter(categoryRVAdaptor);
        getCategories();
        getNews("All");
        newsRVAdaptor.notifyDataSetChanged();


    }

    private void getCategories() {
        categoryRVModalArrayList.add(new CategoryRVModal("All", "https://colorlib.com/wp-content/uploads/sites/2/wordpress-categories.jpg"));
        categoryRVModalArrayList.add(new CategoryRVModal("Technology", "https://images.unsplash.com/photo-1488590528505-98d2b5aba04b?ixid=MnwxMjA3fDB8MHxzZWFyY2h8Mnx8dGVjaG5vbG9neXxlbnwwfHwwfHw%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=500&q=60"));
        categoryRVModalArrayList.add(new CategoryRVModal("Science", "https://media.istockphoto.com/photos/medicine-doctor-holding-hologram-virtual-interface-electronic-medical-picture-id1274407122?b=1&k=20&m=1274407122&s=170667a&w=0&h=NzJ25QLc-fdSeLg_RJAiMaMJOv5Ghj5aDSrxZ93x3YM="));
        categoryRVModalArrayList.add(new CategoryRVModal("Sports", "https://media.istockphoto.com/photos/full-stadium-and-neoned-colorful-flashlights-background-picture-id1276444914?b=1&k=20&m=1276444914&s=170667a&w=0&h=FKHO_16rIS7zdUYBJ0yWMa5MkcAGvgnhDiKOztsbgzg="));
        categoryRVModalArrayList.add(new CategoryRVModal("General", "https://images.unsplash.com/photo-1432821596592-e2c18b78144f?ixid=MnwxMjA3fDB8MHxzZWFyY2h8MTN8fGdlbmVyYWx8ZW58MHx8MHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=500&q=60"));
        categoryRVModalArrayList.add(new CategoryRVModal("Business", "https://media.istockphoto.com/photos/business-development-to-success-and-growing-growth-concept-pointing-picture-id1145631842?k=20&m=1145631842&s=612x612&w=0&h=fkLeeD7b0fV5KJgDRuDOA3vmTyNB8n5f5gLlmk785OQ="));
        categoryRVModalArrayList.add(new CategoryRVModal("Entertainment", "https://media.istockphoto.com/photos/media-concept-smart-tv-picture-id503685712?b=1&k=20&m=503685712&s=170667a&w=0&h=01OPzR5XIyg3HQF5ziFyD566oMIE9InO8oB6GdbQe_M="));
        categoryRVModalArrayList.add(new CategoryRVModal("Health", "https://media.istockphoto.com/photos/cheerful-fit-couple-on-white-background-picture-id1287910461?b=1&k=20&m=1287910461&s=170667a&w=0&h=A4HaJr7JSonN79ACVIjM-dP1WEHocwfPnKR95C0PzMQ="));
        categoryRVAdaptor.notifyDataSetChanged();
    }

    private void getNews(String category) {
        loadingPB.setVisibility(View.VISIBLE);
        articlesArrayList.clear();
        String categoryURL = "https://newsapi.org/v2/top-headlines?country=in&category=" + category + "&apiKey=594b7b2bdef84c4a9150b3843f38be84";
        String url = "https://newsapi.org/v2/top-headlines?country=in&excludeDomain=stackoverflow.com&sortBy=publishedAT&language=en&apiKey=594b7b2bdef84c4a9150b3843f38be84";
        String BASE_URL = "https://newsapi.org/";
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RetrofitAPI retrofitAPI = retrofit.create(RetrofitAPI.class);
        Call<NewsModal> call;
        if (category.equals("All")) {
            call = retrofitAPI.getALLNews(url);
        } else {
            call = retrofitAPI.getNewsByCategory(categoryURL);
        }

        call.enqueue(new Callback<NewsModal>() {
            @Override
            public void onResponse(Call<NewsModal> call, Response<NewsModal> response) {
                NewsModal newsModal = response.body();
                loadingPB.setVisibility(View.GONE);
                ArrayList<Articles> articles = newsModal.getArticles();
                for (int i = 0; i < articles.size(); i++) {
                    articlesArrayList.add(new Articles(articles.get(i).getTitle(), articles.get(i).getDescription(), articles.get(i).
                            getUrlToImage(), articles.get(i).getUrl(), articles.get(i).getContent()));
                }

                newsRVAdaptor.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<NewsModal> call, Throwable t) {
                Toast.makeText(NewsActivity.this, "FAIL TO GET NEWS", Toast.LENGTH_SHORT).show();

            }
        });

    }
    public void onCategoryClick(int position) {
        String category = categoryRVModalArrayList.get(position).getCategory();
        getNews(category);
    }
}