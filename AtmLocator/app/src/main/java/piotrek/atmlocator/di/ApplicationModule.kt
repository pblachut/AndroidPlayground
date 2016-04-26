package piotrek.atmlocator.di

import android.content.Context

import java.sql.SQLException

import javax.inject.Singleton

import dagger.Module
import dagger.Provides
import piotrek.atmlocator.orm.Atm
import piotrek.atmlocator.orm.AtmDao
import piotrek.atmlocator.orm.Bank
import piotrek.atmlocator.orm.BankDao
import piotrek.atmlocator.orm.DbHelper

/**
 * Created by Admin on 2016-04-26.
 */
@Module
class ApplicationModule(private val context: Context) {

    @Provides
    @Singleton
    fun provideContext(): Context {
        return context
    }

    @Provides
    @Singleton
    fun provideDbHelper(context: Context): DbHelper {
        return DbHelper(context)
    }

    @Provides
    @Singleton
    fun provideBankDao(dbHelper: DbHelper): BankDao {
        try {
            return dbHelper.getDao<BankDao, Bank>(Bank::class.java)
        } catch (e: SQLException) {
            throw RuntimeException()
        }

    }

    @Provides
    @Singleton
    fun provideAtmDao(dbHelper: DbHelper): AtmDao {
        try {
            return dbHelper.getDao<AtmDao, Atm>(Atm::class.java)
        } catch (e: SQLException) {
            throw RuntimeException()

        }

    }
}
