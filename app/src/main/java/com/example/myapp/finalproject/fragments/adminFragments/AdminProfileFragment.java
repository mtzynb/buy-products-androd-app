package com.example.myapp.finalproject.fragments.adminFragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.myapp.finalproject.R;
import com.example.myapp.finalproject.WelcomActivity;
import com.example.myapp.finalproject.model.Person;


public class AdminProfileFragment extends Fragment {



    private TextView tvPersonName;
    private TextView tvPersonFamilyName;
    private TextView tvPersonCity;
    private TextView tvPersonPhone;
    private TextView tvPersonEmail;
    private TextView tvPersonUsername;
    private TextView tvPersonPassword;

//    private OnFragmentInteractionListener mListener;

    public AdminProfileFragment() {}


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_admin_profile, container, false);

        tvPersonName = view.findViewById(R.id.tvCustomerName);
        tvPersonFamilyName = view.findViewById(R.id.tvCustomerFamilyName);
        tvPersonCity = view.findViewById(R.id.tvCustomerCity);
        tvPersonPhone = view.findViewById(R.id.tvCustomerPhone);
        tvPersonEmail = view.findViewById(R.id.tvCustomerEmail);
        tvPersonUsername = view.findViewById(R.id.tvCustomerUserName);
        tvPersonPassword = view.findViewById(R.id.tvCustomerPassword);

//        Bundle bundle = getArguments();
//        Person person = bundle.getParcelable(WelcomActivity.ADMIN_KEY);
//
//        tvPersonName.setText(person.getFirstName());
//        tvPersonFamilyName.setText(person.getLastName());
//        tvPersonCity.setText(person.getCity());
//        tvPersonPhone.setText(person.getPhone());
//        tvPersonEmail.setText(person.getEmail());
//        tvPersonUsername.setText(person.getUsername());
//        tvPersonPassword.setText(person.getPassword());





        return view;
    }

//    public void onButtonPressed(Uri uri) {
//        if (mListener != null) {
//            mListener.onFragmentInteraction(uri);
//        }
//    }

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
//
//    public interface OnFragmentInteractionListener {
//        void onFragmentInteraction(Uri uri);
//    }
}
