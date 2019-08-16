package com.example.myapp.finalproject.recyclerAdapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.myapp.finalproject.R;
import com.example.myapp.finalproject.model.Products;
import com.example.myapp.finalproject.myRecyclerAdapterListener.MyListenerInterface;


import java.util.List;

public class ProductAdapterForCustomer extends RecyclerView.Adapter<ProductAdapterForCustomer.ProductViewHolderForCustomer> {

    private Context context;
    private List<Products> productsList;
    private MyListenerInterface myListener;

    public ProductAdapterForCustomer(Context context, List<Products> productsList) {

        try {

            this.myListener = ((MyListenerInterface) context);
            this.productsList = productsList;
            this.context = context;
        } catch (Exception e) {
            throw new ClassCastException("Activity must implements MyListenerInterface.");
        }
    }

    @Override
    public ProductViewHolderForCustomer onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View mView = inflater.inflate(R.layout.product_item_for_customer_to_buy, parent, false);

        ProductViewHolderForCustomer holder = new ProductViewHolderForCustomer(mView);
        return holder;


    }

    @Override
    public void onBindViewHolder(ProductViewHolderForCustomer holder, int position) {

        final Products products = productsList.get(position);

        final String pName = products.getProductName();
        final int pPrice = products.getProductPrice();
        final int pID =products.getProductID();
        final byte[] imageBytes = products.getProductPicture();
        Bitmap imageBitmap = BitmapFactory.decodeByteArray(imageBytes,0,imageBytes.length);

        holder.tvProductName.setText(pName);
        holder.tvProductPrice.setText(String.valueOf(pPrice));
        holder.imageViewProduct.setImageBitmap(imageBitmap);
        holder.tvProductID.setText(String.valueOf(pID));

        holder.btnBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                myListener.onBuyListener(pName,pPrice,imageBytes);
                myListener.onBuyListener(products);
            }
        });

    }

    @Override
    public int getItemCount() {
        return productsList.size();
    }

    public class ProductViewHolderForCustomer extends RecyclerView.ViewHolder {

        public TextView tvProductID;
        public TextView tvName;
        public TextView tvProductName;
        public TextView tvPrice;
        public TextView tvProductPrice;
        public ImageView imageViewProduct;

        public Button btnBuy;

        public ProductViewHolderForCustomer(View itemView) {
            super(itemView);

            tvProductID = itemView.findViewById(R.id.tvProductID);
            tvName = itemView.findViewById(R.id.tvName);
            tvProductName = itemView.findViewById(R.id.tvProductName);
            tvPrice = itemView.findViewById(R.id.tvPrice);
            tvProductPrice = itemView.findViewById(R.id.tvProductPrice);
            imageViewProduct = itemView.findViewById(R.id.imageViewProduct);
            btnBuy = itemView.findViewById(R.id.btnBuy);


        }
    }

}
