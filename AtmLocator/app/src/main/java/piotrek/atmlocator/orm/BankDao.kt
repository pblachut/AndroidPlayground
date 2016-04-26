package piotrek.atmlocator.orm

import com.j256.ormlite.dao.BaseDaoImpl
import com.j256.ormlite.support.ConnectionSource
import com.j256.ormlite.table.DatabaseTableConfig

import java.sql.SQLException

/**
 * Created by Admin on 2016-04-26.
 */
class BankDao : BaseDaoImpl<Bank, Int> {
    @Throws(SQLException::class)
    protected constructor(dataClass: Class<Bank>) : super(dataClass) {
    }

    @Throws(SQLException::class)
    protected constructor(connectionSource: ConnectionSource, dataClass: Class<Bank>) : super(connectionSource, dataClass) {
    }

    @Throws(SQLException::class)
    protected constructor(connectionSource: ConnectionSource, tableConfig: DatabaseTableConfig<Bank>) : super(connectionSource, tableConfig) {
    }
}
