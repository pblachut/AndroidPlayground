package piotrek.logintest;

import android.content.SharedPreferences;

/**
 * Created by Admin on 2016-04-27.
 */
public class CredentialsStorage implements ICredentialsStorage {
    private final SharedPreferences preferences;

    public CredentialsStorage(SharedPreferences preferences) {

        this.preferences = preferences;
    }

    @Override
    public void storeUsername(String username) {
        preferences
                .edit()
                .putString("user", username)
                .apply();

        // apply - asynchroniczny
        // commit - synchroniczny
    }
}
