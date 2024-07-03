document.addEventListener('DOMContentLoaded', function () {
    // 'events-data' ID를 가진 요소를 가져옵니다.
    const eventsDataElement = document.getElementById('events-data');

    // 요소가 존재하는지 확인합니다.
    if (eventsDataElement) {
        try {
            // 이벤트 데이터를 HTML의 텍스트 콘텐츠로부터 가져와 JSON으로 파싱합니다.
            const events = JSON.parse(unescapeHTML(eventsDataElement.innerHTML));
            console.log(events); // 파싱된 이벤트 데이터를 콘솔에 출력합니다.

            // 캘린더를 표시할 요소를 가져옵니다.
            var calendarEl = document.getElementById('calendar');

            // FullCalendar 캘린더 인스턴스를 생성합니다.
            var calendar = new FullCalendar.Calendar(calendarEl, {
                initialView: 'dayGridMonth', // 초기 뷰를 'dayGridMonth'로 설정합니다.
                locale: 'ko', // 로케일을 한국어로 설정합니다.
                height: '100%', // 캘린더 높이를 100%로 설정합니다.
                events: events, // 이벤트 데이터를 캘린더에 전달합니다.
                eventContent: function (arg) {
                    // 이벤트 제목을 표시하는 사용자 정의 콘텐츠를 생성합니다.
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
                    // 특정 날짜를 클릭했을 때 해당 날짜의 이벤트 목록을 표시합니다.
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

                    // 수정 버튼 클릭 시 이벤트를 수정하는 모달을 표시합니다.
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

                    // 삭제 버튼 클릭 시 이벤트를 삭제합니다.
                    document.querySelectorAll('.delete-event').forEach(button => {
                        button.addEventListener('click', function () {
                            var eventId = this.dataset.eventId;
                            var event = calendar.getEventById(eventId);
                            event.remove();
                            var eventDetailModal = bootstrap.Modal.getInstance(document.getElementById('eventDetailModal'));
                            eventDetailModal.hide();
                        });
                    });

                    // 이벤트 상세 모달을 표시합니다.
                    var eventDetailModal = new bootstrap.Modal(document.getElementById('eventDetailModal'));
                    eventDetailModal.show();
                }
            });

            // 캘린더를 렌더링합니다.
            calendar.render();

            // 새 이벤트 추가 버튼 클릭 시 모달을 표시합니다.
            document.getElementById('newEventButton').addEventListener('click', function () {
                // 기존 이벤트 상세 모달을 닫습니다.
                var eventDetailModal = bootstrap.Modal.getInstance(document.getElementById('eventDetailModal'));
                if (eventDetailModal) {
                    eventDetailModal.hide();
                }

                // 폼을 리셋하고 모달을 표시합니다.
                document.getElementById('newEventForm').reset();
                document.getElementById('newEventModalLabel').innerText = '새 이벤트 작성';
                document.getElementById('saveNewEvent').dataset.eventId = '';
                var newEventModal = new bootstrap.Modal(document.getElementById('newEventModal'));
                newEventModal.show();
            });

            // 새 이벤트 저장 버튼 클릭 시 이벤트를 추가합니다.
            document.getElementById('saveNewEvent').addEventListener('click', function () {
                var title = document.getElementById('newEventTitle').value;
                var description = document.getElementById('newEventDescription').value;
                var startDate = document.getElementById('newEventStartDate').value;
                var startTime = document.getElementById('newEventStartTime').value;
                var endDate = document.getElementById('newEventEndDate').value;
                var endTime = document.getElementById('newEventEndTime').value;
                var eventId = this.dataset.eventId;

                // 기존 이벤트를 수정하거나 새 이벤트를 추가합니다.
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

                // 모달을 닫고 폼을 리셋합니다.
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

// HTML에서 특수 문자를 이스케이프 해제하는 함수입니다.
function unescapeHTML(html) {
    var doc = new DOMParser().parseFromString(html, 'text/html');
    return doc.documentElement.textContent;
}
