package com.money.moneyworld;

import androidx.annotation.NonNull;
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
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.navigation.NavigationView;
import com.google.android.play.core.review.ReviewInfo;
import com.google.android.play.core.review.ReviewManager;
import com.google.android.play.core.review.ReviewManagerFactory;
import com.google.android.play.core.tasks.OnCompleteListener;
import com.google.android.play.core.tasks.Task;
import com.money.moneyworld.Fragment.Contact_Us;
import com.money.moneyworld.Fragment.Home;
import com.money.moneyworld.Fragment.Wallet;
import com.money.moneyworld.utils.AppUtils;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    private DrawerLayout mDrawerLayout;
    private View navHeader;
    TextView name, email, mobile;
    Toolbar toolbar;
    ImageView setpic, UploadPic;
    Context context;
    NavigationView navigationView;
    ReviewManager reviewManager;
    ActionBarDrawerToggle  toggle;

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

        navHeader = navigationView.getHeaderView(0);
        name = (TextView) navHeader.findViewById(R.id.tv_head_nav_user_name);
        email = (TextView) navHeader.findViewById(R.id.tv_head_nav_email);
        mobile = (TextView) navHeader.findViewById(R.id.tv_head_nav_mobile);
        setpic = (ImageView) navHeader.findViewById(R.id.img_profile_pic);
        UploadPic = (ImageView) navHeader.findViewById(R.id.img_camera_picker);

        context = MainActivity.this;

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
                Toast.makeText(this, "Welcome to home fragment", Toast.LENGTH_SHORT).show();
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

                Toast.makeText(this, "Welcome to Result", Toast.LENGTH_SHORT).show();
                break;


            case R.id.nav_contact:

                Fragment fragment2 = new Contact_Us();
                replaceFragment(fragment2,"Contact_us");
                toolbar.setTitle("Contact Us");
                toolbar.setTitleTextColor(getResources().getColor(R.color.white));

              //  Toast.makeText(this, "Welcome to Contact Us", Toast.LENGTH_SHORT).show();
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
                        // SharedPrefManager.getInstance(getApplicationContext()).logout();
                        Toast.makeText(MainActivity.this, "Logout Successfully", Toast.LENGTH_SHORT).show();

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



}