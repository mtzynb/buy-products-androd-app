package com.example.myapp.finalproject.fragments.adminFragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myapp.finalproject.R;
import com.example.myapp.finalproject.db.PersonDB;
import com.example.myapp.finalproject.model.Products;
import com.example.myapp.finalproject.recyclerAdapter.ProductAdapter;

import java.util.List;


public class AdminProductsFragment extends Fragment {


    private RecyclerView recyclerViewProducts;
    private PersonDB db;
    private List<Products> productsList;
    private ProductAdapter adapter;

//    private OnFragmentInteractionListener mListener;

    public AdminProductsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_admin_products, container, false);

        recyclerViewProducts = view.findViewById(R.id.recyclerViewProducts);
        db = new PersonDB(getContext());
        productsList = db.getAllProducts();

        adapter = new ProductAdapter(getContext(), productsList);
        recyclerViewProducts.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerViewProducts.setAdapter(adapter);


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

//    public interface OnFragmentInteractionListener {
//        // TODO: Update argument type and name
//        void onFragmentInteraction(Uri uri);
//    }
}
