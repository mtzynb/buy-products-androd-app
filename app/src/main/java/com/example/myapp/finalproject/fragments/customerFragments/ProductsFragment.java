package com.example.myapp.finalproject.fragments.customerFragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.myapp.finalproject.R;
import com.example.myapp.finalproject.db.PersonDB;
import com.example.myapp.finalproject.model.Products;
import com.example.myapp.finalproject.recyclerAdapter.ProductAdapterForCustomer;

import java.util.List;


public class ProductsFragment extends Fragment {

    private RecyclerView recyclerViewProducts;
    private Spinner productsSpinner;
    private TextView tvStatus;
    private PersonDB db;
    private List<Products> productsList;
    private ProductAdapterForCustomer adpter;


    //  private OnFragmentInteractionListener mListener;

    public ProductsFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_products, container, false);

        recyclerViewProducts = view.findViewById(R.id.recyclerViewProducts);
        productsSpinner = view.findViewById(R.id.productsSpinner);
        tvStatus = view.findViewById(R.id.tvStatus);
        productsSpinner.setOnItemSelectedListener(new CustomOnItemSelectedListener());


        return view;
    }


    //////// action item listener for spinner items
    ////////// inner class for spinner item selection
    private class CustomOnItemSelectedListener implements AdapterView.OnItemSelectedListener {


        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int pos, long l) {

            long selectedItemID = productsSpinner.getSelectedItemId();

            PersonDB db = new PersonDB(getContext());
            productsList = db.getAllProductsByCategoryId(selectedItemID);
            adpter = new ProductAdapterForCustomer(getContext(), productsList);
            recyclerViewProducts.setLayoutManager(new LinearLayoutManager(getContext()));
            recyclerViewProducts.setAdapter(adpter);


            if (productsList.size() != 0 && productsList != null) {
                tvStatus.setText("");
            } else if (productsList.size() == 0 && selectedItemID != 0) {
                tvStatus.setText("No data in this Category!! Sorry :(");
            } else if (selectedItemID == 0) {
                tvStatus.setText("Choose a Category pls");
            }


        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {

        }

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


//    public interface OnFragmentInteractionListener {
//        void onFragmentInteraction(Uri uri);
//    }
}
