package profkomsmolgu.smolgu.ru.profkomsmolgu.singleactivity.diskont;

import java.util.ArrayList;

import com.google.android.gms.common.data.e;

import profkomsmolgu.smolgu.ru.profkomsmolgu.Place;
import profkomsmolgu.smolgu.ru.profkomsmolgu.R;
import ru.test.image.ImageLoader;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class SingleAllDiscontActivity extends ActionBarActivity {

	// JSON node keys
	private static final String TAG_DISCONT_NAME = "name";
	private static final String TAG_DISCONT_IMAGE_PATH = "image_path";
	private static final String TAG_DISCONT_CATEGORY = "category";
	private static final String TAG_DISCONT_DESCRIPTION = "description";
	private static final String TAG_DISCONT_DISCOUNT = "discount";
	private static final String TAG_DISCONT_SITE = "site";
	private static final String TAG_DISCONT_PLACES = "places";

	String Site;
	String Phone;
	String MapDiskont;
    private ActionBarDrawerToggle toggle;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.single_all_diskont);

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

		// getting intent data
		Intent in = getIntent();
		// Get JSON values from previous intent
		final String nameDiscont = in.getStringExtra(TAG_DISCONT_NAME);
		String imageName = in.getStringExtra(TAG_DISCONT_IMAGE_PATH);
		String categoryDiscont = in.getStringExtra(TAG_DISCONT_CATEGORY);
		String descriptionDiscont = in.getStringExtra(TAG_DISCONT_DESCRIPTION);
		String discontInfo = in.getStringExtra(TAG_DISCONT_DISCOUNT);
		final String siteDiscont = in.getStringExtra(TAG_DISCONT_SITE);
		final ArrayList<Place> places = (ArrayList<Place>) in.getSerializableExtra(TAG_DISCONT_PLACES);

		// Displaying all values on the screen
		TextView lblName = (TextView) findViewById(R.id.singles_name_discont);
		ImageView lblImage = (ImageView) findViewById(R.id.singles_image_discont);
		TextView lblCategory = (TextView) findViewById(R.id.singles_category_diskont);
		TextView lblDescription = (TextView) findViewById(R.id.singles_description_diskont);
		TextView lblDiscont = (TextView) findViewById(R.id.singles_discont_diskont);
		Button btnPhone = (Button) findViewById(R.id.singles_phone_diskont);
		Button btnMap = (Button) findViewById(R.id.singles_map_diskont);
		Button btnShare = (Button) findViewById(R.id.share_diskont);
		Button btnMail = (Button) findViewById(R.id.mail_diskont);
		Button btnSite = (Button) findViewById(R.id.site_diskont);

		lblName.setText(nameDiscont);
		lblCategory.setText("Категория: " + categoryDiscont);
		lblDescription.setText(descriptionDiscont);
		lblDiscont.setText("Скидка: " + discontInfo);

		// Loader image - will be shown before loading image
		int loader = R.drawable.loader;

		// ImageLoader class instance
		ImageLoader imgLoader = new ImageLoader(getApplicationContext());
		imgLoader.DisplayImage(imageName, loader, lblImage);

		boolean yphone = false;
		for (Place place : places) {
			if (place.phone != null && !place.phone.isEmpty()) {
				yphone = true;
			}
		}

		boolean ymap = false;
		for (Place place : places) {
			if (place.latitude != null && !place.latitude.isEmpty()) {
				if (place.longitude != null && !place.longitude.isEmpty()) {
					ymap = true;
				}
			}
		}
		if (!ymap){
			btnMap.setVisibility(View.GONE);
			return;
		}
		if (!yphone){
			btnPhone.setVisibility(View.GONE);
			return;
		}

		
		btnPhone.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				AlertShowPhone(places);
			}
		});

		btnMap.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent in = new Intent(SingleAllDiscontActivity.this, profkomsmolgu.smolgu.ru.profkomsmolgu.MapSingleActivity.class);
				in.putExtra(TAG_DISCONT_PLACES, places);
				startActivity(in);
			}
		});

		Site = in.getStringExtra(TAG_DISCONT_SITE);
		if (Site.length() == 0) {
			btnSite.setVisibility(View.GONE);
			return;
		}

		btnSite.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				String url = Site.trim();
				Intent i = new Intent(Intent.ACTION_VIEW);
				i.setData(Uri.parse("http://" + url));
				startActivity(i);
				return;
			}
		});

		btnShare.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent shareIntent = new Intent(Intent.ACTION_SEND);
				shareIntent.setType("text/plain");
				shareIntent
						.putExtra(
								Intent.EXTRA_TEXT,
								"Я пользуюсь скидками в "
										+ nameDiscont
										+ " с помощью приложения #ПрофкомСмолГУ на #Android!");
				startActivity(Intent.createChooser(shareIntent,
						"Рассказать друзьям"));
			}
		});

		btnMail.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent emailIntent = new Intent(
						Intent.ACTION_SEND);
				String aEmailList[] = { "profkom-smol@yandex.ru" };
				emailIntent.putExtra(Intent.EXTRA_EMAIL,
						aEmailList);
				emailIntent.putExtra(Intent.EXTRA_SUBJECT,
						"Профком дисконт");
				emailIntent.setType("plain/text");
				emailIntent.putExtra(Intent.EXTRA_TEXT,
						"Не дали скидку в " + nameDiscont
								+ " по карте Профком Дисконт");
				startActivity(Intent.createChooser(emailIntent,
						"Написать email в Профком"));
			}
		});

	}

	public void AlertShowPhone(ArrayList<Place> arrayListPlace) {
		final ArrayList<String> arrayPhone = new ArrayList<String>();
		for (Place place : arrayListPlace) {
			String phone = place.phone;
			arrayPhone.add(phone);
		}
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle("Выберете номер телефона").setItems(
				arrayPhone.toArray(new String[0]),
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						Intent i = new Intent(Intent.ACTION_CALL, Uri
								.parse("tel:" + arrayPhone.get(which)));
						startActivity(i);
					}
				});
		builder.show();
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