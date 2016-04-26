package piotrek.atmlocator.orm;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by Admin on 2016-04-26.
 */

@DatabaseTable(tableName = "atm", daoClass = AtmDao.class)
public class Atm {
    @DatabaseField(generatedId = true, columnName = Columns.ID)
    private int id;

    // eager loading
    //@DatabaseField(foreign = true, foreignAutoRefresh = true)
    @DatabaseField(foreign = true)
    private Bank bank;
    @DatabaseField(canBeNull = false)
    private double longitude;
    @DatabaseField(canBeNull = false)
    private double latitude;
    @DatabaseField(canBeNull = false)
    private String address;

    public Atm(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setBank(Bank bank) {
        this.bank = bank;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getLatitude() {

        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public Bank getBank() {
        return bank;
    }

    public String getAddress() {
        return address;
    }

    public class Columns {
        public static final String ID = "id";
    }
}
