package profkomsmolgu.smolgu.ru.profkomsmolgu.singleactivity;


import profkomsmolgu.smolgu.ru.profkomsmolgu.R;
import ru.test.image.ImageLoader;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

public class SingleArticlesActivity extends ActionBarActivity {

    private ActionBarDrawerToggle toggle;

	// JSON node keys
	private static final String TAG_TITLE = "title";
	private static final String TAG_IMAGE = "image_path";
	private static final String TAG_CONTENT = "content";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.single_articles);

        Toolbar toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.abc_ic_ab_back_mtrl_am_alpha);

        DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        toggle = new ActionBarDrawerToggle(
                this,
                drawerLayout,
                R.string.app_name,
                R.string.app_name);
        toggle.setDrawerIndicatorEnabled(false);
        drawerLayout.setDrawerListener(toggle);
		
		// Создание кнопки назад.
		//getActionBar().setDisplayHomeAsUpEnabled(true);
		//Intent intent = getIntent();

		// getting intent data
		Intent in = getIntent();
		// Get JSON values from previous intent
		String title = in.getStringExtra(TAG_TITLE);
		String content = in.getStringExtra(TAG_CONTENT);

		String imageName = in.getStringExtra(TAG_IMAGE);

		// Displaying all values on the screen
		TextView lbltitle = (TextView) findViewById(R.id.singles_title_articles);
		TextView lblcontent = (TextView) findViewById(R.id.singles_content_articles);
		ImageView lblimage = (ImageView) findViewById(R.id.singles_image_articles);

		lbltitle.setText(title);
		lblcontent.setText(Html.fromHtml(content));

		int loader = R.drawable.loader;
		ImageLoader imgLoader = new ImageLoader(getApplicationContext());
		imgLoader.DisplayImage(imageName, loader, lblimage);
	}


    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        toggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        toggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
