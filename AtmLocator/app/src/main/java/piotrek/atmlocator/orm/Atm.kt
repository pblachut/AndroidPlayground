package piotrek.atmlocator.orm

import com.j256.ormlite.field.DatabaseField
import com.j256.ormlite.table.DatabaseTable
import piotrek.atmlocator.orm.Atm.Columns.ID

/**
 * Created by Admin on 2016-04-26.
 */

@DatabaseTable(tableName = "atm", daoClass = AtmDao::class)
class Atm {
    @DatabaseField(generatedId = true, columnName = ID)
    var id: Int = 0

    // eager loading
    //@DatabaseField(foreign = true, foreignAutoRefresh = true)
    @DatabaseField(foreign = true)
    var bank: Bank? = null
    @DatabaseField(canBeNull = false)
    var longitude: Double = 0.toDouble()
    @DatabaseField(canBeNull = false)
    var latitude: Double = 0.toDouble()
    @DatabaseField(canBeNull = false)
    var address: String? = null

    object Columns {
        const val ID = "id"
    }
}
