package org.example._1_cheav_sarin_pp_spring_homework003.service.Impl;

import jakarta.validation.constraints.Min;
import org.example._1_cheav_sarin_pp_spring_homework003.Model.enity.Attendee;
import org.example._1_cheav_sarin_pp_spring_homework003.Model.enity.Venue;
import org.example._1_cheav_sarin_pp_spring_homework003.Model.request.VenueRequest;

import java.util.List;

public interface VenueService {
    List<Venue> getAllVenue(Integer page, Integer size);

    Venue getVenueById(Integer venueId);

    Venue createVenue(VenueRequest venueRequest);
}
