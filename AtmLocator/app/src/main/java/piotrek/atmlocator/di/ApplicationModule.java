package piotrek.atmlocator.di;

import android.content.Context;

import java.sql.SQLException;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import piotrek.atmlocator.orm.Atm;
import piotrek.atmlocator.orm.AtmDao;
import piotrek.atmlocator.orm.Bank;
import piotrek.atmlocator.orm.BankDao;
import piotrek.atmlocator.orm.DbHelper;

/**
 * Created by Admin on 2016-04-26.
 */
@Module
public class ApplicationModule {

    private Context context;

    public ApplicationModule(Context context){

        this.context = context;
    }

    @Provides
    @Singleton
    public Context provideContext(){
        return context;
    }

    @Provides
    @Singleton
    public DbHelper provideDbHelper(Context context){
        return new DbHelper(context);
    }

    @Provides
    @Singleton
    public BankDao provideBankDao(DbHelper dbHelper) {
        try {
            return dbHelper.getDao(Bank.class);
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    @Provides
    @Singleton
    public AtmDao provideAtmDao(DbHelper dbHelper) {
        try {
            return dbHelper.getDao(Atm.class);
        } catch (SQLException e) {
            throw new RuntimeException();

        }
    }
}
