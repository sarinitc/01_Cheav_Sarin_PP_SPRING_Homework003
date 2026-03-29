package org.example._1_cheav_sarin_pp_spring_homework003.Controller;

import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.example._1_cheav_sarin_pp_spring_homework003.Model.enity.Attendee;
import org.example._1_cheav_sarin_pp_spring_homework003.Model.enity.Venue;
import org.example._1_cheav_sarin_pp_spring_homework003.Model.response.ApiResponse;
import org.example._1_cheav_sarin_pp_spring_homework003.service.Impl.AttendeeService;
import org.example._1_cheav_sarin_pp_spring_homework003.service.Impl.VenueService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1/venues")
@RequiredArgsConstructor
public class VenueController {
    private final VenueService venueService;
    @GetMapping
    public ResponseEntity<ApiResponse<List<Venue>>> getAllVenue(
            @RequestParam @Min(value = 1, message = "must be greater than 0") Integer page,
            @RequestParam @Min(value = 1, message = "must be greater than 0") Integer size) {
        ApiResponse<List<Venue>> response = ApiResponse.<List<Venue>>builder()
                .status("OK")
                .message("Retrieved venue successfully")
                .payload(venueService.getAllVenue(page, size))
                .timestamp(LocalDateTime.now())
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
