package io.orbeon.wrapper.entities

import io.orbeon.wrapper.annotations.Open
import io.orbeon.wrapper.entities.keys.OrbeonFormDataAttachEntityId
import io.orbeon.wrapper.entities.listeners.PreventAnyUpdate
import javax.persistence.*

@Open
@EntityListeners(PreventAnyUpdate::class)
@IdClass(OrbeonFormDataAttachEntityId::class)
@Entity
@Table(name = "orbeon_form_data_attach", schema = "orbeon")
class OrbeonFormDataAttachEntity {

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
    @Column(name = "username", nullable = true)
    var username: String? = null

    @Basic
    @Column(name = "groupname", nullable = true)
    var groupname: String? = null

    @Basic
    @Column(name = "organization_id", nullable = true)
    var organizationId: Int? = null

    @Basic
    @Column(name = "app", nullable = true)
    var app: String? = null

    @Basic
    @Column(name = "form", nullable = true)
    var form: String? = null

    @Basic
    @Column(name = "form_version", nullable = false)
    var formVersion: Int? = null

    @Id
    @Basic
    @Column(name = "document_id", nullable = true)
    var documentId: String? = null

    @Basic
    @Column(name = "draft", nullable = false)
    var draft: String? = null

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
                "username = $username " +
                "groupname = $groupname " +
                "organizationId = $organizationId " +
                "app = $app " +
                "form = $form " +
                "formVersion = $formVersion " +
                "documentId = $documentId " +
                "draft = $draft " +
                "deleted = $deleted " +
                "fileName = $fileName " +
                "fileContent = $fileContent " +
                ")"

    // constant value returned to avoid entity inequality to itself before and after it's update/merge
    override fun hashCode(): Int = 42

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        other as OrbeonFormDataAttachEntity

        if (created != other.created) return false
        if (lastModifiedTime != other.lastModifiedTime) return false
        if (lastModifiedBy != other.lastModifiedBy) return false
        if (username != other.username) return false
        if (groupname != other.groupname) return false
        if (organizationId != other.organizationId) return false
        if (app != other.app) return false
        if (form != other.form) return false
        if (formVersion != other.formVersion) return false
        if (documentId != other.documentId) return false
        if (draft != other.draft) return false
        if (deleted != other.deleted) return false
        if (fileName != other.fileName) return false
        if (!fileContent.contentEquals(other.fileContent)) return false

        return true
    }

}

