package com.ohih.scheduler.scheduler;

import com.ohih.scheduler.scheduler.dto.Event;
import com.ohih.scheduler.scheduler.dto.EventStatus;
import com.ohih.scheduler.utility.Utility;
import com.ohih.scheduler.webConstant.ResponseCode;
import com.ohih.scheduler.webConstant.ValidationPattern;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class EventValidator {

    private final Utility utility;

    public EventValidator(Utility utility) {
        this.utility = utility;
    }

    public List<Integer> validateEvent(Event event) {
        List<Integer> validationResults = new ArrayList<>();

        validationResults.add(validateTitle(event.getTitle()));
        validationResults.add(validateLocation(event.getLocation()));
        validationResults.add(validateDescription(event.getDescription()));
        validationResults.add(validateParticipants(event.getParticipants()));
        validationResults.add(validateDate(event.getStartDate(), ResponseCode.START_DATE_VALIDATION_ERROR));
        validationResults.add(validateDate(event.getEndDate(), ResponseCode.END_DATE_VALIDATION_ERROR));
        validationResults.add(validateDateOrder(event.getStartDate(), event.getEndDate()));
        validationResults.add(validateAllDay(event.getAllDay(), event.getStartTime(), event.getEndTime()));

        if (Boolean.FALSE.equals(event.getAllDay())) {
            validationResults.add(validateTime(event.getStartTime(), ResponseCode.START_TIME_VALIDATION_ERROR));
            validationResults.add(validateTime(event.getEndTime(), ResponseCode.END_TIME_VALIDATION_ERROR));
            validationResults.add(validateTimeOrder(event.getStartDate(), event.getStartTime(), event.getEndDate(), event.getEndTime()));
        }

        validationResults.add(validateStatus(event.getStatus()));

        validationResults.add(validateDateTimeNotPast(event.getStartDate(), event.getStartTime(), ResponseCode.START_DATE_TIME_PAST_ERROR));
        validationResults.add(validateDateTimeNotPast(event.getEndDate(), event.getEndTime(), ResponseCode.END_DATE_TIME_PAST_ERROR));

        return validationResults;
    }

    private int validateTitle(String title) {
        if (!utility.isValidated(ValidationPattern.TITLE_PATTERN, title)) {
            return ResponseCode.TITLE_VALIDATION_ERROR;
        }
        return ResponseCode.EVENT_IS_VALIDATED;
    }

    private int validateLocation(String location) {
        if (!utility.isValidated(ValidationPattern.LOCATION_PATTERN, location)) {
            return ResponseCode.LOCATION_VALIDATION_ERROR;
        }
        return ResponseCode.EVENT_IS_VALIDATED;
    }

    private int validateDescription(String description) {
        if (!utility.isValidated(ValidationPattern.DESCRIPTION_PATTERN, description)) {
            return ResponseCode.DESCRIPTION_VALIDATION_ERROR;
        }
        return ResponseCode.EVENT_IS_VALIDATED;
    }

    private int validateParticipants(List<String> participants) {
        if (participants != null) {
            for (String participant : participants) {
                if (!utility.isValidated(ValidationPattern.PARTICIPANT_PATTERN, participant)) {
                    return ResponseCode.PARTICIPANTS_VALIDATION_ERROR;
                }
            }
        }
        return ResponseCode.EVENT_IS_VALIDATED;
    }

    private int validateDate(LocalDate date, int errorCode) {
        if (date == null) {
            return errorCode;
        }
        return ResponseCode.EVENT_IS_VALIDATED;
    }

    private int validateDateOrder(LocalDate startDate, LocalDate endDate) {
        if (startDate.isAfter(endDate)) {
            return ResponseCode.DATE_ORDER_VALIDATION_ERROR;
        }
        return ResponseCode.EVENT_IS_VALIDATED;
    }

    private int validateTime(LocalTime time, int errorCode) {
        if (time == null) {
            return errorCode;
        }
        return ResponseCode.EVENT_IS_VALIDATED;
    }

    private int validateTimeOrder(LocalDate startDate, LocalTime startTime, LocalDate endDate, LocalTime endTime) {
        if (startDate.isEqual(endDate) && startTime.isAfter(endTime)) {
            return ResponseCode.TIME_ORDER_VALIDATION_ERROR;
        }
        return ResponseCode.EVENT_IS_VALIDATED;
    }

    private int validateAllDay(Boolean allDay, LocalTime startTime, LocalTime endTime) {
        if (Boolean.TRUE.equals(allDay)) {
            if (startTime != null || endTime != null) {
                return ResponseCode.ALLDAY_TIME_VALIDATION_ERROR;
            }
        }
        return ResponseCode.EVENT_IS_VALIDATED;
    }

    private int validateStatus(EventStatus status) {
        if (status == null) {
            return ResponseCode.STATUS_VALIDATION_ERROR;
        }
        return ResponseCode.EVENT_IS_VALIDATED;
    }

    private int validateDateTimeNotPast(LocalDate date, LocalTime time, int errorCode) {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime dateTime = LocalDateTime.of(date, (time != null) ? time : LocalTime.MIN);
        if (dateTime.isBefore(now)) {
            return errorCode;
        }
        return ResponseCode.EVENT_IS_VALIDATED;
    }
}
