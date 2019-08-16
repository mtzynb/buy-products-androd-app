package com.example.myapp.finalproject;

import android.content.Intent;
import android.os.PersistableBundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.myapp.finalproject.fragments.LoginFragment;
import com.example.myapp.finalproject.model.Person;

public class WelcomActivity extends AppCompatActivity implements LoginFragment.OnLoginFragmentInteractionListener {

    public static final int REQUEST_CODE_FOR_ADMIN_ACTIVITY = 1 ;
    public static final int REQUEST_CODE_FOR_CUSTOMER_ACTIVITY = 2 ;
    public static final String ADMIN_KEY = "adminKey";
    public static final String CURRENT_CUSTOMER_KEY = "customerKey";
    private long id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcom);


    }



    /////////// override abstract methods (in LoginFragment.java)
    ////////// for interaction between this fragment and its activity !
    ///////// sendCustomerDatas : receive datas of the customer who currently loged in and these data
    //////// should go to the next Activity (CustomerActivity)
    /////// and these data would be shown in the Profile Tab in the CustomerActivity
    @Override
    public void sendCustomerDatas(Person person) {
        Intent intent = new Intent(this, CustomerActivity.class);
        intent.putExtra(CURRENT_CUSTOMER_KEY, person);
        startActivityForResult(intent ,REQUEST_CODE_FOR_CUSTOMER_ACTIVITY);
//        Toast.makeText(this, "Welcome Dear " + person.getFirstName(), Toast.LENGTH_SHORT).show();

    }


//    @Override
//    public void sendCustomerDatas(long id) {
//        Intent intent = new Intent(this, CustomerActivity.class);
//        intent.putExtra(CURRENT_CUSTOMER_KEY, id);
//        startActivityForResult(intent ,REQUEST_CODE_FOR_CUSTOMER_ACTIVITY);
////        Toast.makeText(this, "Welcome Dear " + person.getFirstName(), Toast.LENGTH_SHORT).show();
//
//    }

    @Override
    public void sendAdminDatas(Person person) {
//        Intent intent = new Intent(this ,MainActivity.class);
//        startActivity(intent);
        Intent intent = new Intent(this, AdminActivity.class);
        intent.putExtra(ADMIN_KEY, person);
        startActivityForResult(intent ,REQUEST_CODE_FOR_ADMIN_ACTIVITY);
        Toast.makeText(this, "Welcome Dear " + person.getFirstName(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        outState.putLong("id",id);

    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onRestoreInstanceState(savedInstanceState, persistentState);
        id=savedInstanceState.getLong("id");
    }
}
