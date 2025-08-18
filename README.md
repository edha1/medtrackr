# 💊 MedTrackr

**MedTrackr** is a full-stack application built with **Spring Boot** (backend) and **React** (frontend) that helps users **save medications and receive reminders** in real time. It demonstrates features such as multithreading, WebSockets, JWT authentication, and email verification.

---

> ⚠️ **Note:** This repository contains a demo version of the project. The full application requires database setup and email configuration. The code is included to showcase architecture, features, and implementation.

## ✨ Features

- **🧑‍💻 User Registration & Authentication**  
  - Secure login and registration using **JWT tokens**.  
  - Email verification via **JavaMail**. *(Requires configuration in `application.properties`.)*  

- **💊 Medication Management**  
  - Save and manage multiple medications concurrently using **multithreading**.  
  - Real-time updates using **WebSockets**.  

- **⏰ Real-Time Reminders**  
  - Notifications and reminders for medications in real-time.  

- **🗄️ Database Integration**  
  - Uses **MySQL** to store user and medication data. *(Requires configuration in `application.properties`.)*  

- **⚡ State Management**  
  - Efficient state handling on the frontend with **React**.  

---

## 🛠️ Technologies Used

- **Backend:** Spring Boot, Java, WebSockets, Multithreading, JavaMail, JWT  
- **Frontend:** React, HTML, CSS, JavaScript  
- **Database:** MySQL  
- **Other:** REST APIs

---

## 🎬 Demo

Here are some screenshots showcasing the app’s interface and functionality:

![Home Page](path/to/login-screenshot.png)  
*Home Page interface*

![Register Page](path/to/login-screenshot.png)  
*Register and login interface*

![Personal Profile Page](path/to/dashboard-screenshot.png)  
*Personal Profile Page to see information*

![Save Medication](path/to/reminder-screenshot.png)  
*Page to save medications*

## ⚠️ Privacy Disclaimer
This project is a demo for educational purposes only. It is **not intended for real-world use** due to the handling of sensitive medication and personal health data.
