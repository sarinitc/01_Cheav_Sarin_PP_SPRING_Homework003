package org.example._1_cheav_sarin_pp_spring_homework003.service;

import lombok.RequiredArgsConstructor;
import org.example._1_cheav_sarin_pp_spring_homework003.Model.enity.Attendee;
import org.example._1_cheav_sarin_pp_spring_homework003.repository.AttendeeRepository;
import org.example._1_cheav_sarin_pp_spring_homework003.service.Impl.AttendeeService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class AttendeeServiceImpl implements AttendeeService {
    private final AttendeeRepository attendeeRepository;


    @Override
    public List<Attendee> getAllAttendee(Integer page, Integer size) {
        return attendeeRepository.findAllAttendeeWithPagination(page, size);
    }
}
