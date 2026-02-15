package com.fieldwork.offline.domain.model

enum class UserRole {
    PERSONEL,
    TASERON_YONETICI
}

data class User(
    val id: String,
    val phone: String,
    val displayName: String
)

data class Session(
    val token: String,
    val userId: String,
    val activeRole: UserRole,
    val companyId: String,
    val user: User
)

enum class JobStatus {
    ACTIVE, COMPLETED
}

data class Job(
    val id: String,
    val title: String,
    val locationText: String,
    val assignedToUserId: String,
    val status: JobStatus,
    val lastActivityAt: Long
)

enum class MessageSyncStatus {
    PENDING, SYNCING, SYNCED, FAILED
}

data class Message(
    val id: String,
    val jobId: String,
    val text: String,
    val createdAt: Long,
    val createdByUserId: String,
    val syncStatus: MessageSyncStatus,
    val lastError: String? = null
)

enum class PhotoApprovalStatus {
    PENDING, APPROVED, REJECTED
}

enum class PhotoSyncStatus {
    PENDING, SYNCING, SYNCED, FAILED
}

data class PhotoTask(
    val id: String,
    val jobId: String,
    val localPath: String,
    val remoteUrl: String? = null,
    val note: String? = null,
    val takenAt: Long,
    val approvalStatus: PhotoApprovalStatus,
    val syncStatus: PhotoSyncStatus,
    val lastError: String? = null
)

enum class JobEventType {
    PHOTO_UPLOADED,
    MESSAGE_SENT,
    PHOTO_APPROVED,
    PHOTO_REJECTED,
    JOB_COMPLETED
}

enum class JobEventSyncStatus {
    PENDING, SYNCING, SYNCED, FAILED
}

data class JobEvent(
    val id: String,
    val jobId: String,
    val type: JobEventType,
    val createdAt: Long,
    val createdByUserId: String,
    val payloadJson: String,
    val syncStatus: JobEventSyncStatus,
    val lastError: String? = null
)

sealed class TimelineItem {
    data class MessageItem(val message: Message) : TimelineItem()
    data class PhotoItem(val photo: PhotoTask) : TimelineItem()
}