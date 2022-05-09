package fr.isep.bookingRoom.domain.model.enums;

public enum EventStatusEnum {
    SUBMITED("submited"),
    IN_REVIEW("in_review"),
    ACCEPTED("accepted"),
    REFUSED("refused"),
    HYPERPLANNING("hyperplanning");

    public final String label;

    EventStatusEnum(String label) {
        this.label = label;
    }
}
