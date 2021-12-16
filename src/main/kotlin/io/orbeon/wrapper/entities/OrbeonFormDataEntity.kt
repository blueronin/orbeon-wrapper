package io.orbeon.wrapper.entities

import io.orbeon.wrapper.annotations.Open
import io.orbeon.wrapper.entities.listeners.PreventAnyUpdate
import javax.persistence.*

@Open
@EntityListeners(PreventAnyUpdate::class)
@Entity
@Table(name = "orbeon_form_data", schema = "orbeon")
class OrbeonFormDataEntity {
    @Id
    @Column(name = "id", nullable = false, insertable = false, updatable = false)
    var id: Int? = null

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

    @Basic
    @Column(name = "stage", nullable = true)
    var stage: String? = null

    @Basic
    @Column(name = "document_id", nullable = true)
    var documentId: String? = null

    @Basic
    @Column(name = "draft", nullable = false)
    var draft: String? = null

    @Basic
    @Column(name = "deleted", nullable = false)
    var deleted: String? = null

    @Basic
    @Column(name = "xml", nullable = true)
    var xml: String? = null

    @OneToMany(mappedBy = "refOrbeonFormDataEntity")
    var refOrbeonIControlTextEntities: MutableList<OrbeonIControlTextEntity> = mutableListOf()

    @OneToMany(mappedBy = "refOrbeonFormDataEntity")
    var refOrbeonICurrentEntities: MutableList<OrbeonICurrentEntity> = mutableListOf()

    override fun toString(): String =
        "Entity of type: ${javaClass.name} ( " +
                "id = $id " +
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
                "deleted = $deleted " +
                "xml = $xml " +
                ")"

    // constant value returned to avoid entity inequality to itself before and after it's update/merge
    override fun hashCode(): Int = 42

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        other as OrbeonFormDataEntity

        if (id != other.id) return false
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
        if (deleted != other.deleted) return false
        if (xml != other.xml) return false

        return true
    }

}

