package br.ufrn.imd.imdmarket;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import br.ufrn.imd.imdmarket.helper.LoginPreferencesHelper;

public class FragmentForgot extends Fragment {
    Button forgot_bt_01;
    EditText forgot_edt_01;
    EditText forgot_edt_02;
    TextView forgot_tv_03;

    public FragmentForgot() {
        // Required empty public constructor
    }

    public static FragmentForgot newInstance(String param1, String param2) {
        FragmentForgot fragment = new FragmentForgot();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_forgot, container, false);
    }

    @Override
    public void onViewCreated(View v, Bundle savedInstanceState) {
        forgot_bt_01 = v.findViewById(R.id.forgot_bt_01);
        forgot_edt_01 = v.findViewById(R.id.forgot_edt_01);
        forgot_edt_02 = v.findViewById(R.id.forgot_edt_02);
        forgot_tv_03 = v.findViewById(R.id.forgot_tv_03);

        forgot_bt_01.setOnClickListener(v12 -> {
            performRecoverLogin(v12);
         });

        forgot_tv_03.setOnClickListener(v12 -> {
            MainActivity mainActivity = (MainActivity) getActivity();
            mainActivity.changeFragAux(v12);
        });
    }

    public void performRecoverLogin(View v12) {
        if(forgot_edt_01.getText().length() == 0 ||
           forgot_edt_02.getText().length() == 0) {
            Toast.makeText(getView().getContext(), "Todos os campos são obrigatórios!", Toast.LENGTH_SHORT).show();
            return;
        }

        String username = String.valueOf(forgot_edt_01.getText());
        String password = String.valueOf(forgot_edt_02.getText());

        LoginPreferencesHelper.saveLoginCredentials(getContext(), username, password);
        MainActivity mainActivity = (MainActivity) getActivity();
        mainActivity.changeFragAux(v12);
    }
}