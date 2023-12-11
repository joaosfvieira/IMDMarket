package br.ufrn.imd.imdmarket;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import br.ufrn.imd.imdmarket.adapter.ProductRecyclerAdapter;
import br.ufrn.imd.imdmarket.model.Product;
import br.ufrn.imd.imdmarket.repository.ProductRepository;
import br.ufrn.imd.imdmarket.utils.AlertDialogUtils;

public class FragmentRecycler extends Fragment implements ProductRecyclerAdapter.OnItemClickListener {

    public FragmentRecycler() {
        // Required empty public constructor
    }

    public static FragmentRecycler newInstance(String param1, String param2) {
        FragmentRecycler fragment = new FragmentRecycler();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recycler, container, false);
        view.findViewById(R.id.list_bt_01).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateBack();
            }
        });
        return view;
    }

    @Override
    public void onViewCreated(View v, Bundle savedInstanceState) {
        ProductRepository productRepository = ProductRepository.getInstance();
        List<Product> products = productRepository.getProductList();
        ProductRecyclerAdapter adapter = new ProductRecyclerAdapter(getContext(), products, this);

        RecyclerView rv = v.findViewById(R.id.list_rv_01);
        rv.setLayoutManager(new LinearLayoutManager(getContext()));
        rv.setAdapter(adapter);

    }

    @Override
    public void onItemClick(Product product) {
        Product selectedProduct = product;

        String title = "Produto";
        String details = "Nome: " + selectedProduct.getName()
                + "\nDescrição: " + selectedProduct.getDescription()
                + "\nCódigo: " + selectedProduct.getCode()
                + "\nEstoque: " + selectedProduct.getStock();

        AlertDialogUtils.showDetailsAlert(getContext(), title, details);
    }

    private void navigateBack() {
        if (getActivity() != null) {
            getActivity().getSupportFragmentManager().popBackStack();
        }
    }
}