package io.orbeon.wrapper.models.form


data class FrParam(
    val fr: String? = null,
    val type: String? = null,
    val name: String? = null,
    val controlName: String? = null,
) {
    companion object: Base<FrParam>() {
        override fun fromJSON(data: Map<String, Any>?): FrParam {
            return FrParam(
                fr = data?.get("@xmlns:fr") as? String,
                type = data?.get("@type") as? String,
                name = data?.get("fr:name") as? String,
                controlName = data?.get("fr:controlName") as? String,
            )
        }

        override fun fromArray(data: ArrayList<LinkedHashMap<String, String>>?): ArrayList<FrParam> {
            if (data == null) {
                return arrayListOf()
            }
            return arrayListOf(*data.map { this.fromJSON(it) }.toTypedArray())
        }
    }
}
