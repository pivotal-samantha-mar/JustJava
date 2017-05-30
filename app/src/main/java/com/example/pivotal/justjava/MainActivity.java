/**
 * IMPORTANT: Add your package below. Package name can be found in the project's AndroidManifest.xml file.
 * This is the package name our example uses:
 *
 * package com.example.android.justjava; 
 *
 */

package com.example.pivotal.justjava;
import android.content.Intent;
import android.icu.text.NumberFormat;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    int quantity =0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        CheckBox whippedCreamCheckBox = (CheckBox) findViewById(R.id.whippedCream);
        boolean hasWhippedCream = whippedCreamCheckBox.isChecked();

        CheckBox choclateCheckBox = (CheckBox) findViewById(R.id.chocolate);
        boolean hasChocolate = choclateCheckBox.isChecked();

        EditText name = (EditText) findViewById(R.id.yourName);
        String customerName = name.getText().toString();


        int price = calculatePrice(quantity,hasWhippedCream,hasChocolate);
        String priceMessage = createOrderSummary(price, hasWhippedCream, hasChocolate,customerName);

        Intent email = new Intent(Intent.ACTION_SENDTO);
        email.setData(Uri.parse("mailto: "));
        email.putExtra(Intent.EXTRA_SUBJECT ,"Just Java order for " + customerName);
        email.putExtra(Intent.EXTRA_TEXT,priceMessage);
        if (email.resolveActivity(getPackageManager())!=null) {
            startActivity(email);
        }
        displayMessage(priceMessage);



    }

    public void increment(View view) {
        if (quantity <100) {
        quantity++;
        displayQuantity(quantity);
        }
    }

    public void decrement(View view) {

        if (quantity >0) {
            quantity--;
            displayQuantity(quantity);
        }
    }

   /* public void clear(View view) {

        quantity=0;
        display(quantity);
        displayPrice(0);
    } */

    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int amount) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + amount);
    }

    /**
     * This method displays the given text on the screen.
     */
    private void displayMessage(String message) {
        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(message);
    }

    private String createOrderSummary (int price, boolean addWhippedCream, boolean addChocolate, String name) {

        String priceMessage = "Name: " + name;
        priceMessage += "\nAdd whipped cream? "+addWhippedCream;
        priceMessage += "\nAdd chocolate? "+addChocolate;
        priceMessage += "\nQuantity: " + quantity;
        priceMessage += "\nTotal: $ "+price;
        priceMessage += "\nThank you!";

        return priceMessage;


    }

    private int calculatePrice (int quantity, boolean whippedCream, boolean chocolate) {

        int basePrice = 5;

        if (whippedCream) {
            basePrice += 1;
        }

        if (chocolate) {

            basePrice += 2;
        }



        return quantity*basePrice;
    }





}