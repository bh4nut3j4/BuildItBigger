import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;

import bhanuteja.android.com.builditbigger.DataReciveInterface;
import bhanuteja.android.com.builditbigger.R;
import bhanuteja.android.com.builditbigger.SyncEndpoint;
import bhanuteja.android.com.displayjokeandroidlib.DisplayJoke;

/**
 * Created by root on 6/8/17.
 */

public class MainFragment extends Fragment implements DataReciveInterface {
    Button button;
    ProgressBar progressBar;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main,container,false);
        button = (Button) rootView.findViewById(R.id.button);
        progressBar = (ProgressBar) rootView.findViewById(R.id.progressBar);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                button.setVisibility(View.GONE);
                progressBar.setVisibility(View.VISIBLE);
                launchJokeActivity();
            }
        });

        return rootView;
    }

    public void launchJokeActivity(){
        SyncEndpoint syncEndpoint = new SyncEndpoint(this);
        syncEndpoint.execute();
    }

    @Override
    public void onDataReceived(String data) {
        Intent intent = new Intent(getContext(), DisplayJoke.class);
        intent.putExtra("Joke_Key",data);
        button.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.GONE);
        startActivity(intent);
    }

}