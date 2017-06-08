import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;

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
    AdView adView;
    InterstitialAd interstitialAd;
    boolean adOnScreen;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main,container,false);
        button = (Button) rootView.findViewById(R.id.button);
        progressBar = (ProgressBar) rootView.findViewById(R.id.progressBar);
        MobileAds.initialize(getContext(),"ca-app-pub-3940256099942544~3347511713");
        adView = (AdView) rootView.findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
        adView.loadAd(adRequest);
        interstitialAd = new InterstitialAd(getContext());
        interstitialAd.setAdUnitId("ca-app-pub-3940256099942544/1033173712");
        requestNewInterstitial();
        interstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                button.setVisibility(View.GONE);
                progressBar.setVisibility(View.VISIBLE);
                requestNewInterstitial();
                adOnScreen = false;
                launchJokeActivity();
            }

            @Override
            public void onAdFailedToLoad(int i) {
                super.onAdFailedToLoad(i);
            }

            @Override
            public void onAdLeftApplication() {
                super.onAdLeftApplication();
            }

            @Override
            public void onAdOpened() {
                super.onAdOpened();
            }

            @Override
            public void onAdLoaded() {
                super.onAdLoaded();
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (interstitialAd.isLoaded()){
                    adOnScreen=true;
                    interstitialAd.show();
                }else {
                    adOnScreen=false;
                    Log.d("LOG","ad not loaded");
                    button.setVisibility(View.GONE);
                    progressBar.setVisibility(View.VISIBLE);
                    launchJokeActivity();
                }
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

    private void requestNewInterstitial() {
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
        interstitialAd.loadAd(adRequest);
    }

}