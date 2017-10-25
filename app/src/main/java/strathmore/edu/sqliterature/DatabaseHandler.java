package strathmore.edu.sqliterature;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Glenn on 19/10/2017.
 */

public class DatabaseHandler extends SQLiteOpenHelper {
    //All Static variables
    //Database Version
    private static final int DATABASE_VERSION = 1;


    //Database Name
    private static final String DATABASE_NAME = "contactsManager";

    //Contacts table name
    private static final String TABLE_CONTACTS = "contacts";

    private static final String TABLE_COURSE = "course";


    //Contacts Tbale Columns names

    private static final String KEY_COURSE_NAME = "course_name";
    private static final String KEY_COURSE_ID = "course_id";
    private static final String KEY_PH_NO = "phone_number";
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    //Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_CONTACTS + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT,"
                + KEY_PH_NO + " TEXT" + ")";
        db.execSQL(CREATE_CONTACTS_TABLE);

        String CREATE_COURSE_TABLE = "CREATE TABLE " + TABLE_COURSE + "("
                + KEY_COURSE_ID + " INTEGER PRIMARY KEY," + KEY_COURSE_NAME + " TEXT" + ")";
        db.execSQL(CREATE_COURSE_TABLE);
    }

    //Updating database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_COURSE);

        //Create tables again
        onCreate(db);
    }

    //Adding new contact
    public void addContact(Contact contact) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, contact.getName()); //Contact Name
        values.put(KEY_PH_NO, contact.getPhoneNumber()); //Contact Phone Number

        //Inserting Row
        db.insert(TABLE_CONTACTS, null, values);
        db.close(); //Closing database connection
    }

    public void addCourse(Course course) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_COURSE_NAME, course.getCourse_name());
        //Inserting Row
        db.insert(TABLE_COURSE, null, values);
        db.close();
    }

    //Getting single contact
    public Contact getContact(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_CONTACTS, new String[]{KEY_ID,
                        KEY_NAME, KEY_PH_NO}, KEY_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Contact contact = new Contact(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2));
        //return contact
        return contact;
    }

    public Course getCourse(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_COURSE, new String[]{KEY_COURSE_ID,
                        KEY_COURSE_NAME}, KEY_COURSE_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Course course = new Course(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1));
        //return course
        return course;
    }

    //Getting All Contacts
    public List<Contact> getAllContacts() {
        List<Contact> contactList = new ArrayList<Contact>();
        //Select All Query
        String selectQuery = "SELECT * FROM " + TABLE_CONTACTS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        //looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Contact contact = new Contact();
                contact.setID(Integer.parseInt(cursor.getString(0)));
                contact.setName(cursor.getString(1));
                contact.setPhoneNumber(cursor.getString(2));
                //Adding contact to list
                contactList.add(contact);
            } while (cursor.moveToNext());
        }
        //return contact list
        return contactList;
    }


    //Getting All Course
    public List<Course> getAllCourse() {
        List<Course> courseList = new ArrayList<Course>();
        //Select All Query
        String selectQuery = "SELECT * FROM " + TABLE_COURSE;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        //looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Course course = new Course();
                course.setCourse_id(Integer.parseInt(cursor.getString(0)));
                course.setCourse_name(cursor.getString(1));
                //Adding contact to list
                courseList.add(course);
            } while (cursor.moveToNext());
        }
        //return course list
        return courseList;
    }

    //Getting contacts Count
    public int getContactsCount() {
        String countQuery = "SELECT * FROM " + TABLE_CONTACTS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        //return count
        return cursor.getCount();
    }

    public int getCourseCount() {
        String countQuery = "SELECT * FROM " + TABLE_COURSE;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        //return count
        return cursor.getCount();
    }

    //Updating single contact
    public int updateContact(Contact contact) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, contact.getName());
        values.put(KEY_PH_NO, contact.getPhoneNumber());

        //updating row
        return db.update(TABLE_CONTACTS, values, KEY_ID + " = ?",
                new String[]{String.valueOf(contact.getID())});
    }

    public int updateCourse(Course course) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_COURSE_NAME, course.getCourse_name());

        //updating row
        return db.update(TABLE_COURSE, values, KEY_COURSE_ID + "=?", new String[]{String.valueOf(course.course_id)});
    }


    //Deleting single contact
    public void deleteContact(Contact contact) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_CONTACTS, KEY_ID + " = ?",
                new String[]{String.valueOf(contact.getID())});
        db.close();
    }

    public void deleteCourse(Course course) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_COURSE, KEY_COURSE_ID + "=?", new String[]{String.valueOf(course.getCourse_id())});
        db.close();
        {
        }


    }


}


