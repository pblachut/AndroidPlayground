package piotrek.androidworkshop;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity  implements FirstFragment.Callback{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null){
            Fragment fragment = new FirstFragment();

            getSupportFragmentManager().beginTransaction().add(R.id.container, fragment).commit();
        }
    }

    @Override
    public void onClick() {

    }
}
