package com.villa.deimer.hackathon.presenters.products.network;

import java.util.List;
import com.villa.deimer.hackathon.models.entities.Product;
import com.villa.deimer.hackathon.views.products.network.ProductNetworkInterface;
import com.villa.deimer.hackathon.models.services.data.remote.products.ProductRetrofitAdapter;

public class ProductNetworkPresenterImpl implements ProductNetworkPresenter {

    private ProductNetworkInterface productNetworkInterface;

    public ProductNetworkPresenterImpl(ProductNetworkInterface productNetworkInterface) {
        this.productNetworkInterface = productNetworkInterface;
    }

    @Override
    public void getProducts(String baseUrl, int option) {
        ProductRetrofitAdapter productRetrofitAdapter = new ProductRetrofitAdapter(this);
        productRetrofitAdapter.getProducts(baseUrl, option);
    }

    @Override
    public void susccessProducts(List<Product> products) {
        productNetworkInterface.susccessProducts(products);
    }

    @Override
    public void getDataError(String error) {
        productNetworkInterface.getDataError(error);
    }
}
