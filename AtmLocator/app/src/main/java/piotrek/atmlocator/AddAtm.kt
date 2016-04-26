package piotrek.atmlocator

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast

import com.google.android.gms.common.GooglePlayServicesNotAvailableException
import com.google.android.gms.common.GooglePlayServicesRepairableException
import com.google.android.gms.common.GooglePlayServicesUtil
import com.google.android.gms.location.places.Place
import com.google.android.gms.location.places.ui.PlacePicker
import com.google.android.gms.maps.model.LatLng
import com.j256.ormlite.dao.Dao

import java.sql.SQLException

import javax.inject.Inject

import butterknife.Bind
import butterknife.ButterKnife
import butterknife.OnClick
import kotlinx.android.synthetic.main.activity_add_atm.*
import piotrek.atmlocator.orm.Atm
import piotrek.atmlocator.orm.AtmDao
import piotrek.atmlocator.orm.Bank
import piotrek.atmlocator.orm.BankDao
import piotrek.atmlocator.orm.DbHelper

class AddAtm : AppCompatActivity() {

    private var latLng: LatLng? = null
    private var name: String? = null
    private var address: String? = null
    private var phone: String? = null

    @Inject
    lateinit  var atmDao: AtmDao

    @Inject
    lateinit  var bankDao: BankDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_atm)

        AtmLocatorApplication.component.inject(this)

        ButterKnife.bind(this)

        val bankArrayAdapter = ArrayAdapter<Bank>(this, android.R.layout.simple_expandable_list_item_1)

        bankSpinner.adapter = bankArrayAdapter

        try {
            val banks = bankDao.queryForAll()
            bankArrayAdapter.addAll(banks)

        } catch (e: SQLException) {
            e.printStackTrace()
        }

    }

    @OnClick(R.id.saveButton)
    fun onSaveButtonClick() {
        val atm = Atm()
        atm.latitude = latLng!!.latitude
        atm.longitude = latLng!!.longitude
        atm.address = address
        atm.bank = bankSpinner.selectedItem as Bank

        try {


            atmDao.create(atm)
            setResult(Activity.RESULT_OK)
            finish()

        } catch (e: SQLException) {
            e.printStackTrace()
        }

    }

    @OnClick(R.id.picklocationButton)
    fun onPickLocationButtonClick() {
        try {
            val intentBuilder = PlacePicker.IntentBuilder()
            val intent = intentBuilder.build(this@AddAtm)
            // Start the Intent by requesting a result, identified by a request code.
            startActivityForResult(intent, REQUEST_PLACE_PICKER)

            // Hide the pick option in the UI to prevent users from starting the picker
            // multiple times.
            // showPickAction(false);

        } catch (e: GooglePlayServicesRepairableException) {
            GooglePlayServicesUtil.getErrorDialog(e.connectionStatusCode, this@AddAtm, 0)
        } catch (e: GooglePlayServicesNotAvailableException) {
            Toast.makeText(this@AddAtm, "Google Play Services is not available.",
                    Toast.LENGTH_LONG).show()
        }

    }

    public override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) = // BEGIN_INCLUDE(activity_result)
            if (requestCode == REQUEST_PLACE_PICKER) {
                // This result is from the PlacePicker dialog.

                // Enable the picker option
                //showPickAction(true);

                if (resultCode == Activity.RESULT_OK) {
                    /* User has picked a place, extract data.
                       Data is extracted from the returned intent by retrieving a Place object from
                       the PlacePicker.
                     */
                    val place = PlacePicker.getPlace(this, data)

                    /* A Place object contains details about that place, such as its name, address
                    and phone number. Extract the name, address, phone number, place ID and place types.
                     */

                    latLng = place.latLng
                    name = place.name.toString()
                    address = place.address.toString()
                    phone = place.phoneNumber.toString()

                    var latitudeString = latLng?.latitude.toString() ?: "";

                    latitudeId.text = latLng!!.latitude.toString()
                    longitudeId.text = latLng!!.longitude.toString()
                } else {
                    // User has not selected a place, hide the card.
                    //getCardStream().hideCard(CARD_DETAIL);
                }

            } else {
                super.onActivityResult(requestCode, resultCode, data)
            }
    // END_INCLUDE(activity_result)

    companion object {


        private val REQUEST_PLACE_PICKER = 123
    }

}
