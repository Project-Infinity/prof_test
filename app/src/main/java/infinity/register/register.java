package infinity.register;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.OutputStreamWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class register extends ActionBarActivity
{

    private final static String STORETEXT = "reginfo.txt";
    private EditText email;
    private EditText password;
    private EditText password2;
    private EditText name;
    private EditText zip;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //initialize
        email = (EditText) findViewById(R.id.emailEntry);
        password = (EditText) findViewById(R.id.passwordEntry);
        password2 = (EditText) findViewById(R.id.password2Entry);
        name = (EditText) findViewById(R.id.nameEntry);
        zip = (EditText) findViewById(R.id.zipEntry);
    }

    public void regClicked(View v)
    {
        try
        {
            String emailStr = ("Email: " + email.getText().toString().trim() + "\n");
            String nameStr = ("Name: " + name.getText().toString().trim() + "\n");
            String zipStr = ("Zip: " + zip.getText().toString().trim() + "\n");

            OutputStreamWriter out = new OutputStreamWriter(openFileOutput(STORETEXT, 0));

            out.write(emailStr);
            out.write(nameStr);
            out.write(zipStr);
            out.close();

            Toast.makeText(this, "Registration successful.", Toast.LENGTH_LONG).show();

            startActivity(new Intent(register.this, userInfo.class));
        }
        catch (Throwable t)
        {
            Toast.makeText(this, "Exception: "+t.toString(), Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_register, menu);
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

/*    //input sanitation
    public String sanitizeString(String input)
    {

    }

    //email validation
    public boolean isValidEmailAddress(String email)
    {

    }

    //name validation
    public boolean isValidName(String name)
    {

    }

    //password validation
    public boolean isValidZip(String zip)
    {

    }

    //zip validation
    public boolean isValidZip(String zip)
    {

    }*/
}
