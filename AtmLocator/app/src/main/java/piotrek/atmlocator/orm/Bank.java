package piotrek.atmlocator.orm;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "bank", daoClass = BankDao.class)
public class Bank {

    @DatabaseField(generatedId = true, columnName = Columns.ID)
    private int id;
    @DatabaseField(columnName = Columns.BANK_NAME, canBeNull = false, unique = true)
    private String name;
    @DatabaseField(columnName = Columns.PHONE)
    private String phone;

    public Bank(){ }

    @Override
    public String toString() {
        return name;
    }

    public Bank(String name, String phone){
        this();
        this.name = name;
        this.phone = phone;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getId() {

        return id;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public class Columns {
        public static final String ID = "id";
        public static final String BANK_NAME = "bank_name";
        public static final String PHONE = "phone";
    }
}
