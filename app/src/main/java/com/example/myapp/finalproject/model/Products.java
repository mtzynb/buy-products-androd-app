package com.example.myapp.finalproject.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Raha on 12/16/2017.
 */

public class Products implements Parcelable {

    private int productID ;
    private String productName;
    private int productPrice;
    private int product_CategoryID;
    private byte[] productPicture;

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(int productPrice) {
        this.productPrice = productPrice;
    }

    public int getProduct_CategoryID() {
        return product_CategoryID;
    }

    public void setProduct_CategoryID(int product_CategoryID) {
        this.product_CategoryID = product_CategoryID;
    }

    public byte[] getProductPicture() {
        return productPicture;
    }

    public void setProductPicture(byte[] productPicture) {
        this.productPicture = productPicture;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByteArray(this.productPicture);
        dest.writeString(this.productName);
        dest.writeInt(this.productPrice);
        dest.writeInt(this.product_CategoryID);
        dest.writeInt(this.productID);
    }

    public Products() {
    }

    protected Products(Parcel in) {
        this.productPicture = in.createByteArray();
        this.productName = in.readString();
        this.productPrice = in.readInt();
        this.product_CategoryID = in.readInt();
        this.productID = in.readInt();
    }

    public static final Parcelable.Creator<Products> CREATOR = new Parcelable.Creator<Products>() {
        @Override
        public Products createFromParcel(Parcel source) {
            return new Products(source);
        }

        @Override
        public Products[] newArray(int size) {
            return new Products[size];
        }
    };
}
