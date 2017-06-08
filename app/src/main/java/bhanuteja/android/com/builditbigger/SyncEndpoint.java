package bhanuteja.android.com.builditbigger;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.example.root.myapplication.backend.myApi.MyApi;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;

import java.io.IOException;

/**
 * Created by root on 6/8/17.
 */

public class SyncEndpoint extends AsyncTask<Void,Void,String>{
    Context context;
    private MyApi myApi = null;
    public DataReciveInterface dataReciveInterface;

    public SyncEndpoint(DataReciveInterface dataReciveInterface) {
        this.dataReciveInterface = dataReciveInterface;
    }

    public SyncEndpoint(MainActivity mainActivity) {
    }

    @Override
    protected String doInBackground(Void... params) {
        if (myApi == null) {
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(), new AndroidJsonFactory(), null)
                    .setRootUrl("https://udacitybuilditbigger-project4.appspot.com/_ah/api/")
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> request) throws IOException {
                        }
                    });
            myApi = builder.build();
        }
        try {
            return myApi.getJoke().execute().getData();
        } catch (IOException e) {
            return e.getMessage();
        }
    }

    @Override
    public void onPostExecute(String s) {
        Log.d("TAG",s);
        dataReciveInterface.onDataReceived(s);
    }
}
