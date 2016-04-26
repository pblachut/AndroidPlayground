package piotrek.atmlocator.orm

import com.j256.ormlite.field.DatabaseField
import com.j256.ormlite.table.DatabaseTable

@DatabaseTable(tableName = "bank", daoClass = BankDao::class)
class Bank() {

    @DatabaseField(generatedId = true, columnName = Columns.ID)
    var id: Int = 0
    @DatabaseField(columnName = Columns.BANK_NAME, canBeNull = false, unique = true)
    var name: String? = null
    @DatabaseField(columnName = Columns.PHONE)
    var phone: String? = null

    override fun toString(): String {
        return name ?: ""
    }

    constructor(name: String, phone: String) : this() {
        this.name = name
        this.phone = phone
    }

    object Columns {
        const val ID = "id"
        const val BANK_NAME = "bank_name"
        const val PHONE = "phone"
    }
}
