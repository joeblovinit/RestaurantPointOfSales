package com.example.restaurantpointofsales;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DataBaseHelper extends SQLiteOpenHelper {

    public static final String FOOD_MENU = "food_menu";
    public static final String COLUMN_FOOD_NAME = "food_name";
    public static final String COLUMN_FOOD_TYPE = "food_type";
    public static final String COLUMN_FOOD_PRICE = "food_price";
    public static final String COLUMN_FOOD_ID = "food_id";

    public static final String STAFF_LIST = "STAFF_LIST";
    public static final String STAFF_ID = "staff_id";
    public static final String STAFF_ROLE = "staff_role";
    public static final String STAFF_NAME = "staff_name";
    public static final String STAFF_SURNAME = "staff_surname";
    public static final String STAFF_USERNAME = "staff_username";
    public static final String STAFF_PASSWORD = "staff_password";
    public static final String STAFF_TIME_WORKED = "staff_time_worked";

    public static final String RESTAURANT_TABLES = "restaurant_tables";
    public static final String TABLE_ID = "table_id";
    public static final String TABLE_NUMBER = "table_number";
    public static final String TABLE_SEATS = "table_seats";

    public static final String FOOD_ORDER = "food_order";
    public static final String ORDER_ID = "order_id";
    public static final String ORDER_FOOD_NAME = "order_food_name";
    public static final String ORDER_FOOD_TYPE = "order_food_type";
    public static final String FOOD_MODIFIER = "food_modifier";
    public static final String ORDER_FOOD_PRICE = "order_food_price";
    public static final String ORDER_TABLE_NUM = "order_table_num";

    public static final String CURRENT_USER = "current_user";
    public static final String LOGIN_ID = "login_id";
    public static final String CURRENT_STAFF_NAME = "current_staff_name";
    public static final String CURRENT_STAFF_SURNAME = "current_staff_surname";
    public static final String CURRENT_STAFF_ROLE = "current_staff_role";

    public static final String CURRENT_ORDERS = "current_orders";
    public static final String ORDER_NUM = "order_num";
    public static final String ORDER_STARTER = "order_starter";
    public static final String ORDER_MAIN = "order_main";
    public static final String ORDER_DESSERT = "order_dessert";
    public static final String ORDER_SIDE = "order_side";
    public static final String ORDER_DRINK = "order_drink";
    public static final String ORDER_STAFF_NAME = "order_staff_name";
    public static final String ORDER_TABLE_NUMBER = "order_table_number";
    public static final String ORDER_TIME = "order_time";
    public static final String ORDER_PRICE = "order_price";

    public DataBaseHelper(@Nullable Context context) {
        super(context, "restaurant", null, 1);
    }

    public static final String DATABASE_CREATE_FOOD_TABLE = "CREATE TABLE "
            + FOOD_MENU
            + "("
            + COLUMN_FOOD_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_FOOD_NAME + " STRING, "
            + COLUMN_FOOD_TYPE + " STRING, "
            + COLUMN_FOOD_PRICE + " DOUBLE)";

    public static final String DATABASE_CREATE_STAFF_TABLE = "CREATE TABLE "
            + STAFF_LIST
            + "("
            + STAFF_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + STAFF_ROLE + " STRING, "
            + STAFF_NAME + " STRING, "
            + STAFF_SURNAME + " STRING, "
            + STAFF_USERNAME + " STRING, "
            + STAFF_PASSWORD + " STRING, "
            + STAFF_TIME_WORKED + " INTEGER)";


    public static final String DATABASE_CREATE_TABLES_TABLE = "CREATE TABLE "
            + RESTAURANT_TABLES
            + "("
            + TABLE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + TABLE_NUMBER + " INTEGER, "
            + TABLE_SEATS + " INTEGER)";


    public static final String database_create_order_table = "CREATE TABLE "
            + FOOD_ORDER
            + "("
            + ORDER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + ORDER_FOOD_NAME + " STRING, "
            + ORDER_FOOD_TYPE + " String, "
            + FOOD_MODIFIER + " STRING, "
            + ORDER_FOOD_PRICE + " DOUBLE, "
            + ORDER_TABLE_NUM + " INTEGER)";


    public static final String database_create_current_user_table = "CREATE TABLE "
            + CURRENT_USER
            + "("
            + LOGIN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + CURRENT_STAFF_NAME + " STRING, "
            + CURRENT_STAFF_SURNAME + " STRING, "
            + CURRENT_STAFF_ROLE + " STRING)";




    public static final String database_create_current_orders_table = "CREATE TABLE "
            + CURRENT_ORDERS
            + "("
            + ORDER_NUM + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + ORDER_STARTER + " TEXT, "
            + ORDER_MAIN + " TEXT, "
            + ORDER_DESSERT + " TEXT, "
            + ORDER_SIDE + " TEXT, "
            + ORDER_DRINK + " TEXT, "
            + ORDER_STAFF_NAME + " STRING, "
            + ORDER_TABLE_NUMBER + " INTEGER, "
            + ORDER_TIME + " STRING, "
            + ORDER_PRICE + " DOUBLE)";


    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(DATABASE_CREATE_FOOD_TABLE);
        db.execSQL(DATABASE_CREATE_STAFF_TABLE);
        db.execSQL(DATABASE_CREATE_TABLES_TABLE);
        db.execSQL(database_create_order_table);
        db.execSQL(database_create_current_user_table);
        db.execSQL(database_create_current_orders_table);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + FOOD_MENU);
        db.execSQL("DROP TABLE IF EXISTS " + STAFF_LIST);
        db.execSQL("DROP TABLE IF EXISTS " + RESTAURANT_TABLES);
        db.execSQL("DROP TABLE IF EXISTS " + FOOD_ORDER);
        db.execSQL("DROP TABLE IF EXISTS " + CURRENT_USER);
        db.execSQL("DROP TABLE IF EXISTS " + CURRENT_ORDERS);
        onCreate(db);

    }

    public boolean addFood(food newFood) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_FOOD_NAME, newFood.getFood_name());
        cv.put(COLUMN_FOOD_TYPE, newFood.getFood_type());
        cv.put(COLUMN_FOOD_PRICE, newFood.getPrice());

        long insert = db.insert(FOOD_MENU, null, cv);
        if (insert == -1) {
            return false;
        } else {
            return true;


        }
    }

    public Boolean addTicket(foodOrder newTicket){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(ORDER_STARTER, newTicket.getStarter());
        cv.put(ORDER_MAIN, newTicket.getMain());
        cv.put(ORDER_DESSERT, newTicket.getDessert());
        cv.put(ORDER_SIDE, newTicket.getSide());
        cv.put(ORDER_DRINK, newTicket.getDrink());
        cv.put(ORDER_STAFF_NAME, newTicket.getStaff_name());
        cv.put(ORDER_TABLE_NUMBER, newTicket.getTable_num());
        cv.put(ORDER_TIME, newTicket.getTime_ordered());
        cv.put(ORDER_PRICE, newTicket.getPrice());

        long insert = db.insert(CURRENT_ORDERS, null, cv);
        if (insert == -1) {
            return false;
        } else {
            return true;


        }
    }

    public boolean addOrder(food newFood, Integer tableNum) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(ORDER_FOOD_NAME, newFood.getFood_name());
        cv.put(ORDER_FOOD_TYPE, newFood.getFood_type());
        cv.put(FOOD_MODIFIER, newFood.getFood_modifiers());
        cv.put(ORDER_FOOD_PRICE, newFood.getPrice());
        cv.put(ORDER_TABLE_NUM, tableNum);

        long insert = db.insert(FOOD_ORDER, null, cv);
        if (insert == -1) {
            return false;
        } else {
            return true;


        }
    }

    public boolean addStaff(staffMember newStaff) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(STAFF_ROLE, newStaff.getStaffRole());
        cv.put(STAFF_NAME, newStaff.getStaffName());
        cv.put(STAFF_SURNAME, newStaff.getStaffSurname());
        cv.put(STAFF_USERNAME, newStaff.getStaffUsername());
        cv.put(STAFF_PASSWORD, newStaff.getStaffPass());
        cv.put(STAFF_TIME_WORKED, newStaff.getStaffTimeWorked());

        long insert = db.insert(STAFF_LIST, null, cv);
        if (insert == -1) {
            return false;
        } else {
            return true;

        }
    }

    public boolean addTable(table newTable) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(TABLE_NUMBER, newTable.getTable_num());
        cv.put(TABLE_SEATS, newTable.getTable_seats());

        long insert = db.insert(RESTAURANT_TABLES, null, cv);
        if (insert == -1) {
            return false;
        } else {
            return true;

        }
    }

    public boolean getCurrentStaff(staffMember newStaff) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(CURRENT_STAFF_NAME, newStaff.getStaffName());
        cv.put(CURRENT_STAFF_SURNAME, newStaff.getStaffSurname());
        cv.put(CURRENT_STAFF_ROLE, newStaff.getStaffRole());

        long insert = db.insert(CURRENT_USER, null, cv);
        if (insert == -1) {
            return false;
        } else {
            return true;

        }


    }

    public List<food> populate_food_menu() {

        List<food> returnList = new ArrayList<>();

        String sql = "SELECT * FROM " + FOOD_MENU;

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(sql, null);

        if (cursor.moveToFirst()) {
            do {
                int foodId = cursor.getInt(0);
                String foodName = cursor.getString(1);
                String foodType = cursor.getString(2);
                Double foodPrice = cursor.getDouble(3);

                food newFood = new food(foodId, foodName, foodType, null, foodPrice);
                returnList.add(newFood);
            } while (cursor.moveToNext());
            {

            }

        }


        return returnList;

    }

    public List<food> populate_order() {

        List<food> returnList = new ArrayList<>();

        String sql = "SELECT * FROM " + FOOD_ORDER;

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(sql, null);

        if (cursor.moveToFirst()) {
            do {
                int foodId = cursor.getInt(0);
                String foodName = cursor.getString(1);
                String foodType = cursor.getString(2);
                String foodModifier = cursor.getString(3);
                Double foodPrice = cursor.getDouble(4);

                food newFood = new food(foodId, foodName, foodType, foodModifier, foodPrice);
                returnList.add(newFood);
            } while (cursor.moveToNext());

            {

            }

        }


        return returnList;
    }


    public List<staffMember> populate_staff_list() {

        List<staffMember> returnList = new ArrayList<>();

        String sql = "SELECT * FROM " + STAFF_LIST;

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(sql, null);

        if (cursor.moveToFirst()) {
            do {
                int staffId = cursor.getInt(0);
                String staffRole = cursor.getString(1);
                String staffName = cursor.getString(2);
                String staffSurname = cursor.getString(3);
                String staffUsername = cursor.getString(4);
                String staffPassword = cursor.getString(5);
                int staffTimeWorked = cursor.getInt(6);

                staffMember newStaff = new staffMember(staffId, staffRole, staffName, staffSurname, staffUsername, staffPassword, staffTimeWorked);
                returnList.add(newStaff);
            } while (cursor.moveToNext());
            {

            }

        }


        return returnList;

    }

    public List<foodOrder> populate_tickets(){

        List<foodOrder> returnList = new ArrayList<>();

        String sql = "SELECT * FROM " + CURRENT_ORDERS;

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(sql, null);

        if (cursor.moveToFirst()) {
            do {
                int orderId = cursor.getInt(0);
                String starter = cursor.getString(1);
                String main = cursor.getString(2);
                String side = cursor.getString(3);
                String dessert = cursor.getString(4);
                String drink = cursor.getString(5);
                String staffName = cursor.getString(6);
                int tableNum = cursor.getInt(7);
                String time = cursor.getString(8);
                Double price = cursor.getDouble(9);
                foodOrder newOrder = new foodOrder(orderId, starter, main, side, dessert, drink, staffName, tableNum, time, price);
                returnList.add(newOrder);

            } while (cursor.moveToNext());
            {

            }

        }


        return returnList;

    }




    public List<table> populate_table_list() {

        List<table> returnList = new ArrayList<>();

        String sql = "SELECT * FROM " + RESTAURANT_TABLES;

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(sql, null);

        if (cursor.moveToFirst()) {
            do {
                int tableId = cursor.getInt(0);
                int tableNum = cursor.getInt(1);
                int tableSeats = cursor.getInt(2);


                table newTable = new table(tableId, tableNum, tableSeats);
                returnList.add(newTable);
            } while (cursor.moveToNext());
            {

            }

        }


        return returnList;

    }

    public List<food> populate_food_starter() {

        List<food> returnList = new ArrayList<>();

        String sql = "SELECT * FROM " + FOOD_MENU + " WHERE " + COLUMN_FOOD_TYPE + " = 'Starter'";

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(sql, null);

        if (cursor.moveToFirst()) {
            do {
                int foodId = cursor.getInt(0);
                String foodName = cursor.getString(1);
                String foodType = cursor.getString(2);
                Double foodPrice = cursor.getDouble(3);

                food newFood = new food(foodId, foodName, foodType, null, foodPrice);
                returnList.add(newFood);
            } while (cursor.moveToNext());
            {

            }

        }


        return returnList;

    }

    public List<food> populate_food_main() {

        List<food> returnList = new ArrayList<>();

        String sql = "SELECT * FROM " + FOOD_MENU + " WHERE " + COLUMN_FOOD_TYPE + " = 'Main'";

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(sql, null);

        if (cursor.moveToFirst()) {
            do {
                int foodId = cursor.getInt(0);
                String foodName = cursor.getString(1);
                String foodType = cursor.getString(2);
                Double foodPrice = cursor.getDouble(3);

                food newFood = new food(foodId, foodName, foodType, null, foodPrice);
                returnList.add(newFood);
            } while (cursor.moveToNext());
            {

            }

        }


        return returnList;

    }

    public List<food> populate_food_dessert() {

        List<food> returnList = new ArrayList<>();

        String sql = "SELECT * FROM " + FOOD_MENU + " WHERE " + COLUMN_FOOD_TYPE + " = 'Dessert'";

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(sql, null);

        if (cursor.moveToFirst()) {
            do {
                int foodId = cursor.getInt(0);
                String foodName = cursor.getString(1);
                String foodType = cursor.getString(2);
                Double foodPrice = cursor.getDouble(3);

                food newFood = new food(foodId, foodName, foodType, null, foodPrice);
                returnList.add(newFood);
            } while (cursor.moveToNext());
            {

            }

        }


        return returnList;

    }

    public List<food> populate_food_side() {

        List<food> returnList = new ArrayList<>();

        String sql = "SELECT * FROM " + FOOD_MENU + " WHERE " + COLUMN_FOOD_TYPE + " = 'Side'";

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(sql, null);

        if (cursor.moveToFirst()) {
            do {
                int foodId = cursor.getInt(0);
                String foodName = cursor.getString(1);
                String foodType = cursor.getString(2);
                Double foodPrice = cursor.getDouble(3);

                food newFood = new food(foodId, foodName, foodType, null, foodPrice);
                returnList.add(newFood);
            } while (cursor.moveToNext());
            {

            }

        }


        return returnList;

    }

    public List<food> populate_food_drinks() {

        List<food> returnList = new ArrayList<>();

        String sql = "SELECT * FROM " + FOOD_MENU + " WHERE " + COLUMN_FOOD_TYPE + " = 'Drink'";

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(sql, null);

        if (cursor.moveToFirst()) {
            do {
                int foodId = cursor.getInt(0);
                String foodName = cursor.getString(1);
                String foodType = cursor.getString(2);
                Double foodPrice = cursor.getDouble(3);

                food newFood = new food(foodId, foodName, foodType, null, foodPrice);
                returnList.add(newFood);
            } while (cursor.moveToNext());
            {

            }

        }


        return returnList;

    }

    public Boolean login(String username, String password) {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + STAFF_LIST + " WHERE " + STAFF_USERNAME + " = ? AND " + STAFF_PASSWORD + " = ?", new String[]{username, password});

        if (cursor.getCount() > 0) {
            return true;
        } else {
            return false;
        }
    }

    public staffMember getStaff(String username, String password) {


        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM " + STAFF_LIST + " WHERE " + STAFF_USERNAME + " = ? AND " + STAFF_PASSWORD + " = ?", new String[]{username, password});

        if (cursor.moveToFirst()) {
            int staffId = cursor.getInt(0);
            String staffRole = cursor.getString(1);
            String staffName = cursor.getString(2);
            String staffSurname = cursor.getString(3);


            staffMember newStaff = new staffMember(staffId, staffRole, staffName, staffSurname, null, null, 0);
            return newStaff;


        }
        return null;

    }

    public staffMember getCurrentUser() {

        String sql = "SELECT * FROM " + CURRENT_USER;

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(sql, null);

        if (cursor.moveToFirst()) {

            String staffName = cursor.getString(1);
            String staffSurname = cursor.getString(2);
            String staffRole = cursor.getString(3);

            staffMember newStaff = new staffMember(0, staffRole, staffName, staffSurname, null,null,0);
            return newStaff;


        }
            return null;
    }



    public void deleteOrder(){

        String sql = "DELETE FROM " + FOOD_ORDER;

        SQLiteDatabase db = this.getWritableDatabase();

        db.execSQL(sql);


    }

    public void deleteCurrentUser(){

        String sql = "DELETE FROM " + CURRENT_USER;

        SQLiteDatabase db = this.getWritableDatabase();

        db.execSQL(sql);

    }

    public boolean updateFood(food newFood){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(COLUMN_FOOD_NAME,newFood.getFood_name());
        cv.put(COLUMN_FOOD_TYPE,newFood.getFood_type());
        cv.put(COLUMN_FOOD_PRICE,newFood.getPrice());

        db.update(FOOD_MENU, cv, "food_id=?", new String[]{String.valueOf(newFood.getFood_id())});


        return true;


    }

    public boolean updateStaff(staffMember newStaff){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(STAFF_ROLE, newStaff.getStaffRole());
        cv.put(STAFF_NAME, newStaff.getStaffName());
        cv.put(STAFF_SURNAME, newStaff.getStaffSurname());
        cv.put(STAFF_USERNAME, newStaff.getStaffUsername());
        cv.put(STAFF_TIME_WORKED, newStaff.getStaffTimeWorked());

        db.update(STAFF_LIST, cv, "staff_id=?", new String[]{String.valueOf(newStaff.getStaffId())});


        return true;


    }

    public boolean updateStaffPass(staffMember newStaff){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(STAFF_PASSWORD, newStaff.getStaffPass());


        db.update(STAFF_LIST, cv, "staff_id=?", new String[]{String.valueOf(newStaff.getStaffId())});


        return true;


    }

    public boolean checkIfStaffIsEmpty(){
        SQLiteDatabase db = this.getReadableDatabase();

        String query = "SELECT COUNT(*) FROM "+ STAFF_LIST;
        Cursor cursor = db.rawQuery(query,null);
        cursor.moveToFirst();
        int count = cursor.getInt(0);

        if(count > 0){
            return false;
        }
        else{
            return true;
        }

    }

    public boolean deleteStaff(staffMember newStaff){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "DELETE FROM " + STAFF_LIST + " WHERE " + STAFF_ID + " = " + newStaff.getStaffId();

        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()){
            return true;
        }
        else{
            return false;
        }
    }

    public boolean deleteFromFoodOrder(food newFood){
        SQLiteDatabase db = this.getWritableDatabase();

        String query = "DELETE FROM " + FOOD_ORDER + " WHERE " + ORDER_ID + " = " + newFood.getFood_id();

        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()){
            return true;
        }
        else{
            return false;
        }
    }

    public boolean deleteFood(food newFood){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "DELETE FROM " + FOOD_MENU + " WHERE " + COLUMN_FOOD_ID + " = " + newFood.getFood_id();

        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()){
            return true;
        }
        else{
            return false;
        }


    }


    public boolean deleteTicket(foodOrder newOrder){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "DELETE FROM " + CURRENT_ORDERS + " WHERE " + ORDER_NUM + " = " + newOrder.getOrder_id();

        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()){
            return true;
        }
        else{
            return false;
        }
    }



    public boolean deleteTable(table newTable){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "DELETE FROM " + RESTAURANT_TABLES + " WHERE " + TABLE_ID + " = " + newTable.getTable_id();

        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()){
            return true;
        }
        else{
            return false;
        }



    }



}


