package io.orbeon.wrapper.models.form

import io.orbeon.wrapper.models.BaseCompanion
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit


data class Form(
    val operation: String? = "",
    val application: String? = "",
    val name: String? = "",
    val version: String? = null,
    val lastModifiedTime: LocalDateTime? = null,
    val wizardMode: String? = null,
    val dataMigration: String? = null,
    val updatedWithVersion: ArrayList<String?>? = arrayListOf(),
    val wizard: Boolean? = false,
    val available: Boolean? = true,
    val title: ArrayList<TranslateText> = arrayListOf(),
    val description: ArrayList<TranslateText> = arrayListOf(),
    val libraryVersions: Map<String, String>? = null,
    val migration: ArrayList<MigrationText> = arrayListOf(),
    val logo: Logo? = null,
    val email: ArrayList<Email> = arrayListOf(),
) {
    val canonicalName: String
        get() = "${this.application}:${this.name}"

    private var _versionCount = 1
    var versionCount: Int
        get() = _versionCount
        set(value) {
            _versionCount = value
        }

    fun timePassed(timestamp: LocalDateTime): String {
        val now = LocalDateTime.now()

        val days = ChronoUnit.DAYS.between(timestamp.toLocalDate(), now.toLocalDate())
        if (days > 31) {
            val months = ChronoUnit.MONTHS.between(timestamp.toLocalDate(), now.toLocalDate())
            return "$months month(s) ago"
        }
        if (days == 0L) {
            return "less than 24 hours ago"
        }
        if (days == 1L) {
            return "Yesterday"
        }
        return "$days day(s) ago"
    }

    companion object : BaseCompanion<Form>() {
        override fun fromJSON(data: Map<String, Any>): Form {
            @Suppress("UNCHECKED_CAST")
            return Form(
                operation = data["@operations"] as? String,
                application = data["application-name"] as? String,
                name = data["form-name"] as? String,
                version = data["form-version"] as? String,
                lastModifiedTime = LocalDateTime.parse(
                    data["last-modified-time"] as? String,
                    DateTimeFormatter.ISO_DATE_TIME
                ),
                wizardMode = data["wizard-mode"] as? String,
                dataMigration = data["data-migration"] as? String,
                updatedWithVersion = data["updated-with-version"] as? ArrayList<String?>,
                wizard = (data["wizard"] as? String ?: "true").toBoolean(),
                available = (data["available"] as? String ?: "true").toBoolean(),
                title = TranslateText.fromMixedJSON(data["title"]),
                description = TranslateText.fromMixedJSON(data["description"]),
                libraryVersions = data["library-versions"] as? LinkedHashMap<String, String>,
                migration = MigrationText.fromMixedJSON(data["library-versions"]),
                logo = if (data["logo"] != null) Logo.fromJSON(data["logo"] as LinkedHashMap<String, String>) else null,
                email = Email.fromMixedJSON(data["email"])
            )
        }

        override fun fromArray(data: ArrayList<LinkedHashMap<String, String>>): ArrayList<Form> {
            return arrayListOf(*data.map { this.fromJSON(it) }.toTypedArray())
        }
    }
}