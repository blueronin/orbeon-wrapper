package io.orbeon.wrapper.models.form


data class MigrationText(
    val version: String? = null,
    val text: String? = null,
) {
    companion object : Base<MigrationText>() {
        override fun fromJSON(data: Map<String, Any>?): MigrationText {
            return MigrationText(
                version = data?.get("@version") as? String,
                text = data?.get("#text") as? String,
            )
        }

        override fun fromArray(data: ArrayList<LinkedHashMap<String, String>>?): ArrayList<MigrationText> {
            if (data == null) {
                return arrayListOf()
            }
            return arrayListOf(*data.map { this.fromJSON(it) }.toTypedArray())
        }
    }
}
