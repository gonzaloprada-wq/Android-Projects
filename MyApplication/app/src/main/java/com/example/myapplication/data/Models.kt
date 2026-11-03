package com.example.myapplication.data

import java.util.UUID

enum class UserRole {
    ADMINISTRATOR,
    COMMON_USER
}

enum class TreatmentType {
    MEDICINE,
    VACCINE,
    CHECKUP,
    SURGERY
}

enum class TreatmentStatus {
    ACTIVE,
    EXPIRED,
    PENDING_VERIFICATION,
    COMPLETED
}

data class Treatment(
    val id: String = UUID.randomUUID().toString(),
    val title: String,
    val type: TreatmentType,
    val expiryDate: String, // e.g., "2026-03-30" or "2025-10-10"
    val status: TreatmentStatus = TreatmentStatus.ACTIVE,
    val verifiedByAdmin: Boolean = false,
    val notes: String = ""
)

data class VaccineRecord(
    val id: String = UUID.randomUUID().toString(),
    val vaccineName: String,
    val batchNumber: String,
    val administeredDate: String,
    val nextDoseDate: String
)

data class Patient(
    val id: String = UUID.randomUUID().toString(),
    val name: String,
    val age: Int,
    val gender: String,
    val condition: String,
    val phone: String,
    val status: String = "Active", // Active, Discharged
    val treatments: List<Treatment> = emptyList(),
    val vaccines: List<VaccineRecord> = emptyList(),
    val notifications: List<String> = emptyList()
)

data class NGO(
    val id: String = UUID.randomUUID().toString(),
    val name: String,
    val description: String,
    val location: String,
    val activePatientsCount: Int,
    val totalVaccinesDistributed: Int
)

data class Post(
    val id: String = UUID.randomUUID().toString(),
    val authorName: String,
    val authorRole: String,
    val title: String,
    val content: String,
    val timestamp: String,
    val ngoId: String
)

data class ChatMessage(
    val id: String = UUID.randomUUID().toString(),
    val senderName: String,
    val senderRole: String,
    val message: String,
    val timestamp: String
)

data class AuditLog(
    val id: String = UUID.randomUUID().toString(),
    val adminName: String,
    val actionType: String,
    val description: String,
    val timestamp: String
)

data class CalendarEvent(
    val id: String = UUID.randomUUID().toString(),
    val title: String,
    val date: String,
    val time: String,
    val description: String,
    val isCommonBroadcast: Boolean = false
)

data class UserProfile(
    val name: String,
    val email: String,
    val phone: String,
    val role: UserRole,
    val profilePictureUrl: String = ""
)
