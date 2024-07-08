package com.ohih.scheduler.scheduler;

import com.ohih.scheduler.scheduler.dto.EventCreationRequest;
import com.ohih.scheduler.utility.Utility;
import com.ohih.scheduler.webConstant.ResponseCode;
import com.ohih.scheduler.webConstant.ValidationPattern;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class EventValidator {

    private final Utility utility;

    public List<Integer> validateEvent(EventCreationRequest eventCreationRequest) {
        List<Integer> validationResults = new ArrayList<>();

        setAllDayTimes(eventCreationRequest);

        validationResults.add(validateUserId(eventCreationRequest.getAuthorId()));
        validationResults.add(validateTitle(eventCreationRequest.getTitle()));
        validationResults.add(validateLocation(eventCreationRequest.getLocation()));
        validationResults.add(validateDescription(eventCreationRequest.getDescription()));
        validationResults.add(validateParticipants(eventCreationRequest.getParticipants()));
        validationResults.add(validateDate(eventCreationRequest.getStartDate(), ResponseCode.START_DATE_VALIDATION_ERROR));
        validationResults.add(validateDate(eventCreationRequest.getEndDate(), ResponseCode.END_DATE_VALIDATION_ERROR));
        validationResults.add(validateDateOrder(eventCreationRequest.getStartDate(), eventCreationRequest.getEndDate()));

        if (Boolean.FALSE.equals(eventCreationRequest.getAllDay())) {
            validationResults.add(validateTime(eventCreationRequest.getStartTime(), ResponseCode.START_TIME_VALIDATION_ERROR));
            validationResults.add(validateTime(eventCreationRequest.getEndTime(), ResponseCode.END_TIME_VALIDATION_ERROR));
            validationResults.add(validateTimeOrder(eventCreationRequest.getStartDate(), eventCreationRequest.getStartTime(), eventCreationRequest.getEndDate(), eventCreationRequest.getEndTime()));
        }

        validationResults.add(validateDateTimeNotPast(eventCreationRequest.getStartDate(), eventCreationRequest.getStartTime(), ResponseCode.START_DATE_TIME_PAST_ERROR));
        validationResults.add(validateDateTimeNotPast(eventCreationRequest.getEndDate(), eventCreationRequest.getEndTime(), ResponseCode.END_DATE_TIME_PAST_ERROR));

        return validationResults;
    }

    private int validateUserId(Integer authorId) {
        if (authorId == null) {
            return ResponseCode.AUTHOR_ID_NULL_ERROR;
        }
        return ResponseCode.EVENT_IS_VALIDATED;
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

    private void setAllDayTimes(EventCreationRequest eventCreationRequest) {
        if (Boolean.TRUE.equals(eventCreationRequest.getAllDay())) {
            LocalTime startTime = LocalTime.of(9, 0);
            LocalTime endTime = LocalTime.of(18, 0);
            LocalDateTime now = LocalDateTime.now();
            LocalDateTime eventStartDateTime = LocalDateTime.of(eventCreationRequest.getStartDate(), startTime);

            if (now.isAfter(eventStartDateTime)) {
                eventCreationRequest.setStartTime(now.toLocalTime());
            } else {
                eventCreationRequest.setStartTime(startTime);
            }

            eventCreationRequest.setEndTime(endTime);
        }
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
