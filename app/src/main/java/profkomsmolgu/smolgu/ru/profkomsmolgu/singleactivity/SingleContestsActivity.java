package profkomsmolgu.smolgu.ru.profkomsmolgu.singleactivity;

import profkomsmolgu.smolgu.ru.profkomsmolgu.R;
import ru.test.image.ImageLoader;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.Html;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

public class SingleContestsActivity extends ActionBarActivity {

	// JSON node keys
	private static final String TAG_TITLE_CONTESTS = "title";
	private static final String TAG_IMAGE_CONTESTS = "image_path";
	private static final String TAG_DESCRIPTION_CONTESTS = "description";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.single_contests);
		// Создание кнопки назад.
		//getActionBar().setDisplayHomeAsUpEnabled(true);
		Intent intent = getIntent();

		// getting intent data
		Intent in = getIntent();
		// Get JSON values from previous intent
		String title = in.getStringExtra(TAG_TITLE_CONTESTS);
		String content = in.getStringExtra(TAG_DESCRIPTION_CONTESTS);
		String imageName = in.getStringExtra(TAG_IMAGE_CONTESTS);


		// Displaying all values on the screen
		TextView lbltitle = (TextView) findViewById(R.id.singles_title_contests);
		TextView lblcontent = (TextView) findViewById(R.id.singles_content_contests);
		ImageView lblimage = (ImageView) findViewById(R.id.singles_image_contests);

		lbltitle.setText(title);
		lblcontent.setText(Html.fromHtml(content));

		// Loader image - will be shown before loading image
		int loader = R.drawable.loader;

		// ImageLoader class instance
		ImageLoader imgLoader = new ImageLoader(getApplicationContext());
		imgLoader.DisplayImage(imageName, loader, lblimage);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			finish();
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}
}
