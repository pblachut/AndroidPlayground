package piotrek.atmlocator.orm

import android.content.Context
import android.database.sqlite.SQLiteDatabase

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper
import com.j256.ormlite.dao.Dao
import com.j256.ormlite.support.ConnectionSource
import com.j256.ormlite.table.TableUtils

import java.sql.SQLException

/**
 * Created by Admin on 2016-04-26.
 */
class DbHelper(context: Context) : OrmLiteSqliteOpenHelper(context, "atm.db", null, 1) {

    override fun onCreate(database: SQLiteDatabase, connectionSource: ConnectionSource) {
        try {
            TableUtils.createTable(connectionSource, Bank::class.java)
            TableUtils.createTable(connectionSource, Atm::class.java)

            val bankDao = getDao<BankDao, Bank>(Bank::class.java)

            val bank1 = Bank("ING", "+4832323232")
            val bank2 = Bank("PKO BP", "+4821212121")
            val bank3 = Bank("BGŻ", "+545454543")

            bankDao.create(bank1)
            bankDao.create(bank2)
            bankDao.create(bank3)

        } catch (e: SQLException) {
            e.printStackTrace()
        }

    }

    override fun onUpgrade(database: SQLiteDatabase, connectionSource: ConnectionSource, oldVersion: Int, newVersion: Int) {

    }


}
