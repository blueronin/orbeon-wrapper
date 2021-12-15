package io.orbeon.wrapper.entities

import javax.persistence.*

@Entity
@Table(name = "orbeon_form_definition", schema = "orbeon", catalog = "")
open class OrbeonFormDefinitionEntity {
    @get:Basic
    @get:Column(name = "created", nullable = false)
    var created: java.sql.Timestamp? = null

    @get:Basic
    @get:Column(name = "last_modified_time", nullable = false)
    var lastModifiedTime: java.sql.Timestamp? = null

    @get:Basic
    @get:Column(name = "last_modified_by", nullable = true)
    var lastModifiedBy: String? = null

    @get:Basic
    @get:Column(name = "app", nullable = true)
    var app: String? = null

    @get:Basic
    @get:Column(name = "form", nullable = true)
    var form: String? = null

    @get:Basic
    @get:Column(name = "form_version", nullable = false)
    var formVersion: Int? = null

    @get:Basic
    @get:Column(name = "form_metadata", nullable = true)
    var formMetadata: String? = null

    @get:Basic
    @get:Column(name = "deleted", nullable = false)
    var deleted: String? = null

    @get:Basic
    @get:Column(name = "xml", nullable = true)
    var xml: String? = null


    override fun toString(): String =
        "Entity of type: ${javaClass.name} ( " +
                "created = $created " +
                "lastModifiedTime = $lastModifiedTime " +
                "lastModifiedBy = $lastModifiedBy " +
                "app = $app " +
                "form = $form " +
                "formVersion = $formVersion " +
                "formMetadata = $formMetadata " +
                "deleted = $deleted " +
                "xml = $xml " +
                ")"

    // constant value returned to avoid entity inequality to itself before and after it's update/merge
    override fun hashCode(): Int = 42

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        other as OrbeonFormDefinitionEntity

        if (created != other.created) return false
        if (lastModifiedTime != other.lastModifiedTime) return false
        if (lastModifiedBy != other.lastModifiedBy) return false
        if (app != other.app) return false
        if (form != other.form) return false
        if (formVersion != other.formVersion) return false
        if (formMetadata != other.formMetadata) return false
        if (deleted != other.deleted) return false
        if (xml != other.xml) return false

        return true
    }

}

