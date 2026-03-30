package org.example._1_cheav_sarin_pp_spring_homework003.Controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.javassist.tools.reflect.CannotInvokeException;
import org.example._1_cheav_sarin_pp_spring_homework003.Model.enity.Attendee;
import org.example._1_cheav_sarin_pp_spring_homework003.Model.enity.Venue;
import org.example._1_cheav_sarin_pp_spring_homework003.Model.request.AttendeeRequest;
import org.example._1_cheav_sarin_pp_spring_homework003.Model.request.PaginRequest;
import org.example._1_cheav_sarin_pp_spring_homework003.Model.request.VenueRequest;
import org.example._1_cheav_sarin_pp_spring_homework003.Model.response.ApiResponse;
import org.example._1_cheav_sarin_pp_spring_homework003.exception.BadRequestException;
import org.example._1_cheav_sarin_pp_spring_homework003.exception.NotFoundException;
import org.example._1_cheav_sarin_pp_spring_homework003.exception.ValidationException;
import org.example._1_cheav_sarin_pp_spring_homework003.service.Impl.VenueService;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/venues")
@RequiredArgsConstructor
public class VenueController {
    private final VenueService venueService;
    @GetMapping
    public ResponseEntity<ApiResponse<List<Venue>>> getAllVenue(
            @Valid
            @ParameterObject @ModelAttribute PaginRequest request) {
        Map<String, String> errors = new LinkedHashMap<>();

        if (request.getPage() == null || request.getPage() < 1) {
            errors.put("Page", "must be greater than 0");
        }
        if (request.getSize() == null || request.getSize() < 1) {
            errors.put("Size", "must be greater than 0");
        }
        if (!errors.isEmpty()){
            throw new ValidationException(errors);
        }


        ApiResponse<List<Venue>> response = ApiResponse.<List<Venue>>builder()
                .status("OK")
                .message("Retrieved venue successfully")
                .payload(venueService.getAllVenue(request.getPage(), request.getSize()))
                .timestamp(LocalDateTime.now())
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
    @GetMapping("/{venueId}")
    public ResponseEntity<ApiResponse<Venue>> getVenueById( @PathVariable(name = "venueId") Integer venueId) {
        Venue venue = venueService.getVenueById(venueId);
        if (venue != null) {
            ApiResponse<Venue> response = ApiResponse.<Venue>builder()
                    .status("200")
                    .message("Retrieved venue with  " + venueId + " Successfully")
                    .payload(venue)
                    .timestamp(LocalDateTime.now())
                    .build();
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }
        ApiResponse<Venue> response = ApiResponse.<Venue>builder()
                .status("404")
                .message("Retrieved venue with  -" + venueId + " not found")
                .payload(null)
                .timestamp(LocalDateTime.now())
                .build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }
    @PostMapping
    public ResponseEntity<ApiResponse<Venue>> createVenue(@Valid @RequestBody VenueRequest venueRequest) {
        Venue venue =venueService.createVenue(venueRequest);

        ApiResponse<Venue> response = ApiResponse.<Venue>builder()
                .status("Created")
                .message("Venue created successfully")
                .payload(venue)
                .timestamp(LocalDateTime.now())
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    @PutMapping("/{venueId}")
    public ResponseEntity<ApiResponse<Venue>> updateVenue(
            @PathVariable("venueId") Integer venueId,
            @RequestBody VenueRequest venueRequest) {
        Venue venue = venueService.updateVenue(venueId,venueRequest);
        ApiResponse<Venue> response = ApiResponse.<Venue>builder()
                .status("Created")
                .message("Venue updated successfully")
                .payload(venue)
                .timestamp(LocalDateTime.now())
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
    @DeleteMapping("/{venueId}")
    public ResponseEntity<ApiResponse<String>> deleteVenue(
            @PathVariable("venueId") Integer venueId) {

        if (venueId == null || venueId < 1) {
            Map<String, String> errors = new LinkedHashMap<>();
            errors.put("venueId", "must be greater than 0");
            throw new ValidationException(errors);
        }
        Boolean isSuccess = venueService.deleteVenueById(venueId);
        if (!isSuccess){
            throw new NotFoundException("Venue not found");
        }

        String msg = "Deleted venue with id "+ venueId  +"successfully";
        ApiResponse<String> response = ApiResponse.<String>builder()
                .status("200")
                .message(msg)
                .timestamp(LocalDateTime.now())
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}

