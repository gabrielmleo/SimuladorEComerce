package com.example.gabrielmoraes.simuladorecomerce.domain;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by gabri on 28/03/2017.
 */

public class Product implements Parcelable{

    private String title;
    private String price;
    private String zipcode;
    private String seller;
    private String thumbnailHd;
    private String date;

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getSeller() {
        return seller;
    }

    public void setSeller(String seller) {
        this.seller = seller;
    }

    public String getThumbnailHd() {
        return thumbnailHd;
    }

    public void setThumbnailHd(String thumbnailHd) {
        this.thumbnailHd = thumbnailHd;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTitle() {

        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Product(){

    }

    protected Product(Parcel in){

        this.title = in.readString();
        this.price = in.readString();
        this.zipcode = in.readString();
        this.seller = in.readString();
        this.thumbnailHd = in.readString();
        this.date = in.readString();

    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeString(this.title);
        dest.writeString(this.price);
        dest.writeString(this.zipcode);
        dest.writeString(this.seller);
        dest.writeString(this.thumbnailHd);
        dest.writeString(this.date);
    }

    public static final Parcelable.Creator<Product> CREATOR = new Parcelable.Creator<Product>() {
        @Override
        public Product createFromParcel(Parcel source) {
            return new Product(source);
        }

        @Override
        public Product[] newArray(int size) {
            return new Product[size];
        }
    };


}
