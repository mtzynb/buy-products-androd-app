package com.example.myapp.finalproject;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapp.finalproject.db.PersonDB;
import com.example.myapp.finalproject.model.Products;

import java.io.ByteArrayOutputStream;

public class AddProductActivity extends AppCompatActivity {

    private EditText etProductName;
    private EditText etProductPrice;
    private Spinner productsSpinner;
    private TextView tvErrors;
    private ImageView imageViewProduct;
    private Bitmap imageBitmap;


    public static final int REQUEST_CODE_FOR_CAMERA = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);

        etProductName = findViewById(R.id.etProductName);
        etProductPrice = findViewById(R.id.etProductPrice);
        productsSpinner = findViewById(R.id.productsSpinner);
        tvErrors = findViewById(R.id.tvErrors);
        imageViewProduct = findViewById(R.id.imageViewProduct);


    }


    ///// add an menu to this activity (add product menu)
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.admin_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.menuAdminAddProduct:
                clearViewsForTheNextAddition();

        }

        return super.onOptionsItemSelected(item);
    }

    private void clearViewsForTheNextAddition() {
        tvErrors.setText("");
        etProductName.setText("");
        etProductPrice.setText("");
        productsSpinner.setSelection(0);
        imageViewProduct.setImageDrawable(null);


    }


    private Products setProductsInfo() {

        Products products = new Products();

        products.setProductName(etProductName.getText().toString());

        products.setProductPrice(Integer.parseInt(etProductPrice.getText().toString()));

        String selectedSpinnerItem = String.valueOf(productsSpinner.getSelectedItemId());
        products.setProduct_CategoryID(Integer.parseInt(selectedSpinnerItem));


        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        imageBitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] imageBytes = stream.toByteArray();
        products.setProductPicture(imageBytes);

        return products;


    }

    private void insertProductsToDataBase() {

        PersonDB db = new PersonDB(this);
        Products products = setProductsInfo();
        long productID = db.insertProducts(products);
        Toast.makeText(this, "Saved in data base, Last Product ID: " + productID, Toast.LENGTH_LONG).show();


    }

    private boolean isViewsEmpty() {

        boolean isEmpty = true;

        if (productsSpinner.getSelectedItemId() == 0)
            tvErrors.setText("Choose a Category!!");
        else if (etProductName.getText().toString().equals("") || etProductPrice.getText().toString().equals(""))
            tvErrors.setText("Fill the Fields");
        else if (imageViewProduct.getDrawable() == null)
            tvErrors.setText("Capture a Photo");
        else isEmpty = false;


        return isEmpty;
    }

    public void saveProduct_onClick(View view) {

        boolean isEmpty = isViewsEmpty();
        if (!isEmpty) {
            insertProductsToDataBase();
            clearViewsForTheNextAddition();
        }


    }

    public void capturePhoto_onClick(View view) {

        PackageManager pm = getPackageManager();
        if (pm.hasSystemFeature(PackageManager.FEATURE_CAMERA)) {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(intent, REQUEST_CODE_FOR_CAMERA);
        } else
            Toast.makeText(this, "Your device doesn't support Camera", Toast.LENGTH_LONG).show();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == REQUEST_CODE_FOR_CAMERA && resultCode == RESULT_OK) {

            imageBitmap = (Bitmap) data.getExtras().get("data");
            imageViewProduct.setImageBitmap(imageBitmap);

        }

    }
}
