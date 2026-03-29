package org.example._1_cheav_sarin_pp_spring_homework003.Controller;

import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.example._1_cheav_sarin_pp_spring_homework003.Model.enity.Venue;
import org.example._1_cheav_sarin_pp_spring_homework003.Model.response.ApiResponse;
import org.example._1_cheav_sarin_pp_spring_homework003.service.Impl.VenueService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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
    @GetMapping("/{venue-id}")
    public ResponseEntity<ApiResponse<Venue>> getVenueById(
            @PathVariable("venue-id") Integer venueId) {

        Venue venue = venueService.getVenueById(venueId);

        ApiResponse<Venue> response = ApiResponse.<Venue>builder()
                .status("OK")
                .message("Retrieved venue with " + venueId + " successfully")
                .payload(venue)
                .timestamp(LocalDateTime.now())
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}

