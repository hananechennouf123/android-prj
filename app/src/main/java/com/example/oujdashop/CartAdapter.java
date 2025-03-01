package com.example.oujdashop;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.List;

public class CartAdapter extends BaseAdapter {

    private Context context;
    private List<Product> cartProducts;

    public CartAdapter(Context context, List<Product> cartProducts) {
        this.context = context;
        this.cartProducts = cartProducts;
    }

    @Override
    public int getCount() {
        return cartProducts.size();
    }

    @Override
    public Object getItem(int position) {
        return cartProducts.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_cart, parent, false);
        }

        // Récupérer les éléments du layout
        ImageView productImage = convertView.findViewById(R.id.cart_product_image);
        TextView productName = convertView.findViewById(R.id.cart_product_name);
        TextView productPrice = convertView.findViewById(R.id.cart_product_price);

        // Assigner les valeurs des produits
        Product product = cartProducts.get(position);
        productImage.setImageResource(product.getImageResource());
        productName.setText(product.getName());
        productPrice.setText(product.getPrice() + " MAD");

        return convertView;
    }
}
