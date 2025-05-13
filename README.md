
# Telegram Password Manager (Web App)

![Java](https://img.shields.io/badge/Java-21.0.6-blue)  
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-16+-blue)  
![Tomcat](https://img.shields.io/badge/Apache_Tomcat-10.1-red)  

A pet project — a Telegram Web App for generating and securely storing passwords directly within the Telegram mini-app.

## 📌 Key Features  

- 🔐 **Authorization via PIN code** with the ability to change the PIN  
- 🛡️ **Generate complex passwords** with customizable parameters  
- 💾 **Secure storage** of passwords in a database  
- 👁️ **View** saved passwords  
- ❌ **Delete** unwanted passwords  

## ⚙️ Tech Stack  

- **Backend**: Pure Java Servlet (no Spring)  
- **Frontend**: Telegram Web Apps (mini-app)  
- **Database**: PostgreSQL (see init script)
- **Application Server**: Apache Tomcat 10.1  
- **Language**: Java 21.0.6  
- **Build Tool**: Maven  

## ⚠️ Limitations  

Currently not implemented:  
- Telegram data verification using HMAC-SHA-256  

## 🚀 Installation & Setup  

1. Clone the repository:  
   ```bash  
   git clone https://github.com/yourusername/telegram-password-manager.git  
   ```  

2. Import the database schema from `resources.database.initDb.sql`  

3. Configure PostgreSQL connection in the application settings  

4. Build the project:  
   ```bash  
   mvn clean package  
   ```  

5. Deploy the WAR file on Tomcat 10.1+  


## 🔧 Dependencies  

Key dependencies:  
- Jakarta Servlet API  
- JSTL (JavaServer Pages Standard Tag Library)  
- Lombok (for reducing boilerplate code)  

For the full list of dependencies, see [pom.xml](pom.xml)  

## 🎥 Video Demo

Demonstration of the App in Action
![Demo GIF](./demo.gif) 
The video shows:

    Full PIN code workflow (verification, changing)
    Password generation process
    Vault management
    Interface features

## 📝 License  

This project is licensed under the MIT License. See the LICENSE file for details.  
