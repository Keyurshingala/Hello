 <androidx.drawerlayout.widget.DrawerLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:id="@+id/drawer_layout"
    tools:context=".MainActivity">

    <include
        android:id="@+id/app_bar"
        layout="@layout/app_bar_layout"/>

    <com.google.android.material.navigation.NavigationView
        android:id="@+id/navigation_view"
        android:layout_width="wrap_content"
        android:layout_height="match_parent"
        app:menu="@menu/nav_menu"
        android:layout_gravity="start"
        app:headerLayout="@layout/header_layout"/>
</androidx.drawerlayout.widget.DrawerLayout>

********************************************************Menu****************************************************************************************
<menu xmlns:android="http://schemas.android.com/apk/res/android">

    <group android:checkableBehavior="single">

        <item
            android:id="@+id/action_home"
            android:icon="@drawable/ic_action_home"
            android:title="Home"/>

        <item
            android:id="@+id/action_search"
            android:icon="@drawable/ic_action_search"
            android:title="Search"/>

        <item
            android:id="@+id/action_notification"
            android:icon="@drawable/ic_action_notification"
            android:title="Notification"/>

        <item
            android:id="@+id/action_profile"
            android:icon="@drawable/ic_action_profile"
            android:title="Profile"/>


    </group>

    <item android:title="Other">
        <menu>
            <item
                android:id="@+id/nav_about_us"
                android:title="About us" />
            <item
                android:id="@+id/nav_privacy_policy"
                android:title="Privacy Policy" />
        </menu>
    </item>

</menu>


*****************************************************************************************************************************************************
// dynamically change header layout component value
        View headerView = binding.navigationView.getHeaderView(0);
        HeaderLayoutBinding bind = HeaderLayoutBinding.bind(headerView);
        bind.tvHeader.setText("abc");

ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(this, binding.drawerLayout, binding.appBar.toolBar, R.string.open, R.string.close);

binding.navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
              
                switch (item.getItemId()){
                    case R.id.action_home:
                        break;
                    case R.id.action_notification:
                        break;
                    
                }

                binding.drawerLayout.closeDrawer(Gravity.LEFT);
                return true;
            }
        });
