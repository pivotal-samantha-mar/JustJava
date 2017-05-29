/**
 * IMPORTANT: Add your package below. Package name can be found in the project's AndroidManifest.xml file.
 * This is the package name our example uses:
 *
 * package com.example.android.justjava; 
 *
 */

package com.example.pivotal.justjava;
import android.icu.text.NumberFormat;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
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

            int price = calculatePrice(quantity);
            //String priceMessage = "Total: " + "$" +price + "\nThank you!";
            //displayMessage(priceMessage);
            //displayPrice(price);
            displayMessage(createOrderSummary(price));


    }

    public void increment(View view) {

        quantity++;
        displayQuantity(quantity);
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

    private String createOrderSummary (int price) {

        String priceMessage = "Name: Kaptain Kunal \n" + "Quantity: " + quantity+ "\n" + "Total: $" + price +"\n" +"Thank you!";
        return priceMessage;


    }

    private int calculatePrice (int quantity) {

        return quantity*5;
    }





}