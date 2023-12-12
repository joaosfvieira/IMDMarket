package br.ufrn.imd.imdmarket;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.fragment.app.Fragment;

import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.io.InputStream;

import br.ufrn.imd.imdmarket.model.Product;
import br.ufrn.imd.imdmarket.repository.ProductRepository;
import br.ufrn.imd.imdmarket.utils.ImageUtils;

public class FragmentUpdate extends Fragment {
    Button update_bt_01;
    Button update_bt_02;
    EditText update_edt_01;
    EditText update_edt_02;
    EditText update_edt_03;
    EditText update_edt_04;
    ImageButton update_ibt_01;
    Uri selected_image_uri;

    Bitmap image;
    Drawable upload;

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
        return inflater.inflate(R.layout.fragment_product_form, container, false);
    }

    @Override
    public void onViewCreated(View v, Bundle savedInstanceState) {
        update_ibt_01 = v.findViewById(R.id.update_ibt_01);
        update_bt_01 = v.findViewById(R.id.update_bt_01);
        update_bt_02 = v.findViewById(R.id.update_bt_02);
        update_edt_01 = v.findViewById(R.id.update_edt_01);
        update_edt_02 = v.findViewById(R.id.update_edt_02);
        update_edt_03 = v.findViewById(R.id.update_edt_03);
        update_edt_04 = v.findViewById(R.id.update_edt_04);

        update_ibt_01.setOnClickListener(v12 -> {
            openImagePicker();
        });
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

            boolean savedImage = ImageUtils.saveImage(getContext(), image, productCode.concat(".png"));
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
        update_ibt_01.setBackground(getContext().getDrawable(R.drawable.upload_image));
    }

    private void navigateBack() {
        if (getActivity() != null) {
            getActivity().getSupportFragmentManager().popBackStack();
        }
    }

    private final ActivityResultLauncher<Intent> imagePickerLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == getActivity().RESULT_OK && result.getData() != null) {
                    handleImageSelection(result.getData());
                }
            }
    );

    private void openImagePicker() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        imagePickerLauncher.launch(intent);
    }

    private void handleImageSelection(Intent data) {
        if (data.getData() != null) {
            selected_image_uri = data.getData();
            try {
                Drawable drawable = ImageUtils.getDrawableFromUri(getContext(), selected_image_uri);
                image = ImageUtils.getBitmapFromUri(getContext(), selected_image_uri);
                update_ibt_01.setBackground(drawable);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}