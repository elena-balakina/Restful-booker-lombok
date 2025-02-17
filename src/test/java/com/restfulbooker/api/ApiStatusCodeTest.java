package com.restfulbooker.api;

import com.restfulbooker.api.api.AuthApi;
import com.restfulbooker.api.api.BookingApi;
import com.restfulbooker.models.request.Auth;
import com.restfulbooker.models.request.Booking;
import com.restfulbooker.models.request.BookingDates;
import com.restfulbooker.models.response.AuthResponse;
import com.restfulbooker.models.response.BookingResponse;
import io.restassured.response.Response;
import org.approvaltests.Approvals;
import org.junit.jupiter.api.Test;

public class ApiStatusCodeTest {

    @Test
    public void getBookingShouldReturn200() {
        Response response = BookingApi.getBookings();
        Approvals.verify(response.getStatusCode());
    }

    @Test
    public void getBookingIdShouldReturn200() {
        Response response = BookingApi.getBooking(1, "application/json");
        Approvals.verify(response.getStatusCode());
    }

    @Test
    public void getBookingIdWithBadAcceptShouldReturn418() {
        Response response = BookingApi.getBooking(1, "text/plain");

        Approvals.verify(response.getStatusCode());
    }

    @Test
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

    @Test
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
