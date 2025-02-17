package com.restfulbooker.models.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.restfulbooker.models.request.Booking;
import lombok.*;

@Builder
@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class BookingResponse {

    private int bookingid;
    private Booking booking;

}
