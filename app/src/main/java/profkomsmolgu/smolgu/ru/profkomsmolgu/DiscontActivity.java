package profkomsmolgu.smolgu.ru.profkomsmolgu;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import profkomsmolgu.smolgu.ru.profkomsmolgu.tabs.SlidingTabLayout;


public class DiscontActivity extends ActionBarActivity {

    private SlidingTabLayout mTabs;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.discont_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        NavigationDrawerFragment drawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.nav_drawer);
        drawerFragment.setUp(R.id.nav_drawer, (DrawerLayout) findViewById(R.id.drawer_layout_diskont), toolbar);

        mTabs = (SlidingTabLayout) findViewById(R.id.tabs_diskont);
        viewPager = (ViewPager) findViewById(R.id.viewPagerDiskont);
        MyPagerAdapter adapter = new MyPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        mTabs.setViewPager(viewPager);

//        viewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener(){
//            @Override
//            public void onPageSelected(int position) {
//                tabHost.setSelectedNavigationItem(position);
//            }
//        });
//        for (int i = 0; i < adapter.getCount(); i++){
//            tabHost.addTab(
//                    tabHost.newTab()
//                           .setText(adapter.getPageTitle(i))
//                           .setTabListener(this)
//            );
//        }

    }


    class MyPagerAdapter extends FragmentStatePagerAdapter {

        String[] tabs;

        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
            tabs = getResources().getStringArray(R.array.nav_drawer_discont_items);
        }

        @Override
        public Fragment getItem(int position) {
            MyFragment myFragment = MyFragment.getInstance(position);
            return myFragment;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return tabs[position];
        }

        @Override
        public int getCount() {
            return 6;
        }

//		mTitle = mDrawerTitle = getTitle();
//
//		// load slide menu items
//		navMenuTitles = getResources().getStringArray(R.array.nav_drawer_discont_items);
//
//		// nav drawer icons from resources
//		navMenuIcons = getResources().obtainTypedArray(R.array.nav_drawer_discont_icons);
//
//		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
//        toggle = new android.support.v7.app.ActionBarDrawerToggle(
//                this,
//                mDrawerLayout,
//                R.string.app_name,
//                R.string.app_name);
//        toggle.setDrawerIndicatorEnabled(true);
//        mDrawerLayout.setDrawerListener(toggle);
//
//
//		mDrawerList = (ListView) findViewById(R.id.list_slidermenu);
//
//		navDrawerItems = new ArrayList<NavDrawerItem>();
//
//		// adding nav drawer items to array
//		// Home
//		navDrawerItems.add(new NavDrawerItem(navMenuTitles[0], navMenuIcons.getResourceId(0, -1)));
//		// Find People
//		navDrawerItems.add(new NavDrawerItem(navMenuTitles[1], navMenuIcons.getResourceId(1, -1)));
//		// Photos
//		navDrawerItems.add(new NavDrawerItem(navMenuTitles[2], navMenuIcons.getResourceId(2, -1)));
//		// Communities, Will add a counter here
//		navDrawerItems.add(new NavDrawerItem(navMenuTitles[3], navMenuIcons.getResourceId(3, -1)));
//		// Pages
//		navDrawerItems.add(new NavDrawerItem(navMenuTitles[4], navMenuIcons.getResourceId(4, -1)));
//		// What's hot, We  will add a counter here
//		navDrawerItems.add(new NavDrawerItem(navMenuTitles[5], navMenuIcons.getResourceId(5, -1)));
//
//		navDrawerItems.add(new NavDrawerItem(navMenuTitles[6], navMenuIcons.getResourceId(6, -1)));
//		navDrawerItems.add(new NavDrawerItem(navMenuTitles[7], navMenuIcons.getResourceId(7, -1)));
//		navDrawerItems.add(new NavDrawerItem(navMenuTitles[8], navMenuIcons.getResourceId(8, -1)));
//
//
//        // Recycle the typed array
//		navMenuIcons.recycle();
//
//		mDrawerList.setOnItemClickListener(new SlideMenuClickListener());
//
//		// setting the nav drawer list adapter
//		adapter = new NavDrawerListAdapter(getApplicationContext(),
//				navDrawerItems);
//        mDrawerList.setAdapter(adapter);
//
//		// enabling action bar app icon and behaving it as toggle button
//		//getActionBar().setDisplayHomeAsUpEnabled(true);
//		//getActionBar().setHomeButtonEnabled(true);
//
//		mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
//				R.drawable.ic_drawer, //nav menu toggle icon
//				R.string.app_name, // nav drawer open - description for accessibility
//				R.string.app_name // nav drawer close - description for accessibility
//		) {
//			public void onDrawerClosed(View view) {
//				getActionBar().setTitle(mTitle);
//				// calling onPrepareOptionsMenu() to show action bar icons
//				invalidateOptionsMenu();
//			}
//
//			public void onDrawerOpened(View drawerView) {
//				getActionBar().setTitle(mDrawerTitle);
//				// calling onPrepareOptionsMenu() to hide action bar icons
//				invalidateOptionsMenu();
//			}
//		};
//		mDrawerLayout.setDrawerListener(mDrawerToggle);
//
//		if (savedInstanceState == null) {
//			// on first time display view for first nav item
//			displayView(0);
//		}
//    }

//	/**
//	 * Slide menu item click listener
//	 * */
//	private class SlideMenuClickListener implements
//			ListView.OnItemClickListener {
//		@Override
//		public void onItemClick(AdapterView<?> parent, View view, int position,
//				long id) {
//			// display view for selected nav drawer item
//			displayView(position);
//		}
//	}


//	/**
//	 * Diplaying fragment view for selected nav drawer list item
//	 * */
//	private void displayView(int position) {
//		// update the main content by replacing fragments
//		Fragment fragment = null;
//        new AllDiscontActivity();
//		switch (position) {
//		case 0:
//			fragment = new AllDiscontActivity();
//			break;
//		case 1:
//			MapFragment();
//			break;
//		case 2:
//			fragment = new CafeActivity();
//			break;
//		case 3:
//			fragment = new PrazdnikiActivity();
//			break;
//		case 4:
//			fragment = new DosugActivity();
//			break;
//		case 5:
//			fragment = new MagazinActivity();
//			break;
//		case 6:
//			fragment = new ProcheeActivity();
//			break;
//		case 7:
//			fragment = new AkchiiActivity();
//			break;
//		case 8:
//			finish();
//			break;
//		}
//
//        showFragment(fragment);
//        mDrawerList.setItemChecked(position, true);
//        mDrawerList.setSelection(position);
//        setTitle(navMenuTitles[position]);
//        mDrawerLayout.closeDrawer(mDrawerList);
//	}
//
//    public void showFragment(Fragment fragment){
//        if (fragment != null) {
//            FragmentManager fragmentManager = getSupportFragmentManager();
//            fragmentManager.beginTransaction().replace(R.id.frame_container, fragment).commit();
//        } else {
//            // error in creating fragment
//            Log.e("MainActivity", "Error in creating fragment");
//        }
//    }
//
//	private void MapFragment() {
//		Intent intent = new Intent(this, MapAllDiscontActivity.class);
//		startActivity(intent);
//	}
//
////	private void ProfkomActivity() {
////		Intent intent = new Intent(this, ProfkomActivity.class);
////		startActivity(intent);
////	}
//
//        @Override
//        public void setTitle(CharSequence title) {
//            mTitle = title;
//            //getActionBar().setTitle(mTitle);
//        }
//
//        @Override
//        public boolean onOptionsItemSelected(MenuItem item) {
//            // The action bar home/up action should open or close the drawer.
//            // ActionBarDrawerToggle will take care of this.
//            if (toggle.onOptionsItemSelected(item))
//                return true;
//            return super.onOptionsItemSelected(item);
//        }

//        /**
//         * When using the ActionBarDrawerToggle, you must call it during
//         * onPostCreate() and onConfigurationChanged()...
//         */
//
//        @Override
//        protected void onPostCreate(Bundle savedInstanceState) {
//            super.onPostCreate(savedInstanceState);
//            toggle.syncState();
//        }
//
//        @Override
//        public void onConfigurationChanged(Configuration newConfig) {
//            super.onConfigurationChanged(newConfig);
//            // Pass any configuration change to the drawer toggls
//            toggle.onConfigurationChanged(newConfig);
//        }
    }

    public static class MyFragment extends Fragment {
        private TextView textView;
        public static MyFragment getInstance(int position) {
            MyFragment myFragment = new MyFragment();
            Bundle args = new Bundle();
            args.putInt("position", position);
            myFragment.setArguments(args);
            return myFragment;
        }

        @Override
        public View onCreateView(LayoutInflater Inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            View layout = Inflater.inflate(R.layout.my_activity, container, false);
            textView = (TextView) layout.findViewById(R.id.position);
            Bundle bundle = getArguments();
            if (bundle != null) {
                textView.setText("Страница" + bundle.getInt("position"));
            }
            return layout;
        }
    }
}