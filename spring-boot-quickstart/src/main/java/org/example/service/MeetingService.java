package org.example.service;

import org.example.entity.Meeting;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MeetingService {

    private final List<Meeting> meetings = new ArrayList<>();

    public Boolean isRoomAvailable(Meeting newMeeting) {
        return meetings.stream()
                .noneMatch(exitingMeeting -> exitingMeeting.isOverlapping(newMeeting));
    }

    public void addMeeting(Meeting meeting){
        meetings.add(meeting);
    }

}
