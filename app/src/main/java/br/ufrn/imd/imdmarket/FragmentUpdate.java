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

public class FragmentUpdate extends Fragment {
    Button update_bt_01;
    Button update_bt_02;
    EditText update_edt_01;
    EditText update_edt_02;
    EditText update_edt_03;
    EditText update_edt_04;

    public FragmentUpdate() {
        // Required empty public constructor
    }

    public static FragmentUpdate newInstance(String param1, String param2) {
        FragmentUpdate fragment = new FragmentUpdate();
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
        return inflater.inflate(R.layout.fragment_update, container, false);
    }

    @Override
    public void onViewCreated(View v, Bundle savedInstanceState) {
        update_bt_01 = v.findViewById(R.id.update_bt_01);
        update_bt_02 = v.findViewById(R.id.update_bt_02);
        update_edt_01 = v.findViewById(R.id.update_edt_01);
        update_edt_02 = v.findViewById(R.id.update_edt_02);
        update_edt_03 = v.findViewById(R.id.update_edt_03);
        update_edt_04 = v.findViewById(R.id.update_edt_04);


        update_bt_01.setOnClickListener(v12 -> {
            updateProduct();
        });
        update_bt_02.setOnClickListener(v12 -> {
            flushEditText();
        });
    }

    public void updateProduct() {
        if(update_edt_01.getText().length() == 0) {
            Toast.makeText(getView().getContext(), "Código do produto obrigatório!", Toast.LENGTH_SHORT).show();
            return;
        }

        try{
            String productCode = String.valueOf(update_edt_01.getText());
            String productName = String.valueOf(update_edt_02.getText());
            String productDescription = String.valueOf(update_edt_03.getText());
            String stock = String.valueOf(update_edt_04.getText());

            ProductRepository productRepository = ProductRepository.getInstance();

            if(productRepository.updateProduct(productCode, productName, productDescription, stock)) {
                Toast.makeText(getView().getContext(), "Produto atualizado", Toast.LENGTH_SHORT).show();
                System.out.println("> Product updated");
            }
            else {
                Toast.makeText(getView().getContext(), "Código não encontrado", Toast.LENGTH_SHORT).show();
                System.out.println("> Product code not found. Nothing was updated");
            }
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(getView().getContext(), "ERRO ao atualizar produto!", Toast.LENGTH_SHORT).show();
            System.out.println("> ERROR while updating product");
        }
        finally {
            navigateBack();
        }
    }

    public void flushEditText() {
        update_edt_01.setText("");
        update_edt_02.setText("");
        update_edt_03.setText("");
        update_edt_04.setText("");
    }

    private void navigateBack() {
        if (getActivity() != null) {
            getActivity().getSupportFragmentManager().popBackStack();
        }
    }
}