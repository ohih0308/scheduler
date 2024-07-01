package com.ohih.scheduler.scheduler;

import com.ohih.scheduler.scheduler.dto.Event;
import com.ohih.scheduler.webConstant.ResponseCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleService {
    private final SchedulerMapper schedulerMapper;


    public Event getEventById(int id) {
        return schedulerMapper.getEventById(id);
    }

    public List<Event> getEventsByMonth(LocalDate localDate) {
        return schedulerMapper.getEventsByMonth(localDate);
    }

    public int creatEvent(Event event) {
        return schedulerMapper.createEvent(event) == 1 ? ResponseCode.EVENT_CREATION_SUCCESS : ResponseCode.EVENT_CREATION_FAILURE;
    }

    public int deleteEventById(Event event) {
        return 0;
    }

    public int modifyEventById(Event event) {
        return 0;
    }
}
