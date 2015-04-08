package profkomsmolgu.smolgu.ru.profkomsmolgu;

import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ProfkomActivity extends ActionBarActivity {

    private ActionBarDrawerToggle toggle;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.profkom_fragment);

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

		TextView textView = (TextView) findViewById(R.id.o_profkom);
		textView.setText(Html.fromHtml(getString(R.string.o_profkome)));

		Button b_s_p = (Button) findViewById(R.id.btn_site_profkom);
		Button b_vk = (Button) findViewById(R.id.btn_vk);
		Button b_ins = (Button) findViewById(R.id.btn_ins);
		Button b_tw = (Button) findViewById(R.id.btn_tw);

		b_s_p.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Intent myWebLink = new Intent(Intent.ACTION_VIEW);
				myWebLink.setData(Uri.parse("http://www.profcom.pro/"));
				startActivity(myWebLink);
			}
		});

		b_vk.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Intent myWebLink = new Intent(Intent.ACTION_VIEW);
				myWebLink.setData(Uri.parse("http://vk.com/v_profkome"));
				startActivity(myWebLink);
			}
		});

		b_ins.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Intent myWebLink_ins = new Intent(Intent.ACTION_VIEW);
				myWebLink_ins.setData(Uri.parse("http://instagram.com/profkomsmolgu"));
				startActivity(myWebLink_ins);
			}
		});

		b_tw.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Intent myWebLink_tw = new Intent(Intent.ACTION_VIEW);
				myWebLink_tw.setData(Uri.parse("https://twitter.com/Profkom_SmolGU"));
				startActivity(myWebLink_tw);
			}
		});
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