package piotrek.logintest;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowPreferenceManager;

import static org.junit.Assert.*;

/**
 * Created by Admin on 2016-04-27.
 */
@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21)
public class ICredentialsStorageTest {

    @Test
    public void should_save_in_shared_preferences() throws Exception {
        // prepare
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(RuntimeEnvironment.application);

        ICredentialsStorage credentialsStorage = new CredentialsStorage(preferences);

        // run
        credentialsStorage.storeUsername("username");
        // assert

        String current = preferences.getString("user", null);
        assertEquals("username", current);
    }
}