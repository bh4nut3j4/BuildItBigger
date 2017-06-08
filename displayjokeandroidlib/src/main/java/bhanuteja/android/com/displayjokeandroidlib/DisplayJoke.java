package bhanuteja.android.com.displayjokeandroidlib;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class DisplayJoke extends AppCompatActivity {

    TextView jokeTv;
    public static final String CONSTANT = "string";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_joke);
        jokeTv= (TextView) findViewById(R.id.joketext);
        if (savedInstanceState!=null){
            String s = savedInstanceState.getString(CONSTANT);
            jokeTv.setText(s);
        }
        String joke = getIntent().getExtras().getString("Joke_Key");
        if (joke!=null || joke.length()>0){
            jokeTv.setText(joke);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putString(CONSTANT,jokeTv.getText().toString());
        super.onSaveInstanceState(outState);
    }
}
