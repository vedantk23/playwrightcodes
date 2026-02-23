# Playwright + TestNG Cross-Browser Automation Framework

This project demonstrates UI automation using:

- Java
- Playwright
- TestNG
- Maven
- Cross-browser testing (Chromium, Firefox, WebKit)
- Parallel execution

---

## ⚙️ Prerequisites

Make sure the following are installed:

- Java 17+
- Maven
- Node.js (required by Playwright)
- Playwright browsers

---

## 🔧 Setup Instructions

### 1️⃣ Clone the repository
 `git clone https://github.com/vedantk23/playwrightcodes.git`
 
 `cd playwright-assignment`

---

### 2️⃣ Install Playwright Browsers
`mvn exec:java -e -Dexec.mainClass=com.microsoft.playwright.CLI -Dexec.args="install"`

OR if Playwright is already configured:

`npx playwright install`

---

### 3️⃣ Run Tests (All Browsers)

Make sure you are inside the project root (where `pom.xml` exists).
Run

`mvn clean test`

This will:

- Run tests on Chromium
- Run tests on Firefox
- Run tests on WebKit
- Execute in parallel (as configured in `testng.xml`)

---

## 📸 Screenshots

Screenshots are saved automatically in the project root directory.

---
## To Run Headless set Headless to True as Currently it's false


