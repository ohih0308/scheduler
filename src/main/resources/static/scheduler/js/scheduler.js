document.addEventListener('DOMContentLoaded', function () {
    const eventsDataElement = document.getElementById('events-data');
    if (eventsDataElement) {
        try {
            const events = JSON.parse(unescapeHTML(eventsDataElement.innerHTML));
            console.log(events)

            var calendarEl = document.getElementById('calendar');
            var calendar = new FullCalendar.Calendar(calendarEl, {
                initialView: 'dayGridMonth',
                locale: 'ko',
                height: '100%',
                events: events,
                eventContent: function (arg) {
                    let title = arg.event.title;
                    let content = document.createElement('div');
                    content.className = 'fc-event-title fc-st-title';
                    content.style.whiteSpace = 'nowrap';
                    content.style.overflow = 'hidden';
                    content.style.textOverflow = 'ellipsis';
                    content.innerText = title;

                    return {domNodes: [content]};
                },
                dateClick: function (info) {
                    var events = calendar.getEvents();
                    var eventList = events.filter(event => event.start.toISOString().split('T')[0] === info.dateStr);
                    var eventListHtml = eventList.map(event => `
                        <div class="event-item" data-event-id="${event.id}">
                            <h5>${event.title}</h5>
                            <p>${event.start.toLocaleString()} ~ ${event.end.toLocaleString()}</p>
                            <p>${event.extendedProps.description}</p>
                            <button class="btn btn-primary btn-small edit-event" data-event-id="${event.id}">수정</button>
                            <button class="btn btn-danger btn-small delete-event" data-event-id="${event.id}">삭제</button>
                        </div>
                    `).join('');

                    document.getElementById('eventList').innerHTML = eventListHtml;

                    document.querySelectorAll('.edit-event').forEach(button => {
                        button.addEventListener('click', function () {
                            var eventId = this.dataset.eventId;
                            var event = calendar.getEventById(eventId);
                            document.getElementById('newEventTitle').value = event.title;
                            document.getElementById('newEventDescription').value = event.extendedProps.description;
                            document.getElementById('newEventStartDate').value = event.start.toISOString().split('T')[0];
                            document.getElementById('newEventStartTime').value = event.start.toISOString().split('T')[1].substring(0, 5);
                            document.getElementById('newEventEndDate').value = event.end.toISOString().split('T')[0];
                            document.getElementById('newEventEndTime').value = event.end.toISOString().split('T')[1].substring(0, 5);
                            document.getElementById('newEventModalLabel').innerText = '이벤트 수정';
                            document.getElementById('saveNewEvent').dataset.eventId = eventId;
                            var newEventModal = new bootstrap.Modal(document.getElementById('newEventModal'));
                            newEventModal.show();
                        });
                    });

                    document.querySelectorAll('.delete-event').forEach(button => {
                        button.addEventListener('click', function () {
                            var eventId = this.dataset.eventId;
                            var event = calendar.getEventById(eventId);
                            event.remove();
                            var eventDetailModal = bootstrap.Modal.getInstance(document.getElementById('eventDetailModal'));
                            eventDetailModal.hide();
                        });
                    });

                    var eventDetailModal = new bootstrap.Modal(document.getElementById('eventDetailModal'));
                    eventDetailModal.show();
                }
            });

            calendar.render();

            // 새 이벤트 추가 버튼 클릭 시 모달 표시
            document.getElementById('newEventButton').addEventListener('click', function () {
                // 기존 이벤트 상세 모달 닫기
                var eventDetailModal = bootstrap.Modal.getInstance(document.getElementById('eventDetailModal'));
                if (eventDetailModal) {
                    eventDetailModal.hide();
                }

                document.getElementById('newEventForm').reset();
                document.getElementById('newEventModalLabel').innerText = '새 이벤트 작성';
                document.getElementById('saveNewEvent').dataset.eventId = '';
                var newEventModal = new bootstrap.Modal(document.getElementById('newEventModal'));
                newEventModal.show();
            });

            // 새 이벤트 저장 버튼 클릭 시 이벤트 추가
            document.getElementById('saveNewEvent').addEventListener('click', function () {
                var title = document.getElementById('newEventTitle').value;
                var description = document.getElementById('newEventDescription').value;
                var startDate = document.getElementById('newEventStartDate').value;
                var startTime = document.getElementById('newEventStartTime').value;
                var endDate = document.getElementById('newEventEndDate').value;
                var endTime = document.getElementById('newEventEndTime').value;
                var eventId = this.dataset.eventId;

                if (eventId) {
                    var event = calendar.getEventById(eventId);
                    event.setProp('title', title);
                    event.setExtendedProp('description', description);
                    event.setStart(startDate + 'T' + startTime);
                    event.setEnd(endDate + 'T' + endTime);
                } else {
                    calendar.addEvent({
                        id: String(Date.now()),
                        title: title,
                        start: startDate + 'T' + startTime,
                        end: endDate + 'T' + endTime,
                        description: description
                    });
                }

                var newEventModal = bootstrap.Modal.getInstance(document.getElementById('newEventModal'));
                newEventModal.hide();
                document.getElementById('newEventForm').reset();
            });
        } catch (e) {
            console.error("Failed to parse JSON data:", e);
        }
    } else {
        console.error("Failed to find 'events-data' element");
    }
});
