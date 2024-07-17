package org.exampledana.entity;

public class AccommodationRoomFairRelation {
    private int id;
    private int accommodationId;
    private int roomFairId;

    public AccommodationRoomFairRelation(int id, int accommodationId, int roomFairId) {
        this.id = id;
        this.accommodationId = accommodationId;
        this.roomFairId = roomFairId;
    }

    public int getId() {
        return id;
    }

    public int getAccommodationId() {
        return accommodationId;
    }

    public int getRoomFairId() {
        return roomFairId;
    }

    @Override
    public String toString() {
        return "AccommodationRoomFairRelation:" +
                "id=" + id +
                ", accommodationId=" + accommodationId +
                ", roomFairId=" + roomFairId +
                "\n";
    }
}
