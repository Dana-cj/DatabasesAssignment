package org.exampledana;

import org.exampledana.entity.Accommodation;
import org.exampledana.entity.AccommodationRoomFairRelation;
import org.exampledana.entity.RoomFair;
import org.exampledana.service.AccommodationRoomFairRelationService;
import org.exampledana.service.AccommodationService;
import org.exampledana.service.RoomFairService;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BookingApp {
    public static void main(String[] args) {
        Connection dbConnection = getDbConnection(args);
        AccommodationService accommodationService=new AccommodationService(dbConnection);
        RoomFairService roomFairService=new RoomFairService(dbConnection);
        AccommodationRoomFairRelationService accommodationRoomFairRelationService=new AccommodationRoomFairRelationService(dbConnection);

       //insert new room, new price, new relation
        insertNewAccommodationNewRoomFairAndNewRelation(accommodationService,roomFairService,accommodationRoomFairRelationService,
                "Classic Room",	"King",	2,	"Garden view", 310.0);

        insertNewAccommodationNewRoomFairAndNewRelation(accommodationService,roomFairService,accommodationRoomFairRelationService,
                "Premium Suite",	"King",	2,	"Sea view, Balcony++", 450.0);
        insertNewAccommodationNewRoomFairAndNewRelation(accommodationService,roomFairService,accommodationRoomFairRelationService,
                "Single Room",	"single bed",	1,	"Garden view, Balcony", 150.0);
        insertNewAccommodationNewRoomFairAndNewRelation(accommodationService,roomFairService,accommodationRoomFairRelationService,
                "Classic Room",	"King",	2,	"Garden view", 250.0);
        insertNewAccommodationNewRoomFairAndNewRelation(accommodationService,roomFairService,accommodationRoomFairRelationService,
                "Double Room",	"King",	3,	"Garden view, Balcony", 300.0);
        insertNewAccommodationNewRoomFairAndNewRelation(accommodationService,roomFairService,accommodationRoomFairRelationService,
                "Premium Twin Room",	"2 single beds",	2,	"Sea view, Balcony", 400.0);

       accommodationRoomFairRelationService.printPricesForEachRoom();
    }

    public static Connection getDbConnection(String[] args) {
        try {
            Class.forName("org.postgresql.Driver");
            String url = "jdbc:postgresql://localhost:5432/MyDatabase";
            String username = "postgres";
            String password = args[0];

            return  DriverManager.getConnection(url, username, password);

        } catch (ClassNotFoundException e) {
            System.out.println("Driver not found");
            e.printStackTrace(System.err);
        } catch (SQLException e) {
            System.out.println("Unable to create connection");
            e.printStackTrace(System.err);
        }
        return null;
    }

    public static void insertNewAccommodationNewRoomFairAndNewRelation(AccommodationService accommodationService, RoomFairService roomFairService,
                                                                       AccommodationRoomFairRelationService accommodationRoomFairRelationService,
                                                                       String type, String bedType, int maxGuest, String description, double value){
        Accommodation accommodation= accommodationService.createAccommodation(new Accommodation(accommodationService.nextId(), type,bedType,maxGuest,description));
        RoomFair roomFair1= roomFairService.createRoomFair(new RoomFair(roomFairService.nextId(), value, Season.ON.toString()));
        RoomFair roomFair2=roomFairService.createRoomFair(new RoomFair(roomFairService.nextId(),value,Season.OFF.toString()));
        accommodationRoomFairRelationService.createAccommodationRoomFairRelation(
                new AccommodationRoomFairRelation(accommodationRoomFairRelationService.nextId(), accommodation.getId(), roomFair1.getId())
        );
        accommodationRoomFairRelationService.createAccommodationRoomFairRelation(
                new AccommodationRoomFairRelation(accommodationRoomFairRelationService.nextId(), accommodation.getId(), roomFair2.getId())
        );
    }
}
