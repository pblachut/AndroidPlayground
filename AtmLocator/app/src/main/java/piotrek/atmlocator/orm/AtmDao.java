package piotrek.atmlocator.orm;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.DatabaseTableConfig;

import java.sql.SQLException;

/**
 * Created by Admin on 2016-04-26.
 */
public class AtmDao extends BaseDaoImpl<Atm, Integer> {
    protected AtmDao(Class<Atm> dataClass) throws SQLException {
        super(dataClass);
    }

    protected AtmDao(ConnectionSource connectionSource, Class<Atm> dataClass) throws SQLException {
        super(connectionSource, dataClass);
    }

    protected AtmDao(ConnectionSource connectionSource, DatabaseTableConfig<Atm> tableConfig) throws SQLException {
        super(connectionSource, tableConfig);
    }
}
