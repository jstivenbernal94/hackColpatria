package com.villa.deimer.hackathon.models.services.data.remote.login;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Callback;
import retrofit2.Retrofit;
import okhttp3.OkHttpClient;
import android.support.annotation.Nullable;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.converter.gson.GsonConverterFactory;
import com.villa.deimer.hackathon.models.entities.User;
import com.villa.deimer.hackathon.models.services.data.remote.ApiService;
import com.villa.deimer.hackathon.presenters.login.network.LoginNetworkPresenter;

public class LoginRetrofitAdapter implements Callback<User> {

    private LoginNetworkPresenter loginNetworkPresenter;

    public LoginRetrofitAdapter(LoginNetworkPresenter loginNetworkPresenter) {
        this.loginNetworkPresenter = loginNetworkPresenter;
    }

    private OkHttpClient setupOkHttpClient() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return new OkHttpClient.Builder().addInterceptor(interceptor).build();
    }

    public void login(String baseUrl, String username, String password) {
        String platform = "m";
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(setupOkHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiService api = retrofit.create(ApiService.class);
        Call<User> call = api.login(username, password, platform);
        call.enqueue(this);
    }

    @Override
    public void onResponse(@Nullable  Call<User> call, @Nullable Response<User> response) {
        assert response != null;
        if(response.isSuccessful()) {
            User user = response.body();
            assert user != null;
            if(user.getUser_id() > 0) {
                loginNetworkPresenter.resultLogin(response.isSuccessful(), "", user);
            } else {
                String message = "Usuario o contraseña incorrecta.";
                loginNetworkPresenter.resultLogin(false, message, null);
            }
        } else {
            String message = "Data not found.";
            loginNetworkPresenter.resultLogin(response.isSuccessful(), message, null);
        }
    }

    @Override
    public void onFailure(@Nullable Call<User> call, @Nullable Throwable t) {
        assert t != null;
        String message = t.getMessage();
        loginNetworkPresenter.resultLogin(false, message, null);
    }

}
