# Restful-booker API Tests (with Lombok & Allure)

[![Allure Report](https://img.shields.io/badge/Allure-Report-blue?logo=allure&style=for-the-badge)](https://elena-balakina.github.io/Restful-booker-lombok/)

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
