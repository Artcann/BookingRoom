package fr.isep.bookingRoom.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Week {
    Date weekStart;
    Date weekEnd;
    List<Event> weekEvents;

    public Week(Date weekStart, Date weekEnd, List<Event> weekEvents) {
        this.weekStart = weekStart;
        this.weekEnd = weekEnd;
        this.weekEvents =  weekEvents;
    }
}
