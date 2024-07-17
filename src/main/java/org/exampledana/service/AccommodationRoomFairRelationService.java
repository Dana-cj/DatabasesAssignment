package org.exampledana.service;

import org.exampledana.entity.AccommodationRoomFairRelation;

import java.sql.*;

public class AccommodationRoomFairRelationService {
    private Connection dbConnection;

    public AccommodationRoomFairRelationService(Connection dbConnection) {
        this.dbConnection = dbConnection;
    }

    public void createAccommodationRoomFairRelation(AccommodationRoomFairRelation accommodationRoomFairRelation){
        String command = "insert into accommodation_room_fair_relation values (?, ?, ?)";
        try {
            PreparedStatement preparedStatement = this.dbConnection.prepareStatement(command);
            preparedStatement.setInt(1,accommodationRoomFairRelation.getId());
            preparedStatement.setInt(2, accommodationRoomFairRelation.getAccommodationId());
            preparedStatement.setInt(3,accommodationRoomFairRelation.getRoomFairId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int nextId() {
        int nextIndex=1;
        try {
            Statement statement = dbConnection.createStatement();
            ResultSet results = statement.executeQuery("select accommodation_room_fair_relation.id\n" +
                    "from accommodation_room_fair_relation\n" +
                    "order by id desc\n" +
                    "limit 1");
            results.next();
            nextIndex=  results.getInt(1)+1;

        } catch (SQLException e) {
            e.printStackTrace(System.err);
        }
        return nextIndex;
    }

    public String printPricesForEachRoom() {
    String result="";
    try {
            Statement statement = dbConnection.createStatement();
            ResultSet clientsResults = statement.executeQuery("SELECT accommodation.type,room_fair.season, room_fair.value\n" +
                    "FROM accommodation_room_fair_relation\n" +
                    "JOIN accommodation\n" +
                    "ON accommodation_room_fair_relation.accommodation_id = accommodation.id\n" +
                    "JOIN room_fair\n" +
                    "ON room_fair.id = accommodation_room_fair_relation.room_fair_id");

            while (clientsResults.next()) {
                result+=(clientsResults.getString(1)+" ("+clientsResults.getString(2).toLowerCase()+"-season)"+": "+clientsResults.getDouble(3)+"\n");
            }
        } catch (SQLException e) {
            e.printStackTrace(System.err);
        }
    //result=(result.isEmpty())?"No accommodation or room fair available":result;
    System.out.println(result);
    return result;
    }

//    public AccommodationRoomFairRelation findById(int id) {
//        AccommodationRoomFairRelation accommodationRoomFairRelation=null;
//        try {
//            PreparedStatement statement = dbConnection.prepareStatement("select * from accommodation_room_fair_relation where id = ?");
//            statement.setInt(1, id);
//            ResultSet result = statement.executeQuery();
//            result.next();
//            accommodationRoomFairRelation=new AccommodationRoomFairRelation(id,
//                    result.getInt(2),
//                    result.getInt(3));
//
//        } catch (SQLException e) {
//            e.printStackTrace(System.err);
//        }
//        return accommodationRoomFairRelation;
//    }
//

//    public String findRoomByRelationId(int accommodationRoomFairRelationId) {
//        int accommodationId= findById(accommodationRoomFairRelationId).getAccommodationId();
//        int roomFairId= findById(accommodationRoomFairRelationId).getRoomFairId();
//        String resultStr="";
//        try {
//            PreparedStatement statement = dbConnection.prepareStatement("SELECT accommodation.type, room_fair.value\n" +
//                    "FROM accommodation_room_fair_relation\n" +
//                    "JOIN accommodation\n" +
//                    "ON  ? = ?  \n" +
//                    "JOIN room_fair\n" +
//                    "ON  ? = ? ");
//            statement.setInt(1, findById(accommodationRoomFairRelationId).getAccommodationId());
//            statement.setInt(2, accommodationId);
//            statement.setInt(3, roomFairId);
//            statement.setInt(4,  findById(accommodationRoomFairRelationId).getRoomFairId());
//            ResultSet result = statement.executeQuery();
//            result.next();
//            resultStr+=(result.getString(1)+": "+result.getDouble(2)+"\n");
//
//        } catch (SQLException e) {
//            e.printStackTrace(System.err);
//        }
//
//        System.out.println(resultStr);
//        return resultStr;
//    }

 //   public Map<String, Double> findPrices() {
//        Map<String, Double> roomsPrices = new HashMap<>();
//        try {
//            Statement statement = dbConnection.createStatement();
//            ResultSet clientsResults = statement.executeQuery("SELECT accommodation.type, room_fair.value\n" +
//                    "FROM accommodation_room_fair_relation\n" +
//                    "JOIN accommodation\n" +
//                    "ON accommodation_room_fair_relation.id = accommodation.id\n" +
//                    "JOIN room_fair\n" +
//                    "ON room_fair.id = accommodation_room_fair_relation.id");
//            while (clientsResults.next()) {
//                roomsPrices.put(clientsResults.getString(1),clientsResults.getDouble(2));
//            }
//        } catch (SQLException e) {
//            e.printStackTrace(System.err);
//        }
//        return roomsPrices;
//    }
}
