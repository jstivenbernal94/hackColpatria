package com.villa.deimer.hackathon.views.welcome;

import android.animation.Animator;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.villa.deimer.hackathon.R;
import com.villa.deimer.hackathon.models.entities.User;
import com.villa.deimer.hackathon.presenters.home.database.HomeDatabasePresenter;
import com.villa.deimer.hackathon.presenters.home.database.HomeDatabasePresenterImpl;
import com.villa.deimer.hackathon.views.home.HomeActivity;
import com.villa.deimer.hackathon.views.home.database.HomeDatabaseInterface;
import com.villa.deimer.hackathon.views.login.LoginActivity;
import butterknife.BindView;
import butterknife.ButterKnife;

public class WelcomeActivity extends AppCompatActivity implements HomeDatabaseInterface {

    private Context context;
    private HomeDatabasePresenter homeDatabasePresenter;

    @BindView(R.id.img_logo)
    ImageView imgLogo;
    @BindView(R.id.img_logo_two)
    ImageView imgLogoTwo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        ButterKnife.bind(this);
        setupActivity();
    }

    private void setupActivity() {
        context = this;
        homeDatabasePresenter = new HomeDatabasePresenterImpl(
                context, this
        );
        animateLogo();
    }

    private void animateLogo() {
        YoYo.with(Techniques.FadeInLeft)
                .duration(1300)
                .repeat(0)
                .playOn(imgLogo);
        YoYo.with(Techniques.FadeInRight)
                .duration(1300)
                .repeat(0)
                .onEnd(new animatorCallback())
                .playOn(imgLogoTwo);
    }

    private class animatorCallback implements YoYo.AnimatorCallback {
        @Override
        public void call(Animator animator) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    homeDatabasePresenter.showUser();
                }
            }, 1300);
        }
    }

    @Override
    public void resultShowUser(User user) {
        if(user != null) {
            startActivity(new Intent(context, HomeActivity.class));
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            finish();
        } else {
            startActivity(new Intent(context, LoginActivity.class));
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            finish();
        }
    }

    @Override
    public void resultLogout(boolean result) {}
}
