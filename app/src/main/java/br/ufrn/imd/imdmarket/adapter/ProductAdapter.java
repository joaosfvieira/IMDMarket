package br.ufrn.imd.imdmarket.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import br.ufrn.imd.imdmarket.R;
import br.ufrn.imd.imdmarket.model.Product;

public class ProductAdapter extends ArrayAdapter<Product> {

        public ProductAdapter(Context context, List<Product> products) {
            super(context, 0, products);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            Product product = getItem(position);

            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_product, parent, false);
            }

            TextView nameTextView = convertView.findViewById(R.id.item_tv_01);
            TextView codeTextView = convertView.findViewById(R.id.item_tv_02);

            nameTextView.setText(product.getName());
            codeTextView.setText(product.getCode());

            return convertView;
        }
}
