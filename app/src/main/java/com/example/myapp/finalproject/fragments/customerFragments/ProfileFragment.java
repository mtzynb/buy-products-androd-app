package com.example.myapp.finalproject.fragments.customerFragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapp.finalproject.R;
import com.example.myapp.finalproject.WelcomActivity;
import com.example.myapp.finalproject.db.PersonDB;
import com.example.myapp.finalproject.model.Person;

public class ProfileFragment extends Fragment {


    private TextView tvPersonName;
    private TextView tvPersonFamilyName;
    private TextView tvPersonCity;
    private TextView tvPersonPhone;
    private TextView tvPersonEmail;
    private TextView tvPersonUsername;
    private TextView tvPersonPassword;
    private Bundle bundle ;
    private Person person;


//    private OnFragmentInteractionListener mListener;

    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);


        tvPersonName = view.findViewById(R.id.tvCustomerName);
        tvPersonFamilyName = view.findViewById(R.id.tvCustomerFamilyName);
        tvPersonCity = view.findViewById(R.id.tvCustomerCity);
        tvPersonPhone = view.findViewById(R.id.tvCustomerPhone);
        tvPersonEmail = view.findViewById(R.id.tvCustomerEmail);
        tvPersonUsername = view.findViewById(R.id.tvCustomerUserName);
        tvPersonPassword = view.findViewById(R.id.tvCustomerPassword);

//        bundle = getArguments();
//        currentCustomerID = bundle.getLong(WelcomActivity.CURRENT_CUSTOMER_KEY);
//        long i = currentCustomerID;
//        PersonDB db = new PersonDB(getContext());
//        person = db.selectForPersonProFile(i);
//        tvPersonName.setText(person.getFirstName());
//        tvPersonFamilyName.setText(person.getLastName());
//        tvPersonCity.setText(person.getCity());
//        tvPersonPhone.setText(person.getPhone());
//        tvPersonEmail.setText(person.getEmail());
//        tvPersonUsername.setText(person.getUsername());
//        tvPersonPassword.setText(person.getPassword());


      //*****************************************//////
        bundle = getArguments();
        person = bundle.getParcelable(WelcomActivity.CURRENT_CUSTOMER_KEY);

       final int currentCustomerID = person.getPersonID();

        tvPersonName.setText(person.getFirstName());
        tvPersonFamilyName.setText(person.getLastName());
        tvPersonCity.setText(person.getCity());
        tvPersonPhone.setText(person.getPhone());
        tvPersonEmail.setText(person.getEmail());
        tvPersonUsername.setText(person.getUsername());
        tvPersonPassword.setText(person.getPassword());



        return view;
    }



//    public void onButtonPressed(Uri uri) {
//        if (mListener != null) {
//            mListener.onFragmentInteraction(uri);
//        }
//    }
//
//    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
//        if (context instanceof OnFragmentInteractionListener) {
//            mListener = (OnFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
//    }
//
//    @Override
//    public void onDetach() {
//        super.onDetach();
//        mListener = null;
//    }
//
//    public interface OnFragmentInteractionListener {
//        void onFragmentInteraction(Uri uri);
//    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putBundle("b", bundle);
//        outState.putLong("id", currentCustomerID);
        outState.putParcelable("p", person);


    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (savedInstanceState != null) {
            bundle = savedInstanceState.getBundle("b");
//            currentCustomerID = savedInstanceState.getLong("id");
            person = savedInstanceState.getParcelable("p");


        }


    }


}
