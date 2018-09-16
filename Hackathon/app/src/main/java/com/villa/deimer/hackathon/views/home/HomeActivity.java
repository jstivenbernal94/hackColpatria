package com.villa.deimer.hackathon.views.home;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import com.squareup.otto.Subscribe;
import com.villa.deimer.hackathon.R;
import com.villa.deimer.hackathon.models.entities.User;
import com.villa.deimer.hackathon.models.services.events.EventDialogMessage;
import com.villa.deimer.hackathon.models.services.events.StationBus;
import com.villa.deimer.hackathon.presenters.home.database.HomeDatabasePresenter;
import com.villa.deimer.hackathon.presenters.home.database.HomeDatabasePresenterImpl;
import com.villa.deimer.hackathon.views.dialogs.MaterialDialog;
import com.villa.deimer.hackathon.views.home.database.HomeDatabaseInterface;
import com.villa.deimer.hackathon.views.login.LoginActivity;
import com.villa.deimer.hackathon.views.products.ProductsActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HomeActivity extends AppCompatActivity implements HomeDatabaseInterface {

    private Context context;
    private HomeDatabasePresenter homeDatabasePresenter;
    private User user;

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.lbl_points)
    TextView lblPoints;
    @BindView(R.id.lbl_name)
    TextView lblName;
    @BindView(R.id.lbl_expiration_points)
    TextView lblExpirationPoints;
    @BindView(R.id.lbl_expiration_date)
    TextView lblExpirationDate;

    public void onStart() {
        super.onStart();
        StationBus.getBus().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        StationBus.getBus().unregister(this);
    }

    @Subscribe
    public void recievedMessage(EventDialogMessage eventDialogMessage){
        int option = eventDialogMessage.getOption();
        if(option == 1) {
            boolean success = eventDialogMessage.isSuccess();
            if(success) {
                homeDatabasePresenter.logout(user);
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        setupActivity();
    }

    private void setupActivity() {
        context = this;
        homeDatabasePresenter = new HomeDatabasePresenterImpl(context, this);
        homeDatabasePresenter.showUser();
        setupToolbar();
    }

    public void setupToolbar() {
        setSupportActionBar(toolbar);
        toolbar.setTitle("Home");
    }

    @Override
    public void resultShowUser(User userDB) {
        user = userDB;
        loadDatauser();
    }

    @SuppressLint("SetTextI18n")
    private void loadDatauser() {
        lblPoints.setText(""+user.getTotalPoints());
        lblName.setText(user.getName());
        lblExpirationPoints.setText(user.getExpirationPoints());
        lblExpirationDate.setText("Vencen el 15/10/2018");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_logout:
                actionLogout();
                break;
        }
        return true;
    }

    private void actionLogout() {
        MaterialDialog dialog = new MaterialDialog(context);
        dialog.createDialogQuestion("¿Está seguro que desea cerrar la sesión?");
    }

    @Override
    public void resultLogout(boolean result) {
        if(result) {
            openLogin();
        } else {
            String message = "Error al cerrar sesión.";
            Snackbar.make(toolbar, message, Snackbar.LENGTH_LONG).show();
        }
    }

    private void openLogin() {
        startActivity(new Intent(context, LoginActivity.class));
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        finish();
    }


    @OnClick(R.id.card_one)
    public void openCardOne() {
        openCard(1, "Servicios");
    }

    @OnClick(R.id.card_two)
    public void openCardTwo() {
        openCard(2, "Comida");
    }

    @OnClick(R.id.card_three)
    public void openCardThree() {
        openCard(3, "Ropa");
    }

    private void openCard(int option, String title) {
        Intent intent = new Intent(context, ProductsActivity.class);
        intent.putExtra("option", option);
        intent.putExtra("title", title);
        startActivity(intent);
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }

}
