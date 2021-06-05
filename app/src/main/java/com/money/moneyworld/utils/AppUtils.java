package com.money.moneyworld.utils;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.location.Address;
import android.location.Geocoder;
import android.net.ConnectivityManager;
import android.os.Build;
import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.badge.BadgeDrawable;
import com.google.android.play.core.review.ReviewInfo;
import com.google.android.play.core.review.ReviewManager;
import com.google.android.play.core.tasks.OnCompleteListener;
import com.google.android.play.core.tasks.Task;
import com.money.moneyworld.R;
import com.money.moneyworld.api.ApiManager;
import com.money.moneyworld.api.ApiService;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.TimeZone;


public class AppUtils {

    public static final int PERMISSION_REQUEST_CODE = 200;
    public static ReviewManager reviewManager;
    public static  ReviewInfo reviewInfo = null;


    public static ApiService getApi(Context context) {
        return ApiManager.getRetrofit(context).create(ApiService.class);
    }

    public static AlertDialog showDialogMessage(Context context, String title, String message) {
        AlertDialog alertDialog = new AlertDialog.Builder(context).setTitle(title).setMessage(message).show();
        if (alertDialog.isShowing()) {
            alertDialog.cancel();
        }
        return alertDialog;
    }


    public static Dialog hideShowProgress(Context context) {
        Dialog dialog = new Dialog(context);
        Objects.requireNonNull(dialog.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        LayoutInflater inflater = LayoutInflater.from(context);
        View dialogView = inflater.inflate(R.layout.progress_dialog, null);
        dialog.setContentView(dialogView);
        dialog.setCancelable(false);

        return dialog;
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public static void setStatusBarGradient(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            activity.requestWindowFeature (Window.FEATURE_NO_TITLE);
            Window window = activity.getWindow();
            window.setFlags (WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.
                    LayoutParams.FLAG_FULLSCREEN);
            window.setNavigationBarColor(activity.getResources().getColor(R.color.status_color));
        }
    }


    public  static  void setUpToolbar(AppCompatActivity activity, Toolbar toolbar, boolean isTitleEnable, boolean isBackEnable, String title){
           activity.setSupportActionBar(toolbar);

           Objects.requireNonNull(activity.getSupportActionBar()).setDisplayShowTitleEnabled(isTitleEnable);
            activity.getSupportActionBar().setTitle(title);

            activity.getSupportActionBar().setDisplayHomeAsUpEnabled(isBackEnable);
             activity.getSupportActionBar().setDisplayShowHomeEnabled(isBackEnable);

             toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View v) {
                     activity.onBackPressed();
                 }
             });
    }




    public static void showMessageOKCancel(String message, Activity activity, DialogInterface.OnClickListener okListener,Boolean aBoolean) {
        new android.app.AlertDialog.Builder(activity)
                .setMessage(message)
                .setCancelable(aBoolean)
                .setPositiveButton("OKAY", okListener)
                .create()
                .show();

    }


    public static String getTime(String dateTime){
       String times ="";
        try {
            DateFormat f = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
            Date d = f.parse(dateTime);

            DateFormat date = new SimpleDateFormat("MM/dd/yyyy");
            DateFormat time = new SimpleDateFormat("hh:mm:ss a");
            System.out.println("Date: " + date.format(d));
            System.out.println("Time: " + time.format(d));//09:00:00 am

            String[]word= time.format(d).split(":");
            times=times+word[0]+":"+word[1]+" "+word[2].split(" ")[1];

        } catch (ParseException e) {
            e.printStackTrace();
        }
    return times;
    }

    public static String getDate(String dateTime){
        String dateStr="";
        try {
            DateFormat f = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
            Date d = f.parse(dateTime);

            DateFormat date = new SimpleDateFormat("EEE, d MMM yyyy");
//            System.out.println("Date: " + date.format(d));
            dateStr = date.format(d);


        } catch (ParseException e) {
            e.printStackTrace();
        }
        return dateStr;
    }

public static String getDateTime(String timeStamp){
        String dateTime ="";
        try{
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
            Date date = sdf.parse(timeStamp);

            SimpleDateFormat sdf2 = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
            sdf2.setTimeZone(TimeZone.getTimeZone("IST"));
            dateTime = sdf2.format(date);
            System.out.println("ssss"+dateTime);
            return dateTime;
        }catch (ParseException e){
            e.printStackTrace();
        }
    System.out.println("ssss"+dateTime);

    return dateTime;
}
//    public static  boolean isNetworkConnected(Context context) {
//        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
//
//        return cm.getActiveNetworkInfo() != null;
//    }

    public static void avoidDoubleClick(long mLastClickTime){
        // Preventing multiple clicks, using threshold of 1 second
        if (SystemClock.elapsedRealtime() - mLastClickTime < 1500) {
            return;
        }
        mLastClickTime = SystemClock.elapsedRealtime();
    }
    static AlertDialog alertDialog;

   /* public static void alertMessage(final AppCompatActivity context, String msg) {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(context,R.style.CustomDialog);
        LayoutInflater inflater =context.getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.login_dialog_layout, null);
        TextView textView = dialogView.findViewById(R.id.txt_alert_msg);
        textView.setText(msg);
        dialogBuilder.setView(dialogView);



        Button okay = dialogView.findViewById(R.id.okay_btn);

        okay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
                logout(context);

            }
        });


        alertDialog = dialogBuilder.create();
        alertDialog.setCancelable(false);
//        alertDialog.getWindow().setGravity(Gravity.BOTTOM);
        alertDialog.show();

    }
*/
   /* private static void logout(AppCompatActivity context) {
        MyPreferences.getInstance(context).clearPreferences();
        Intent intent = new Intent(context, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
        context.finish();
    }*/

  /*  public static void alert(final Context context) {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(context,R.style.CustomDialog);
        LayoutInflater inflater =((AppCompatActivity)context).getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.book_ticket_layout, null);

        dialogBuilder.setView(dialogView);

        TextView save = dialogView.findViewById(R.id.tv_book_more_btn);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
                Intent intent = new Intent(context, ConfirmActivity.class);
//                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK |Intent.FLAG_ACTIVITY_CLEAR_TASK);
                context.startActivity(intent);

            }
        });
//        cancel.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                alertDialog.dismiss();
//            }
//        });

        alertDialog = dialogBuilder.create();

        alertDialog.show();

    }
*/
   /* public static void exitDialog(final AppCompatActivity context) {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(context);
        LayoutInflater inflater =context.getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.custom_exit_dialog, null);
        dialogBuilder.setView(dialogView);

        Button cancel =  dialogView.findViewById(R.id.cancel_button);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    alertDialog.dismiss();
                } catch (Exception e) {
                }
            }
        });
        Button rateButton =  dialogView.findViewById(R.id.rate_button);
        rateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                rate(context);
            }
        });
        Button yesButton =  dialogView.findViewById(R.id.exit_button);
        yesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
                context.finish();
            }
        });

        alertDialog = dialogBuilder.create();
        alertDialog.show();


    }*/


    public static void shareApp(Context context) {

        try {
            Intent i = new Intent(Intent.ACTION_SEND);
            i.setType("text/plain");
            String sAux = "Hey,\n Its amazing install Money World form "+ context.getResources().getString(R.string.app_name) + "\n";

            sAux = sAux + "https://play.google.com/store/apps/details?id=" + context.getPackageName() + "\n";
            i.putExtra(Intent.EXTRA_TEXT, sAux);
            context.startActivity(Intent.createChooser(i, "choose one"));
        } catch (Exception e) {
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    public static boolean checkAndRequestPermissions(Activity context) {
        if (context != null) {

            int storagePermission = ContextCompat.checkSelfPermission(context,
                    Manifest.permission.READ_EXTERNAL_STORAGE);
            int storageWritePermission = ContextCompat.checkSelfPermission(context,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE);

            List<String> listPermissionsNeeded = new ArrayList<>();


            if (storagePermission != PackageManager.PERMISSION_GRANTED) {
                listPermissionsNeeded.add(Manifest.permission.READ_EXTERNAL_STORAGE);
            }

            if (storageWritePermission != PackageManager.PERMISSION_GRANTED) {
                listPermissionsNeeded.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
            }

            if (!listPermissionsNeeded.isEmpty()) {
                ActivityCompat.requestPermissions(context,
                        listPermissionsNeeded.toArray(new String[listPermissionsNeeded.size()]), PERMISSION_REQUEST_CODE);
                return false;
            }
        } else {
            return false;
        }

        return true;
    }





}


