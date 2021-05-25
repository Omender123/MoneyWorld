package com.money.moneyworld.UI.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.money.moneyworld.R;
import com.money.moneyworld.databinding.ActivityAddMoneyBinding;
import com.money.moneyworld.utils.AppUtils;
import com.razorpay.Checkout;
import com.razorpay.PaymentData;
import com.razorpay.PaymentResultListener;
import com.razorpay.PaymentResultWithDataListener;

import org.json.JSONObject;

import de.mateware.snacky.Snacky;

public class Add_Money extends AppCompatActivity implements PaymentResultWithDataListener {
    Checkout checkout;
    ActivityAddMoneyBinding binding;
    private static final String TAG = Add_Money.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_add__money);

        AppUtils.setUpToolbar(this, binding.toolbar, true, true, "Add Money");
        binding.toolbar.setTitleTextColor(getResources().getColor(R.color.white));

        Checkout.preload(getApplicationContext());

        binding.addButtpn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Amount = binding.enterAmount.getText().toString().trim();

                if (Amount.isEmpty()) {
                    Snacky.builder()
                            .setView(v)
                            .setText("Please Enter Amount ")
                            .setTextColor(getResources().getColor(R.color.white))
                            .warning()
                            .show();
                } else {
                    startPayment(Amount);
                }

            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        onSaveInstanceState(new Bundle());
    }

    public void startPayment(String amount) {
       // checkout.setKeyID(getResources().getString(R.string.rozerpay_api_key));
        Checkout checkout = new Checkout();

        checkout.setImage(R.mipmap.rzp);
        final Activity activity = this;

        /**
         * Pass your payment options to the Razorpay Checkout as a JSONObject
         */
        try {
            JSONObject options = new JSONObject();

            options.put("name", "Omender Singh");
            options.put("description", "Reference No. #123456");
            options.put("image", "https://s3.amazonaws.com/rzp-mobile/images/rzp.png");
            //  options.put("order_id", "order_DBJOWzybf0sJbb");//from response of step 3.
            options.put("theme.color", "#3399cc");
            options.put("currency", "INR");
            options.put("amount", amount);//pass amount in currency subunits
            options.put("prefill.email", "omendersindhrajput@gmail.com");
            options.put("prefill.contact", "8766372989");
            checkout.open(activity, options);
        } catch (Exception e) {
            Log.e(TAG, "Error in starting Razorpay Checkout", e);
        }
    }

    @Override
    public void onPaymentSuccess(String s, PaymentData paymentData) {
        String name = paymentData.getUserEmail();

        Toast.makeText(this, ""+name, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPaymentError(int i, String s, PaymentData paymentData) {
        try {
            Toast.makeText(this, "Payment failed: " + i + " " + s, Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Log.e(TAG, "Exception in onPaymentError", e);
        }


    }

   /* @Override
    public void onPaymentSuccess(String s) {
        try {
            Toast.makeText(this, "Payment Successful: " + s, Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Log.e(TAG, "Exception in onPaymentSuccess", e);
        }
    }

    @Override
    public void onPaymentError(int i, String s) {
        try {
            Toast.makeText(this, "Payment failed: " + i + " " + s, Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Log.e(TAG, "Exception in onPaymentError", e);
        }
    }*/
}