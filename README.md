# RepliTalker

A dedicated chat and communication application developed exclusively for **Repliforce** members.

## 🚀 About the Project
RepliTalker is a real-time communication platform designed to facilitate interaction and information exchange within the Repliforce organization, ensuring a secure and efficient environment for all members.

## ✨ Latest Features
- **User Authentication:** Secure login system powered by Firebase Authentication (Email/Password).
- **User Profile Persistence:** Automatic creation of user profiles in **Cloud Firestore** upon registration, including username and metadata.
- **User Signup:** New member registration system integrated with Firebase Auth and database persistence.
- **Advanced Home Layout:** Feature-rich home screen with a custom toolbar, user profile logo, and integrated search bar.
- **Navigation System:** Bottom navigation implementation using `TabLayout` combined with **ViewPager** for smooth screen swiping between Home, Search, and User Activity sections.
- **Floating Action Button (FAB):** Quick access button with a feather icon, designed for creating new posts or messages.
- **Home Screen:** A dedicated landing area for authenticated users.
- **Logout Functionality:** Integrated secure sign-out process using Firebase Auth, accessible directly from the home screen.
- **Modern UI:** Built using Material Design 3 and AndroidX components.
- **View Binding:** Implemented for safe and efficient UI interactions.

## 🛠️ Tech Stack
- **Language:** Kotlin
- **UI Architecture:** XML Layouts (AndroidX) & Jetpack Compose integration.
- **Backend:** Firebase (Authentication, Firestore, Storage).
- **Gradle:** Version Catalog implementation for dependency management.
- **Compatibility:** Optimized for Android SDK 35.

## ⚙️ Required Setup
This project requires Firebase services to handle authentication and data. To run it locally:

1. Create a new project in the [Firebase Console](https://console.firebase.com/).
2. Add an Android app to your Firebase project using the package name `com.repliforce.replitalker`.
3. Download the `google-services.json` file provided by Firebase.
4. Move the file into the `app/` directory of this project.
5. Enable **Email/Password** sign-in method in the Firebase Authentication console.
6. (Optional) Configure **Cloud Firestore** and **Firebase Storage** if you plan to extend chat capabilities.

## 📸 Screenshots
*(Coming soon)*

---
*Developed as part of the Repliforce digital infrastructure.*
