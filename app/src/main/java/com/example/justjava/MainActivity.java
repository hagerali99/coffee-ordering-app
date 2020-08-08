package com.example.justjava;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import java.text.NumberFormat;


/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    int numberOfCubs =0;

    public void Increment(View view)
    {
        numberOfCubs++;
        display(numberOfCubs);

    }
      public void Decrement(View view)
    {
        if(numberOfCubs!=0)
            numberOfCubs--;
        display(numberOfCubs);

    }

    /**
     * This method displays the given text on the screen.
     */
    private void displayMessage(String message) {
        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
        priceTextView.setText(message);
    }

    /**
     * This method is called when the order button is clicked.
     */
    int price=5;
    public void submitOrder(View view) {
        String mess=displayOrderSummary();

        Intent intent = new Intent(Intent.ACTION_SENDTO);
            intent.setData(Uri.parse("mailto:"));
            intent.putExtra(Intent.EXTRA_SUBJECT, "Just Java Order For "+name);
            intent.putExtra(Intent.EXTRA_TEXT,mess);

        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
        displayMessage(mess);

    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }
    /**
     * This method displays the given price on the screen.
     */
    private void displayPrice(int number) {
        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
        priceTextView.setText(NumberFormat.getCurrencyInstance().format(number));
    }
    String name="";
    public String displayOrderSummary()
    {
        EditText Name=(EditText) findViewById(R.id.Name_view);

        name=Name.getText().toString();
        String mess="Name: "+ name +"\nAdd Whipped cream?";
        CheckBox cream= (CheckBox) findViewById(R.id.cream_checkbox);

        if(cream.isChecked())
        {
            price+=1;
            mess+="Ture\n";
        }
        else mess+="False\n";

        mess+="Add Chocolate?";

        CheckBox Chocolate= (CheckBox) findViewById(R.id.chocolate_checkbox);
        if(Chocolate.isChecked())
        {   price+=2;
            mess+="Ture\n";
        }
        else mess+="False\n";

        mess+="Quantity: "+numberOfCubs+"\n";
        mess+="Total: $"+numberOfCubs*price+"\n";
        mess+="Thank you!";
        return mess;
    }

}