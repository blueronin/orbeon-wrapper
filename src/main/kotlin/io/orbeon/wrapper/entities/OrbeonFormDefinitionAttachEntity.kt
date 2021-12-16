package io.orbeon.wrapper.entities

import io.orbeon.wrapper.annotations.Open
import javax.persistence.*

@Open
@Entity
@Table(name = "orbeon_form_definition_attach", schema = "orbeon")
open class OrbeonFormDefinitionAttachEntity {
    @get:Id
    @get:Column(name = "id", nullable = false, insertable = false, updatable = false)
    var id: Int? = null

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
    @get:Column(name = "deleted", nullable = false)
    var deleted: String? = null

    @get:Basic
    @get:Column(name = "file_name", nullable = true)
    var fileName: String? = null

    @get:Basic
    @get:Column(name = "file_content", nullable = true)
    var fileContent: ByteArray? = null


    override fun toString(): String =
        "Entity of type: ${javaClass.name} ( " +
                "created = $created " +
                "lastModifiedTime = $lastModifiedTime " +
                "lastModifiedBy = $lastModifiedBy " +
                "app = $app " +
                "form = $form " +
                "formVersion = $formVersion " +
                "deleted = $deleted " +
                "fileName = $fileName " +
                "fileContent = $fileContent " +
                ")"

    // constant value returned to avoid entity inequality to itself before and after it's update/merge
    override fun hashCode(): Int = 42

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        other as OrbeonFormDefinitionAttachEntity

        if (created != other.created) return false
        if (lastModifiedTime != other.lastModifiedTime) return false
        if (lastModifiedBy != other.lastModifiedBy) return false
        if (app != other.app) return false
        if (form != other.form) return false
        if (formVersion != other.formVersion) return false
        if (deleted != other.deleted) return false
        if (fileName != other.fileName) return false
        if (!fileContent.contentEquals(other.fileContent)) return false

        return true
    }

}

