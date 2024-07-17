package org.exampledana.entity;

public class Accommodation {
            private int id;
            private String type;
            private String bedType;
            private int maxGuest;
            private String  description;

    public Accommodation(int id, String type, String bedType, int maxGuest, String description) {
        this.id = id;
        this.type = type;
        this.bedType = bedType;
        this.maxGuest = maxGuest;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public String getBedType() {
        return bedType;
    }

    public int getMaxGuest() {
        return maxGuest;
    }

    public String getDescription() {
        return description;
    }


    @Override
    public String toString() {
        return "Accommodation: " +
                "id=" + id +
                ", type='" + type + '\'' +
                ", bedType='" + bedType + '\'' +
                ", maxGuest=" + maxGuest +
                ", description='" + description + '\'' + "\n";
    }
}
