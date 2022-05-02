package fr.isep.bookingRoom.domain.enums;

public enum RoomTypeEnum {

    APP("app"),
    AMPHI("amphi"),
    COMPUTER_LAB("computer_lab"),
    ELECTRONIC("electronic_lab"),
    CLASSIC("classic");

    public final String label;

    RoomTypeEnum(String label) {
        this.label = label;
    }
}
