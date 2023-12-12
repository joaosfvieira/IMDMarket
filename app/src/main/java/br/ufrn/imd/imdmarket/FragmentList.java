package br.ufrn.imd.imdmarket;

import android.os.Build;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

import br.ufrn.imd.imdmarket.adapter.ProductAdapter;
import br.ufrn.imd.imdmarket.model.Product;
import br.ufrn.imd.imdmarket.repository.ProductRepository;
import br.ufrn.imd.imdmarket.utils.AlertDialogUtils;

public class FragmentList extends Fragment {

    public FragmentList() {
        // Required empty public constructor
    }

    public static FragmentList newInstance(String param1, String param2) {
        FragmentList fragment = new FragmentList();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);
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
        ArrayList<Product> products = new ArrayList<>(productRepository.getProductList());
        ProductAdapter adapter = new ProductAdapter(getContext(), products);

        ListView lv = (ListView) v.findViewById(R.id.list_lv_01);
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Product selectedProduct = (Product) parent.getItemAtPosition(position);

                String title = "Produto";
                String details = "Nome: " + selectedProduct.getName()
                        + "\nDescrição: " + selectedProduct.getDescription()
                        + "\nCódigo: " + selectedProduct.getCode()
                        + "\nEstoque: " + selectedProduct.getStock();

                AlertDialogUtils.showDetailsAlert(view.getContext(), title, details);
            }
        });
    }

    private void navigateBack() {
        if (getActivity() != null) {
            getActivity().getSupportFragmentManager().popBackStack();
        }
    }
}