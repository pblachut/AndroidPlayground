package piotrek.atmlocator;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;
import com.google.android.gms.maps.model.LatLng;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import piotrek.atmlocator.orm.Bank;
import piotrek.atmlocator.orm.DbHelper;

public class AddAtm extends AppCompatActivity {


    private static final int REQUEST_PLACE_PICKER = 123;
    @Bind(R.id.longitudeId)
    TextView longitudeTextView;

    @Bind(R.id.latitudeId)
    TextView latitudeTextView;
    @Bind(R.id.picklocationButton)
    Button pickLocationButton;
    @Bind(R.id.bankSpinner)
    Spinner bankSpinner;
    @Bind(R.id.saveButton)
    Button saveButton;
    private LatLng latLng;
    private String name;
    private String address;
    private String phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_atm);

        ButterKnife.bind(this);

        ArrayAdapter<Bank> bankArrayAdapter = new ArrayAdapter<Bank>(this, android.R.layout.simple_expandable_list_item_1);

        bankSpinner.setAdapter(bankArrayAdapter);

        DbHelper dbHelper = new DbHelper(this);
        try {
            Dao<Bank, Integer> dao = dbHelper.getDao(Bank.class);
            List<Bank> banks = dao.queryForAll();
            bankArrayAdapter.addAll(banks);

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @OnClick(R.id.saveButton)
    public void onSaveButtonClick() {

    }

    @OnClick(R.id.picklocationButton)
    public void onPickLocationButtonClick() {
        try {
            PlacePicker.IntentBuilder intentBuilder = new PlacePicker.IntentBuilder();
            Intent intent = intentBuilder.build(AddAtm.this);
            // Start the Intent by requesting a result, identified by a request code.
            startActivityForResult(intent, REQUEST_PLACE_PICKER);

            // Hide the pick option in the UI to prevent users from starting the picker
            // multiple times.
            // showPickAction(false);

        } catch (GooglePlayServicesRepairableException e) {
            GooglePlayServicesUtil
                    .getErrorDialog(e.getConnectionStatusCode(), AddAtm.this, 0);
        } catch (GooglePlayServicesNotAvailableException e) {
            Toast.makeText(AddAtm.this, "Google Play Services is not available.",
                    Toast.LENGTH_LONG)
                    .show();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        // BEGIN_INCLUDE(activity_result)
        if (requestCode == REQUEST_PLACE_PICKER) {
            // This result is from the PlacePicker dialog.

            // Enable the picker option
            //showPickAction(true);

            if (resultCode == Activity.RESULT_OK) {
                /* User has picked a place, extract data.
                   Data is extracted from the returned intent by retrieving a Place object from
                   the PlacePicker.
                 */
                final Place place = PlacePicker.getPlace(this, data);

                /* A Place object contains details about that place, such as its name, address
                and phone number. Extract the name, address, phone number, place ID and place types.
                 */

                latLng = place.getLatLng();
                name = place.getName().toString();
                address = place.getAddress().toString();
                phone = place.getPhoneNumber().toString();

                latitudeTextView.setText(String.valueOf(latLng.latitude));
                longitudeTextView.setText(String.valueOf(latLng.longitude));
            } else {
                // User has not selected a place, hide the card.
                //getCardStream().hideCard(CARD_DETAIL);
            }

        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
        // END_INCLUDE(activity_result)
    }

}
