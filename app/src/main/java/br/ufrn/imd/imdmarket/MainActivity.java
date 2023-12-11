package br.ufrn.imd.imdmarket;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import java.io.File;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import br.ufrn.imd.imdmarket.repository.ProductRepository;

public class MainActivity extends AppCompatActivity {

    FragmentLogin fragmentLogin;
    FragmentForgot fragmentForgot;
    FragmentCreate fragmentCreate;
    FragmentMenu fragmentMenu;
    FragmentRecycler fragmentRecycler;
    FragmentDelete fragmentDelete;
    FragmentUpdate fragmentUpdate;
    private static boolean isFirstRun = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.fragmentLogin = new FragmentLogin();
        this.fragmentForgot = new FragmentForgot();
        this.fragmentCreate = new FragmentCreate();
        this.fragmentRecycler = new FragmentRecycler();
        this.fragmentDelete = new FragmentDelete();
        this.fragmentUpdate = new FragmentUpdate();
        this.fragmentMenu = new FragmentMenu();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, fragmentLogin);
        transaction.commit();


        File internalStorageDir = getFilesDir();
        File productsFile = new File(internalStorageDir, "products.txt");
        ProductRepository.setProductsFile(productsFile);

        if(isFirstRun) {
            ProductRepository.readProductsFromFile();
            isFirstRun = false;
        }

    }

    @Override
    protected void onDestroy() {
        ProductRepository.saveProductsInFile();
        super.onDestroy();
    }

    private void changeFragment(Fragment fragment, Integer idFragment) {
        FragmentTransaction fragmentTransaction = this.getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(idFragment, fragment);

        Set<Integer> ignoredFragments = new HashSet<>();
        ignoredFragments.add(fragmentLogin.getId());
        ignoredFragments.add(fragmentForgot.getId());
        // Nao adiciona os fragmentos ignorados a pilha
        if(!ignoredFragments.contains(fragment.getId())) {
            fragmentTransaction.addToBackStack(null);
        }

        fragmentTransaction.commit();
    }

    public void changeFragAux(View view){
        int id = view.getId();
        if(id == R.id.login_bt_01){
            this.changeFragment(fragmentMenu, R.id.fragment_container);
        }
        else if(id == R.id.login_tv_03){
            this.changeFragment(fragmentForgot, R.id.fragment_container);
        }
        else if(id == R.id.forgot_bt_01){
            this.changeFragment(fragmentLogin, R.id.fragment_container);
        }
        else if(id == R.id.forgot_tv_03){
            this.changeFragment(fragmentLogin, R.id.fragment_container);
        }
        else if(id == R.id.menu_bt_01){
            this.changeFragment(fragmentCreate, R.id.fragment_container);
        }
        else if(id == R.id.menu_bt_02){
            this.changeFragment(fragmentRecycler, R.id.fragment_container);
        }
        else if(id == R.id.menu_bt_03){
            this.changeFragment(fragmentUpdate, R.id.fragment_container);
        }
        else if(id == R.id.menu_bt_04){
            this.changeFragment(fragmentDelete, R.id.fragment_container);
        }
        else if(id == R.id.create_bt_01) {
            this.changeFragment(fragmentMenu, R.id.fragment_container);
        }
    }


}