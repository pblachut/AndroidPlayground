package piotrek.atmlocator.orm;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.DatabaseTableConfig;

import java.sql.SQLException;

/**
 * Created by Admin on 2016-04-26.
 */
public class BankDao extends BaseDaoImpl<Bank, Integer> {
    protected BankDao(Class<Bank> dataClass) throws SQLException {
        super(dataClass);
    }

    protected BankDao(ConnectionSource connectionSource, Class<Bank> dataClass) throws SQLException {
        super(connectionSource, dataClass);
    }

    protected BankDao(ConnectionSource connectionSource, DatabaseTableConfig<Bank> tableConfig) throws SQLException {
        super(connectionSource, tableConfig);
    }
}
