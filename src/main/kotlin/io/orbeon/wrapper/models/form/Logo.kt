package io.orbeon.wrapper.models.form


data class Logo(
    val mediaType: String? = null,
    val filename: String? = null,
    val size: String? = null,
) {
    companion object: Base<Logo>() {
        override fun fromJSON(data: Map<String, Any>?): Logo {
            return Logo(
                mediaType = data?.get("@mediatype") as? String,
                filename = data?.get("@filename") as? String,
                size = data?.get("@size") as? String,
            )
        }

        override fun fromArray(data: ArrayList<LinkedHashMap<String, String>>?): ArrayList<Logo> {
            if (data == null) {
                return arrayListOf()
            }
            return arrayListOf(*data.map { this.fromJSON(it) }.toTypedArray())
        }
    }
}
