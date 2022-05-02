package fr.isep.bookingRoom.domain.enums;

public enum RoomCampusEnum {

    NDC("ndc"),
    NDL("ndl");

    public final String label;

    RoomCampusEnum(String label) {
        this.label = label;
    }
}
