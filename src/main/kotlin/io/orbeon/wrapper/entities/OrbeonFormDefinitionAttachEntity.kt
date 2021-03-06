package io.orbeon.wrapper.entities

import io.orbeon.wrapper.annotations.Open
import io.orbeon.wrapper.entities.keys.OrbeonFormDefinitionAttachEntityId
import io.orbeon.wrapper.entities.listeners.PreventAnyUpdate
import javax.persistence.*

@Open
@EntityListeners(PreventAnyUpdate::class)
@IdClass(OrbeonFormDefinitionAttachEntityId::class)
@Entity
@Table(name = "orbeon_form_definition_attach", schema = "orbeon")
class OrbeonFormDefinitionAttachEntity {
    @Id
    @Basic
    @Column(name = "created", nullable = false)
    var created: java.sql.Timestamp? = null

    @Basic
    @Column(name = "last_modified_time", nullable = false)
    var lastModifiedTime: java.sql.Timestamp? = null

    @Basic
    @Column(name = "last_modified_by", nullable = true)
    var lastModifiedBy: String? = null

    @Basic
    @Column(name = "app", nullable = true)
    var app: String? = null

    @Basic
    @Column(name = "form", nullable = true)
    var form: String? = null

    @Basic
    @Column(name = "form_version", nullable = false)
    var formVersion: Int? = null

    @Basic
    @Column(name = "deleted", nullable = false)
    var deleted: String? = null

    @Id
    @Basic
    @Column(name = "file_name", nullable = true)
    var fileName: String? = null

    @Basic
    @Column(name = "file_content", nullable = true)
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

