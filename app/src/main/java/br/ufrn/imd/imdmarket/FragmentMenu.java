package br.ufrn.imd.imdmarket;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class FragmentMenu extends Fragment {
    Button menu_bt_01;
    Button menu_bt_02;
    Button menu_bt_03;
    Button menu_bt_04;

    public FragmentMenu() {
        // Required empty public constructor
    }

    public static FragmentMenu newInstance(String param1, String param2) {
        FragmentMenu fragment = new FragmentMenu();
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
        return inflater.inflate(R.layout.fragment_menu, container, false);
    }

    @Override
    public void onViewCreated(View v, Bundle savedInstanceState) {
        menu_bt_01 = v.findViewById(R.id.menu_bt_01);
        menu_bt_02 = v.findViewById(R.id.menu_bt_02);
        menu_bt_03 = v.findViewById(R.id.menu_bt_03);
        menu_bt_04 = v.findViewById(R.id.menu_bt_04);

        menu_bt_01.setOnClickListener(v12 -> {
            MainActivity mainActivity = (MainActivity) getActivity();
            mainActivity.changeFragAux(v12);
        });
        menu_bt_02.setOnClickListener(v12 -> {
            MainActivity mainActivity = (MainActivity) getActivity();
            mainActivity.changeFragAux(v12);
        });
        menu_bt_03.setOnClickListener(v12 -> {
            MainActivity mainActivity = (MainActivity) getActivity();
            mainActivity.changeFragAux(v12);
        });
        menu_bt_04.setOnClickListener(v12 -> {
            MainActivity mainActivity = (MainActivity) getActivity();
            mainActivity.changeFragAux(v12);
        });

    }


}