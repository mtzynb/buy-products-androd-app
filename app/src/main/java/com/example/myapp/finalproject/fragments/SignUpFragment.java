package com.example.myapp.finalproject.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapp.finalproject.R;
import com.example.myapp.finalproject.SignUpActivity;
import com.example.myapp.finalproject.db.PersonDB;
import com.example.myapp.finalproject.model.Person;

import java.util.List;


public class SignUpFragment extends Fragment {


    private EditText etName;
    private EditText etFamilyName;
    private EditText etUsername;
    private EditText etPassword;
    private EditText etRe_Password;
    private EditText etCity;
    private EditText etPhone;
    private EditText etEmail;
    private TextView tvErrors;
    private Button signUpBtn;

    // private OnFragmentInteractionListener mListener;

    public SignUpFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sign_up, container, false);

        etName = view.findViewById(R.id.etName);
        etFamilyName = view.findViewById(R.id.etFamliyName);
        etUsername = view.findViewById(R.id.etUsername);
        etPassword = view.findViewById(R.id.etPassword);
        etRe_Password = view.findViewById(R.id.etRePassword);
        etCity = view.findViewById(R.id.etCity);
        etPhone = view.findViewById(R.id.etPhone);
        etEmail = view.findViewById(R.id.etEmail);
        tvErrors = view.findViewById(R.id.tvErrors);
        signUpBtn = view.findViewById(R.id.signUpBtn);


        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                boolean isValid = isSignUpFormValid();
                if (isValid) {
                    insertPersonSignUpdatasToDataBase();
                    clearAlLSignUpViews();

                }


//                PersonDB db = new PersonDB(getContext());
//
//
//                Person p = new Person();
//                p.setFirstName("Zahra");
//                p.setLastName("mohammad Tabar");
//                p.setUsername("zahramt");
//                p.setPassword("123456");
//                p.setCity("Tehran");
//                p.setPhone("09121234567");
//                p.setEmail("zahra.mohammadtabar@gmail.com");
//                p.setIsAdmin("0");
//                db.insertPerson(p);
//
//
//                Person p1 = new Person();
//                p1.setFirstName("Arsh");
//                p1.setLastName("negahdari kia");
//                p1.setUsername("arashkia");
//                p1.setPassword("12345");
//                p1.setCity("Tehran");
//                p1.setPhone("09122658742");
//                p1.setEmail("arash.negahdariKia@gmail.com");
//                p1.setIsAdmin("0");
//                long lastPersonID = db.insertPerson(p1);
//                Toast.makeText(getActivity(), "Last Employee ID: " + lastPersonID, Toast.LENGTH_LONG).show();


//                PersonDB db = new PersonDB(getContext());
//
//                db.show();
//                Person p = new Person();
//                p.setFirstName("Zeynab");
//                p.setLastName("mohammad Tabar");
//                p.setUsername("admin");
//                p.setPassword("admin");
//                p.setCity("Tehran");
//                p.setPhone("09360633028");
//                p.setEmail("Zeynab.mohammadtabar@gmail.com");
//                p.setIsAdmin("1");
//
//                db.insertPerson(p);
//
//                Person p1 = new Person();
//                p1.setFirstName("Arsh");
//                p1.setLastName("negahdari kia");
//                p1.setUsername("arashkia");
//                p1.setPassword("12345");
//                p1.setCity("Tehran");
//                p1.setPhone("09122658742");
//                p1.setEmail("arash.negahdariKia@gmail.com");
//                p1.setIsAdmin("0");
//                long lastPersonID = db.insertPerson(p1);
//                Toast.makeText(getActivity(),"Last Employee ID: "+lastPersonID,Toast.LENGTH_LONG).show();


//                db.show();
//                Person p = new Person();
//                p.setFirstName("Zahra");
//                p.setLastName("mohammad Tabar");
//                p.setUsername("zahramt");
//                p.setPassword("123456");
//                p.setCity("Tehran");
//                p.setPhone("09121234567");
//                p.setEmail("zahra.mohammadtabar@gmail.com");
//                p.setIsAdmin("0");
//
//                db.insertPerson(p);
//
//                Person p1 = new Person();
//                p1.setFirstName("Payam");
//                p1.setLastName("Khanteimouri");
//                p1.setUsername("payam");
//                p1.setPassword("12345");
//                p1.setCity("Tehran");
//                p1.setPhone("09122658742");
//                p1.setEmail("payam.khanteimouriKia@gmail.com");
//                p1.setIsAdmin("0");
//                long lastPersonID = db.insertPerson(p1);
//


            }
        });


        return view;
    }


    private void insertPersonSignUpdatasToDataBase() {

        PersonDB db = new PersonDB(getContext());
        Person p = setPersonInfos();
        long lastPersonID = db.insertPerson(p);
        Toast.makeText(getActivity(), "Registration Done! Last Person ID: " + lastPersonID, Toast.LENGTH_LONG).show();


    }

    private void goToSignUpActivity() {

        Intent intent = new Intent(getActivity(), SignUpActivity.class);
        startActivity(intent);

    }

    private boolean isSignUpFormEmpty() {

        boolean isEmpty = true;

        if (TextUtils.isEmpty(etName.getText().toString())) {
            Toast.makeText(getActivity(), "Fill the name field please", Toast.LENGTH_SHORT).show();
            isEmpty = true;
        } else if (TextUtils.isEmpty(etFamilyName.getText().toString())) {
            Toast.makeText(getActivity(), "Fill the family name field please", Toast.LENGTH_SHORT).show();
            isEmpty = true;
        } else if (TextUtils.isEmpty(etUsername.getText().toString())) {
            Toast.makeText(getActivity(), "Fill the username field please", Toast.LENGTH_SHORT).show();
            isEmpty = true;
        } else if (TextUtils.isEmpty(etPassword.getText().toString())) {
            Toast.makeText(getActivity(), "Fill the password field please", Toast.LENGTH_SHORT).show();
            isEmpty = true;
        } else if (TextUtils.isEmpty(etRe_Password.getText().toString())) {
            Toast.makeText(getActivity(), "Fill the re_password field please", Toast.LENGTH_SHORT).show();
            isEmpty = true;
        } else if (TextUtils.isEmpty(etCity.getText().toString())) {
            Toast.makeText(getActivity(), "Fill the city field please", Toast.LENGTH_SHORT).show();
            isEmpty = true;
        } else if (TextUtils.isEmpty(etPhone.getText().toString())) {
            Toast.makeText(getActivity(), "Fill the phone field please", Toast.LENGTH_SHORT).show();
            isEmpty = true;
        } else if (TextUtils.isEmpty(etEmail.getText().toString())) {
            Toast.makeText(getActivity(), "Fill the email field please", Toast.LENGTH_SHORT).show();
            isEmpty = true;
        } else isEmpty = false;

        return isEmpty;
    }

    private boolean isPasswordValid() {

        boolean isPasswordValid = false;

        if (etPassword.getText().length() < 5) {

            isPasswordValid = false;
            tvErrors.setText("Use Atleast 5 chars for password!!");


        } else if (!etPassword.getText().toString().equals(etRe_Password.getText().toString())) {
            isPasswordValid = false;
            tvErrors.setText("Passwords don't match!!");
        } else isPasswordValid = true;

        return isPasswordValid;


    }

    private boolean isUsernameValid() {

        boolean isValid = true;

        if (etUsername.getText().length() < 5) {

            tvErrors.setText("Use Atleast 5 chars for Username!!");
            isValid = false;

        } else {
            PersonDB db = new PersonDB(getContext());
            List<String> usernameList = db.selectUsernames();
            for (String item : usernameList) {
                if (item.equals(etUsername.getText().toString())) {
                    isValid = false;
                    tvErrors.setText("This Username has taken!! Try anonther");
                    break;
                }
            }


        }


        return isValid;

    }

    private boolean isPhoneNumberFieldValid() {
        boolean isValid = false;

        if (etPhone.getText().length() != 11) {
            isValid = false;
            tvErrors.setText("Wrong phone number");
        } else isValid = true;

        return isValid;
    }

    private boolean isSignUpFormValid() {


        if (isSignUpFormEmpty())
            isSignUpFormEmpty();
        else if (!isUsernameValid())
            isUsernameValid();
        else if (!isPasswordValid())
            isPasswordValid();
        else if (!isPhoneNumberFieldValid())
            isPhoneNumberFieldValid();
        else if (isPasswordValid() && isUsernameValid() && isPhoneNumberFieldValid() && !isSignUpFormEmpty()) {
            Toast.makeText(getActivity(), "Thank You for ur registration!", Toast.LENGTH_SHORT).show();
            return true;
        }


        return false;


    }


    private Person setPersonInfos() {

        Person p = new Person();

        p.setFirstName(etName.getText().toString());
        p.setLastName(etFamilyName.getText().toString());
        p.setUsername(etUsername.getText().toString());
        p.setPassword(etPassword.getText().toString());
        p.setCity(etCity.getText().toString());
        p.setPhone(etPhone.getText().toString());
        p.setEmail(etEmail.getText().toString());
        p.setIsAdmin("0");


        return p;


    }
    private void clearAlLSignUpViews(){

        tvErrors.setText("");
        etName.setText("");
        etFamilyName.setText("");
        etUsername.setText("");
        etPassword.setText("");
        etRe_Password.setText("");
        etCity.setText("");
        etPhone.setText("");
        etEmail.setText("");

    }


//    // TODO: Rename method, update argument and hook method into UI event
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
//    /**
//     * This interface must be implemented by activities that contain this
//     * fragment to allow an interaction in this fragment to be communicated
//     * to the activity and potentially other fragments contained in that
//     * activity.
//     * <p>
//     * See the Android Training lesson <a href=
//     * "http://developer.android.com/training/basics/fragments/communicating.html"
//     * >Communicating with Other Fragments</a> for more information.
//     */
//    public interface OnFragmentInteractionListener {
//        // TODO: Update argument type and name
//        void onFragmentInteraction(Uri uri);
//    }
}
