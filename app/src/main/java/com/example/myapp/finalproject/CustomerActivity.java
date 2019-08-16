package com.example.myapp.finalproject;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapp.finalproject.fragments.customerFragments.DialogForFinalShoppingFragment;
import com.example.myapp.finalproject.fragments.customerFragments.ProductsFragment;
import com.example.myapp.finalproject.fragments.customerFragments.ProfileFragment;
import com.example.myapp.finalproject.fragments.customerFragments.ShoppingHistoryFragment;
import com.example.myapp.finalproject.javaClasses.MainPagerAdapter;
import com.example.myapp.finalproject.model.Person;
import com.example.myapp.finalproject.model.Products;
import com.example.myapp.finalproject.myRecyclerAdapterListener.MyListenerInterface;

import java.util.ArrayList;
import java.util.List;

public class CustomerActivity extends AppCompatActivity implements MyListenerInterface {


    public static final String PRODUCT_NAME_KEY = "productName";
    public static final String PRODUCT_PRICE_KEY = "productPrice";
    public static final String PRODUCT_IMAGE_KEY = "productImage";

    public static final String PRODUCT_KEY = "productKey";
    public static final String CURRENT_CUSTOMER_ID_KEY = "currentCustomerIdKey";
    private int currentCustomerID;
    private ViewPager viewPagerPerson;
    private TabLayout tabLayoutPerson;
    private List<Fragment> fragments = new ArrayList<>();
    private List<String> titles = new ArrayList<>();
    private Person person;
    private TextView textView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer);


        viewPagerPerson = findViewById(R.id.viewPagerPerson);
        tabLayoutPerson = findViewById(R.id.tabLayoutPerson);


        initFragmentAdapterData();

//        Person person = getIntent().getParcelableExtra(WelcomActivity.CURRENT_CUSTOMER_KEY);
//         currentCustomerID = person.getPersonID();
        sendPersonDatasToPersonFragments();


        MainPagerAdapter adapter = new MainPagerAdapter(getSupportFragmentManager(), fragments, titles);
        viewPagerPerson.setAdapter(adapter);
        tabLayoutPerson.setupWithViewPager(viewPagerPerson);

        setTabIcons();
    }


    /////////// receive Intent datas from WelcomeActivity's intent////
    public Person reciveDatas() {
        Person person = getIntent().getParcelableExtra(WelcomActivity.CURRENT_CUSTOMER_KEY);
        return person;
    }


    public void sendPersonDatasToPersonFragments() {

        Person person = reciveDatas();

        Bundle b = new Bundle();
        b.putParcelable(WelcomActivity.CURRENT_CUSTOMER_KEY, person);

        for (int i = 0; i < fragments.size(); i++) {
            fragments.get(i).setArguments(b);
        }
    }

    private void initFragmentAdapterData() {

        fragments.add(new ProfileFragment());
        fragments.add(new ProductsFragment());
        fragments.add(new ShoppingHistoryFragment());

        titles.add("");
        titles.add("");
        titles.add("");


    }

    private void setTabIcons() {

        tabLayoutPerson.getTabAt(0).setIcon(R.drawable.ic_account_circle_black_24dp);
        tabLayoutPerson.getTabAt(1).setIcon(R.drawable.ic_add_shopping_cart_black_24dp);
        tabLayoutPerson.getTabAt(2).setIcon(R.drawable.ic_history_black_24dp);


    }

    /////////////////////// override my abstract method in MyListenerInterface


    @Override
    public void onBuyListener(Products products) {
        Bundle bundle = new Bundle();
        bundle.putParcelable(PRODUCT_KEY, products);

        Person person = reciveDatas();
        currentCustomerID = person.getPersonID();
        bundle.putInt(CURRENT_CUSTOMER_ID_KEY, currentCustomerID);

        DialogForFinalShoppingFragment mDialog = new DialogForFinalShoppingFragment();
        mDialog.setArguments(bundle);
        mDialog.show(getSupportFragmentManager(), "MyDialog");


    }


}
