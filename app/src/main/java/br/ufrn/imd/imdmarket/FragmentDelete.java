package br.ufrn.imd.imdmarket;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

import br.ufrn.imd.imdmarket.model.Product;
import br.ufrn.imd.imdmarket.repository.ProductRepository;

public class FragmentDelete extends Fragment {
    Button delete_bt_01;
    Button delete_bt_02;
    EditText delete_edt_01;

    public FragmentDelete() {
        // Required empty public constructor
    }

    public static FragmentDelete newInstance(String param1, String param2) {
        FragmentDelete fragment = new FragmentDelete();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_delete, container, false);
    }

    @Override
    public void onViewCreated(View v, Bundle savedInstanceState) {
        delete_bt_01 = v.findViewById(R.id.delete_bt_01);
        delete_bt_02 = v.findViewById(R.id.delete_bt_02);
        delete_edt_01 = v.findViewById(R.id.delete_edt_01);


        delete_bt_01.setOnClickListener(v12 -> {
            removeProduct();
            navigateBack();
        });
        delete_bt_02.setOnClickListener(v12 -> {
            flushEditText();
        });
    }

    public void removeProduct() {
        try {
            String productCode = String.valueOf(delete_edt_01.getText());

            ProductRepository productRepository = ProductRepository.getInstance();
            if(productRepository.removeProductByCode(productCode)) {
                Toast.makeText(getView().getContext(), "Produto deletado", Toast.LENGTH_SHORT).show();
                System.out.println("> Product deleted");
            }
            else {
                Toast.makeText(getView().getContext(), "Código não encontrado", Toast.LENGTH_SHORT).show();
                System.out.println("> Product code not found. Nothing was deleted");
            }
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(getView().getContext(), "ERRO ao deletar produto!", Toast.LENGTH_SHORT).show();
            System.out.println("> ERROR while deleting product");
        }
    }

    public void flushEditText() {
        delete_edt_01.setText("");
    }

    private void navigateBack() {
        if (getActivity() != null) {
            getActivity().getSupportFragmentManager().popBackStack();
        }
    }
}