package br.ufrn.imd.imdmarket.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import br.ufrn.imd.imdmarket.R;
import br.ufrn.imd.imdmarket.model.Product;

public class ProductRecyclerAdapter extends RecyclerView.Adapter<ProductRecyclerAdapter.ProductViewHolder> {

    private Context context;
    private List<Product> products;
    private OnItemClickListener onItemClickListener;

    public ProductRecyclerAdapter(Context context, List<Product> products, OnItemClickListener onItemClickListener) {
        this.context = context;
        this.products = products;
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.card_product, parent, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        Product product = products.get(position);

        holder.imageViewProduct.setImageResource(product.getImage());
        holder.textViewProductName.setText(product.getName());
        holder.textViewProductCode.setText(product.getCode());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListener.onItemClick(product);
            }
        });
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public static class ProductViewHolder extends RecyclerView.ViewHolder {
        ImageView imageViewProduct;
        TextView textViewProductName;
        TextView textViewProductCode;
        CardView cardView;


        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);

            imageViewProduct = itemView.findViewById(R.id.item_iv_01);
            textViewProductName = itemView.findViewById(R.id.item_tv_01);
            textViewProductCode = itemView.findViewById(R.id.item_tv_02);
            cardView = itemView.findViewById(R.id.item_cv_01);
        }


    }

    public interface OnItemClickListener {
        void onItemClick(Product product);
    }
}