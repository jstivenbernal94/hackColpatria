package com.villa.deimer.hackathon.views.products.network;

import java.util.List;
import com.villa.deimer.hackathon.models.entities.Product;

public interface ProductNetworkInterface {

    void susccessProducts(List<Product> products);
    void getDataError(String error);

}
