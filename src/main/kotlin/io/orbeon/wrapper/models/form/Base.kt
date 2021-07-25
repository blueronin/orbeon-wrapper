package io.orbeon.wrapper.models.form


abstract class Base<T> {
    abstract fun fromJSON(data: Map<String, Any>?): T

    abstract fun fromArray(data: ArrayList<LinkedHashMap<String, String>>?): ArrayList<T>

    @Suppress("UNCHECKED_CAST")
    fun fromMixedJSON(data: Any?): ArrayList<T> {
        if (data is ArrayList<*>?) {
            return this.fromArray(data = data as ArrayList<LinkedHashMap<String, String>>?)
        }
        if (data is LinkedHashMap<*, *>?) {
            return arrayListOf(this.fromJSON(data = data as LinkedHashMap<String, String>?))
        }
        return arrayListOf()
    }
}