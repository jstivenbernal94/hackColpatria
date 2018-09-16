package com.villa.deimer.hackathon.models.services.data.remote.products;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import com.villa.deimer.hackathon.models.entities.Product;
import com.villa.deimer.hackathon.models.services.data.remote.ApiService;
import com.villa.deimer.hackathon.presenters.products.network.ProductNetworkPresenter;
import android.support.annotation.NonNull;
import java.util.List;

public class ProductRetrofitAdapter implements Callback<List<Product>> {

    private ProductNetworkPresenter productNetworkPresenter;

    public ProductRetrofitAdapter(ProductNetworkPresenter productNetworkPresenter) {
        this.productNetworkPresenter = productNetworkPresenter;
    }

    private OkHttpClient setupOkHttpClient() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return new OkHttpClient.Builder().addInterceptor(interceptor).build();
    }

    public void getProducts(String baseUrl, int option) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(setupOkHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiService api = retrofit.create(ApiService.class);
        Call<List<Product>> call = api.getProducts(option);
        call.enqueue(this);
    }

    @Override
    public void onResponse(@NonNull Call<List<Product>> call, @NonNull Response<List<Product>> response) {
        if(response.isSuccessful()) {
            List<Product> products = response.body();
            productNetworkPresenter.susccessProducts(products);
        }
    }

    @Override
    public void onFailure(@NonNull Call<List<Product>> call, @NonNull Throwable t) {
        productNetworkPresenter.getDataError(t.getMessage());
    }
}
