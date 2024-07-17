package org.exampledana;

import org.exampledana.service.AccommodationRoomFairRelationService;
import org.exampledana.service.AccommodationService;
import org.exampledana.service.RoomFairService;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.sql.*;

public class BookingAppTest {
    @Test
    public void insertingDataWorks(){
        Connection dbConnectionTest = getDbConnection();
        AccommodationService accommodationServiceTest=new AccommodationService(dbConnectionTest);
        RoomFairService roomFairServiceTest=new RoomFairService(dbConnectionTest);
        AccommodationRoomFairRelationService accommodationRoomFairRelationServiceTest=new AccommodationRoomFairRelationService(
                dbConnectionTest);

        BookingApp.insertNewAccommodationNewRoomFairAndNewRelation(accommodationServiceTest,roomFairServiceTest,accommodationRoomFairRelationServiceTest,
                "Classic Room",	"King",	2,	"Garden view", 400.0);

        String accommodation="";
        String roomFair="";
        String idAccommodation="";
        String ids="";
        String relations="";

        //gathering data from DB
        try {
            Statement statement1 = dbConnectionTest.createStatement();
            ResultSet results = statement1.executeQuery("select *\n" +
                    "from accommodation\n" +
                    "order by id desc\n" +
                    "limit 1");
            while(results.next()) {
                idAccommodation += results.getInt(1)+" ";
                accommodation += results.getString(2)+" ";
            }
        } catch (SQLException e) {
            e.printStackTrace(System.err);
        }
        accommodation+="\n";

        try {
            Statement statement1 = dbConnectionTest.createStatement();
            ResultSet results = statement1.executeQuery("select *\n" +
                    "from room_fair\n" +
                    "order by id desc\n" +
                    "limit 2");
            while(results.next()) {
               ids += idAccommodation+ results.getInt(1)+" ";
                roomFair += results.getDouble(2)+" ";

            }
        } catch (SQLException e) {
            e.printStackTrace(System.err);
        }
        roomFair+="\n";

        try {
            Statement statement1 = dbConnectionTest.createStatement();
            ResultSet results = statement1.executeQuery("select *\n" +
                    "from accommodation_room_fair_relation\n" +
                    "order by id desc\n" +
                    "limit 2");
            while(results.next()) {
                relations += results.getInt(2)+" ";
                relations+= results.getInt(3)+" ";
            }
        } catch (SQLException e) {
            e.printStackTrace(System.err);
        }

        Assertions.assertEquals("Classic Room \n", accommodation);
        Assertions.assertEquals("300.0 400.0 \n", roomFair);
        Assertions.assertEquals(ids, relations);

    }
    @Test
    public void printPricesForEachRoomWorks(){
        Connection dbConnectionTest = getDbConnection();
        AccommodationService accommodationServiceTest=new AccommodationService(dbConnectionTest);
        RoomFairService roomFairServiceTest=new RoomFairService(dbConnectionTest);
        AccommodationRoomFairRelationService accommodationRoomFairRelationServiceTest=new AccommodationRoomFairRelationService(
                dbConnectionTest);

   // new test values
        String testType="Double Room";
        double testValue=350.0;
        String printPricesForExistingRooms= accommodationRoomFairRelationServiceTest.printPricesForEachRoom();

        BookingApp.insertNewAccommodationNewRoomFairAndNewRelation(accommodationServiceTest, roomFairServiceTest,
                accommodationRoomFairRelationServiceTest,
                testType,	"King",	2,	"Sea view", testValue);

        Assertions.assertEquals(printPricesForExistingRooms+
                        testType+" (on-season): "+testValue+"\n"
                        +testType+" (off-season): "+testValue*75/100+"\n",
                accommodationRoomFairRelationServiceTest.printPricesForEachRoom());
    }



    public static Connection getDbConnection() {
        try {
            Class.forName("org.postgresql.Driver");
            String url = "jdbc:postgresql://localhost:5432/MyDatabase";
            String username = "postgres";
            String password = "";

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

}
