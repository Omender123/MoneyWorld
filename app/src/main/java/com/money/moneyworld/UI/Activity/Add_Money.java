package com.money.moneyworld.UI.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.money.moneyworld.MainActivity;
import com.money.moneyworld.Model.ResponseModel.CreateOderIdResponse;
import com.money.moneyworld.Model.ResponseModel.LoginResponse;
import com.money.moneyworld.Model.request.CreateOrderIdBody;
import com.money.moneyworld.Model.request.SaveBody;
import com.money.moneyworld.R;
import com.money.moneyworld.SharedPrefernce.SharedPrefManager;
import com.money.moneyworld.databinding.ActivityAddMoneyBinding;
import com.money.moneyworld.utils.AppUtils;
import com.money.moneyworld.view_presenter.PartialPaymentPresenter;
import com.razorpay.Checkout;
import com.razorpay.PaymentData;
import com.razorpay.PaymentResultListener;
import com.razorpay.PaymentResultWithDataListener;

import org.json.JSONObject;

import de.mateware.snacky.Snacky;

public class Add_Money extends AppCompatActivity implements PaymentResultWithDataListener, PartialPaymentPresenter.PartialPaymentView {

    ActivityAddMoneyBinding binding;
    private static final String TAG = Add_Money.class.getSimpleName();
    private Context context;
    private View view;
    private Dialog dialog;
    private PartialPaymentPresenter presenter;
    LoginResponse.Logindetails loginResponse;
    String Amount,status,orderID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_add__money);

        AppUtils.setUpToolbar(this, binding.toolbar, true, true, "Add Money");
        binding.toolbar.setTitleTextColor(getResources().getColor(R.color.white));
        loginResponse = SharedPrefManager.getInstance(Add_Money.this).getUser();

        context = Add_Money.this;
        view = binding.getRoot();
        dialog = AppUtils.hideShowProgress(context);
        presenter = new PartialPaymentPresenter(this);

        Checkout.preload(getApplicationContext());

        binding.addButtpn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 Amount = binding.enterAmount.getText().toString().trim();

                if (Amount.isEmpty()) {
                    Snacky.builder()
                            .setView(v)
                            .setText("Please Enter Amount ")
                            .setTextColor(getResources().getColor(R.color.white))
                            .warning()
                            .show();
                } else {
                    CreateOrderIdBody createOrderIdBody = new CreateOrderIdBody(loginResponse.getUserId(),Amount);
                  presenter.CreateOderID(createOrderIdBody);
                }

            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        onSaveInstanceState(new Bundle());
    }

    public void startPayment(String amount,String OrderId) {
       // checkout.setKeyID(getResources().getString(R.string.rozerpay_api_key));
        Checkout checkout = new Checkout();

        checkout.setImage(R.mipmap.rzp);
        final Activity activity = this;

        /**
         * Pass your payment options to the Razorpay Checkout as a JSONObject
         */
        try {
            JSONObject options = new JSONObject();
            options.put("order_id", OrderId);
            options.put("name", loginResponse.getName());
            options.put("description", "Reference No. #123456");
            options.put("image", "https://s3.amazonaws.com/rzp-mobile/images/rzp.png");
            //  options.put("order_id", "order_DBJOWzybf0sJbb");//from response of step 3.
            options.put("theme.color", getResources().getColor(R.color.status_color));
            options.put("currency", "INR");
            options.put("amount", amount);//pass amount in currency subunits
            options.put("prefill.email", loginResponse.getEmail());
            options.put("prefill.contact", loginResponse.getMobile());
            checkout.open(activity, options);
        } catch (Exception e) {
            Log.e(TAG, "Error in starting Razorpay Checkout", e);
        }
    }

    @Override
    public void onPaymentSuccess(String s, PaymentData paymentData) {
         orderID = paymentData.getOrderId();
        String Payment_id = paymentData.getPaymentId();
        status ="Success";

        if (paymentData!=null){

            SaveBody saveBody = new SaveBody(Amount,orderID,status,Payment_id);
            presenter.savePaymentData(saveBody,context);
        }
    }

    @Override
    public void onPaymentError(int i, String s, PaymentData paymentData) {
         orderID = paymentData.getOrderId();
         String Payment_id = paymentData.getPaymentId();
         status =s;
        if (paymentData!=null){
            SaveBody saveBody = new SaveBody(Amount,orderID,status,Payment_id);
            presenter.savePaymentData(saveBody,context);
        }else{
            SaveBody saveBody = new SaveBody(Amount,orderID,status,Payment_id);
            presenter.savePaymentData(saveBody,context);
        }
    }

    @Override
    public void showHideProgress(boolean isShow) {
        if (isShow){
            dialog.show();
        }else{
            dialog.dismiss();
        }
    }

    @Override
    public void onError(String message) {
        Snackbar.make(view,message,Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void onCreateOrderID(CreateOderIdResponse createOderIdResponse) {
        String  orderId =  createOderIdResponse.getOrderId();
       int amount = Integer.parseInt(Amount);
       int totalAmount = amount*100;
       String real_amount = String.valueOf(totalAmount);

       startPayment(real_amount,orderId);

    }

    @Override
    public void onRazorDataSaved(String message) {
        AppUtils.showMessageOKCancel("Payment "+status+"\n"  + "\nOrder No :" + orderID,this, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent = new Intent(context, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
            }
        },false);
    }

    @Override
    public void onFailure(Throwable t) {
        Snackbar.make(view,t.getMessage(),Snackbar.LENGTH_SHORT).show();
    }


}