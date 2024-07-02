package com.ohih.scheduler.scheduler;

import com.ohih.scheduler.scheduler.dto.Event;
import com.ohih.scheduler.scheduler.dto.EventRequest;
import org.apache.ibatis.annotations.Mapper;

import java.time.LocalDate;
import java.util.List;

@Mapper
public interface SchedulerMapper {

    int createEvent(Event event);

    List<EventRequest> getEventsByMonth(LocalDate startOfMonth, LocalDate endOfMonth);
}
