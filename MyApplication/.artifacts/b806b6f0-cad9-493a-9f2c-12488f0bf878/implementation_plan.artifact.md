# NGO Health & Management ERP / Prototype - Implementation Plan

This plan outlines the architecture and implementation of a model Android application designed for NGOs to manage vaccines, patients, treatments, posts, chat, and calendar events, supporting both **Administrator** and **Common User** roles with advanced features like audit trails, expiration tracking, and PDF report export simulation.

## User Review Required

> [!IMPORTANT]
> Since this is a comprehensive model/prototype application built with Jetpack Compose (Material 3), we will implement a fully interactive in-memory data flow with role switching (Admin vs. Common User) so you can test all features firsthand.

## Open Questions

- *None at this stage*. The requirements are thorough and cover lists, patient details, treatments with expiration status, posts, chat, calendar, admin audit history, and PDF export simulation.

## Proposed Changes

We will build the app using Jetpack Compose and Material 3, structuring it cleanly into packages:
- `data`: Models and mock repositories (NGOs, Patients, Treatments, Vaccines, Posts, Chat Messages, Audit Logs, Calendar Events).
- `ui`: Jetpack Compose screens and ViewModels for navigation, lists, details, role switcher, admin panel, and chat/calendar.

### Architecture & UI Modules

#### [NEW] [Models & Repository](file:///C:/Users/gonzalo.prada/Desktop/CLASES 2627/Android-Projects/MyApplication/app/src/main/java/com/example/myapplication/data/Models.kt)
- Define data classes for `NGO`, `Patient`, `Treatment` (with types: medicine, vaccine, checkup, surgery, status: active/expired/pending), `Post`, `ChatMessage`, `AuditLog`, `CalendarEvent`, and user profile state.
- Create a reactive mock repository supporting CRUD operations and role switching (Admin / Common User).

#### [NEW] [ViewModel & State Management](file:///C:/Users/gonzalo.prada/Desktop/CLASES 2627/Android-Projects/MyApplication/app/src/main/java/com/example/myapplication/ui/MainViewModel.kt)
- Manage app state: current role (Admin / Common User), selected NGO, patients list, posts feed, chat messages, calendar events, audit history, and notification alerts.

#### [NEW] [UI Screens (Jetpack Compose)](file:///C:/Users/gonzalo.prada/Desktop/CLASES 2627/Android-Projects/MyApplication/app/src/main/java/com/example/myapplication/ui/screens/Screens.kt)
- **Role & NGO Selector / Home**: Switch between Admin and Common User, list NGOs.
- **NGO Dashboard**: Feed of Posts, Patient list, Notifications, Chat room, Calendar.
- **Patient Detail Screen**: Vaccines, treatments list (auto-marked as expired or active by date/type), profile, and patient notifications.
- **Admin Panel**: Audit history (ERP action logs), user verification queue, treatment verification queue, vaccine management.
- **Common User Panel**: Patient registration/discharge, profile editor, alarm/calendar adder.
- **PDF Export Feature**: Generate and view simulated PDF report summary for NGO/patients.

#### [MODIFY] [MainActivity.kt](file:///C:/Users/gonzalo.prada/Desktop/CLASES 2627/Android-Projects/MyApplication/app/src/main/java/com/example/myapplication/MainActivity.kt)
- Setup Compose root content with `MainViewModel` and navigation flow.

## Verification Plan

### Automated Tests
- Build verification via Gradle (`app:assembleDebug`) to ensure zero compilation errors.

### Manual Verification
- Launch app on emulator/device and verify:
  1. Role switching between Administrator and Common User.
  2. NGO list and navigation into NGO dashboard (Posts, Patients, Chat, Calendar).
  3. Patient view with treatment types (Medicine, Vaccine, Checkup, Surgery) and automatic expiration check.
  4. Admin features: Audit trail history, user/treatment verification, alarm/common calendar broadcasting.
  5. Common user features: Patient registration, profile editing, chat messaging.
  6. PDF export simulation.
