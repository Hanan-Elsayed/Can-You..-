package com.example.canyou.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.bumptech.glide.Glide;
import com.example.canyou.PreferenceManager;
import com.example.canyou.R;
import com.example.canyou.pojo.CurrentUser;
import com.example.canyou.pojo.LoginResponse;
import com.google.android.material.navigation.NavigationView;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private PreferenceManager preferenceManager;

    private DrawerLayout drawerLayout;
    LoginResponse loginResponse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get the shared preferences instance
        preferenceManager = new PreferenceManager(this);

        Toolbar toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        drawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open_nav
                , R.string.close_nav);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        if (savedInstanceState == null) {
            changeFragment(new HomeFragment());
            navigationView.setCheckedItem(R.id.nav_home);
        }
        setUserImageAndNameWithClicksHandling();
    }

    private void changeFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, fragment);
        fragmentTransaction.addToBackStack(null); // This enables back navigation
        fragmentTransaction.commit();
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {


        switch (item.getItemId()) {
            case R.id.nav_home:
                changeFragment(new HomeFragment());
                break;
            case R.id.nav_chats:
                changeFragment(new ChatsFragment());
                break;
            case R.id.nav_settings:
                changeFragment(new SettingsFragment());
                break;
            case R.id.nav_requests:
                changeFragment(new RequestsFragment());
                break;
            case R.id.nav_saf_doc:
                changeFragment(new SafetyDocFragment());
                break;
            case R.id.nav_about:
                changeFragment(new AboutFragment());
                break;
            case R.id.nav_logout:
                preferenceManager.clearUserAndToken();
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
                break;
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
        if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
            getSupportFragmentManager().popBackStack();
        } else {
            super.onBackPressed();
        }
    }

    public void setUserImageAndNameWithClicksHandling() {
        CurrentUser currentUser = preferenceManager.getUser();
        if (currentUser != null) {
            NavigationView navigationView = findViewById(R.id.nav_view);
            View navHeaderView = navigationView.getHeaderView(0);
            CircleImageView profileImage = navHeaderView.findViewById(R.id.profile_image);
            TextView userName = navHeaderView.findViewById(R.id.user_name);

            // Set the user's profile image using the avatarUrl
            // use Glide library for image loading
            Glide.with(this)
                    .load(currentUser.getAvatarUrl())
                    .into(profileImage);

            // Set the user's name
            userName.setText(currentUser.getFullName());

            profileImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    changeFragment(new MyProfileFragment());
                    drawerLayout.closeDrawer(GravityCompat.START);

                }
            });
            userName.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    changeFragment(new ProfileFragment());
                    drawerLayout.closeDrawer(GravityCompat.START);
                }
            });
        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_search:
                changeFragment(new SearchFragment());
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


}