package piotrek.atmlocator

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import piotrek.atmlocator.orm.Atm
import piotrek.atmlocator.orm.AtmDao
import piotrek.atmlocator.orm.BankDao
import java.sql.SQLException
import javax.inject.Inject

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private val REQUEST_CODE = 5
    private var mMap: GoogleMap? = null

    @Inject
    lateinit var atmDao: AtmDao

    @Inject
    lateinit var bankDao: BankDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        AtmLocatorApplication.component.inject(this)
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
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        refreshMap()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.maps, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if (item.itemId == R.id.addId) {

            val intent = Intent(this, AddAtm::class.java)

            startActivityForResult(intent, REQUEST_CODE)

            return true
        }

        return super.onOptionsItemSelected(item)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == REQUEST_CODE) {
            Toast.makeText(this, "Result code " + resultCode, Toast.LENGTH_SHORT).show()
            refreshMap()
        }
    }

    private fun refreshMap() {

        if (mMap != null) {

            mMap!!.uiSettings.isZoomControlsEnabled = true

            try {
                val atms = atmDao.queryForAll()
                for (atm in atms) {
                    bankDao.refresh(atm.bank)

                    addMarkerForAtm(atm)
                }


            } catch (e: SQLException) {
                e.printStackTrace()
            }


        }

    }

    private fun addMarkerForAtm(atm: Atm) {
        val sydney = LatLng(atm.latitude, atm.longitude)
        mMap?.addMarker(MarkerOptions().position(sydney).title(atm.bank?.name).snippet(atm.bank?.phone))
        mMap?.moveCamera(CameraUpdateFactory.newLatLng(sydney))

    }
}
