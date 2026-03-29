package org.example._1_cheav_sarin_pp_spring_homework003.service;

import lombok.RequiredArgsConstructor;
import org.example._1_cheav_sarin_pp_spring_homework003.Model.enity.Venue;
import org.example._1_cheav_sarin_pp_spring_homework003.exception.BadRequestException;
import org.example._1_cheav_sarin_pp_spring_homework003.repository.VenueRepository;
import org.example._1_cheav_sarin_pp_spring_homework003.service.Impl.VenueService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VenueServiceImpl implements VenueService {

    private final VenueRepository venueRepository;

    @Override
    public List<Venue> getAllVenue(Integer page, Integer size) {
        if (page == null || page <= 0) {
            throw new BadRequestException("Page must be greater than 0");
        }

        if (size == null || size <= 0) {
            throw new BadRequestException("Size must be greater than 0");
        }

        return venueRepository.findAllVenueWithPagination(page, size);
    }

    @Override
    public Venue getVenueById(Integer venueId) {
        return venueRepository.getVenueById(venueId);

    }
}