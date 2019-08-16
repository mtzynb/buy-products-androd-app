package com.example.myapp.finalproject;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapp.finalproject.fragments.LoginFragment;
import com.example.myapp.finalproject.fragments.adminFragments.AdminProductsFragment;
import com.example.myapp.finalproject.fragments.adminFragments.AdminProfileFragment;
import com.example.myapp.finalproject.fragments.adminFragments.AdminReportShoppingCartFragment;
import com.example.myapp.finalproject.javaClasses.MainPagerAdapter;
import com.example.myapp.finalproject.model.Person;

import java.util.ArrayList;
import java.util.List;

public class AdminActivity extends AppCompatActivity implements LoginFragment.OnLoginFragmentInteractionListener{

    private Person person ;
    private TabLayout tabLayoutAdmin;
    private ViewPager viewPagerAdmin;
    private List<Fragment> fragments = new ArrayList<>();
    private List<String> titles = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        tabLayoutAdmin = findViewById(R.id.tabLayoutAdmin);
        viewPagerAdmin = findViewById(R.id.viewPagerAdmin);

        intitFragmentAdapterData();
        MainPagerAdapter adapter = new MainPagerAdapter(getSupportFragmentManager(),fragments,titles);
        viewPagerAdmin.setAdapter(adapter);
        tabLayoutAdmin.setupWithViewPager(viewPagerAdmin);
        setTabIcons();

        person = getIntent().getParcelableExtra(WelcomActivity.ADMIN_KEY);

    }


    private void intitFragmentAdapterData(){

//        fragments.add(new AdminProfileFragment());
        fragments.add(new AdminProductsFragment());
        fragments.add(new AdminReportShoppingCartFragment());

//        titles.add("Profile");
        titles.add("Products");
        titles.add("Report");

    }

    private void setTabIcons(){
//        tabLayoutAdmin.getTabAt(0).setIcon(R.drawable.ic_supervisor_account_black_24dp);
//        tabLayoutAdmin.getTabAt(1).setIcon(R.drawable.ic_info_outline_black_24dp);
        tabLayoutAdmin.getTabAt(0).setIcon(R.drawable.ic_info_outline_black_24dp);
        tabLayoutAdmin.getTabAt(1).setIcon(R.drawable.ic_shopping_cart_black_24dp);
    }
  ///// add an menu to this activity (add product menu)
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.admin_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.menuAdminAddProduct :
                goToAddProductActivity();
        }

        return super.onOptionsItemSelected(item);
    }

    private void goToAddProductActivity(){
        Intent intent = new Intent(this,AddProductActivity.class);
        startActivity(intent);


    }

    @Override
    public void sendCustomerDatas(Person currentPersonId) {

    }

    @Override
    public void sendAdminDatas(Person adminId) {

    }
}
