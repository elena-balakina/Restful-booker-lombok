package com.restfulbooker.api;

import com.restfulbooker.api.api.AuthApi;
import com.restfulbooker.api.config.Config;
import com.restfulbooker.models.request.Auth;
import io.qameta.allure.*;
import io.restassured.response.Response;
import org.approvaltests.Approvals;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ApiAuthTest {

    @Epic("Booking API")
    @Feature("Authentication")
    @Story("Positive Authentication")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    @DisplayName("Авторизация с валидными данными")
    public void authApiShouldReturnToken() {
        String username = Config.getProperty("username");
        String password = Config.getProperty("password");

        Auth auth = Auth.builder()
                .username(username)
                .password(password)
                .build();
        Response response = AuthApi.postAuth(auth);
        Approvals.verify(response.getStatusCode());
        String actualToken = response.getBody().jsonPath().get("token").toString();
        Assertions.assertFalse(actualToken.isEmpty());
    }

    @Epic("Booking API")
    @Feature("Authentication")
    @Story("Negative Authentication")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    @DisplayName("Авторизация с невалидным паролем")
    public void authApiShouldReturn400ForInvalidPassword() {
        String username = Config.getProperty("username");
        String password = "invalid-password";

        Auth auth = Auth.builder()
                .username(username)
                .password(password)
                .build();
        Response response = AuthApi.postAuth(auth);
        String reasonInResponse = response.getBody().jsonPath().get("reason").toString();
        Assertions.assertEquals("Bad credentials", reasonInResponse);
    }
}
