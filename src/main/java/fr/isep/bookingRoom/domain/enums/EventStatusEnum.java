package fr.isep.bookingRoom.domain.enums;

public enum EventStatusEnum {
    SUBMITED("submited"),
    IN_REVIEW("in_review"),
    ACCEPTED("accepted"),
    REFUSED("refused");

    public final String label;

    EventStatusEnum(String label) {
        this.label = label;
    }
}
