package com.example.myapp.finalproject.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Raha on 12/13/2017.
 */

public class Person implements Parcelable {

    private int personID;
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private String city;
    private String phone;
    private String email;
    private String isAdmin ;


    public Person()
    {

    }
    public Person(int personID,String firstName,String lastName,String username,String password ,String city, String phone ,String email,String isAdmin)
    {
        this.setPersonID(personID);
        this.setFirstName(firstName);
        this.setLastName(lastName);
        this.setUsername(username);
        this.setPassword(password);
        this.setCity(city);
        this.setPhone(phone);
        this.setEmail(email);
        this.setIsAdmin(isAdmin);
    }

    public Person(String firstName,String lastName,String username,String password ,String city, String phone ,String email, String isAdmin)
    {

        this.setFirstName(firstName);
        this.setLastName(lastName);
        this.setUsername(username);
        this.setPassword(password);
        this.setCity(city);
        this.setPhone(phone);
        this.setEmail(email);
        this.setIsAdmin(isAdmin);
    }


    public int getPersonID() {
        return personID;
    }

    public void setPersonID(int personID) {
        this.personID = personID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(String isAdmin) {
        this.isAdmin = isAdmin;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.personID);
        dest.writeString(this.firstName);
        dest.writeString(this.lastName);
        dest.writeString(this.username);
        dest.writeString(this.password);
        dest.writeString(this.city);
        dest.writeString(this.phone);
        dest.writeString(this.email);
        dest.writeString(this.isAdmin);
    }

    protected Person(Parcel in) {
        this.personID = in.readInt();
        this.firstName = in.readString();
        this.lastName = in.readString();
        this.username = in.readString();
        this.password = in.readString();
        this.city = in.readString();
        this.phone = in.readString();
        this.email = in.readString();
        this.isAdmin = in.readString();
    }

    public static final Parcelable.Creator<Person> CREATOR = new Parcelable.Creator<Person>() {
        @Override
        public Person createFromParcel(Parcel source) {
            return new Person(source);
        }

        @Override
        public Person[] newArray(int size) {
            return new Person[size];
        }
    };
}
