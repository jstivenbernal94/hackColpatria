package com.villa.deimer.hackathon.presenters.products.network;

import java.util.List;
import com.villa.deimer.hackathon.models.entities.Product;

public interface ProductNetworkPresenter {

    void getProducts(String baseUrl, int option);

    void susccessProducts(List<Product> products);
    void getDataError(String error);

}
