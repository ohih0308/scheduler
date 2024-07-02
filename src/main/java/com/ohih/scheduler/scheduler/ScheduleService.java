package com.ohih.scheduler.scheduler;

import com.ohih.scheduler.scheduler.dto.Event;
import com.ohih.scheduler.scheduler.dto.EventRequest;
import com.ohih.scheduler.webConstant.ResponseCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleService {
    private final SchedulerMapper schedulerMapper;
    private final EventValidator eventValidator;

    public List<Integer> createEvent(Event event) {
        List<Integer> validationResults = eventValidator.validateEvent(event);

        for (int result : validationResults) {
            if (result != ResponseCode.EVENT_IS_VALIDATED) {
                return validationResults;
            }
        }

        if (schedulerMapper.createEvent(event) == 1) {
            validationResults.add(ResponseCode.EVENT_CREATION_SUCCESS);
        } else {
            validationResults.add(ResponseCode.EVENT_CREATION_FAILURE);
        }
        return validationResults;
    }

    public List<EventRequest> getEventsByMonth(LocalDate localDate) {
        LocalDate startOfMonth = localDate.withDayOfMonth(1);
        LocalDate endOfMonth = localDate.withDayOfMonth(localDate.lengthOfMonth());

        return schedulerMapper.getEventsByMonth(startOfMonth, endOfMonth);
    }
}
