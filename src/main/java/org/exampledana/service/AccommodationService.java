package org.exampledana.service;

import org.exampledana.entity.Accommodation;

import java.sql.*;

public class AccommodationService {

    private Connection dbConnection;

    public AccommodationService(Connection dbConnection) {
        this.dbConnection = dbConnection;
    }

    public Accommodation createAccommodation(Accommodation accommodation){
        String command = "insert into accommodation values (?, ?, ?, ?, ?)";
        try {
            PreparedStatement preparedStatement = this.dbConnection.prepareStatement(command);
            preparedStatement.setInt(1, accommodation.getId());
            preparedStatement.setString(2,accommodation.getType());
            preparedStatement.setString(3, accommodation.getBedType());
            preparedStatement.setInt(4,accommodation.getMaxGuest());
            preparedStatement.setString(5,accommodation.getDescription());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return accommodation;
    }

    public int nextId() {
        int nextIndex=1;
        try {
            Statement statement = dbConnection.createStatement();
            ResultSet results = statement.executeQuery("select accommodation.id\n" +
                    "from accommodation\n" +
                    "order by id desc\n" +
                    "limit 1");
            results.next();
            nextIndex=  results.getInt(1)+1;

        } catch (SQLException e) {
            e.printStackTrace(System.err);
        }
        return nextIndex;
    }

 //   public Accommodation findById(int id) {
//        Accommodation accommodation=null;
//        try {
//            PreparedStatement statement = dbConnection.prepareStatement("select * from accommodation where id = ?");
//            statement.setInt(1, id);
//            ResultSet result = statement.executeQuery();
//            result.next();
//            accommodation=new Accommodation(result.getInt(1),
//                    result.getString(2),
//                    result.getString(3),
//                    result.getInt(4),
//                    result.getString(5));
//        } catch (SQLException e) {
//            e.printStackTrace(System.err);
//        }
//        return accommodation;
//    }

//    public void update(int id, String type, String bedType, int maxGuest, String description) {
//        String insertCommand1 = "update accommodation set type = ? where id = ?";
//        String insertCommand2 = "update accommodation set bedType = ? where id = ?";
//        String insertCommand3 = "update accommodation set maxGuest = ? where id = ?";
//        String insertCommand4 = "update accommodation set description = ? where id = ?";
//        try {//int id, String type, String bedType, int maxGuest, String description
//            PreparedStatement preparedStatement1 = this.dbConnection.prepareStatement(insertCommand1);
//            preparedStatement1.setString(1,type);
//            preparedStatement1.setInt(2, id);
//            preparedStatement1.executeUpdate();
//
//            PreparedStatement preparedStatement2 = this.dbConnection.prepareStatement(insertCommand2);
//            preparedStatement2.setString(1,bedType);
//            preparedStatement2.setInt(2, id);
//            preparedStatement2.executeUpdate();
//
//            PreparedStatement preparedStatement3 = this.dbConnection.prepareStatement(insertCommand3);
//            preparedStatement2.setInt(1,maxGuest);
//            preparedStatement2.setInt(2, id);
//            preparedStatement2.executeUpdate();
//
//            PreparedStatement preparedStatement4 = this.dbConnection.prepareStatement(insertCommand4);
//            preparedStatement2.setString(1,description);
//            preparedStatement2.setInt(2, id);
//            preparedStatement2.executeUpdate();
//
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }
//    public void delete(int id) {
//        String insertCommand = "delete from accommodation where id= ?";
//        try {
//            PreparedStatement preparedStatement = this.dbConnection.prepareStatement(insertCommand);
//            preparedStatement.setInt(1,id);
//            preparedStatement.executeUpdate();
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }
//    public List<Accommodation> findAll() {
//        List<Accommodation> accommodations = new ArrayList<>();
//        try {
//            Statement statement = dbConnection.createStatement();
//            ResultSet results = statement.executeQuery("select * from accommodation");
//            while (results.next()) {
//                accommodations.add(
//                        new Accommodation(
//                                results.getInt(1),
//                                results.getString(2),
//                                results.getString(3),
//                                results.getInt(4),
//                                results.getString(5)
//                        )
//                );
//            }
//        } catch (SQLException e) {
//            e.printStackTrace(System.err);
//        }
//        return accommodations;
//    }
}
