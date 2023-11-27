package at.ac.fhstp.doki

import at.ac.fhstp.doki.entries.ModuleEntry
import java.io.Serializable
import java.sql.Timestamp

class Report : Serializable {

    //TODO Add add function
    //TODO second constructor without moduleEntries

    val id: Long
    val name: String
    val longitude: Double
    val latitude: Double
    val timestamp: Timestamp
    var moduleEntries: ArrayList<ModuleEntry>?

    constructor(
        id: Long,
        name: String,
        longitude: Double,
        latitude: Double,
        timestamp: Timestamp,
        moduleEntries: ArrayList<ModuleEntry>?
    ) {
        this.id = id
        this.name = name
        this.longitude = longitude
        this.latitude = latitude
        this.timestamp = timestamp
        this.moduleEntries = moduleEntries
    }

}