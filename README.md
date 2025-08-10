# 📦 Restful-booker API Tests

[![Build Status](https://github.com/elena-balakina/Restful-booker-lombok/actions/workflows/run-tests.yml/badge.svg)](https://github.com/elena-balakina/Restful-booker-lombok/actions)
[![Allure Report](https://img.shields.io/badge/Allure-Report-blueviolet?logo=allure&style=flat-square)](https://elena-balakina.github.io/Restful-booker-lombok/)

---

Автоматизированные API тесты для [Restful Booker](https://restful-booker.herokuapp.com/) с использованием:
- **Java 17**
- **JUnit 5**
- **Rest-Assured**
- **Lombok**
- **Allure Reports**
- **GitHub Actions (CI)**

---

## 🚀 Как запустить тесты из консоли

```bash
mvn test -Dtest='com.restfulbooker.api.**'

```

---

## ⚙️ CI/CD Pipeline Overview

```mermaid
graph TD;
    Code[🧠 Push Code] --> Test[🧪 Run API Tests];
    Test --> Allure[📊 Generate Allure Report];
    Allure --> Pages[🌐 Publish to GitHub Pages];
    Test --> GH[🔁 Save History to Artifact];
    GH --> Allure;
