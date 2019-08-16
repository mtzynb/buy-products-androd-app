package com.example.myapp.finalproject.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapp.finalproject.AdminActivity;
import com.example.myapp.finalproject.CustomerActivity;
import com.example.myapp.finalproject.R;
import com.example.myapp.finalproject.SignUpActivity;
import com.example.myapp.finalproject.db.PersonDB;
import com.example.myapp.finalproject.model.Person;
import com.example.myapp.finalproject.model.Products;

import java.util.List;


public class LoginFragment extends Fragment {

    private Button loginBtn;
    private Button signUpBtn;
    private EditText etUsername;
    private EditText etPassword;
    private TextView tvErrors;



    private OnLoginFragmentInteractionListener mListener;

    public LoginFragment() {}



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        etUsername = view.findViewById(R.id.etUsername);
        etPassword = view.findViewById(R.id.etPassword);
        tvErrors = view.findViewById(R.id.tvErrors);

        loginBtn = view.findViewById(R.id.btnLogin);
        signUpBtn = view.findViewById(R.id.btnSignUp);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isLoginValid();


            }
        });

        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToSignUpActivity();

            }
        });


        return view;
    }


    public void goToSignUpActivity() {

        Intent intent = new Intent(getActivity(), SignUpActivity.class);
        startActivity(intent);

    }





    public boolean isPersonAdmin(Person person) {

        boolean isAdmin = false;
        if (person.getIsAdmin().equals("1"))
            isAdmin = true;

        return isAdmin;

    }

    public boolean isLoginValid() {

        PersonDB db = new PersonDB(getContext());
        String username = etUsername.getText().toString();
        String password = etPassword.getText().toString();

        Person person = db.selectForLoginValidation(username, password);

        if (person != null) {

            tvErrors.setText("");
            Toast.makeText(getActivity(), "valid login", Toast.LENGTH_SHORT).show();



            ////////calling the abstract method (for sending data from fragment to its activity)
            if (mListener != null) {

                ///choosing proper activity after entering correct username and password
                if (isPersonAdmin(person)) {
                    mListener.sendAdminDatas(person);
//                    goToAdminActivity();

                } else {
                    mListener.sendCustomerDatas(person);
//                    goToPersonProfileActivity();
                }

            }

            return true;

        } else {

            boolean isRegistered = false;

            //......... there is a username like the user entered in the database but the password entered is wrong......
            List<String> usernameList = db.selectUsernames();
            for (String item : usernameList) {

                if (item.equals(username)) {
                    Toast.makeText(getActivity(), "Wrong Password ", Toast.LENGTH_SHORT).show();
                    tvErrors.setText("**Wrong Password**");
                    isRegistered = true;
                    break;
                }
            }

            //......... there is a password like the user entered  in the database but the username entered is wrong......
            List<String> passWordList = db.selectPasswords();
            for (String item : passWordList) {

                if (item.equals(password)) {
                    Toast.makeText(getActivity(), "Wrong Username ", Toast.LENGTH_SHORT).show();
                    tvErrors.setText("**Wrong Username**");
                    isRegistered = true;
                    break;
                }
            }
            ///****** there is no such username and password in database, so the uer should signUp first
            if (!isRegistered) {
                Toast.makeText(getActivity(), "NOT valid ", Toast.LENGTH_SHORT).show();
                tvErrors.setText("U r not registered! signUp First");

            }

            return false;

        }


    }




    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnLoginFragmentInteractionListener) {
            mListener = (OnLoginFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    public interface OnLoginFragmentInteractionListener {
        void sendCustomerDatas(Person currentPersonId);
        void sendAdminDatas(Person adminId);
    }







}
