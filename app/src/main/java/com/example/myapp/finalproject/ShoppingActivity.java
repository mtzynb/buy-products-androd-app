package com.example.myapp.finalproject;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapp.finalproject.fragments.customerFragments.DialogForFinalShoppingFragment;

public class ShoppingActivity extends AppCompatActivity {


    private TextView tvName;
    private TextView tvProductName;
    private TextView tvPrice;
    private TextView tvProductPrice;
    private TextView tvQuantity;
    private EditText etQuantity;
    private ImageView imageViewProduct;
    private Button btnBuy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping);


        tvName = findViewById(R.id.tvName);
        tvProductName = findViewById(R.id.tvProductName);
        tvPrice = findViewById(R.id.tvPrice);
        tvProductPrice = findViewById(R.id.tvProductPrice);
        tvQuantity = findViewById(R.id.tvQuantity);
        etQuantity = findViewById(R.id.etQuantity);
        imageViewProduct = findViewById(R.id.imageViewProduct);

        Intent intent = getIntent();
        String pName = intent.getStringExtra(CustomerActivity.PRODUCT_NAME_KEY);
        int pPrice = intent.getIntExtra(CustomerActivity.PRODUCT_PRICE_KEY, 0);
        byte[] pImage = intent.getByteArrayExtra(CustomerActivity.PRODUCT_IMAGE_KEY);

        Bitmap imageBitmap = BitmapFactory.decodeByteArray(pImage, 0, pImage.length);

        tvProductName.setText(pName);
        tvProductPrice.setText(String.valueOf(pPrice));
        imageViewProduct.setImageBitmap(imageBitmap);


    }

    public void btnBuyAndGoToFinalFragment(View view) {

        DialogForFinalShoppingFragment mDialog = new DialogForFinalShoppingFragment();
        mDialog.show(getSupportFragmentManager(),"MyDialog");



    }
}
