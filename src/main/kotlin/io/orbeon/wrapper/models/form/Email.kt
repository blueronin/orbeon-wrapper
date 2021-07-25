package io.orbeon.wrapper.models.form


data class Email(
    val subject: Subject? = null,
) {
    @Suppress("UNCHECKED_CAST")
    companion object : Base<Email>() {
        override fun fromJSON(data: Map<String, Any>?): Email {
            return Email(
                subject = Subject.fromJSON(data?.get("subject") as? Map<String, Any>),
            )
        }

        override fun fromArray(data: ArrayList<LinkedHashMap<String, String>>?): ArrayList<Email> {
            if (data == null) {
                return arrayListOf()
            }
            return arrayListOf(*data.map { this.fromJSON(it) }.toTypedArray())
        }
    }
}
