package piotrek.logintest;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements ILoginView {

    @BindView(R.id.usernameEditText)
    public EditText usernameEditTextView;
    @BindView(R.id.passwordEditText)
    public EditText passwordEditTextView;
    @BindView(R.id.loginButton)
    Button loginButtton;

    ILoginInteractor interactor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        ICredentialsStorage storage = new CredentialsStorage(preferences);
        ILoginApi api = new ILoginApi() {
            @Override
            public String login(String username, String password) {
                if (username != "username")
                    return "username_too_short";
                return "OK";
            }
        };

        interactor = new LoginManager(storage, api, this);

        ButterKnife.bind(this);
    }

    @OnClick(R.id.loginButton)
    public void onLoginButtonClick(){
        String username = usernameEditTextView.getText().toString();
        String password = passwordEditTextView.getText().toString();

        interactor.login(username, password);
    }


    @Override
    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
