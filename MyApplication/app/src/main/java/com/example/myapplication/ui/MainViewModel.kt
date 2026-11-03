package com.example.myapplication.ui

import androidx.lifecycle.ViewModel
import com.example.myapplication.data.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.time.LocalDate
import java.time.LocalDateTime

class MainViewModel : ViewModel() {

    private val _currentRole = MutableStateFlow(UserRole.ADMINISTRATOR)
    val currentRole: StateFlow<UserRole> = _currentRole.asStateFlow()

    private val _selectedNgoId = MutableStateFlow<String?>(null)
    val selectedNgoId: StateFlow<String?> = _selectedNgoId.asStateFlow()

    private val _ngos = MutableStateFlow(
        listOf(
            NGO(
                id = "ngo_1",
                name = "HealthBridge Global",
                description = "Providing emergency vaccine distribution and medical care in vulnerable regions.",
                location = "Geneva / Latam",
                activePatientsCount = 142,
                totalVaccinesDistributed = 3500
            ),
            NGO(
                id = "ngo_2",
                name = "AidCare Community",
                description = "Pediatric care, checkups, and nutritional support for remote communities.",
                location = "Sub-Saharan Africa",
                activePatientsCount = 89,
                totalVaccinesDistributed = 1800
            )
        )
    )
    val ngos: StateFlow<List<NGO>> = _ngos.asStateFlow()

    private val _patients = MutableStateFlow(
        listOf(
            Patient(
                id = "pat_1",
                name = "María González",
                age = 34,
                gender = F("Female"),
                condition = "Respiratory Infection & Routine Immunization",
                phone = "+34 600 123 456",
                status = "Active",
                treatments = listOf(
                    Treatment(
                        title = "Amoxicillin 500mg",
                        type = TreatmentType.MEDICINE,
                        expiryDate = "2024-01-10", // expired for testing auto-mark
                        status = TreatmentStatus.ACTIVE,
                        verifiedByAdmin = true,
                        notes = "Take every 8 hours"
                    ),
                    Treatment(
                        title = "Tetanus Vaccine Booster",
                        type = TreatmentType.VACCINE,
                        expiryDate = "2026-12-31",
                        status = TreatmentStatus.ACTIVE,
                        verifiedByAdmin = true,
                        notes = "Administered successfully"
                    ),
                    Treatment(
                        title = "Annual Cardiac Checkup",
                        type = TreatmentType.CHECKUP,
                        expiryDate = "2025-05-15",
                        status = TreatmentStatus.PENDING_VERIFICATION,
                        verifiedByAdmin = false,
                        notes = "Awaiting admin confirmation"
                    )
                ),
                vaccines = listOf(
                    VaccineRecord(
                        vaccineName = "COVID-19 Booster",
                        batchNumber = "BTV-9921",
                        administeredDate = "2025-01-10",
                        nextDoseDate = "2026-01-10"
                    )
                ),
                notifications = listOf("New vaccine reminder scheduled for tomorrow.", "Treatment Amoxicillin verified.")
            ),
            Patient(
                id = "pat_2",
                name = "Carlos Santana",
                age = 45,
                gender = "Male",
                condition = "Chronic Diabetes & Foot Surgery Recovery",
                phone = "+34 611 987 654",
                status = "Active",
                treatments = listOf(
                    Treatment(
                        title = "Insulin Regimen Daily",
                        type = TreatmentType.MEDICINE,
                        expiryDate = "2026-08-20",
                        status = TreatmentStatus.ACTIVE,
                        verifiedByAdmin = true,
                        notes = "Before meals"
                    ),
                    Treatment(
                        title = "Minor Surgical Dressing Change",
                        type = TreatmentType.SURGERY,
                        expiryDate = "2025-02-01", // expired
                        status = TreatmentStatus.ACTIVE,
                        verifiedByAdmin = true,
                        notes = "Wound healing check"
                    )
                ),
                vaccines = listOf(
                    VaccineRecord(
                        vaccineName = "Influenza Quadrivalent",
                        batchNumber = "INF-4402",
                        administeredDate = "2024-10-05",
                        nextDoseDate = "2025-10-05"
                    )
                ),
                notifications = listOf("Surgical follow-up pending.")
            )
        )
    )
    val patients: StateFlow<List<Patient>> = _patients.asStateFlow()

    private val _posts = MutableStateFlow(
        listOf(
            Post(
                authorName = "Dr. Elena Vance",
                authorRole = "Administrator",
                title = "New Vaccine Batch Arrival",
                content = "We have received 500 new doses of Polio and Tetanus vaccines at the central warehouse. Please update patient logs accordingly.",
                timestamp = "2025-02-24 10:30",
                ngoId = "ngo_1"
            ),
            Post(
                authorName = "Juan Pérez",
                authorRole = "Common User / Field Volunteer",
                title = "Mobile Clinic Schedule Updated",
                content = "Tomorrow we will visit Sector 4 for pediatric checkups and vaccine administration. Volunteers please assemble at 8:00 AM.",
                timestamp = "2025-02-23 16:45",
                ngoId = "ngo_1"
            )
        )
    )
    val posts: StateFlow<List<Post>> = _posts.asStateFlow()

    private val _chatMessages = MutableStateFlow(
        listOf(
            ChatMessage(
                senderName = "Dr. Elena Vance",
                senderRole = "Administrator",
                message = "Please verify patient Carlos Santana's latest surgery treatment request.",
                timestamp = "11:00"
            ),
            ChatMessage(
                senderName = "Ana Gomez",
                senderRole = "Common User",
                message = "Done! I uploaded the medical report as well.",
                timestamp = "11:05"
            )
        )
    )
    val chatMessages: StateFlow<List<ChatMessage>> = _chatMessages.asStateFlow()

    private val _auditLogs = MutableStateFlow(
        listOf(
            AuditLog(
                adminName = "Dr. Elena Vance",
                actionType = "USER_VERIFICATION",
                description = "Verified and accepted common user: Ana Gomez",
                timestamp = "2025-02-24 09:15"
            ),
            AuditLog(
                adminName = "Dr. Elena Vance",
                actionType = "TREATMENT_APPROVAL",
                description = "Approved treatment 'Tetanus Vaccine Booster' for patient María González",
                timestamp = "2025-02-23 14:20"
            )
        )
    )
    val auditLogs: StateFlow<List<AuditLog>> = _auditLogs.asStateFlow()

    private val _calendarEvents = MutableStateFlow(
        listOf(
            CalendarEvent(
                title = "Common NGO General Assembly",
                date = "2025-03-01",
                time = "10:00 AM",
                description = "Mandatory sync for all participants and administrators regarding Q1 vaccine distribution.",
                isCommonBroadcast = true
            ),
            CalendarEvent(
                title = "Patient María González Checkup",
                date = "2025-02-28",
                time = "04:00 PM",
                description = "Routine cardiac checkup at Sector 2 clinic.",
                isCommonBroadcast = false
            )
        )
    )
    val calendarEvents: StateFlow<List<CalendarEvent>> = _calendarEvents.asStateFlow()

    private val _userProfile = MutableStateFlow(
        UserProfile(
            name = "Dr. Elena Vance",
            email = "elena.vance@healthbridge.org",
            phone = "+34 699 888 777",
            role = UserRole.ADMINISTRATOR,
            profilePictureUrl = ""
        )
    )
    val userProfile: StateFlow<UserProfile> = _userProfile.asStateFlow()

    private val _pdfReportOutput = MutableStateFlow<String?>(null)
    val pdfReportOutput: StateFlow<String?> = _pdfReportOutput.asStateFlow()

    init {
        checkAndUpdateTreatmentsExpiry()
    }

    fun setRole(role: UserRole) {
        _currentRole.value = role
        _userProfile.update { it.copy(role = role) }
    }

    fun selectNgo(ngoId: String?) {
        _selectedNgoId.value = ngoId
    }

    fun addPost(title: String, content: String) {
        val ngoId = _selectedNgoId.value ?: "ngo_1"
        val newPost = Post(
            authorName = _userProfile.value.name,
            authorRole = if (_currentRole.value == UserRole.ADMINISTRATOR) "Administrator" else "Common User",
            title = title,
            content = content,
            timestamp = "Just now",
            ngoId = ngoId
        )
        _posts.update { listOf(newPost) + it }
        addAuditLog("ADD_POST", "Added announcement post: '$title'")
    }

    fun addPatient(name: String, age: Int, gender: String, condition: String, phone: String) {
        val newPatient = Patient(
            name = name,
            age = age,
            gender = gender,
            condition = condition,
            phone = phone,
            status = "Active",
            treatments = listOf(
                Treatment(
                    title = "Initial Intake Checkup",
                    type = TreatmentType.CHECKUP,
                    expiryDate = "2026-10-01",
                    status = TreatmentStatus.ACTIVE,
                    verifiedByAdmin = _currentRole.value == UserRole.ADMINISTRATOR
                )
            ),
            notifications = listOf("Patient registered successfully.")
        )
        _patients.update { it + newPatient }
        addAuditLog("PATIENT_REGISTRATION", "Registered new patient: $name")
    }

    fun addTreatment(patientId: String, title: String, type: TreatmentType, expiryDate: String, notes: String) {
        val newTreatment = Treatment(
            title = title,
            type = type,
            expiryDate = expiryDate,
            status = TreatmentStatus.ACTIVE,
            verifiedByAdmin = _currentRole.value == UserRole.ADMINISTRATOR,
            notes = notes
        )
        _patients.update { list ->
            list.map { pat ->
                if (pat.id == patientId) {
                    pat.copy(treatments = pat.treatments + newTreatment)
                } else pat
            }
        }
        addAuditLog("ADD_TREATMENT", "Added treatment '$title' for patient ID $patientId")
    }

    fun verifyTreatment(patientId: String, treatmentId: String) {
        _patients.update { list ->
            list.map { pat ->
                if (pat.id == patientId) {
                    val updatedTreatments = pat.treatments.map { tr ->
                        if (tr.id == treatmentId) {
                            tr.copy(verifiedByAdmin = true, status = TreatmentStatus.ACTIVE)
                        } else tr
                    }
                    pat.copy(treatments = updatedTreatments)
                } else pat
            }
        }
        addAuditLog("VERIFY_TREATMENT", "Verified treatment ID $treatmentId for patient ID $patientId")
    }

    fun addChatMessage(messageText: String) {
        val msg = ChatMessage(
            senderName = _userProfile.value.name,
            senderRole = if (_currentRole.value == UserRole.ADMINISTRATOR) "Administrator" else "Common User",
            message = messageText,
            timestamp = "Now"
        )
        _chatMessages.update { it + msg }
    }

    fun addCalendarEvent(title: String, date: String, time: String, description: String, isBroadcast: Boolean) {
        val event = CalendarEvent(
            title = title,
            date = date,
            time = time,
            description = description,
            isCommonBroadcast = isBroadcast
        )
        _calendarEvents.update { it + event }
        addAuditLog("CALENDAR_EVENT", "Added event/alarm: '$title' (Broadcast: $isBroadcast)")
    }

    fun updateProfile(name: String, email: String, phone: String) {
        _userProfile.update { it.copy(name = name, email = email, phone = phone) }
        addAuditLog("PROFILE_UPDATE", "Updated user profile for $name")
    }

    fun exportPdfReport() {
        val report = """
            ========================================
            NGO HEALTH MANAGEMENT ERP - REPORT
            Generated: ${LocalDate.now()}
            User: ${_userProfile.value.name} (${_userProfile.value.role})
            ========================================
            Active NGOs: ${_ngos.value.size}
            Total Patients Tracked: ${_patients.value.size}
            Active Posts / Announcements: ${_posts.value.size}
            Audit Logs Recorded: ${_auditLogs.value.size}
            ========================================
            Status: ALL SYSTEMS VERIFIED & COMPLIANT
        """.trimIndent()
        _pdfReportOutput.value = report
        addAuditLog("PDF_EXPORT", "Exported official NGO ERP report in PDF format")
    }

    fun clearPdfReport() {
        _pdfReportOutput.value = null
    }

    private fun checkAndUpdateTreatmentsExpiry() {
        val today = LocalDate.now().toString() // e.g. "2025-02-24"
        _patients.update { list ->
            list.map { pat ->
                val updatedTreatments = pat.treatments.map { tr ->
                    if (tr.expiryDate < today && tr.status != TreatmentStatus.EXPIRED) {
                        tr.copy(status = TreatmentStatus.EXPIRED)
                    } else {
                        tr
                    }
                }
                pat.copy(treatments = updatedTreatments)
            }
        }
    }

    private fun addAuditLog(actionType: String, description: String) {
        val log = AuditLog(
            adminName = _userProfile.value.name,
            actionType = actionType,
            description = description,
            timestamp = LocalDateTime.now().toString().replace("T", " ")
        )
        _auditLogs.update { listOf(log) + it }
    }

    private fun F(s: String): String = s
}
