package profkomsmolgu.smolgu.ru.profkomsmolgu;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class ArticlesActivity extends Fragment {

    private ProgressDialog pDialog;

    // URL to get contacts JSON
    private static String urlArticles = "http://profcom.pro/api/v1/articles";

    // JSON Node names
    private static final String TAG_CONTENT = "content";
    private static final String TAG_PREVIEW = "preview";
    private static final String TAG_TITLE = "title";
    private static final String TAG_IMAGE = "image_path";
    private static final String TAG_TIME = "created_at";

    ListView listViewArticles;

    // Hashmap for ListView
    ArrayList<HashMap<String, String>> articles;


    public ArticlesActivity() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.main_list_articles, container, false);


        articles = new ArrayList<HashMap<String, String>>();

        listViewArticles = (ListView)rootView.findViewById(R.id.list_articles);
        // Убираем разделители между элементами списка.
        ColorDrawable devidrColor = new ColorDrawable(
                this.getResources().getColor(android.R.color.transparent));
        listViewArticles.setDivider(devidrColor);
        listViewArticles.setDividerHeight(1);




        // Listview on item click listener
        listViewArticles.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Starting single contact activity
                Intent in = new Intent(getActivity().getApplicationContext(), profkomsmolgu.smolgu.ru.profkomsmolgu.singleactivity.SingleArticlesActivity.class);
                in.putExtra(TAG_TITLE, articles.get(position).get(TAG_TITLE));
                in.putExtra(TAG_IMAGE, articles.get(position).get(TAG_IMAGE));
                in.putExtra(TAG_CONTENT, articles.get(position).get(TAG_CONTENT));
                in.putExtra(TAG_TIME, articles.get(position).get(TAG_TIME));

                startActivity(in);
            }

            private Context getApplicationContext() {
                // TODO Auto-generated method stub
                return null;
            }
        });
        // Calling async task to get json
        new GetArticles().execute();
        return rootView;
    }

    private Context getApplicationContext() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }



    /**
     * Async task class to get json by making HTTP call
     * */
    private class GetArticles extends AsyncTask<Void, Void, Void> {


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress dialog
            pDialog = new ProgressDialog(getActivity());
            pDialog.setMessage("Загрузка..");
            pDialog.setCancelable(false);
            pDialog.show();

        }

        @Override
        protected Void doInBackground(Void... arg0) {
            // Creating service handler class instance
            ServiceHandler sh = new ServiceHandler();

            // Making a request to url and getting response
            String jsonStr = sh.makeServiceCall(urlArticles, ServiceHandler.GET);

            Log.d("Response: ", "> " + jsonStr);

            if (jsonStr != null) {
                try {
                    JSONArray jsonArr = new JSONArray(jsonStr);

                    // looping through All Contacts
                    for (int i = 0; i < jsonArr.length(); i++) {
                        JSONObject obj = jsonArr.getJSONObject(i);

                        String content = obj.getString(TAG_CONTENT);
                        String preview = obj.getString(TAG_PREVIEW);
                        String title = obj.getString(TAG_TITLE);
                        String imageName = obj.getString(TAG_IMAGE);
                        String siteUrl = "http://profcom.pro";
                        String timeArticles = obj.getString(TAG_TIME);

                        // tmp hashmap for single contact
                        HashMap<String, String> arrayArticles = new HashMap<String, String>();

                        // adding each child node to HashMap key => value
                        arrayArticles.put(TAG_TITLE, title);
                        arrayArticles.put(TAG_PREVIEW, preview);
                        arrayArticles.put(TAG_CONTENT, content);
                        arrayArticles.put(TAG_IMAGE, siteUrl+imageName);
                        arrayArticles.put(TAG_TIME, timeArticles);

                        // adding contact to contact list
                        articles.add(arrayArticles);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } else {
                Log.e("ServiceHandler", "Couldn't get any data from the url");
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            // Dismiss the progress dialog
            if (pDialog.isShowing())
                pDialog.dismiss();

            Collections.sort(articles, new Comparator<HashMap<String, String>>() {
                @Override
                public int compare(HashMap<String, String> a, HashMap<String, String> b) {
                    return b.get(TAG_TIME).compareTo(a.get(TAG_TIME));
                }
            });

            /**
             * Updating parsed JSON data into ListView
             * */
            ListAdapter adapter = new SimpleAdapter(
                    getActivity(), articles,
                    R.layout.list_item_articles, new String[] {TAG_TITLE, TAG_PREVIEW },
                    new int[] {R.id.title_articles, R.id.preview_articles});
            listViewArticles.setAdapter(adapter);
        }
    }
}