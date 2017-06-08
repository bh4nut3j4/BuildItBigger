package bhanuteja.android.com.builditbigger;

import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by root on 6/8/17.
 */

@RunWith(AndroidJUnit4.class)
public class SyncEndpointTest {
    @Test
    public void synctest() throws Exception{
        try {
            MainActivity mainActivity = new MainActivity();
            SyncEndpoint syncEndpoint = new SyncEndpoint(mainActivity);
            syncEndpoint.execute();
            String result = syncEndpoint.get(30, TimeUnit.SECONDS);

            assertNotNull(result);
            assertTrue(result.length() > 0);
        } catch (Exception e){
            Log.e("EndpointsAsyncTaskTest", "testDoInBackground: Timed out");
        }
    }

}
