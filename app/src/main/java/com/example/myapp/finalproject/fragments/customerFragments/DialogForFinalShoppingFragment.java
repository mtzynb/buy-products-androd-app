package com.example.myapp.finalproject.fragments.customerFragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapp.finalproject.CustomerActivity;
import com.example.myapp.finalproject.R;
import com.example.myapp.finalproject.db.PersonDB;
import com.example.myapp.finalproject.model.OrderHistory;
import com.example.myapp.finalproject.model.Products;

import java.util.List;

public class DialogForFinalShoppingFragment extends DialogFragment {


    private EditText etQuantity;
    private Button btnYes;
    private Button btnNo;

//    private OnFragmentInteractionListener mListener;

    public DialogForFinalShoppingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_dialog_for_final_shopping, container, false);
        etQuantity = view.findViewById(R.id.etQuantity);
        btnYes = view.findViewById(R.id.btnYes);
        btnNo = view.findViewById(R.id.btnNo);


        btnNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

        btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Bundle bundle = getArguments();
                Products product = bundle.getParcelable(CustomerActivity.PRODUCT_KEY);
                int currentCustomerID = bundle.getInt(CustomerActivity.CURRENT_CUSTOMER_ID_KEY);

                PersonDB db = new PersonDB(getContext());
                long orderID = db.insertProductsInOrderTble(currentCustomerID);

                if (etQuantity.getText().toString().equals("")) {
                    db.insertIntoOrderDetailsTabe((int) orderID, product, 1);

                } else {
                    db.insertIntoOrderDetailsTabe((int) orderID, product, Integer.valueOf(etQuantity.getText().toString()));
                }

                Toast.makeText(getContext(), "Thanks for ur shopping", Toast.LENGTH_SHORT).show();
                dismiss();

            }
        });


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
//
//    public interface OnFragmentInteractionListener {
//        void onFragmentInteraction(Uri uri);
//    }
}
