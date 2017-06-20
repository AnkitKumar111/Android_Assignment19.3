package com.example.ankitkumar.json;

        import java.util.ArrayList;

        import org.json.JSONArray;

        import android.app.Activity;
        import android.os.Bundle;
        import android.widget.ListView;

        import com.loopj.android.http.JsonHttpResponseHandler;
        import com.loopj.android.http.RequestParams;
        import com.nostra13.universalimageloader.core.DisplayImageOptions;
        import com.nostra13.universalimageloader.core.ImageLoader;
        import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
        import com.srlucas.github.R;
        import com.srlucas.github.eventviewer.model.Event;
        import com.srlucas.github.eventviewer.network.github.GithubRestClient;

/** Main Activity for our event viewer

 */
public class MainActivity extends Activity {

    /** Will convert Event model to listview. */
    public EventsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Create global configuration and initialize ImageLoader with this configuration
        DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder().
                cacheInMemory(true).cacheOnDisc(true).build();

        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(getApplicationContext())
                .defaultDisplayImageOptions(defaultOptions)
                .build();
        ImageLoader.getInstance().init(config);

        prepareListView();

        fetchEventsAsync(0);
    }

    /** Sets everything to that the EventsAdapter is properly attached to the
     * ListView.
     */
    private void prepareListView() {
        // Construct the data source
        ArrayList<Event> ghEvents = new ArrayList<Event>();

        // Create the adapter to convert the array to views
        this.adapter = new EventsAdapter(this, ghEvents);

        // Attach the adapter to a ListView
        ListView listView = (ListView) findViewById(R.id.listview);
        listView.setAdapter(adapter);
    }

    /**
     * Fetch events to show and populate ListView
     *
     * @param page which page number to fetch
     */
    private void fetchEventsAsync(int page) {

        GithubRestClient.get("events",
                new RequestParams("page", page),
                new JsonHttpResponseHandler() {
                    @Override
                    public void onSuccess(JSONArray events) {

                        // update listview
                        adapter.addAll(Event.fromJson(events));
                    }
                }
        ); // ascii art
    }
}
