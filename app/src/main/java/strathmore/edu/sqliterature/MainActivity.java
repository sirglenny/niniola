package strathmore.edu.sqliterature;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DatabaseHandler db = new DatabaseHandler(this);
        /**
         * CRUD Operations
         * */
        //Inserting Contacts
        Log.d("Insert: ", "Inserting ..");
        db.addContact(new Contact("Quavo", "910000000"));
        db.addContact(new Contact("Takeoff", "91999999"));
        db.addContact(new Contact("Glenny", "9112345678"));
        db.addContact(new Contact("Offset", "91987654321"));

        Log.d("Insert:", "Inserting..");
        db.addCourse(new Course("Physics"));
        db.addCourse(new Course("History"));
        db.addCourse(new Course("Swahili"));
        db.addCourse(new Course("English"));


        //Reading all contacts
        Log.d("Reading: ", "Reading all contacts..");
        Log.d("Reading:",  "Reading all courses..");
        List<Contact> contacts = db.getAllContacts();
        List<Course> course = db.getAllCourse();

        for (Contact cn : contacts) {
            String log = "Id: " +cn.getID()+" ,Name: " + cn.getName() + " ,Phone:"
                    +cn.getPhoneNumber();
        //Writing contacts to log
            Log.d("Name: ", log);

            }
        for (Course cn : course) {
            String log = "Id: " +cn.getCourse_id()+" ,Name: " + cn.getCourse_name()
                 ;
            //Writing contacts to log
            Log.d("Name: ", log);


            }
        }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
