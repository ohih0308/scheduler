package com.ohih.scheduler.scheduler;

import com.ohih.scheduler.scheduler.dto.Event;
import org.apache.ibatis.annotations.Mapper;

import java.time.LocalDate;
import java.util.List;

@Mapper
public interface SchedulerMapper {

    int createEvent(Event event);

    int deleteEventById(Event event);

    int modifyEventById(Event event);

    Event getEventById(int id);

    List<Event> getEventsByMonth(LocalDate localDate);
}
