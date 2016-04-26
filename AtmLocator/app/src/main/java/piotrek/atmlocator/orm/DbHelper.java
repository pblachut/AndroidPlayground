package piotrek.atmlocator.orm;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

/**
 * Created by Admin on 2016-04-26.
 */
public class DbHelper extends OrmLiteSqliteOpenHelper {
    public DbHelper(Context context) {
        super(context, "atm.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource, Bank.class);
            TableUtils.createTable(connectionSource, Atm.class);

            BankDao bankDao = getDao(Bank.class);

            Bank bank1 = new Bank("ING", "+4832323232");
            Bank bank2 = new Bank("PKO BP", "+4821212121");
            Bank bank3 = new Bank("BGŻ", "+545454543");

            bankDao.create(bank1);
            bankDao.create(bank2);
            bankDao.create(bank3);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {

    }



}
