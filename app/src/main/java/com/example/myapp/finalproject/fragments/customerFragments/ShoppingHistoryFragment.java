package com.example.myapp.finalproject.fragments.customerFragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.myapp.finalproject.R;
import com.example.myapp.finalproject.WelcomActivity;
import com.example.myapp.finalproject.db.PersonDB;
import com.example.myapp.finalproject.model.OrderHistory;
import com.example.myapp.finalproject.model.Person;
import com.example.myapp.finalproject.recyclerAdapter.ShoppingHishtoryCartAdapterForCustomer;

import java.util.List;


public class ShoppingHistoryFragment extends Fragment {


    private RecyclerView recyclerViewShoppingCart;

   // private OnFragmentInteractionListener mListener;

    public ShoppingHistoryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_shopping_history, container, false);


        // Receive data from CustomerActivity
        Bundle bundle = getArguments();
        Person person = bundle.getParcelable(WelcomActivity.CURRENT_CUSTOMER_KEY);
        final int currentCustomerID = person.getPersonID();
        ///////////////////////////////////////////////////////////////////

        ///// start a connection to get shoppingCart data and show it in this fragment
        PersonDB db = new PersonDB(getContext());
        List<OrderHistory> orderHistoryList = db.getAllCustomerShoppingCart(currentCustomerID);

        ////////// show list in an adapter
        recyclerViewShoppingCart = view.findViewById(R.id.rvShoppingHistoryCart);
        ShoppingHishtoryCartAdapterForCustomer adapter = new ShoppingHishtoryCartAdapterForCustomer(getContext(),orderHistoryList);
        recyclerViewShoppingCart.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerViewShoppingCart.setAdapter(adapter);










        return view;
    }
//
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
//        void onFragmentInteraction(Uri uri);
//    }
}
