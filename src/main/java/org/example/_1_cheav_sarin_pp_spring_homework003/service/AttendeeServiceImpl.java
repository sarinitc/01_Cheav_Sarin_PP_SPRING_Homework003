package org.example._1_cheav_sarin_pp_spring_homework003.service;

import lombok.RequiredArgsConstructor;
import org.example._1_cheav_sarin_pp_spring_homework003.Model.enity.Attendee;
import org.example._1_cheav_sarin_pp_spring_homework003.Model.request.AttendeeRequest;
import org.example._1_cheav_sarin_pp_spring_homework003.exception.BadRequestException;
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
if(page == null || size <= 0 ){
    throw new BadRequestException("Page must be grater than 0");
}
if  (size == null || size <=0){
    throw new BadRequestException("Size must be grater than 0");
}
        return attendeeRepository.findAllAttendeeWithPagination(page, size);
    }
    @Override
    public Attendee getAttendeeById(Integer attendeeId) {
        return attendeeRepository.getAttendeeById(attendeeId);
    }

    @Override
    public Attendee createAttendee(AttendeeRequest attendeeRequest) {
        return  attendeeRepository.saveAttendee(attendeeRequest);
    }

    @Override
    public Attendee updateAttendee(Integer attendeeId, AttendeeRequest attendeeRequest) {
        attendeeRepository.updateAttendee(attendeeId, attendeeRequest);
        return attendeeRepository.getAttendeeById(attendeeId);
    }

    @Override
    public Boolean deleteAttendeeById(Integer attendeeId) {
        System.out.println("Delete Attendee ID: " + attendeeId);
        Attendee attendee = attendeeRepository.getAttendeeById(attendeeId);
        if (attendee == null){return false;}
        attendeeRepository.deleteAttendeeById(attendeeId);
        return true;
    }


}
