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
        String passVal = password.getText().toString();
        String pass2Val = password2.getText().toString();
        String emailVal = email.getText().toString();
        String nameVal = name.getText().toString();
        String zipVal = zip.getText().toString();

        if(emailValidate(emailVal) && passwordsMatch(passVal, pass2Val) && nameValidate(nameVal) && zipValidate(zipVal))
        {
            try
            {
                String emailStr = ("Email: " + emailVal + "\n");
                String nameStr = ("Name: " + nameVal + "\n");
                String zipStr = ("Zip: " + zipVal + "\n");

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
                Toast.makeText(this, "Exception: " + t.toString(), Toast.LENGTH_LONG).show();
            }
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

    /*//input sanitation
    public String sanitizeString(String input)
    {

    }*/

    //email validation
    public boolean emailValidate(String email)
    {
        String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        Pattern p = Pattern.compile(ePattern);
        Matcher m = p.matcher(email);
        if (!m.matches())
        {
            Toast.makeText(this, "Invalid Email", Toast.LENGTH_LONG).show();
            return false;
        }
        else
        {
            return true;
        }
    }

    //zip validation
    public boolean zipValidate(String zip)
    {
        char [] c = zip.toCharArray();
        for(int i=0; i < zip.length(); i++)
        {
            if (!Character.isDigit(c[i]))
            {
                Toast.makeText(this, "Zip must contain only numbers.", Toast.LENGTH_LONG).show();
                return false;
            }
        }
        return true;
    }

    //name validation
    public boolean nameValidate(String name)
    {
        if(name.matches(".*\\d.*"))
        {
            Toast.makeText(this, "Name can not contain a number.", Toast.LENGTH_LONG).show();
            return false;
        }
        else
        {
            return true;
        }
    }

    //password validation
    public boolean passwordsMatch(String pass1, String pass2)
    {
        if (pass1.equals(pass2))
        {
            return true;
        }
        else
        {
            Toast.makeText(this, "Passwords must match.", Toast.LENGTH_LONG).show();
            return false;
        }
    }

}
