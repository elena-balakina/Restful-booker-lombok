package com.restfulbooker.api;

import com.restfulbooker.api.api.AuthApi;
import com.restfulbooker.api.api.BookingApi;
import com.restfulbooker.models.request.Auth;
import com.restfulbooker.models.request.Booking;
import com.restfulbooker.models.request.BookingDates;
import com.restfulbooker.models.response.AuthResponse;
import com.restfulbooker.models.response.BookingResponse;
import io.qameta.allure.*;
import io.restassured.response.Response;
import org.approvaltests.Approvals;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ApiStatusCodeTest {

    @Epic("Booking API")
    @Feature("Get Bookings")
    @Story("Positive Get Bookings")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    @DisplayName("Получение списка бронирований")
    public void getBookingShouldReturn200() {
        Response response = BookingApi.getBookings();
        Approvals.verify(response.getStatusCode());
    }

    @Epic("Booking API")
    @Feature("Get Booking by Id")
    @Story("Positive Get Booking by Id")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    @DisplayName("Получение бронирования по Id")
    public void getBookingIdShouldReturn200() {
        Response response = BookingApi.getBooking(1, "application/json");
        Approvals.verify(response.getStatusCode());
    }

    @Epic("Booking API")
    @Feature("Get Booking by Id")
    @Story("Negative Get Booking by Id")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    @DisplayName("Получение бронирования по Id с невалидным media type")
    public void getBookingIdWithBadAcceptShouldReturn418() {
        Response response = BookingApi.getBooking(1, "text/plain");
        Assertions.assertEquals(418, response.statusCode(), "Expected status code is 418, but found " + response.statusCode());
    }

    @Epic("Booking API")
    @Feature("Post Booking")
    @Story("Positive Post Booking")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    @DisplayName("Создание бронирования с валидными данными")
    public void postBookingReturns200() {
        BookingDates dates = new BookingDates();
        Booking payload = Booking.builder()
                .firstname("Mary")
                .lastname("White")
                .totalprice(200)
                .depositpaid(true)
                .bookingdates(dates)
                .additionalneeds("None")
                .build();

        Response response = BookingApi.postBooking(payload);
        Approvals.verify(response.getStatusCode());
    }

    @Epic("Booking API")
    @Feature("Delete Booking by Id")
    @Story("Positive Delete Booking by Id")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    @DisplayName("Удаление бронирования по Id")
    public void deleteBookingReturns201() {
        BookingDates dates = new BookingDates();
        Booking payload = Booking.builder()
                .firstname("Mary")
                .lastname("White")
                .totalprice(200)
                .depositpaid(true)
                .bookingdates(dates)
                .additionalneeds("None")
                .build();

        BookingResponse createdBookingResponse = BookingApi.postBooking(payload).as(BookingResponse.class);

        Auth auth = Auth.builder()
                .username("admin")
                .password("password123")
                .build();

        AuthResponse authResponse = AuthApi.postAuth(auth).as(AuthResponse.class);

        Response deleteResponse = BookingApi.deleteBooking(
                createdBookingResponse.getBookingid(),
                authResponse.getToken());

        Approvals.verify(deleteResponse.getStatusCode());
    }

}
