package org.exampledana.service;

import org.exampledana.entity.RoomFair;

import java.sql.*;

public class RoomFairService {
    private Connection dbConnection;

    public RoomFairService(Connection dbConnection) {
        this.dbConnection = dbConnection;
    }

    public RoomFair createRoomFair(RoomFair roomFair){
        String command = "insert into room_fair values (?, ?, ?)";
        try {
            PreparedStatement preparedStatement = this.dbConnection.prepareStatement(command);
            preparedStatement.setInt(1, roomFair.getId());
            preparedStatement.setDouble(2, roomFair.getValue());
            preparedStatement.setString(3,roomFair.getSeason().toString());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return roomFair;
    }

    public int nextId() {
        int nextIndex=1;
        try {
            Statement statement = dbConnection.createStatement();
            ResultSet results = statement.executeQuery("select room_fair.id\n" +
                    "from room_fair\n" +
                    "order by id desc\n" +
                    "limit 1");
            results.next();
            nextIndex=  results.getInt(1)+1;

        } catch (SQLException e) {
            e.printStackTrace(System.err);
        }
        return nextIndex;
    }

 //   public RoomFair findById(int id) {
//        RoomFair roomFair=null;
//        try {
//            PreparedStatement statement = dbConnection.prepareStatement("select * from room_fair where id = ?");
//            statement.setInt(1, id);
//            ResultSet result = statement.executeQuery();
//            result.next();
//            roomFair=new RoomFair(result.getInt(1),
//                    result.getDouble(2),
//                    result.getString(3));
//
//        } catch (SQLException e) {
//            e.printStackTrace(System.err);
//        }
//        return roomFair;
//    }
//    public void update(int id, double value, String season) {
//        String insertCommand1 = "update room_fair set value = ? where id = ?";
//        String insertCommand2 = "update room_fair set season = ? where id = ?";
//
//        try {//int id, String type, String bedType, int maxGuest, String description
//            PreparedStatement preparedStatement1 = this.dbConnection.prepareStatement(insertCommand1);
//            preparedStatement1.setDouble(1,value);
//            preparedStatement1.setInt(2, id);
//            preparedStatement1.executeUpdate();
//
//            PreparedStatement preparedStatement2 = this.dbConnection.prepareStatement(insertCommand2);
//            preparedStatement2.setString(1,season);
//            preparedStatement2.setInt(2, id);
//            preparedStatement2.executeUpdate();
//
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }
//    public void delete(int id) {
//        String insertCommand = "delete from room_fair where id= ?";
//        try {
//            PreparedStatement preparedStatement = this.dbConnection.prepareStatement(insertCommand);
//            preparedStatement.setInt(1,id);
//            preparedStatement.executeUpdate();
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    public List<RoomFair> findAll() {
//        List<RoomFair> roomFairs = new ArrayList<>();
//        try {
//            Statement statement = dbConnection.createStatement();
//            ResultSet results = statement.executeQuery("select * from room_fair");
//            while (results.next()) {
//                roomFairs.add(
//                        new RoomFair(
//                                results.getInt(1),
//                                results.getDouble(2),
//                                results.getString(3)
//                        )
//                );
//            }
//        } catch (SQLException e) {
//            e.printStackTrace(System.err);
//        }
//        return roomFairs;
//    }
}
