package com.manuni.navigationdrawerone;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.google.android.material.navigation.NavigationView;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private ActionBarDrawerToggle toggle;
    private View header;
    private ImageView myImage;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES){
            try {
                setTheme(R.style.Theme_Dark);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else {
            try {
                setTheme(R.style.Theme_Light);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbarId);

        drawerLayout = findViewById(R.id.drawerLayoutId);
        navigationView = findViewById(R.id.navigationView);
        //getSupportActionBar().setTitle("Drawer Layout");
        //getSupportActionBar().setBackgroundDrawable(new ColorDrawable(0));
       // navigationView.bringToFront();
        //navigationView.setItemBackground(getDrawable(R.drawable.switch_track_on));

        //toggle = new ActionBarDrawerToggle(this,drawerLayout,R.string.drawer_open,R.string.drawer_close);
        toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.drawer_open,R.string.drawer_close);

        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

       // getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        navigationView.setNavigationItemSelectedListener(this);

       /* View view = LayoutInflater.from(MainActivity.this).inflate(R.layout.header_file,(ConstraintLayout)findViewById(R.id.header_file_my));
        view.findViewById(R.id.myPic).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "This is my pic", Toast.LENGTH_SHORT).show();
            }
        });*/

        header = navigationView.getHeaderView(0);
        myImage = header.findViewById(R.id.myPic);
        myImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               openGallery();
            }
        });

        ToggleButton myToggle = header.findViewById(R.id.themeChange);
        myToggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (myToggle.isChecked()){
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                }else {

                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                }
            }
        });
        ToggleButton arrowToggle = header.findViewById(R.id.arrowToggle);
        arrowToggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (arrowToggle.isChecked()){
                    header.findViewById(R.id.showOrNot).setVisibility(View.GONE);
                }else {
                    header.findViewById(R.id.showOrNot).setVisibility(View.VISIBLE);
                }
            }
        });


      /* Switch aSwitch = header.findViewById(R.id.theme);
       aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
           @Override
           public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
               if (b){
                   AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
               }else {
                   AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
               }

           }
       });*/


       /* CircleImageView imageView = (CircleImageView) findViewById(R.id.myPic);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "This is me" , Toast.LENGTH_SHORT).show();
            }
        });*/

    }
    private void openGallery(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent,"Image"),2);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 2){
            Uri uri = data.getData();
            myImage.setImageURI(uri);
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (toggle.onOptionsItemSelected(item)){
            return true;
        }
        return true;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.home_nav:
                homeBackground();
                //Toast.makeText(this, "Home is clicked", Toast.LENGTH_SHORT).show();
                drawerGone();
                break;
            case R.id.refresh_nav:
                refreshBackground();
               /* Toast toast1 = Toast.makeText(this,"This is refresh",Toast.LENGTH_SHORT);
                toast1.setGravity(Gravity.CENTER,0,0);
                toast1.show();*/
                drawerGone();
                break;
            case R.id.replay:
                shareBackground();
             /* Toast toast2 =  Toast.makeText(this, "This is Share", Toast.LENGTH_SHORT);
              toast2.setGravity(Gravity.TOP,0,0);
              toast2.show();*/
              drawerGone();
              break;
            case R.id.rate:
                Toast toast3 = Toast.makeText(this,"This is Rate",Toast.LENGTH_LONG);
                toast3.setGravity(Gravity.START,0,0);
                toast3.show();
                drawerGone();
                break;
            case R.id.Login_nav:
             Toast toast4 =   Toast.makeText(this, "This is Login", Toast.LENGTH_SHORT);
             toast4.setGravity(Gravity.BOTTOM,0,0);
             toast4.show();
             drawerGone();
             break;
        }
        return true;
    }
    private void drawerGone(){
        if (drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }
    }


    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }else {
            super.onBackPressed();
        }

    }
    private void homeBackground(){
        View view = getLayoutInflater().inflate(R.layout.custom_toast_one,(ViewGroup) findViewById(R.id.toastContainer));


        Toast homeToast = new Toast(this);
        homeToast.setView(view);
        homeToast.setDuration(Toast.LENGTH_LONG);
        homeToast.setGravity(Gravity.CENTER,0,0);
        homeToast.show();
    }
    private void refreshBackground(){
        View view = getLayoutInflater().inflate(R.layout.toast_for_all,(ViewGroup) findViewById(R.id.toastForAll));

        ((TextView)view.findViewById(R.id.textId)).setText("Refreshing...");
        ((ImageView)view.findViewById(R.id.imaViewId)).setImageResource(R.drawable.ic_refresh);
        Animation animation = AnimationUtils.loadAnimation(MainActivity.this,R.anim.rotate_animation);
        view.findViewById(R.id.imaViewId).startAnimation(animation);

        Toast refreshToast = new Toast(this);
        refreshToast.setView(view);
        refreshToast.setGravity(Gravity.END,0,0);
        refreshToast.setDuration(Toast.LENGTH_LONG);
        refreshToast.show();
    }
    private void shareBackground(){
        View view = getLayoutInflater().inflate(R.layout.share_toast_layout,(ViewGroup) findViewById(R.id.shareContainer));
        Animation shareAnimation = AnimationUtils.loadAnimation(MainActivity.this,R.anim.share_translate_animation);
        view.findViewById(R.id.shareImage).startAnimation(shareAnimation);

        Toast shareToast = new Toast(MainActivity.this);
        shareToast.setView(view);
        shareToast.setGravity(Gravity.TOP,0,0);
        shareToast.setDuration(Toast.LENGTH_LONG);
        shareToast.show();

    }
}