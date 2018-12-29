package com.example.android.justjava;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;
import java.util.Locale;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    boolean hasWhiteCream , hasChocolate;
    CheckBox WhippedCream, Chocolate;
    EditText Name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        WhippedCream = (CheckBox) findViewById(R.id.Whipped_cream);
        Chocolate = (CheckBox) findViewById(R.id.Chocolate);
        Name=(EditText) findViewById(R.id.Name);
    }

    /**
     * This method is called when the order button is clicked.
     */
    int quantity = 0;

    public void increment (View view)
    {
        if(quantity == 100)
        {
            Toast.makeText(this,"You can't increment more than 100 cup ",Toast.LENGTH_LONG).show();
        }else{
            quantity++;
        }

       displayQuantity(quantity);
    }

    public void decrement (View view)
    {
        if(quantity == 1)
        {
            Toast.makeText(this,"You can't be less than 100 cup ",Toast.LENGTH_LONG).show();
        }else{
            quantity--;
        }
        displayQuantity(quantity);
    }

    public void submitOrder(View view ) {
        hasWhiteCream=WhippedCream.isChecked();
        hasChocolate=Chocolate.isChecked();
        String name = Name.getText().toString();
        String Subject = "Just Java order for " + name;
        String message = createOrderSummary(name);
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_SUBJECT, Subject);
        intent.putExtra(Intent.EXTRA_TEXT,message);
        if (intent.resolveActivity(getPackageManager())!=null)
        {
            startActivity(intent);
        }
        //displayMessage(message);

    }
    /**
     * Calculates the price of the order.
     *
     *
     */
    private int calculatePrice() {
        int q = quantity*5;
        if (hasWhiteCream)
        {
            q = q + quantity * 1;
        }
        if (hasChocolate)
        {
            q = q + quantity * 2;
        }
        return q ;
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int number1) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number1);
    }


    /**
     * This method displays the given text on the screen.
     */
    private void displayMessage(String message) {
        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(message);
    }
    public String createOrderSummary(String name)
    {

        int price = calculatePrice();

        String mesaage = "Name:"+ name
                + "\n Add Whiped Cream? "+ hasWhiteCream
                +  "\nAdd Chocolate? "+ hasChocolate
                +"\nQuantity: "+quantity + "\ntotal= $" + price
                + "\nThank You";
        return mesaage;
    }

}