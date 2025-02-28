package com.example.oujdashop;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class ProductAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<Product> productList;

    public ProductAdapter(Context context, ArrayList<Product> productList) {
        this.context = context;
        this.productList = productList;
    }

    @Override
    public int getCount() {
        return productList.size();
    }

    @Override
    public Object getItem(int position) {
        return productList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return productList.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_product, parent, false);
        }

        Product product = productList.get(position);

        ImageView imageView = convertView.findViewById(R.id.productImage);
        TextView textViewName = convertView.findViewById(R.id.productName);
        TextView textViewPrice = convertView.findViewById(R.id.productPrice);
        TextView textViewDescription = convertView.findViewById(R.id.productDescription);
        TextView textViewCategory = convertView.findViewById(R.id.productCategory);

        imageView.setImageResource(product.getImageResource());
        textViewName.setText(product.getName());
        textViewPrice.setText(String.format("$%.2f", product.getPrice()));
        textViewDescription.setText(product.getDescription());
        textViewCategory.setText(product.getCategory());

        return convertView;
    }
}
