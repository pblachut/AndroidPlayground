package piotrek.atmlocator;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.List;

import piotrek.atmlocator.orm.Atm;
import piotrek.atmlocator.orm.Bank;
import piotrek.atmlocator.orm.BankDao;
import piotrek.atmlocator.orm.DbHelper;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback {

    private final int REQUEST_CODE = 5;
    private GoogleMap mMap;
    private Dao<Atm, Integer> atmDao;
    private BankDao bankDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        DbHelper dbHelper = new DbHelper(this);

        try {
            atmDao = dbHelper.getDao(Atm.class);
            bankDao = dbHelper.getDao(Bank.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        refreshMap();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.maps, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.addId){

            Intent intent = new Intent(this, AddAtm.class);

            startActivityForResult(intent, REQUEST_CODE);

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE){
            Toast.makeText(this, "Result code " + resultCode, Toast.LENGTH_SHORT).show();
            refreshMap();
        }
    }

    private void refreshMap(){

        if (mMap != null){

            mMap.getUiSettings().setZoomControlsEnabled(true);

            try {
                List<Atm> atms = atmDao.queryForAll();
                for (Atm atm : atms) {
                    bankDao.refresh(atm.getBank());

                    addMarkerForAtm(atm);
                }


            } catch (SQLException e) {
                e.printStackTrace();
            }



        }

    }

    private void addMarkerForAtm(Atm atm){
        LatLng sydney = new LatLng(atm.getLatitude(), atm.getLongitude());
        mMap.addMarker(new MarkerOptions()
                .position(sydney)
                .title(atm.getBank().getName())
                .snippet(atm.getBank().getPhone()));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));

    }
}
