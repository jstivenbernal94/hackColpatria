package com.villa.deimer.hackathon.views.login;

import android.animation.Animator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.villa.deimer.hackathon.R;
import com.villa.deimer.hackathon.models.entities.User;
import com.villa.deimer.hackathon.presenters.login.database.LoginDatabasePresenter;
import com.villa.deimer.hackathon.presenters.login.database.LoginDatabasePresenterImpl;
import com.villa.deimer.hackathon.presenters.login.network.LoginNetworkPresenter;
import com.villa.deimer.hackathon.presenters.login.network.LoginNetworkPresenterImpl;
import com.villa.deimer.hackathon.views.dialogs.MaterialDialog;
import com.villa.deimer.hackathon.views.home.HomeActivity;
import com.villa.deimer.hackathon.views.login.database.LoginDatabaseInterface;
import com.villa.deimer.hackathon.views.login.network.LoginNetworkInterface;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity implements LoginDatabaseInterface, LoginNetworkInterface {

    private Context context;
    private LoginDatabasePresenter loginDatabasePresenter;
    private LoginNetworkPresenter loginNetworkPresenter;
    private MaterialDialog dialog;

    @BindView(R.id.lbl_title)
    TextView lblTitle;
    @BindView(R.id.lbl_subtitle)
    TextView lblSubtitle;
    @BindView(R.id.card_credentials)
    CardView cardCredentials;
    @BindView(R.id.txt_email)
    EditText txtEmail;
    @BindView(R.id.txt_password)
    EditText txtPassword;
    @BindView(R.id.but_login)
    Button fabLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        setupActivity();
    }

    private void setupActivity() {
        context = this;
        loginDatabasePresenter = new LoginDatabasePresenterImpl(context, this);
        loginNetworkPresenter = new LoginNetworkPresenterImpl(this);
        dialog = new MaterialDialog(context);
        animateLabels();
    }

    private void animateLabels() {
        YoYo.with(Techniques.FadeInRight)
                .duration(700)
                .onEnd(new animatorCardviewCallback())
                .playOn(lblTitle);
        lblTitle.setVisibility(View.VISIBLE);
        YoYo.with(Techniques.FadeInRight)
                .duration(700)
                .playOn(lblSubtitle);
        lblSubtitle.setVisibility(View.VISIBLE);
    }

    private class animatorCardviewCallback implements YoYo.AnimatorCallback {
        @Override
        public void call(Animator animator) {
            YoYo.with(Techniques.FadeInLeft)
                    .duration(700)
                    .onEnd(new animatorFabCallback())
                    .playOn(cardCredentials);
            cardCredentials.setVisibility(View.VISIBLE);
        }
    }

    private class animatorFabCallback implements YoYo.AnimatorCallback {
        @SuppressLint("RestrictedApi")
        @Override
        public void call(Animator animator) {
            YoYo.with(Techniques.FadeInUp)
                    .duration(700)
                    .playOn(fabLogin);
            fabLogin.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void resultCreateUser(boolean result, String message) {
        if(result) {
            openHome();
        } else {
            Toast.makeText(context, message, Toast.LENGTH_LONG).show();
        }
    }

    private void openHome() {
        startActivity(new Intent(context, HomeActivity.class));
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        finish();
    }

    @OnClick(R.id.but_login)
    public void clickButLogin() {
        login();
    }

    private void login() {
        String baseUrl = getString(R.string.base_url);
        String username = txtEmail.getText().toString().trim();
        String password = txtPassword.getText().toString().trim();
        if(username.equalsIgnoreCase("") || password.equalsIgnoreCase("")) {
            Toast.makeText(context, "Antes de avanzar, debe agregar sus credenciales", Toast.LENGTH_LONG).show();
        } else {
            dialog.dialogProgress("Iniciando sesión...");
            loginNetworkPresenter.login(baseUrl, username, password);
        }
    }

    @Override
    public void resultLogin(boolean result, String message, User user) {
        dialog.cancelProgress();
        if(result) {
            loginDatabasePresenter.create(user);
        } else {
            Toast.makeText(context, message, Toast.LENGTH_LONG).show();
        }
    }

}
