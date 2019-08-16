package com.example.myapp.finalproject.recyclerAdapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.myapp.finalproject.R;
import com.example.myapp.finalproject.model.OrderHistory;



import java.util.List;

/**
 * Created by Raha on 12/22/2017.
 */

public class ShoppingHishtoryCartAdapterForCustomer extends RecyclerView.Adapter<ShoppingHishtoryCartAdapterForCustomer.ShoppingCartHistoryViewHolder>{

    private Context context ;
    private List<OrderHistory> orderHistoryList ;


    public ShoppingHishtoryCartAdapterForCustomer(Context context, List<OrderHistory> orderHistoryList)
    {
        this.orderHistoryList=orderHistoryList;
        this.context=context;

    }

    @Override
    public ShoppingCartHistoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View mView=inflater.inflate(R.layout.shopping_history_item,parent,false);

        ShoppingCartHistoryViewHolder holder=new ShoppingCartHistoryViewHolder(mView);

        return holder;
    }

    @Override
    public void onBindViewHolder(ShoppingCartHistoryViewHolder holder, int position) {


        OrderHistory orderHistory = orderHistoryList.get(position);

        final String pName = orderHistory.getProductName();
        final int pPrice = orderHistory.getUnitPrice();
        final int pQuantity = orderHistory.getQuantity();
        final String pOrderDate = orderHistory.getOrderDate();
        final int pTotal = orderHistory.getTotalPrice();

        holder.tvProductName.setText(pName);
        holder.tvProductPrice.setText(String.valueOf(pPrice));
        holder.tvProductQuantity.setText(String.valueOf(pQuantity));
        holder.tvProductOrderDate.setText(pOrderDate);
        holder.tvProductTotal.setText(String.valueOf(pTotal));

    }

    @Override
    public int getItemCount() {
        return orderHistoryList.size();
    }

    public class ShoppingCartHistoryViewHolder extends RecyclerView.ViewHolder{
        public TextView tvName ;
        public TextView tvProductName;
        public TextView tvPrice;
        public TextView tvProductPrice;
        public TextView tvQuantity;
        public TextView tvProductQuantity;
        public TextView tvOrderDate;
        public TextView tvProductOrderDate;
        public TextView tvTotal;
        public TextView tvProductTotal;


        public ShoppingCartHistoryViewHolder(View itemView) {
            super(itemView);

            tvName = itemView.findViewById(R.id.tvName);
            tvProductName = itemView.findViewById(R.id.tvProductName);
            tvPrice = itemView.findViewById(R.id.tvPrice);
            tvProductPrice = itemView.findViewById(R.id.tvProductPrice);
            tvQuantity= itemView.findViewById(R.id.tvQuantity);
            tvProductQuantity = itemView.findViewById(R.id.tvProductQuantity);
            tvOrderDate= itemView.findViewById(R.id.tvDate);
            tvProductOrderDate= itemView.findViewById(R.id.tvProductDate);
            tvTotal= itemView.findViewById(R.id.tvTotal);
            tvProductTotal= itemView.findViewById(R.id.tvProductTotal);

        }
    }

}
