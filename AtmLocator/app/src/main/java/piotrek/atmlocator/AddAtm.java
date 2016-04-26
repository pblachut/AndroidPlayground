package piotrek.atmlocator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddAtm extends AppCompatActivity {


    @BindView(R.id.longitudeId)
    TextView longitudeTextView;

    @BindView(R.id.latitudeId)
    TextView latitudeTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_atm);

        ButterKnife.bind(this);

    }

    @OnClick(R.id.saveButton)
    public void onSaveButtonClick(){

    }
    @OnClick(R.id.picklocationButton)
    public void onPickLoacationButtonClick(){

    }

}
