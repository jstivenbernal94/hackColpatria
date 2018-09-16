package com.villa.deimer.hackathon.views.products;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.villa.deimer.hackathon.R;
import com.villa.deimer.hackathon.models.entities.Product;
import com.villa.deimer.hackathon.models.entities.User;
import com.villa.deimer.hackathon.presenters.home.database.HomeDatabasePresenter;
import com.villa.deimer.hackathon.presenters.home.database.HomeDatabasePresenterImpl;
import com.villa.deimer.hackathon.presenters.products.network.ProductNetworkPresenter;
import com.villa.deimer.hackathon.presenters.products.network.ProductNetworkPresenterImpl;
import com.villa.deimer.hackathon.views.home.database.HomeDatabaseInterface;
import com.villa.deimer.hackathon.views.products.adapter.ProductRecyclerAdapter;
import com.villa.deimer.hackathon.views.products.network.ProductNetworkInterface;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;

public class ProductsActivity extends AppCompatActivity implements ProductNetworkInterface, HomeDatabaseInterface {

    private Context context;
    private ProductNetworkPresenter productNetworkPresenter;
    private User user;
    private int option;
    private String title;

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.lbl_points)
    TextView lblPoints;
    @BindView(R.id.lbl_name)
    TextView lblName;
    @BindView(R.id.recycler)
    RecyclerView recycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);
        ButterKnife.bind(this);
        setupActivity();
    }

    private void setupActivity() {
        context = this;
        productNetworkPresenter = new ProductNetworkPresenterImpl(this);
        HomeDatabasePresenter homeDatabasePresenter = new HomeDatabasePresenterImpl(context, this);
        homeDatabasePresenter.showUser();
        getExtras();
        setupToolbar();
    }

    private void getExtras() {
        Bundle extras = getIntent().getExtras();
        assert extras != null;
        option = extras.getInt("option");
        title = extras.getString("title");
        getData();
    }

    private void getData() {
        String baseUrl = getString(R.string.base_url);
        productNetworkPresenter.getProducts(baseUrl, option);
    }

    public void setupToolbar() {
        setSupportActionBar(toolbar);
        toolbar.setTitle(title);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
            toolbar.setContentInsetStartWithNavigation(0);
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
        }
    }

    @Override
    public void susccessProducts(List<Product> products) {
        if(products.isEmpty()) {
            Snackbar.make(toolbar, "Not data found.", Snackbar.LENGTH_LONG).show();
        } else {
            setupRecyclerProduct(products);
        }
    }

    private void setupRecyclerProduct(List<Product> products) {
        ProductRecyclerAdapter adapter = new ProductRecyclerAdapter(context, products, option, user.getTotalPoints());
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
        recycler.setLayoutManager(staggeredGridLayoutManager);
        recycler.setAdapter(adapter);
        recycler.setVisibility(View.VISIBLE);
    }

    @Override
    public void getDataError(String error) {
        Snackbar.make(toolbar, error, Snackbar.LENGTH_LONG).show();
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
    }

    @Override
    public void resultLogout(boolean result) {}
}
