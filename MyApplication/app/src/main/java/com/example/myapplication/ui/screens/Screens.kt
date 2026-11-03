@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.myapplication.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.data.*
import com.example.myapplication.ui.MainViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NgoAppRoot(viewModel: MainViewModel) {
    val currentRole by viewModel.currentRole.collectAsState()
    val selectedNgoId by viewModel.selectedNgoId.collectAsState()
    val ngos by viewModel.ngos.collectAsState()
    val pdfOutput by viewModel.pdfReportOutput.collectAsState()

    var selectedPatientId by remember { mutableStateOf<String?>(null) }
    var currentTab by remember { mutableStateOf(0) } // 0: Feed/Dashboard, 1: Patients, 2: Chat, 3: Calendar, 4: Admin/User Panel

    val selectedNgo = ngos.find { it.id == selectedNgoId }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        if (pdfOutput != null) {
            PdfReportDialog(pdfOutput!!, onDismiss = { viewModel.clearPdfReport() })
        }

        if (selectedNgoId == null) {
            NgoSelectorScreen(
                ngos = ngos,
                currentRole = currentRole,
                onSelectNgo = { viewModel.selectNgo(it) },
                onToggleRole = { role -> viewModel.setRole(role) },
                onOpenPdf = { viewModel.exportPdfReport() }
            )
        } else if (selectedPatientId != null) {
            val patient = viewModel.patients.collectAsState().value.find { it.id == selectedPatientId }
            if (patient != null) {
                PatientDetailScreen(
                    patient = patient,
                    currentRole = currentRole,
                    onBack = { selectedPatientId = null },
                    onAddTreatment = { title, type, expiry, notes ->
                        viewModel.addTreatment(patient.id, title, type, expiry, notes)
                    },
                    onVerifyTreatment = { treatmentId ->
                        viewModel.verifyTreatment(patient.id, treatmentId)
                    }
                )
            }
        } else {
            Scaffold(
                topBar = {
                    TopAppBar(
                        title = { Text(selectedNgo?.name ?: "NGO Dashboard") },
                        navigationIcon = {
                            IconButton(onClick = { viewModel.selectNgo(null) }) {
                                Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                            }
                        },
                        actions = {
                            // Role Badge
                            BadgeContainer(role = currentRole) {
                                val nextRole = if (currentRole == UserRole.ADMINISTRATOR) UserRole.COMMON_USER else UserRole.ADMINISTRATOR
                                viewModel.setRole(nextRole)
                            }
                            IconButton(onClick = { viewModel.exportPdfReport() }) {
                                Icon(Icons.Default.Share, contentDescription = "Export PDF")
                            }
                        },
                        colors = TopAppBarDefaults.topAppBarColors(
                            containerColor = MaterialTheme.colorScheme.primaryContainer,
                            titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                        )
                    )
                },
                bottomBar = {
                    NavigationBar {
                        NavigationBarItem(
                            selected = currentTab == 0,
                            onClick = { currentTab = 0 },
                            icon = { Icon(Icons.Default.Home, contentDescription = "Feed") },
                            label = { Text("Posts") }
                        )
                        NavigationBarItem(
                            selected = currentTab == 1,
                            onClick = { currentTab = 1 },
                            icon = { Icon(Icons.Default.Person, contentDescription = "Patients") },
                            label = { Text("Patients") }
                        )
                        NavigationBarItem(
                            selected = currentTab == 2,
                            onClick = { currentTab = 2 },
                            icon = { Icon(Icons.Default.Send, contentDescription = "Chat") },
                            label = { Text("Chat") }
                        )
                        NavigationBarItem(
                            selected = currentTab == 3,
                            onClick = { currentTab = 3 },
                            icon = { Icon(Icons.Default.DateRange, contentDescription = "Calendar") },
                            label = { Text("Calendar") }
                        )
                        NavigationBarItem(
                            selected = currentTab == 4,
                            onClick = { currentTab = 4 },
                            icon = { Icon(Icons.Default.Settings, contentDescription = "Panel") },
                            label = { Text(if (currentRole == UserRole.ADMINISTRATOR) "Admin ERP" else "My Profile") }
                        )
                    }
                }
            ) { paddingValues ->
                Box(modifier = Modifier.padding(paddingValues)) {
                    when (currentTab) {
                        0 -> PostsFeedScreen(viewModel = viewModel)
                        1 -> PatientsListScreen(viewModel = viewModel, onSelectPatient = { selectedPatientId = it })
                        2 -> ChatScreen(viewModel = viewModel)
                        3 -> CalendarScreen(viewModel = viewModel)
                        4 -> {
                            if (currentRole == UserRole.ADMINISTRATOR) {
                                AdminPanelScreen(viewModel = viewModel)
                            } else {
                                CommonUserPanelScreen(viewModel = viewModel)
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun NgoSelectorScreen(
    ngos: List<NGO>,
    currentRole: UserRole,
    onSelectNgo: (String) -> Unit,
    onToggleRole: (UserRole) -> Unit,
    onOpenPdf: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(32.dp))
        Icon(
            imageVector = Icons.Default.Favorite,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.primary,
            modifier = Modifier.size(64.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "NGO Health & ERP Manager",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = "Vaccine, Patient & Participant Management",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.secondary
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Role Switcher Card
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text("Current Active Role:", fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = if (currentRole == UserRole.ADMINISTRATOR) "👑 Administrator" else "👤 Common User",
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.primary
                    )
                    Button(onClick = {
                        val newRole = if (currentRole == UserRole.ADMINISTRATOR) UserRole.COMMON_USER else UserRole.ADMINISTRATOR
                        onToggleRole(newRole)
                    }) {
                        Text("Switch Role")
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("Select NGO Organization", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
            OutlinedButton(onClick = onOpenPdf) {
                Icon(Icons.Default.Share, contentDescription = null, modifier = Modifier.size(16.dp))
                Spacer(modifier = Modifier.width(4.dp))
                Text("Export PDF")
            }
        }
        Spacer(modifier = Modifier.height(8.dp))

        LazyColumn(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(ngos) { ngo ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { onSelectNgo(ngo.id) },
                    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(ngo.name, style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(ngo.description, style = MaterialTheme.typography.bodyMedium)
                        Spacer(modifier = Modifier.height(8.dp))
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text("📍 ${ngo.location}", style = MaterialTheme.typography.bodySmall)
                            Text("👥 ${ngo.activePatientsCount} Patients", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.primary)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun PostsFeedScreen(viewModel: MainViewModel) {
    val posts by viewModel.posts.collectAsState()
    var showAddDialog by remember { mutableStateOf(false) }
    var title by remember { mutableStateOf("") }
    var content by remember { mutableStateOf("") }

    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text("Participating Announcements", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
                    Button(onClick = { showAddDialog = true }) {
                        Icon(Icons.Default.Add, contentDescription = null)
                        Spacer(modifier = Modifier.width(4.dp))
                        Text("New Post")
                    }
                }
            }

            items(posts) { post ->
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(post.authorName, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary)
                            Text(post.timestamp, style = MaterialTheme.typography.bodySmall, color = Color.Gray)
                        }
                        Text(post.authorRole, style = MaterialTheme.typography.labelSmall, color = Color.DarkGray)
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(post.title, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.SemiBold)
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(post.content, style = MaterialTheme.typography.bodyMedium)
                    }
                }
            }
        }

        if (showAddDialog) {
            AlertDialog(
                onDismissRequest = { showAddDialog = false },
                title = { Text("Publish Announcement") },
                text = {
                    Column {
                        OutlinedTextField(
                            value = title,
                            onValueChange = { title = it },
                            label = { Text("Post Title") },
                            modifier = Modifier.fillMaxWidth()
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        OutlinedTextField(
                            value = content,
                            onValueChange = { content = it },
                            label = { Text("Content / Update") },
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                },
                confirmButton = {
                    Button(onClick = {
                        if (title.isNotBlank() && content.isNotBlank()) {
                            viewModel.addPost(title, content)
                            title = ""
                            content = ""
                            showAddDialog = false
                        }
                    }) {
                        Text("Post")
                    }
                },
                dismissButton = {
                    TextButton(onClick = { showAddDialog = false }) {
                        Text("Cancel")
                    }
                }
            )
        }
    }
}

@Composable
fun PatientsListScreen(viewModel: MainViewModel, onSelectPatient: (String) -> Unit) {
    val patients by viewModel.patients.collectAsState()
    val currentRole by viewModel.currentRole.collectAsState()
    var showAddPatientDialog by remember { mutableStateOf(false) }

    var name by remember { mutableStateOf("") }
    var age by remember { mutableStateOf("") }
    var gender by remember { mutableStateOf("Female") }
    var condition by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }

    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text("Patients & Medical Registry", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
                    Button(onClick = { showAddPatientDialog = true }) {
                        Icon(Icons.Default.Add, contentDescription = null)
                        Spacer(modifier = Modifier.width(4.dp))
                        Text("Add Patient")
                    }
                }
            }

            items(patients) { patient ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { onSelectPatient(patient.id) },
                    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(patient.name, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
                            Badge(containerColor = if (patient.status == "Active") MaterialTheme.colorScheme.primaryContainer else MaterialTheme.colorScheme.errorContainer) {
                                Text(patient.status, modifier = Modifier.padding(4.dp))
                            }
                        }
                        Spacer(modifier = Modifier.height(4.dp))
                        Text("Age: ${patient.age} | Gender: ${patient.gender}", style = MaterialTheme.typography.bodySmall)
                        Text("Condition: ${patient.condition}", style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.secondary)
                        Spacer(modifier = Modifier.height(8.dp))
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text("Treatments: ${patient.treatments.size}", style = MaterialTheme.typography.bodySmall)
                            Text("Vaccines: ${patient.vaccines.size}", style = MaterialTheme.typography.bodySmall)
                        }
                    }
                }
            }
        }

        if (showAddPatientDialog) {
            AlertDialog(
                onDismissRequest = { showAddPatientDialog = false },
                title = { Text("Register New Patient") },
                text = {
                    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                        OutlinedTextField(value = name, onValueChange = { name = it }, label = { Text("Full Name") }, modifier = Modifier.fillMaxWidth())
                        OutlinedTextField(value = age, onValueChange = { age = it }, label = { Text("Age") }, modifier = Modifier.fillMaxWidth())
                        OutlinedTextField(value = gender, onValueChange = { gender = it }, label = { Text("Gender") }, modifier = Modifier.fillMaxWidth())
                        OutlinedTextField(value = condition, onValueChange = { condition = it }, label = { Text("Medical Condition") }, modifier = Modifier.fillMaxWidth())
                        OutlinedTextField(value = phone, onValueChange = { phone = it }, label = { Text("Phone Number") }, modifier = Modifier.fillMaxWidth())
                    }
                },
                confirmButton = {
                    Button(onClick = {
                        val ageInt = age.toIntOrNull() ?: 30
                        if (name.isNotBlank()) {
                            viewModel.addPatient(name, ageInt, gender, condition, phone)
                            name = ""
                            age = ""
                            condition = ""
                            phone = ""
                            showAddPatientDialog = false
                        }
                    }) {
                        Text("Save Patient")
                    }
                },
                dismissButton = {
                    TextButton(onClick = { showAddPatientDialog = false }) {
                        Text("Cancel")
                    }
                }
            )
        }
    }
}

@Composable
fun PatientDetailScreen(
    patient: Patient,
    currentRole: UserRole,
    onBack: () -> Unit,
    onAddTreatment: (String, TreatmentType, String, String) -> Unit,
    onVerifyTreatment: (String) -> Unit
) {
    var showAddTreatmentDialog by remember { mutableStateOf(false) }
    var treatmentTitle by remember { mutableStateOf("") }
    var treatmentType by remember { mutableStateOf(TreatmentType.MEDICINE) }
    var expiryDate by remember { mutableStateOf("2026-12-31") }
    var notes by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(patient.name) },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                )
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { showAddTreatmentDialog = true }) {
                Icon(Icons.Default.Add, contentDescription = "Add Treatment")
            }
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                Card(modifier = Modifier.fillMaxWidth()) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text("Patient Profile", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
                        Spacer(modifier = Modifier.height(4.dp))
                        Text("Age: ${patient.age} | Gender: ${patient.gender}")
                        Text("Phone: ${patient.phone}")
                        Text("Primary Condition: ${patient.condition}")
                    }
                }
            }

            item {
                Text("Patient Notifications", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
            }
            items(patient.notifications) { note ->
                Card(colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)) {
                    Row(modifier = Modifier.padding(12.dp), verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.Notifications, contentDescription = null, tint = MaterialTheme.colorScheme.primary)
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(note, style = MaterialTheme.typography.bodyMedium)
                    }
                }
            }

            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text("Treatments (Medicine, Vaccines, Checkups, Surgeries)", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
                }
            }

            items(patient.treatments) { treatment ->
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(
                        containerColor = if (treatment.status == TreatmentStatus.EXPIRED)
                            MaterialTheme.colorScheme.errorContainer.copy(alpha = 0.3f)
                        else
                            MaterialTheme.colorScheme.surface
                    )
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(treatment.title, fontWeight = FontWeight.Bold)
                            Badge(
                                containerColor = when (treatment.status) {
                                    TreatmentStatus.ACTIVE -> MaterialTheme.colorScheme.primaryContainer
                                    TreatmentStatus.EXPIRED -> MaterialTheme.colorScheme.errorContainer
                                    TreatmentStatus.PENDING_VERIFICATION -> MaterialTheme.colorScheme.tertiaryContainer
                                    else -> MaterialTheme.colorScheme.surfaceVariant
                                }
                            ) {
                                Text(treatment.status.name, modifier = Modifier.padding(4.dp))
                            }
                        }
                        Spacer(modifier = Modifier.height(4.dp))
                        Text("Type: ${treatment.type} | Expires: ${treatment.expiryDate}", style = MaterialTheme.typography.bodySmall)
                        if (treatment.notes.isNotBlank()) {
                            Text("Notes: ${treatment.notes}", style = MaterialTheme.typography.bodyMedium)
                        }
                        Spacer(modifier = Modifier.height(8.dp))
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = if (treatment.verifiedByAdmin) "✔ Verified by Admin" else "⏳ Pending Admin Verification",
                                style = MaterialTheme.typography.bodySmall,
                                color = if (treatment.verifiedByAdmin) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.error
                            )
                            if (currentRole == UserRole.ADMINISTRATOR && !treatment.verifiedByAdmin) {
                                Button(onClick = { onVerifyTreatment(treatment.id) }) {
                                    Text("Verify")
                                }
                            }
                        }
                    }
                }
            }

            item {
                Text("Vaccine Records", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
            }
            items(patient.vaccines) { vac ->
                Card(modifier = Modifier.fillMaxWidth()) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(vac.vaccineName, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary)
                        Text("Batch: ${vac.batchNumber}", style = MaterialTheme.typography.bodySmall)
                        Text("Administered: ${vac.administeredDate} | Next Dose: ${vac.nextDoseDate}", style = MaterialTheme.typography.bodySmall)
                    }
                }
            }
        }

        if (showAddTreatmentDialog) {
            AlertDialog(
                onDismissRequest = { showAddTreatmentDialog = false },
                title = { Text("Add Treatment / Vaccine") },
                text = {
                    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                        OutlinedTextField(value = treatmentTitle, onValueChange = { treatmentTitle = it }, label = { Text("Treatment Title / Name") }, modifier = Modifier.fillMaxWidth())
                        OutlinedTextField(value = expiryDate, onValueChange = { expiryDate = it }, label = { Text("Expiry Date (YYYY-MM-DD)") }, modifier = Modifier.fillMaxWidth())
                        OutlinedTextField(value = notes, onValueChange = { notes = it }, label = { Text("Dosage / Notes") }, modifier = Modifier.fillMaxWidth())
                    }
                },
                confirmButton = {
                    Button(onClick = {
                        if (treatmentTitle.isNotBlank()) {
                            onAddTreatment(treatmentTitle, treatmentType, expiryDate, notes)
                            treatmentTitle = ""
                            notes = ""
                            showAddTreatmentDialog = false
                        }
                    }) {
                        Text("Add Treatment")
                    }
                },
                dismissButton = {
                    TextButton(onClick = { showAddTreatmentDialog = false }) {
                        Text("Cancel")
                    }
                }
            )
        }
    }
}

@Composable
fun ChatScreen(viewModel: MainViewModel) {
    val messages by viewModel.chatMessages.collectAsState()
    var inputMessage by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text("Participant ERP Chat", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(8.dp))

        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(messages) { msg ->
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
                ) {
                    Column(modifier = Modifier.padding(12.dp)) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(msg.senderName, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary)
                            Text(msg.timestamp, style = MaterialTheme.typography.bodySmall, color = Color.Gray)
                        }
                        Text(msg.senderRole, style = MaterialTheme.typography.labelSmall, color = Color.DarkGray)
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(msg.message, style = MaterialTheme.typography.bodyMedium)
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(8.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            OutlinedTextField(
                value = inputMessage,
                onValueChange = { inputMessage = it },
                label = { Text("Type message...") },
                modifier = Modifier
                    .weight(1f)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Button(onClick = {
                if (inputMessage.isNotBlank()) {
                    viewModel.addChatMessage(inputMessage)
                    inputMessage = ""
                }
            }) {
                Icon(Icons.Default.Send, contentDescription = "Send")
            }
        }
    }
}

@Composable
fun CalendarScreen(viewModel: MainViewModel) {
    val events by viewModel.calendarEvents.collectAsState()
    var showAddDialog by remember { mutableStateOf(false) }

    var title by remember { mutableStateOf("") }
    var date by remember { mutableStateOf("2025-03-01") }
    var time by remember { mutableStateOf("10:00 AM") }
    var desc by remember { mutableStateOf("") }
    var isBroadcast by remember { mutableStateOf(true) }

    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text("Treatments & Events Calendar", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
                    Button(onClick = { showAddDialog = true }) {
                        Icon(Icons.Default.Add, contentDescription = null)
                        Spacer(modifier = Modifier.width(4.dp))
                        Text("Add Event")
                    }
                }
            }

            items(events) { ev ->
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(
                        containerColor = if (ev.isCommonBroadcast)
                            MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.4f)
                        else
                            MaterialTheme.colorScheme.surface
                    )
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(ev.title, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
                            if (ev.isCommonBroadcast) {
                                Badge(containerColor = MaterialTheme.colorScheme.primary) {
                                    Text("Broadcast", modifier = Modifier.padding(4.dp))
                                }
                            }
                        }
                        Spacer(modifier = Modifier.height(4.dp))
                        Text("📅 ${ev.date} at ${ev.time}", style = MaterialTheme.typography.bodySmall, fontWeight = FontWeight.SemiBold)
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(ev.description, style = MaterialTheme.typography.bodyMedium)
                    }
                }
            }
        }

        if (showAddDialog) {
            AlertDialog(
                onDismissRequest = { showAddDialog = false },
                title = { Text("Add Calendar Event / Alarm") },
                text = {
                    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                        OutlinedTextField(value = title, onValueChange = { title = it }, label = { Text("Event Title") }, modifier = Modifier.fillMaxWidth())
                        OutlinedTextField(value = date, onValueChange = { date = it }, label = { Text("Date (YYYY-MM-DD)") }, modifier = Modifier.fillMaxWidth())
                        OutlinedTextField(value = time, onValueChange = { time = it }, label = { Text("Time") }, modifier = Modifier.fillMaxWidth())
                        OutlinedTextField(value = desc, onValueChange = { desc = it }, label = { Text("Description") }, modifier = Modifier.fillMaxWidth())
                    }
                },
                confirmButton = {
                    Button(onClick = {
                        if (title.isNotBlank()) {
                            viewModel.addCalendarEvent(title, date, time, desc, isBroadcast)
                            title = ""
                            desc = ""
                            showAddDialog = false
                        }
                    }) {
                        Text("Save Event")
                    }
                },
                dismissButton = {
                    TextButton(onClick = { showAddDialog = false }) {
                        Text("Cancel")
                    }
                }
            )
        }
    }
}

@Composable
fun AdminPanelScreen(viewModel: MainViewModel) {
    val auditLogs by viewModel.auditLogs.collectAsState()

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        item {
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.errorContainer)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text("👑 Administrator ERP Control Center", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
                    Spacer(modifier = Modifier.height(4.dp))
                    Text("Exclusive Admin Action History (Audit Trail of ERP user actions), treatment verification queues, vaccine inventory control, and common broadcast management.")
                }
            }
        }

        item {
            Text("Audit Trail History (ERP Actions)", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
        }

        items(auditLogs) { log ->
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
            ) {
                Column(modifier = Modifier.padding(12.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(log.actionType, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.error)
                        Text(log.timestamp, style = MaterialTheme.typography.bodySmall, color = Color.Gray)
                    }
                    Text("Admin: ${log.adminName}", style = MaterialTheme.typography.bodySmall)
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(log.description, style = MaterialTheme.typography.bodyMedium)
                }
            }
        }
    }
}

@Composable
fun CommonUserPanelScreen(viewModel: MainViewModel) {
    val profile by viewModel.userProfile.collectAsState()
    var name by remember { mutableStateOf(profile.name) }
    var email by remember { mutableStateOf(profile.email) }
    var phone by remember { mutableStateOf(profile.phone) }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        item {
            Card(modifier = Modifier.fillMaxWidth()) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text("👤 Common User / Volunteer Profile", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
                    Spacer(modifier = Modifier.height(12.dp))
                    OutlinedTextField(
                        value = name,
                        onValueChange = { name = it },
                        label = { Text("Full Name") },
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    OutlinedTextField(
                        value = email,
                        onValueChange = { email = it },
                        label = { Text("Email") },
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    OutlinedTextField(
                        value = phone,
                        onValueChange = { phone = it },
                        label = { Text("Phone Number") },
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Button(onClick = {
                        viewModel.updateProfile(name, email, phone)
                    }) {
                        Text("Update Profile")
                    }
                }
            }
        }
    }
}

@Composable
fun PdfReportDialog(reportText: String, onDismiss: () -> Unit) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("📄 Official PDF Report Export") },
        text = {
            LazyColumn(modifier = Modifier.height(300.dp)) {
                item {
                    Text(text = reportText, style = MaterialTheme.typography.bodyMedium)
                }
            }
        },
        confirmButton = {
            Button(onClick = onDismiss) {
                Text("Close")
            }
        }
    )
}

@Composable
fun BadgeContainer(role: UserRole, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = if (role == UserRole.ADMINISTRATOR) MaterialTheme.colorScheme.errorContainer else MaterialTheme.colorScheme.tertiaryContainer
        )
    ) {
        Text(
            text = if (role == UserRole.ADMINISTRATOR) "👑 Admin" else "👤 User",
            color = Color.Black
        )
    }
}
