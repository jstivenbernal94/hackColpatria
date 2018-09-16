package com.villa.deimer.hackathon.views.products.adapter;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.makeramen.roundedimageview.RoundedImageView;
import com.squareup.picasso.Picasso;
import com.villa.deimer.hackathon.R;
import com.villa.deimer.hackathon.models.entities.Product;
import com.villa.deimer.hackathon.views.ImageActivity;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;

public class ProductRecyclerAdapter extends RecyclerView.Adapter<ProductRecyclerAdapter.AdapterView> {

    private Context context;
    private List<Product> products;
    private int option;
    private int totalPoints;

    public ProductRecyclerAdapter(Context context, List<Product> products, int option, int totalPoints) {
        this.context = context;
        this.products = products;
        this.option = option;
        this.totalPoints = totalPoints;
    }

    @NonNull
    @Override
    public AdapterView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_recycler_products, parent, false);
        return new AdapterView(layoutView);
    }

    public int getItemCount() {
        return products.size();
    }

    class AdapterView extends RecyclerView.ViewHolder {
        @BindView(R.id.card_item)
        CardView cardItem;
        @BindView(R.id.img_item)
        RoundedImageView img_item;
        @BindView(R.id.lbl_name_product)
        TextView lblNameProduct;
        @BindView(R.id.lbl_value_product)
        TextView lblValueProduct;
        @BindView(R.id.lbl_points_product)
        TextView lblPointsProduct;
        @BindView(R.id.but_redimir)
        Button butRedimir;
        @BindView(R.id.but_crowfunding)
        Button butCrowfunding;
        AdapterView(View itemView){
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull final AdapterView holder, int position) {
        Product product = products.get(position);
        setLabels(holder, product);
        setImageItem(holder);
        animateRecycler(holder);
        clickDialog(holder, product);
        clickRedimir(holder);
    }

    @SuppressLint("SetTextI18n")
    private void setLabels(AdapterView holder, Product product) {
        holder.lblNameProduct.setText(product.getName());
        holder.lblValueProduct.setText("$"+product.getValue());
        holder.lblPointsProduct.setText("Puntos: "+product.getPoints());
    }

    private void setImageItem(AdapterView holder) {
        if(option == 1) {
            Picasso.with(context).load(R.drawable.avatar_services).fit().into(holder.img_item);
        } else if(option == 2) {
            Picasso.with(context).load(R.drawable.avatar_food).fit().into(holder.img_item);
        } else if(option == 3) {
            Picasso.with(context).load(R.drawable.avatar_clothes).fit().into(holder.img_item);
        }
    }

    private void animateRecycler(AdapterView holder) {
        YoYo.with(Techniques.FadeInUp)
                .duration(700)
                .playOn(holder.cardItem);
    }

    private void clickDialog(AdapterView holder, final Product product) {
        holder.butCrowfunding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogCrowfunding(product);
            }
        });
    }

    private void clickRedimir(AdapterView holder) {
        holder.butRedimir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String message = "¿Desea redimir este producto con sus puntos?";
                createDialogQuestion(message);
            }
        });
    }

    private void dialogCrowfunding(Product product) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        assert inflater != null;
        @SuppressLint("InflateParams")
        View view = inflater.inflate(R.layout.dialog_crowfunding, null);
        builder.setView(view);
        final AlertDialog alertDialog = builder.create();
        setElementsDialog(view, alertDialog, product);
        alertDialog.show();
    }

    private void setElementsDialog(View view, final AlertDialog alertDialog, Product product) {
        String description = context.getString(R.string.lbl_description_dialog, realValue(product.getPoints()));
        TextView lbl_description_dialog = view.findViewById(R.id.lbl_description_dialog);
        lbl_description_dialog.setText(description);
        //Botones del dialog
        Button but_cancel = view.findViewById(R.id.but_no_dialog);
        Button but_done = view.findViewById(R.id.but_ok_dialog);
        but_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });
        but_done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
                Intent intent = new Intent(context, ImageActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
    }

    private int realValue(int valueProduct) {
        return valueProduct - totalPoints;
    }

    private void createDialogQuestion(String message) {
        final AlertDialog.Builder dialog = new AlertDialog.Builder(context);
        dialog.setCancelable(true).setMessage(message);
        dialog.setNegativeButton("No", createoptionsButtons());
        dialog.setPositiveButton("si", createoptionsButtons());
        dialog.create().show();
    }

    private DialogInterface.OnClickListener createoptionsButtons() {
        return new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        };
    }

}
