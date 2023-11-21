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

public class FragmentLogin extends Fragment {
    Button login_bt_01;
    EditText login_edt_01;
    EditText login_edt_02;
    TextView login_tv_03;

    public FragmentLogin() {
        // Required empty public constructor
    }

    public static FragmentLogin newInstance(String param1, String param2) {
        FragmentLogin fragment = new FragmentLogin();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onViewCreated(View v, Bundle savedInstanceState) {
        login_bt_01 = v.findViewById(R.id.login_bt_01);
        login_edt_01 = v.findViewById(R.id.login_edt_01);
        login_edt_02 = v.findViewById(R.id.login_edt_02);
        login_tv_03 = v.findViewById(R.id.login_tv_03);

        login_bt_01.setOnClickListener(v12 -> {
            performLogin(v12);
         });

        login_tv_03.setOnClickListener(v12 -> {
            MainActivity mainActivity = (MainActivity) getActivity();
            mainActivity.changeFragAux(v12);
        });
    }

    public void performLogin(View v12) {
        if(login_edt_01.getText().length() == 0 ||
           login_edt_02.getText().length() == 0) {
            Toast.makeText(getView().getContext(), "Todos os campos são obrigatórios!", Toast.LENGTH_SHORT).show();
            return;
        }

        String username = String.valueOf(login_edt_01.getText());
        String password = String.valueOf(login_edt_02.getText());

        if (LoginPreferencesHelper.checkLoginCredentials(getContext(), username, password)) {
            MainActivity mainActivity = (MainActivity) getActivity();
            mainActivity.changeFragAux(v12);
        } else {
            Toast.makeText(getContext(), "Usuário ou senha inválidos!", Toast.LENGTH_SHORT).show();
        }
    }
}