package com.example.myapp.finalproject.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.myapp.finalproject.model.OrderHistory;
import com.example.myapp.finalproject.model.Person;
import com.example.myapp.finalproject.model.Products;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Raha on 12/13/2017.
 */

public class PersonDB {
    //Person table Columns
    public static final String DB_NAME = "SuperMarket.db";
    public static final int DB_VERSION = 1;
    public static final String PERSON_TABLE_NAME = "Person";
    public static final String PERSON_COLUMN_ID = "Person_id";
    public static final String PERSON_COLUMN_NAME = "FirstName";
    public static final String PERSON_COLUMN_FAMILY_NAME = "FamilyName";
    public static final String PERSON_COLUMN_USERNAME = "Username";
    public static final String PERSON_COLUMN_PASSWORD = "Password";
    public static final String PERSON_COLUMN_CITY = "City";
    public static final String PERSON_COLUMN_PHONE = "Phone";
    public static final String PERSON_COLUMN_EMAIL = "Email";
    public static final String PERSON_COLUMN_IS_ADMIN = "IsAdmin";

    //values for admin
    public static final String ADMIN_NAME = "Zeynab";
    public static final String ADMIN_FAMILY_NAME = "Mohammad tabar";
    public static final String ADMIN_USERNAME = "admin";
    public static final String ADMIN_PASSWORD = "admin";
    public static final String ADMIN_CITY = "Tehran";
    public static final String ADMIN_PHONE = "09360633028";
    public static final String ADMIN_EMAIL = "zeynab.mohammadtabar@gmail.com";
    public static final String ADMIN_IS_ADMIN = "1";

    //Products table Columns
    public static final String PRODUCT_TABLE_NAME = "Products";
    public static final String PRODUCT_COLUMN_ID = "Product_id";
    public static final String PRODUCT_COLUMN_NAME = "ProductName";
    public static final String PRODUCT_COLUMN_PRODUCT_PRICE = "ProductPrice";
    public static final String PRODUCT_COLUMN_PICTURE = "ProductPicture";
    public static final String PRODUCT_COLUMN_CATEGORY_ID = "Category_id";

    //Category table Columns
    public static final String CATEGORY_TABLE_NAME = "Category";
    public static final String CATEGORY_COLUMN_ID = "Category_id";
    public static final String CATEGORY_COLUMN_NAME = "CategoryName";

    //Orders table Columns
    public static final String ORDERS_TABLE_NAME = "Orders";
    public static final String ORDERS_COLUMN_ID = "Orders_id";
    public static final String ORDERS_COLUMN_ORDER_DATE = "OrderDate";
    public static final String ORDERS_COLUMN_PERSON_ID = "Person_id";

    //Order Details table Column
    public static final String ORDER_DETAILS_TABLE_NAME = "OrderDetails";
    public static final String ORDER_DETAILS_COLUMN_ORDER_ID = "Order_id";
    public static final String ORDER_DETAILS_COLUMN_PRODUCT_ID = "Product_id";
    public static final String ORDER_DETAILS_COLUMN_UNIT_PRICE = "UnitPrice";
    public static final String ORDER_DETAILS_COLUMN_QUANTITY = "Quantity";

    //datas for Category Table
    public static final String ELECTRONIC_CATEGORY = "Electronics, Computers and Office";
    public static final String BOOKS_CATEGORY = "Books and Audible";
    public static final String FOOD_CATEGORY = "Food and Grocery";
    public static final String CLOTHING_CATEGORY = "Clothing, Shoes and Jewerly";


    private DBHelper dbHelper;
    private SQLiteDatabase db;

    public PersonDB(Context context) {
        dbHelper = new DBHelper(context, DB_NAME, null, DB_VERSION);
    }

    private void openWritableDataBase() {
        db = dbHelper.getWritableDatabase();
    }

    private void openReadableDataBase() {
        db = dbHelper.getReadableDatabase();
    }

    private void closeDB() {
        db.close();
    }

    private String calcDate() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();

        return dateFormat.format(date);
    }

    //*********************** Person  Methods ***********************//
    public long insertPerson(Person person) {
        long personID = 0;
        try {
            openWritableDataBase();

            ContentValues cv = new ContentValues();
            cv.put(PERSON_COLUMN_NAME, person.getFirstName());
            cv.put(PERSON_COLUMN_FAMILY_NAME, person.getLastName());
            cv.put(PERSON_COLUMN_USERNAME, person.getUsername());
            cv.put(PERSON_COLUMN_PASSWORD, person.getPassword());
            cv.put(PERSON_COLUMN_CITY, person.getCity());
            cv.put(PERSON_COLUMN_PHONE, person.getPhone());
            cv.put(PERSON_COLUMN_EMAIL, person.getEmail());
            cv.put(PERSON_COLUMN_IS_ADMIN, person.getIsAdmin());


            personID = db.insert(PERSON_TABLE_NAME, null, cv);

            closeDB();
        } catch (Exception ex) {
            personID = -1;
        }
        return personID;
    }


    public Person selectForLoginValidation(String username, String password) {
        try {
            boolean personIsNull = true;
            Person person = new Person();
            openReadableDataBase();

            String where = PERSON_COLUMN_USERNAME + " =? and " + PERSON_COLUMN_PASSWORD + " =?";
            String[] whereArgs = {username, password};
            Cursor c = db.query(PERSON_TABLE_NAME, null, where, whereArgs, null, null, null);
            if (c.moveToNext()) {

                person.setPersonID(c.getInt(c.getColumnIndex(PERSON_COLUMN_ID)));
                person.setFirstName(c.getString(c.getColumnIndex(PERSON_COLUMN_NAME)));
                person.setLastName(c.getString(c.getColumnIndex(PERSON_COLUMN_FAMILY_NAME)));
                person.setUsername(c.getString(c.getColumnIndex(PERSON_COLUMN_USERNAME)));
                person.setPassword(c.getString(c.getColumnIndex(PERSON_COLUMN_PASSWORD)));
                person.setCity(c.getString(c.getColumnIndex(PERSON_COLUMN_CITY)));
                person.setPhone(c.getString(c.getColumnIndex(PERSON_COLUMN_PHONE)));
                person.setEmail(c.getString(c.getColumnIndex(PERSON_COLUMN_EMAIL)));
                person.setIsAdmin(c.getString(c.getColumnIndex(PERSON_COLUMN_IS_ADMIN)));

                personIsNull = false;
            }
            c.close();
            closeDB();

            if (personIsNull)
                return null;
            else return person;


        } catch (Exception ex) {
            return null;
        }


    }


    public long insertProductsInOrderTble(int personID) {

        long orderID = 0;
        try {
            openWritableDataBase();

            ContentValues cv = new ContentValues();
            cv.put(ORDERS_COLUMN_ORDER_DATE, calcDate());
            cv.put(ORDERS_COLUMN_PERSON_ID, personID);

            orderID = db.insert(ORDERS_TABLE_NAME, null, cv);

            closeDB();
        } catch (Exception ex) {
            orderID = -1;
        }
        return orderID;
    }

    public long insertIntoOrderDetailsTabe(int orderID, Products product, int quantity) {

        long row = 0;
        try {
            openWritableDataBase();

            ContentValues cv = new ContentValues();
            cv.put(ORDER_DETAILS_COLUMN_ORDER_ID, orderID);
            cv.put(ORDER_DETAILS_COLUMN_PRODUCT_ID, product.getProductID());
            cv.put(ORDER_DETAILS_COLUMN_UNIT_PRICE, product.getProductPrice());
            cv.put(ORDER_DETAILS_COLUMN_QUANTITY, quantity);


            row = db.insert(ORDER_DETAILS_TABLE_NAME, null, cv);

            closeDB();
        } catch (Exception ex) {
            row = -1;
        }
        return row;
    }



    public List<Products> getAllProductsByCategoryId(long categorySelectedID) {

        try {
            List<Products> productsList = new ArrayList<>();
            openReadableDataBase();
            String[] columns = {PRODUCT_COLUMN_ID, PRODUCT_COLUMN_NAME, PRODUCT_COLUMN_PRODUCT_PRICE, PRODUCT_COLUMN_PICTURE};
            String[] whereArgs = {String.valueOf(categorySelectedID)};

            Cursor c = db.query(PRODUCT_TABLE_NAME, columns, PRODUCT_COLUMN_CATEGORY_ID+"=?", whereArgs, null, null, null);


            while (c.moveToNext()) {

                Products products = new Products();
                products.setProductID(c.getInt(c.getColumnIndex(PRODUCT_COLUMN_ID)));
                products.setProductName(c.getString(c.getColumnIndex(PRODUCT_COLUMN_NAME)));
                products.setProductPrice(c.getInt(c.getColumnIndex(PRODUCT_COLUMN_PRODUCT_PRICE)));
                products.setProductPicture(c.getBlob(c.getColumnIndex(PRODUCT_COLUMN_PICTURE)));

                productsList.add(products);

            }

            c.close();
            closeDB();
            return productsList;

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return null;

        }

    }














    public List<OrderHistory> getAllCustomerShoppingCart(int currentCustomerID) {

        try {
            List<OrderHistory> orderHistoryList = new ArrayList<>();
            openReadableDataBase();

//            select Products.ProductName, OrderDetails.UnitPrice, OrderDetails.Quantity, Orders.OrderDate,
//                    OrderDetails.UnitPrice*OrderDetails.Quantity as TotalPrice
//            from OrderDetails
//            inner join Products
//                    on
//            Products.Product_id = OrderDetails.Product_id
//            inner join Orders
//                    on
//            Orders.Orders_id = OrderDetails.Order_id
//            where Orders.Person_id = 3


            String strSQL = "select " + PRODUCT_TABLE_NAME + "." + PRODUCT_COLUMN_NAME + " , " +
                    ORDER_DETAILS_TABLE_NAME + "." + ORDER_DETAILS_COLUMN_UNIT_PRICE + " , " +
                    ORDER_DETAILS_TABLE_NAME + "." + ORDER_DETAILS_COLUMN_QUANTITY + " , " +
                    ORDERS_TABLE_NAME + "." + ORDERS_COLUMN_ORDER_DATE + " , " +
                    ORDER_DETAILS_TABLE_NAME + "." + ORDER_DETAILS_COLUMN_UNIT_PRICE + "*" +
                    ORDER_DETAILS_TABLE_NAME + "." + ORDER_DETAILS_COLUMN_QUANTITY + " as TotalPrice " +

                    " from " + ORDER_DETAILS_TABLE_NAME +
                    " inner join " + PRODUCT_TABLE_NAME +
                    " on " +
                    PRODUCT_TABLE_NAME + "." + PRODUCT_COLUMN_ID + " = " + ORDER_DETAILS_TABLE_NAME + "." + ORDER_DETAILS_COLUMN_PRODUCT_ID +
                    " inner join " + ORDERS_TABLE_NAME +
                    " on " +
                    ORDERS_TABLE_NAME + "." + ORDERS_COLUMN_ID + " = " + ORDER_DETAILS_TABLE_NAME + "." + ORDER_DETAILS_COLUMN_ORDER_ID +
                    " where " + ORDERS_TABLE_NAME + "." + ORDERS_COLUMN_PERSON_ID + " = ?" ;

            String[] whereArgs = {String.valueOf(currentCustomerID)};
            Cursor c = db.rawQuery(strSQL, whereArgs);


            while (c.moveToNext()) {

                OrderHistory orderHistory = new OrderHistory();
                orderHistory.setProductName(c.getString(c.getColumnIndex(PRODUCT_COLUMN_NAME)));
                orderHistory.setUnitPrice(c.getInt(c.getColumnIndex(ORDER_DETAILS_COLUMN_UNIT_PRICE)));
                orderHistory.setQuantity(c.getInt(c.getColumnIndex(ORDER_DETAILS_COLUMN_QUANTITY)));
                orderHistory.setOrderDate(c.getString(c.getColumnIndex(ORDERS_COLUMN_ORDER_DATE)));
                orderHistory.setTotalPrice(c.getInt(c.getColumnIndex("TotalPrice")));

                orderHistoryList.add(orderHistory);
            }

            c.close();
            closeDB();
            return orderHistoryList;

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return null;

        }

    }


//        public Person selectForPersonProFile(long id) {
//        try {
//            boolean personIsNull = true;
//            Person person = new Person();
//            openReadableDataBase();
//
//            String where = PERSON_COLUMN_ID + " = ? " ;
//            String[] whereArgs = {String.valueOf(id)};
//            Cursor c = db.query(PERSON_TABLE_NAME, null, where, whereArgs, null, null, null);
//            if (c.moveToNext()) {
//
//                person.setPersonID(c.getInt(c.getColumnIndex(PERSON_COLUMN_ID)));
//                person.setFirstName(c.getString(c.getColumnIndex(PERSON_COLUMN_NAME)));
//                person.setLastName(c.getString(c.getColumnIndex(PERSON_COLUMN_FAMILY_NAME)));
//                person.setUsername(c.getString(c.getColumnIndex(PERSON_COLUMN_USERNAME)));
//                person.setPassword(c.getString(c.getColumnIndex(PERSON_COLUMN_PASSWORD)));
//                person.setCity(c.getString(c.getColumnIndex(PERSON_COLUMN_CITY)));
//                person.setPhone(c.getString(c.getColumnIndex(PERSON_COLUMN_PHONE)));
//                person.setEmail(c.getString(c.getColumnIndex(PERSON_COLUMN_EMAIL)));
//                person.setIsAdmin(c.getString(c.getColumnIndex(PERSON_COLUMN_IS_ADMIN)));
//
//                personIsNull = false;
//            }
//            c.close();
//            closeDB();
//
//            if (personIsNull)
//                return null;
//            else return person;
//
//
//        } catch (Exception ex) {
//            return null;
//        }
//
//
//    }

    public List<String> selectUsernames() {

        try {

            List<String> usernameList = new ArrayList<>();
            openReadableDataBase();
            String[] columns = {PERSON_COLUMN_USERNAME};
            Cursor c = db.query(PERSON_TABLE_NAME, columns, null, null, null, null, null);
            while (c.moveToNext()) {

                String username = c.getString(c.getColumnIndex(PERSON_COLUMN_USERNAME));
                usernameList.add(username);
            }

            c.close();
            closeDB();
            return usernameList;

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return null;


        }


    }

    public List<String> selectPasswords() {

        try {

            List<String> passwordList = new ArrayList<>();
            openReadableDataBase();
            String[] columns = {PERSON_COLUMN_PASSWORD};
            Cursor c = db.query(PERSON_TABLE_NAME, columns, null, null, null, null, null);
            while (c.moveToNext()) {

                String password = c.getString(c.getColumnIndex(PERSON_COLUMN_PASSWORD));
                passwordList.add(password);
            }

            c.close();
            closeDB();
            return passwordList;

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return null;


        }


    }


    ///*************************** Admin Methods *************
    public long insertProducts(Products products) {
        long productID = 0;
        try {
            openWritableDataBase();

            ContentValues cv = new ContentValues();
            cv.put(PRODUCT_COLUMN_NAME, products.getProductName());
            cv.put(PRODUCT_COLUMN_PRODUCT_PRICE, products.getProductPrice());
            cv.put(PRODUCT_COLUMN_CATEGORY_ID, products.getProduct_CategoryID());
            cv.put(PRODUCT_COLUMN_PICTURE, products.getProductPicture());

            productID = db.insert(PRODUCT_TABLE_NAME, null, cv);

            closeDB();
        } catch (Exception ex) {
            productID = -1;
        }
        return productID;
    }


    public List<Products> getAllProducts() {

        try {
            List<Products> productsList = new ArrayList<>();
            openReadableDataBase();
            String[] columns = {PRODUCT_COLUMN_ID, PRODUCT_COLUMN_NAME, PRODUCT_COLUMN_PRODUCT_PRICE, PRODUCT_COLUMN_PICTURE};

            Cursor c = db.query(PRODUCT_TABLE_NAME, columns, null, null, null, null, null);


            while (c.moveToNext()) {

                Products products = new Products();
                products.setProductID(c.getInt(c.getColumnIndex(PRODUCT_COLUMN_ID)));
                products.setProductName(c.getString(c.getColumnIndex(PRODUCT_COLUMN_NAME)));
                products.setProductPrice(c.getInt(c.getColumnIndex(PRODUCT_COLUMN_PRODUCT_PRICE)));
                products.setProductPicture(c.getBlob(c.getColumnIndex(PRODUCT_COLUMN_PICTURE)));

                productsList.add(products);

            }

            c.close();
            closeDB();
            return productsList;

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return null;

        }

    }



    public List<OrderHistory> getAllReportShoppingCart() {

        try {
            List<OrderHistory> orderHistoryList = new ArrayList<>();
            openReadableDataBase();

//            select Person.Username, Products.ProductName, OrderDetails.UnitPrice, OrderDetails.Quantity, Orders.OrderDate,
//                    OrderDetails.UnitPrice*OrderDetails.Quantity as TotalPrice
//            from OrderDetails
//            inner join Products
//                    on
//            Products.Product_id = OrderDetails.Product_id
//            inner join Orders
//                    on
//            Orders.Orders_id = OrderDetails.Order_id
//            inner join Person
//                    on
//            Person.Person_id = Orders.Person_id



            String strSQL = "select " +
                    PERSON_TABLE_NAME + "." + PERSON_COLUMN_USERNAME +" , " +
                    PRODUCT_TABLE_NAME + "." + PRODUCT_COLUMN_NAME + " , " +
                    ORDER_DETAILS_TABLE_NAME + "." + ORDER_DETAILS_COLUMN_UNIT_PRICE + " , " +
                    ORDER_DETAILS_TABLE_NAME + "." + ORDER_DETAILS_COLUMN_QUANTITY + " , " +
                    ORDERS_TABLE_NAME + "." + ORDERS_COLUMN_ORDER_DATE + " , " +
                    ORDER_DETAILS_TABLE_NAME + "." + ORDER_DETAILS_COLUMN_UNIT_PRICE + "*" +
                    ORDER_DETAILS_TABLE_NAME + "." + ORDER_DETAILS_COLUMN_QUANTITY + " as TotalPrice " +

                    " from " + ORDER_DETAILS_TABLE_NAME +
                    " inner join " + PRODUCT_TABLE_NAME +
                    " on " +
                    PRODUCT_TABLE_NAME + "." + PRODUCT_COLUMN_ID + " = " + ORDER_DETAILS_TABLE_NAME + "." + ORDER_DETAILS_COLUMN_PRODUCT_ID +
                    " inner join " + ORDERS_TABLE_NAME +
                    " on " +
                    ORDERS_TABLE_NAME + "." + ORDERS_COLUMN_ID + " = " + ORDER_DETAILS_TABLE_NAME + "." + ORDER_DETAILS_COLUMN_ORDER_ID +
                    " inner join " + PERSON_TABLE_NAME +
                    " on " +
                    PERSON_TABLE_NAME +"."+ PERSON_COLUMN_ID + "=" + ORDERS_TABLE_NAME + "." + ORDERS_COLUMN_PERSON_ID
                    ;


            Cursor c = db.rawQuery(strSQL, null);


            while (c.moveToNext()) {

                OrderHistory orderHistory = new OrderHistory();
                orderHistory.setCustomerUsername(c.getString(c.getColumnIndex(PERSON_COLUMN_USERNAME)));
                orderHistory.setProductName(c.getString(c.getColumnIndex(PRODUCT_COLUMN_NAME)));
                orderHistory.setUnitPrice(c.getInt(c.getColumnIndex(ORDER_DETAILS_COLUMN_UNIT_PRICE)));
                orderHistory.setQuantity(c.getInt(c.getColumnIndex(ORDER_DETAILS_COLUMN_QUANTITY)));
                orderHistory.setOrderDate(c.getString(c.getColumnIndex(ORDERS_COLUMN_ORDER_DATE)));
                orderHistory.setTotalPrice(c.getInt(c.getColumnIndex("TotalPrice")));

                orderHistoryList.add(orderHistory);
            }

            c.close();
            closeDB();
            return orderHistoryList;

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return null;

        }

    }


    ///inner class DBHelper
    private static class DBHelper extends SQLiteOpenHelper {

        public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }


        @Override
        public void onCreate(SQLiteDatabase db) {

            String strCrateTablePerson = "CREATE TABLE " + PERSON_TABLE_NAME +
                    " (" + PERSON_COLUMN_ID + "  integer primary key autoincrement not null," +
                    PERSON_COLUMN_NAME + "  Text not null," +
                    PERSON_COLUMN_FAMILY_NAME + " Text not null," +
                    PERSON_COLUMN_USERNAME + "  Text not null unique," +
                    PERSON_COLUMN_PASSWORD + "  Text not null," +
                    PERSON_COLUMN_CITY + "  Text not null," +
                    PERSON_COLUMN_PHONE + "  Text not null," +
                    PERSON_COLUMN_EMAIL + "  Text not null," +
                    PERSON_COLUMN_IS_ADMIN + "  Text not null" +
                    " )";


            String strCreateTableCategory = "CREATE TABLE " + CATEGORY_TABLE_NAME +
                    " (" + CATEGORY_COLUMN_ID + "  integer primary key autoincrement not null," +
                    CATEGORY_COLUMN_NAME + "  Text not null" +
                    " )";


            String strCreateTableProduct = "CREATE TABLE " + PRODUCT_TABLE_NAME +
                    " (" + PRODUCT_COLUMN_ID + "  integer primary key autoincrement not null," +
                    PRODUCT_COLUMN_NAME + "  Text not null," +
                    PRODUCT_COLUMN_PRODUCT_PRICE + " Text not null," +
                    PRODUCT_COLUMN_CATEGORY_ID + " integer not null, " +
                    PRODUCT_COLUMN_PICTURE + " blob, " +
                    "foreign key(" + PRODUCT_COLUMN_CATEGORY_ID + ") references " + CATEGORY_TABLE_NAME + "(" + CATEGORY_COLUMN_ID + ")" +
                    " )";


            String strCreateTableOrders = "CREATE TABLE " + ORDERS_TABLE_NAME +
                    " (" + ORDERS_COLUMN_ID + "  integer primary key autoincrement not null," +
                    ORDERS_COLUMN_ORDER_DATE + "  Text not null," +
                    ORDERS_COLUMN_PERSON_ID + " integer not null," +
                    "foreign key(" + ORDERS_COLUMN_PERSON_ID + ") references " + PERSON_TABLE_NAME + "(" + PERSON_COLUMN_ID + ")" +
                    " )";


            String strCreateTableOrderDetails = "CREATE TABLE " + ORDER_DETAILS_TABLE_NAME +
                    " (" + ORDER_DETAILS_COLUMN_ORDER_ID + "  integer  not null," +
                    ORDER_DETAILS_COLUMN_PRODUCT_ID + "  integer not null," +
                    ORDER_DETAILS_COLUMN_UNIT_PRICE + " integer not null," +
                    ORDER_DETAILS_COLUMN_QUANTITY + " integer not null," +
                    "primary key(" + ORDER_DETAILS_COLUMN_ORDER_ID + " , " + ORDER_DETAILS_COLUMN_PRODUCT_ID + ")" +
                    " )";


            db.execSQL(strCrateTablePerson);
            db.execSQL(strCreateTableCategory);
            db.execSQL(strCreateTableProduct);
            db.execSQL(strCreateTableOrders);
            db.execSQL(strCreateTableOrderDetails);


            //insert admin datas
            ContentValues cv = new ContentValues();
            cv.put(PERSON_COLUMN_NAME, ADMIN_NAME);
            cv.put(PERSON_COLUMN_FAMILY_NAME, ADMIN_FAMILY_NAME);
            cv.put(PERSON_COLUMN_USERNAME, ADMIN_USERNAME);
            cv.put(PERSON_COLUMN_PASSWORD, ADMIN_PASSWORD);
            cv.put(PERSON_COLUMN_CITY, ADMIN_CITY);
            cv.put(PERSON_COLUMN_PHONE, ADMIN_PHONE);
            cv.put(PERSON_COLUMN_EMAIL, ADMIN_EMAIL);
            cv.put(PERSON_COLUMN_IS_ADMIN, ADMIN_IS_ADMIN);
//            Person person = new Person(ADMIN_NAME,ADMIN_FAMILY_NAME,ADMIN_USERNAME,ADMIN_PASSWORD,ADMIN_CITY,ADMIN_PHONE,ADMIN_EMAIL,ADMIN_IS_ADMIN);

            db.insert(PERSON_TABLE_NAME, null, cv);

            //insert category datas

            ContentValues cv2 = new ContentValues();
            cv2.put(CATEGORY_COLUMN_NAME, ELECTRONIC_CATEGORY);
            db.insert(CATEGORY_TABLE_NAME, null, cv2);

            ContentValues cv3 = new ContentValues();
            cv3.put(CATEGORY_COLUMN_NAME, BOOKS_CATEGORY);
            db.insert(CATEGORY_TABLE_NAME, null, cv3);

            ContentValues cv4 = new ContentValues();
            cv4.put(CATEGORY_COLUMN_NAME, FOOD_CATEGORY);
            db.insert(CATEGORY_TABLE_NAME, null, cv4);

            ContentValues cv5 = new ContentValues();
            cv5.put(CATEGORY_COLUMN_NAME, CLOTHING_CATEGORY);
            db.insert(CATEGORY_TABLE_NAME, null, cv5);


        }


        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

            db.execSQL("Drop Table if exists " + PERSON_TABLE_NAME);
            onCreate(db);

        }
    }


}
