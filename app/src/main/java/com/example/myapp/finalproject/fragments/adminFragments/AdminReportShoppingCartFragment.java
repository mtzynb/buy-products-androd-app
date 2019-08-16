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
import com.example.myapp.finalproject.model.OrderHistory;
import com.example.myapp.finalproject.recyclerAdapter.ReportShoppingAdapterForAdmin;
import com.example.myapp.finalproject.recyclerAdapter.ShoppingHishtoryCartAdapterForCustomer;

import java.util.List;

public class AdminReportShoppingCartFragment extends Fragment {

//    private OnFragmentInteractionListener mListener;
    private RecyclerView recyclerViewReportShopingCartForAdmin ;


    public AdminReportShoppingCartFragment() {}


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_admin_report_shopping_cart, container, false);
        recyclerViewReportShopingCartForAdmin = view.findViewById(R.id.rvReportShoppingCartForAdmin);
        ///// start a connection to get shoppingCart data and show it in this fragment
        PersonDB db = new PersonDB(getContext());
        List<OrderHistory> orderHistoryList = db.getAllReportShoppingCart();

        ////////// show list in an adapter
        ReportShoppingAdapterForAdmin adapter = new ReportShoppingAdapterForAdmin(getContext(),orderHistoryList);
        recyclerViewReportShopingCartForAdmin.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerViewReportShopingCartForAdmin.setAdapter(adapter);


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
}
