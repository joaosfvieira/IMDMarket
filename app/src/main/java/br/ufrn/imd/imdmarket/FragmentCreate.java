package br.ufrn.imd.imdmarket;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import br.ufrn.imd.imdmarket.model.Product;
import br.ufrn.imd.imdmarket.repository.ProductRepository;

public class FragmentCreate extends Fragment {
    Button create_bt_01;
    Button create_bt_02;
    EditText create_edt_01;
    EditText create_edt_02;
    EditText create_edt_03;
    EditText create_edt_04;

    public FragmentCreate() {
        // Required empty public constructor
    }

    public static FragmentCreate newInstance(String param1, String param2) {
        FragmentCreate fragment = new FragmentCreate();
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
        return inflater.inflate(R.layout.fragment_create, container, false);
    }

    @Override
    public void onViewCreated(View v, Bundle savedInstanceState) {
        create_bt_01 = v.findViewById(R.id.create_bt_01);
        create_bt_02 = v.findViewById(R.id.create_bt_02);
        create_edt_01 = v.findViewById(R.id.create_edt_01);
        create_edt_02 = v.findViewById(R.id.create_edt_02);
        create_edt_03 = v.findViewById(R.id.create_edt_03);
        create_edt_04 = v.findViewById(R.id.create_edt_04);


        create_bt_01.setOnClickListener(v12 -> {
            addProduct();
         });
        create_bt_02.setOnClickListener(v12 -> {
            flushEditText();
        });
    }

    public void addProduct() {
        if(create_edt_01.getText().length() == 0 ||
           create_edt_02.getText().length() == 0 ||
           create_edt_03.getText().length() == 0 ||
           create_edt_04.getText().length() == 0) {

            Toast.makeText(getView().getContext(), "Todos os campos são obrigatórios!", Toast.LENGTH_SHORT).show();
            return;
        }

        try{
            String productCode = String.valueOf(create_edt_01.getText());
            String productName = String.valueOf(create_edt_02.getText());
            String productDescription = String.valueOf(create_edt_03.getText());
            int stock = Integer.parseInt(String.valueOf(create_edt_04.getText()));

            Product p = new Product(productCode, productName, productDescription, stock);
            ProductRepository productRepository = ProductRepository.getInstance();
            productRepository.addProduct(p);

            Toast.makeText(getView().getContext(), "Produto criado", Toast.LENGTH_SHORT).show();
            System.out.println("> Product: " + p.toString() + " created");
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(getView().getContext(), "ERRO ao criar produto!", Toast.LENGTH_SHORT).show();
            System.out.println("> ERROR while creating product");
        }
        finally {
            navigateBack();
        }
    }

    public void flushEditText() {
        create_edt_01.setText("");
        create_edt_02.setText("");
        create_edt_03.setText("");
        create_edt_04.setText("");
    }

    private void navigateBack() {
        if (getActivity() != null) {
            getActivity().getSupportFragmentManager().popBackStack();
        }
    }
}