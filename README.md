# GuardianLink - NGO Child Welfare & Sponsorship Management System

## 📚 Project Overview
GuardianLink is a JavaFX desktop application demonstrating Object-Oriented Programming principles for a university project. The application showcases proper MVC architecture, inheritance hierarchy, and role-based access control.

## 🏗️ Architecture

### Package Structure
```
com.guardianlink
├── model/
│   ├── user/              (User hierarchy with inheritance)
│   │   ├── User.java (abstract base class)
│   │   ├── UserRole.java (enum)
│   │   ├── SystemAdmin.java
│   │   ├── OrganizationAdmin.java
│   │   ├── Caregiver.java
│   │   ├── Donor.java
│   │   └── Auditor.java
│   └── entity/            (Domain entities)
│       ├── Child.java
│       ├── Organization.java
│       ├── MedicalRecord.java
│       ├── EducationRecord.java
│       └── Donation.java
├── repository/            (Data access layer - in-memory storage)
│   ├── UserRepository.java
│   ├── ChildRepository.java
│   └── DonationRepository.java
├── service/               (Business logic layer)
│   ├── UserService.java
│   ├── ChildService.java
│   ├── DonationService.java
│   └── OrganizationService.java
├── controller/            (Application controllers)
│   ├── AuthController.java
│   ├── DonorController.java
│   └── CaregiverController.java
├── util/                  (Utility classes)
│   ├── PasswordUtil.java
│   └── ValidationUtil.java
├── exception/             (Custom exceptions)
│   ├── UserNotFoundException.java
│   └── InsufficientBalanceException.java
└── GuardianLinkApp.java   (Main JavaFX application)
```

## 🎯 Features

### 1. Login System
- Role-based authentication
- Multiple user types (Admin, Donor, Caregiver, Auditor)
- Session management

### 2. Admin Dashboard (Fully Functional)
✅ **View Dashboard** - Statistics overview
✅ **Manage Children** - Full CRUD operations
- ➕ Add new children
- ✏️ Edit child information
- 🗑️ Delete children
- 📊 View all children in a table
✅ **View Donations** - Complete donation history

### 3. Other User Dashboards
- **Donor Dashboard** - Basic view with donation statistics
- **Caregiver Dashboard** - Organization-based view
- **Auditor Dashboard** - Basic dashboard view

## 🔑 Demo Credentials

### Admin Access (Full functionality)
- **Username:** `admin`
- **Password:** `admin123`

### Other Users (Basic dashboards)
- **Org Admin:** `org1admin` / `pass123`
- **Caregiver:** `caregiver1` / `pass123`
- **Donor:** `donor1` / `pass123`
- **Auditor:** `auditor1` / `pass123`

## 🚀 How to Run

### Prerequisites
- Java 11 or higher
- JavaFX SDK configured in IntelliJ IDEA

### Steps
1. Open the project in IntelliJ IDEA
2. Make sure JavaFX is properly configured:
   - Go to File → Project Structure → Libraries
   - Add JavaFX SDK if not already added
3. Run the main class:
   - Right-click on `GuardianLinkApp.java`
   - Select "Run 'GuardianLinkApp.main()'"
4. Login with admin credentials to see full functionality

### If JavaFX is not configured:
Add VM options to the run configuration:
```
--module-path "PATH_TO_JAVAFX_LIB" --add-modules javafx.controls,javafx.fxml
```

## 🎓 OOP Concepts Demonstrated

### 1. Abstraction
- Abstract `User` class with concrete implementations
- Service interfaces separating business logic

### 2. Inheritance
- User hierarchy: `User` → `SystemAdmin`, `Donor`, `Caregiver`, etc.
- Demonstrates IS-A relationship

### 3. Encapsulation
- Private fields with public getters/setters
- Data validation in utility classes

### 4. Polymorphism
- Abstract method `getDashboardTitle()` overridden in subclasses
- Different dashboard behaviors based on user type

### 5. Design Patterns
- **Singleton Pattern** - Services and Repositories
- **MVC Pattern** - Separation of Model, View, Controller
- **Repository Pattern** - Data access abstraction

## 📊 Data Management

### In-Memory Storage
- No database required for demo
- Data persists only during application runtime
- Pre-populated with sample data:
  - 8 children
  - 5 users (different roles)
  - 8 donation records
  - 3 organizations

### Sample Data
- **Children:** Rahim, Fatima, Karim, Ayesha, Ibrahim, Nadia, Sohel, Riya
- **Organizations:** Hope Foundation, Children's Care BD, Future Leaders
- **Donations:** Various amounts from ৳5,000 to ৳15,000

## 🎨 UI Features
- Clean, professional NGO-style design
- Blue (#2196F3) and Green (#4CAF50) color scheme
- Responsive layouts using JavaFX containers
- Modal dialogs for add/edit operations
- Confirmation dialogs for deletions
- Role-based navigation

## 📝 Project Status
✅ **Completed Features:**
- User authentication
- Role-based access control
- Admin child management (Add/Edit/Delete)
- Dashboard statistics
- Donation tracking
- Clean UI with proper navigation

🚧 **For Future Development:**
- Database integration (JDBC)
- Build tools (Maven/Gradle)
- FXML UI separation
- Medical and Education record management
- Advanced reporting features
- User registration system

## 👨‍💻 Development Notes
- **No FXML:** All UI built programmatically in Java
- **No Database:** In-memory storage using ArrayLists
- **No Build Tools:** Pure Java project for simplicity
- **No External Dependencies:** Only JavaFX required

## 📄 License
Academic project for university coursework demonstration.

---
**GuardianLink** - Demonstrating OOP Excellence in Java 💙
