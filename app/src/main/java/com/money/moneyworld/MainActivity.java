package com.money.moneyworld;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.graphics.drawable.DrawerArrowDrawable;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.navigation.NavigationView;
import com.google.android.play.core.review.ReviewInfo;
import com.google.android.play.core.review.ReviewManager;
import com.google.android.play.core.review.ReviewManagerFactory;
import com.google.android.play.core.tasks.OnCompleteListener;
import com.google.android.play.core.tasks.Task;
import com.money.moneyworld.Fragment.Contact_Us;
import com.money.moneyworld.Fragment.Home;
import com.money.moneyworld.Fragment.Result;
import com.money.moneyworld.Fragment.TransactionHistory;
import com.money.moneyworld.Fragment.Wallet;
import com.money.moneyworld.Model.ResponseModel.LoginResponse;
import com.money.moneyworld.Model.ResponseModel.UploadProfileResponse;
import com.money.moneyworld.SharedPerfence.MyPreferences;
import com.money.moneyworld.SharedPerfence.PrefConf;
import com.money.moneyworld.SharedPrefernce.SharedPrefManager;
import com.money.moneyworld.UI.Activity.Update_Profile;
import com.money.moneyworld.authentication.Change_Password;
import com.money.moneyworld.authentication.Successfully_Screen;
import com.money.moneyworld.utils.AppUtils;
import com.money.moneyworld.utils.ImagePath;
import com.money.moneyworld.view_presenter.LogOutPresenter;
import com.money.moneyworld.view_presenter.UpdatePasswordPresenter;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import de.mateware.snacky.Snacky;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, LogOutPresenter.LogOutPresenterView, View.OnClickListener {
    private DrawerLayout mDrawerLayout;
    private View navHeader;
    TextView name, email, mobile;
    Toolbar toolbar;
    ImageView setpic, UploadPic,edit_profile;
    Context context;
    NavigationView navigationView;
    ReviewManager reviewManager;
    ActionBarDrawerToggle  toggle;
    LoginResponse.Logindetails loginResponse;
    public  boolean permissionStatus;
    private int PICK_PHOTO_FOR_AVATAR = 1;
    private Dialog dialog;
    private LogOutPresenter presenter;
    private View view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nav_drawer_layout);
        changeStatusBarColor();
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("Home");
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));

        mDrawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigation_view);
        AppUtils.checkAndRequestPermissions(this);
        navHeader = navigationView.getHeaderView(0);
        name = (TextView) navHeader.findViewById(R.id.tv_head_nav_user_name);
        email = (TextView) navHeader.findViewById(R.id.tv_head_nav_email);
        mobile = (TextView) navHeader.findViewById(R.id.tv_head_nav_mobile);
        setpic = (ImageView) navHeader.findViewById(R.id.img_profile_pic);
        UploadPic = (ImageView) navHeader.findViewById(R.id.img_camera_picker);
        edit_profile  = (ImageView) navHeader.findViewById(R.id.edit_profile);
        loginResponse = SharedPrefManager.getInstance(MainActivity.this).getUser();

        name.setText(loginResponse.getName());
        email.setText(loginResponse.getEmail());
        mobile.setText(loginResponse.getMobile());



        context = MainActivity.this;
        String profileImage = MyPreferences.getInstance(context).getString(PrefConf.PROFILEPIC, null);
        if (profileImage==null){
            Glide.with(context).load(profileImage).apply(new RequestOptions().circleCrop()).placeholder(R.drawable.ic_profile_pic).into(setpic);

        }else{
            Glide.with(context).load(profileImage).apply(new RequestOptions().circleCrop()).placeholder(R.drawable.ic_profile_pic).into(setpic);

        }
         toggle = new ActionBarDrawerToggle(
                this, mDrawerLayout, toolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);

        mDrawerLayout.addDrawerListener(toggle);
        // toggle.getDrawerArrowDrawable().setColor(getResources().getColor(R.color.black));
        toggle.syncState();


        navigationView.setNavigationItemSelectedListener(this);
        if (null == savedInstanceState) {
            getSupportFragmentManager().beginTransaction()
                    .addToBackStack("Home")
                    .replace(R.id.fragment_Container, new Home(), "Home")
                    .commit();
            navigationView.setCheckedItem(R.id.nav_home);
        }

        presenter = new LogOutPresenter(this);
        dialog = AppUtils.hideShowProgress(context);


        UploadPic.setOnClickListener(this);


        edit_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Update_Profile.class));
            }
        });

    }

    @Override
    public void onBackPressed() {
        int fragmentsInStack = getSupportFragmentManager().getBackStackEntryCount();

        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
            navigationView.setCheckedItem(R.id.nav_home);
        }else if (fragmentsInStack > 1) { // If we have more than one fragment, pop back stack
            getSupportFragmentManager().popBackStack();
            toolbar.setTitle("Home");
           toolbar.setTitleTextColor(getResources().getColor(R.color.white));
           navigationView.setCheckedItem(R.id.nav_home);
                 } else if (fragmentsInStack == 1) { // Finish activity, if only one fragment left, to prevent leaving empty screen
            finish();
        } else {
            super.onBackPressed();
        }
    }

    private void changeStatusBarColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_home:
                Fragment fragment = new Home();
                replaceFragment(fragment,"Home");
                toolbar.setTitle("Home");
                toolbar.setTitleTextColor(getResources().getColor(R.color.white));
                 break;

            case R.id.nav_wallet:
                toolbar.setTitle("Wallet");
                toolbar.setTitleTextColor(getResources().getColor(R.color.white));
                Fragment fragment1 = new Wallet();
                replaceFragment(fragment1,"Wallet");


                break;
                case R.id.nav_Result:
                toolbar.setTitle("Result");
                toolbar.setTitleTextColor(getResources().getColor(R.color.white));
                    Fragment fragment4 = new Result();
                    replaceFragment(fragment4,"Result");
                break;

            case R.id.nav_TransactionHistory:
                toolbar.setTitle("Transaction History");
                toolbar.setTitleTextColor(getResources().getColor(R.color.white));
                Fragment fragment3 = new TransactionHistory();
                replaceFragment(fragment3,"Transaction History");


                break;



            case R.id.nav_contact:

                Fragment fragment2 = new Contact_Us();
                replaceFragment(fragment2,"Contact_us");
                toolbar.setTitle("Contact Us");
                toolbar.setTitleTextColor(getResources().getColor(R.color.white));

                break;


            case R.id.nav_Share_us:
                AppUtils.shareApp(context);

                break;
            case R.id.nav_Feedback:
                init();
                break;
            case R.id.nav_Logout:
                AlertDialogBox();
                break;
        }
        mDrawerLayout.closeDrawer(GravityCompat.START);
        return true;

    }

    public void AlertDialogBox() {


        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);


        alertDialogBuilder.setTitle("Money World");


        alertDialogBuilder.setIcon(R.mipmap.ic_launcher_round);
        alertDialogBuilder
                .setMessage("Are you sure to Logout !!!!!")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                       presenter.LogOutUser(loginResponse.getUserId(),"0");
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Toast.makeText(MainActivity.this, "Logout Failed", Toast.LENGTH_SHORT).show();
                        dialog.cancel();

                    }
                });

               AlertDialog alertDialog = alertDialogBuilder.create();

        alertDialog.show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        navigationView.setCheckedItem(R.id.nav_home);
    }

    private void init() {
        reviewManager = ReviewManagerFactory.create(MainActivity.this);
        showRateApp();
    }
    public void showRateApp() {
        Task<ReviewInfo> request = reviewManager.requestReviewFlow();
        request.addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                // We can get the ReviewInfo object
                ReviewInfo reviewInfo = task.getResult();

                Task<Void> flow = reviewManager.launchReviewFlow(this, reviewInfo);
                flow.addOnCompleteListener(task1 -> {
                    // The flow has finished. The API does not indicate whether the user
                    // reviewed or not, or even whether the review dialog was shown. Thus, no
                    // matter the result, we continue our app flow.
                });
            } else {
                // There was some problem, continue regardless of the result.
                // show native rate app dialog on error
                showRateAppFallbackDialog();
            }
        });

    }

    private void showRateAppFallbackDialog() {
        new MaterialAlertDialogBuilder(this)
                .setTitle(R.string.rate_app_title)
                .setMessage(R.string.rate_app_message)
                .setPositiveButton(R.string.rate_btn_pos, (dialog, which) -> redirectToPlayStore())
                .setNegativeButton(R.string.rate_btn_neg,
                        (dialog, which) -> {
                            // take action when pressed not now
                        })
                .setNeutralButton(R.string.rate_btn_nut,
                        (dialog, which) -> {
                            // take action when pressed remind me later
                        })
                .setOnDismissListener(dialog -> {
                })
                .show();
    }

    // redirecting user to PlayStore
    public void redirectToPlayStore() {
        final String appPackageName = getPackageName();
        try {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName)));
        } catch (ActivityNotFoundException exception) {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName)));
        }
    }

    public void replaceFragment(Fragment fragment, String tag) {
        //Get current fragment placed in container
        Fragment currentFragment = getSupportFragmentManager().findFragmentById(R.id.fragment_Container);

        //Prevent adding same fragment on top
        if (currentFragment.getClass() == fragment.getClass()) {
            return;
        }

        //If fragment is already on stack, we can pop back stack to prevent stack infinite growth
        if (getSupportFragmentManager().findFragmentByTag(tag) != null) {
            getSupportFragmentManager().popBackStack(tag, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        }

        //Otherwise, just replace fragment
        getSupportFragmentManager()
                .beginTransaction()
                .addToBackStack(tag)
                .replace(R.id.fragment_Container, fragment, tag)
                .commit();
    }


    @Override
    public void showHideProgress(boolean isShow) {
        if (isShow){
            dialog.show();
        }else {
            dialog.dismiss();
        }
    }

    @Override
    public void onError(String message) {

        Snacky.builder()
                .setActivity(MainActivity.this)
                .setText(message)
                .setTextColor(getResources().getColor(R.color.white))
                .error()
                .show();
    }

    @Override
    public void onSuccess(String message) {

        if (message.equalsIgnoreCase("ok")){
            SharedPrefManager.getInstance(getApplicationContext()).logout();
            Toast.makeText(MainActivity.this, "Logout Successfully", Toast.LENGTH_SHORT).show();

        }
    }

    @Override
    public void onProfileUploadSuccess(UploadProfileResponse uploadProfileResponse, String message) {
        if (message.equalsIgnoreCase("ok")){
            MyPreferences.getInstance(context).putString(PrefConf.PROFILEPIC,uploadProfileResponse.getProfile());

            String image = uploadProfileResponse.getProfile();

             Glide.with(context).load(image).apply(new RequestOptions().circleCrop()).placeholder(R.drawable.ic_profile_pic).into(setpic);

            Toast.makeText(context, ""+image, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onFailure(Throwable t) {
        Snacky.builder()
                .setActivity(MainActivity.this)
                .setText(t.getLocalizedMessage())
                .setTextColor(getResources().getColor(R.color.white))
                .error()
                .show();



    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.img_camera_picker:
                galleryPicker();
                break;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {

            case AppUtils.PERMISSION_REQUEST_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    permissionStatus = true;

                } else {
                    permissionStatus = false;
                    String msg = "Please Allow Permission to Set Profile Image.";
                    customAlert(msg);

                }
                return;
        }
    }

    private void customAlert(String msg) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
        alertDialog.setMessage(msg);
        alertDialog.setCancelable(false);
        alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
            }
        }).show();
    }

    private void galleryPicker() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        startActivityForResult(intent, PICK_PHOTO_FOR_AVATAR);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_PHOTO_FOR_AVATAR && resultCode == RESULT_OK) {
            if (data == null)
                return;
            Uri uri = data.getData();
            System.out.println("urii  "+uri +" "+uri.getPath());
            String path  = ImagePath.getPath(context,uri);
            System.out.println("urii path "+path );
            if(path!=null && !path.equals("")) {
                File file = new File(path);
                Upload( file,loginResponse.getUserId());
                //uploadImage(file);
            }

        }

      /*  if (data != null && requestCode == PICK_PHOTO_FOR_AVATAR) {


            if (resultCode == RESULT_OK) {
                Uri targetUri = data.getData();
                Bitmap bitmap;
                try {
                    bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(targetUri));
                    Bitmap resizedBitmap = Bitmap.createScaledBitmap(bitmap, 500, 500, false);
                   String image = ConvertBitmapToString(resizedBitmap);
                     Upload(image,loginResponse.getUserId());

                } catch (FileNotFoundException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }*/
    }

    private void Upload(File file, String userId) {
        RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        MultipartBody.Part image =
                MultipartBody.Part.createFormData("profile", file.getName(), requestFile);
        RequestBody userid1 =
                RequestBody.create(
                        MediaType.parse("multipart/form-data"), userId);
       presenter.UploadProfile(userid1,image);
    }

    public static String ConvertBitmapToString(Bitmap bitmap){
        String encodedImage = "";

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);

        try {
            encodedImage= URLEncoder.encode(Base64.encodeToString(byteArrayOutputStream.toByteArray(), Base64.DEFAULT), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return encodedImage;
    }


}