package io.orbeon.wrapper.entities

import javax.persistence.*

@Entity
@Table(name = "orbeon_i_current", schema = "orbeon", catalog = "")
open class OrbeonICurrentEntity {
    @get:Basic
    @get:Column(name = "data_id", nullable = false, insertable = false, updatable = false)
    var dataId: Int? = null

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
    @get:Column(name = "username", nullable = true)
    var username: String? = null

    @get:Basic
    @get:Column(name = "groupname", nullable = true)
    var groupname: String? = null

    @get:Basic
    @get:Column(name = "organization_id", nullable = true)
    var organizationId: Int? = null

    @get:Basic
    @get:Column(name = "app", nullable = false)
    var app: String? = null

    @get:Basic
    @get:Column(name = "form", nullable = false)
    var form: String? = null

    @get:Basic
    @get:Column(name = "form_version", nullable = false)
    var formVersion: Int? = null

    @get:Basic
    @get:Column(name = "stage", nullable = true)
    var stage: String? = null

    @get:Basic
    @get:Column(name = "document_id", nullable = false)
    var documentId: String? = null

    @get:Basic
    @get:Column(name = "draft", nullable = false)
    var draft: String? = null

    @get:ManyToOne(fetch = FetchType.LAZY)
    @get:JoinColumn(name = "data_id", referencedColumnName = "id")
    var refOrbeonFormDataEntity: OrbeonFormDataEntity? = null

    override fun toString(): String =
        "Entity of type: ${javaClass.name} ( " +
                "dataId = $dataId " +
                "created = $created " +
                "lastModifiedTime = $lastModifiedTime " +
                "lastModifiedBy = $lastModifiedBy " +
                "username = $username " +
                "groupname = $groupname " +
                "organizationId = $organizationId " +
                "app = $app " +
                "form = $form " +
                "formVersion = $formVersion " +
                "stage = $stage " +
                "documentId = $documentId " +
                "draft = $draft " +
                ")"

    // constant value returned to avoid entity inequality to itself before and after it's update/merge
    override fun hashCode(): Int = 42

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        other as OrbeonICurrentEntity

        if (dataId != other.dataId) return false
        if (created != other.created) return false
        if (lastModifiedTime != other.lastModifiedTime) return false
        if (lastModifiedBy != other.lastModifiedBy) return false
        if (username != other.username) return false
        if (groupname != other.groupname) return false
        if (organizationId != other.organizationId) return false
        if (app != other.app) return false
        if (form != other.form) return false
        if (formVersion != other.formVersion) return false
        if (stage != other.stage) return false
        if (documentId != other.documentId) return false
        if (draft != other.draft) return false

        return true
    }

}

