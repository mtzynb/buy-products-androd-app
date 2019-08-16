package com.example.myapp.finalproject.recyclerAdapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.myapp.finalproject.R;
import com.example.myapp.finalproject.model.Products;

import java.util.List;

/**
 * Created by Raha on 12/17/2017.
 */

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {

    private Context context ;
    private List<Products> productsList ;
//    private MyListenerInterface myListener ;

    public ProductAdapter(Context context,List<Products> productsList)
    {
            this.productsList=productsList;
            this.context=context;
    }


    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater=LayoutInflater.from(context);
        View mView=inflater.inflate(R.layout.product_item,parent,false);

        ProductViewHolder holder=new ProductViewHolder(mView);

        return holder;

    }

    @Override
    public void onBindViewHolder(ProductViewHolder holder, int position) {


        Products products = productsList.get(position);

        final String pName = products.getProductName();
        final int pPrice = products.getProductPrice();
        final byte[] imageBytes = products.getProductPicture();
        Bitmap imageBitmap = BitmapFactory.decodeByteArray(imageBytes,0,imageBytes.length);

        holder.tvProductName.setText(pName);
        holder.tvProductPrice.setText(String.valueOf(pPrice));
        holder.imageViewProduct.setImageBitmap(imageBitmap);

    }

    @Override
    public int getItemCount() {
        return productsList.size();
    }

    public class ProductViewHolder extends RecyclerView.ViewHolder{
        public TextView tvName ;
        public TextView tvProductName;
        public TextView tvPrice;
        public TextView tvProductPrice;
        public ImageView imageViewProduct;

        public ProductViewHolder(View itemView) {
            super(itemView);

            tvName = itemView.findViewById(R.id.tvName);
            tvProductName = itemView.findViewById(R.id.tvProductName);
            tvPrice = itemView.findViewById(R.id.tvPrice);
            tvProductPrice = itemView.findViewById(R.id.tvProductPrice);
            imageViewProduct = itemView.findViewById(R.id.imageViewProduct);

        }
    }

}
