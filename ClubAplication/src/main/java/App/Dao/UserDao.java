package App.Dao;

/**
 * @author Arbaces Restrepo, Jhogan Viancha
 */

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import App.Config.MYSQLConnection;
import App.Dao.Interfaces.UserDaoInteface;
import App.Dto.GuestDto;
import App.Dto.PartnerDto;

import App.Dto.PersonDto;
import App.Model.User;
import App.Dto.UserDto;
import App.Helper.Helper;

public class UserDao implements UserDaoInteface {

    @Override
    public UserDto findByUserName( UserDto userDto ) throws Exception {
        String query = "SELECT ID, USERNAME, PASSWORD, ROLE, PERSONNID FROM USER WHERE USERNAME = ?";
        PreparedStatement preparedStatement = MYSQLConnection.getConnection().prepareStatement(query);
        preparedStatement.setString( 1, userDto.getUserName() );
        ResultSet resulSet = preparedStatement.executeQuery();
        if (resulSet.next()) {
            User user = new User();
            user.setId( resulSet.getLong("ID") );
            user.setUserName( resulSet.getString("USERNAME") );
            user.setPassword( resulSet.getString("PASSWORD") );
            user.setRole( resulSet.getString("ROLE") );
            user.setPersonId( resulSet.getLong("PERSONNID") );
            resulSet.close();
            preparedStatement.close();
            return Helper.parse(user);
        }
        resulSet.close();
        preparedStatement.close();
        return null;
    }

    @Override
    public UserDto findByPersonId( PersonDto personDto ) throws Exception {
        String query = "SELECT ID, USERNAME, PASSWORD, ROLE, PERSONNID FROM USER WHERE PERSONNID = ?";
        PreparedStatement preparedStatement = MYSQLConnection.getConnection().prepareStatement(query);
        preparedStatement.setString(1, String.valueOf( personDto.getId() ) );
        ResultSet resulSet = preparedStatement.executeQuery();
        if (resulSet.next()) {
            User user = new User();
            user.setId( resulSet.getLong("ID") );
            user.setUserName( resulSet.getString("USERNAME") );
            user.setPassword( resulSet.getString("PASSWORD") );
            user.setRole( resulSet.getString("ROLE") );
            user.setPersonId( resulSet.getLong("PERSONNID") );
            resulSet.close();
            preparedStatement.close();
            return Helper.parse(user);
        }
        resulSet.close();
        preparedStatement.close();
        return null;
    }

    @Override
    public UserDto findByUserId( PartnerDto partnerDto ) throws Exception {
        String query = "SELECT ID, USERNAME, PASSWORD, ROLE, PERSONNID FROM USER WHERE ID = ?";
        PreparedStatement preparedStatement = MYSQLConnection.getConnection().prepareStatement(query);
        preparedStatement.setString(1, String.valueOf( partnerDto.getUserId() ) );
        ResultSet resulSet = preparedStatement.executeQuery();
        if (resulSet.next()) {
            User user = new User();
            user.setId( resulSet.getLong("ID") );
            user.setUserName( resulSet.getString("USERNAME") );
            user.setPassword( resulSet.getString("PASSWORD") );
            user.setRole( resulSet.getString("ROLE") );
            user.setPersonId( resulSet.getLong("PERSONNID") );
            resulSet.close();
            preparedStatement.close();
            return Helper.parse(user);
        }
        resulSet.close();
        preparedStatement.close();
        return null;
    }

    @Override
    public UserDto findByGuestUserId( GuestDto guestDto ) throws Exception {
        String query = "SELECT ID, USERNAME, PASSWORD, ROLE, PERSONNID FROM USER WHERE ID = ?";
        PreparedStatement preparedStatement = MYSQLConnection.getConnection().prepareStatement(query);
        preparedStatement.setString(1, String.valueOf( guestDto.getUserId() ) );
        ResultSet resulSet = preparedStatement.executeQuery();
        if (resulSet.next()) {
            User user = new User();
            user.setId( resulSet.getLong("ID") );
            user.setUserName( resulSet.getString("USERNAME") );
            user.setPassword( resulSet.getString("PASSWORD") );
            user.setRole( resulSet.getString("ROLE") );
            user.setPersonId( resulSet.getLong("PERSONNID") );
            resulSet.close();
            preparedStatement.close();
            return Helper.parse(user);
        }
        resulSet.close();
        preparedStatement.close();
        return null;
    }

    @Override
    public boolean existsByUserName(UserDto userDto) throws Exception {
        String query = "SELECT 1 FROM USER WHERE USERNAME = ?";
        PreparedStatement preparedStatement = MYSQLConnection.getConnection().prepareStatement(query);
        preparedStatement.setString(1, userDto.getUserName());
        ResultSet resulSet = preparedStatement.executeQuery();
        boolean exists = resulSet.next();
        resulSet.close();
        preparedStatement.close();
        return exists;
    }

    @Override
    public void createUser(UserDto userDto) throws Exception {
        User user = Helper.parse(userDto);
        String query = "INSERT INTO USER( USERNAME, PASSWORD, PERSONNID, ROLE ) VALUES (?,?,?,?) ";

        PreparedStatement preparedStatement = MYSQLConnection.getConnection().prepareStatement(query);
        preparedStatement.setString( 1, user.getUserName() );
        preparedStatement.setString( 2, user.getPassword() );
        preparedStatement.setLong( 3, user.getPersonId() );
        preparedStatement.setString( 4, user.getRole() );
        
        preparedStatement.execute();
        preparedStatement.close();
    }
    
    @Override
    public void updatePasswordUser(UserDto userDto) throws Exception {
        User user = Helper.parse(userDto);
        String query = "UPDATE USER SET PASSWORD = ? WHERE ID = ? ";

        PreparedStatement preparedStatement = MYSQLConnection.getConnection().prepareStatement(query);
        preparedStatement.setString( 1, user.getPassword() );
        preparedStatement.setLong( 2, user.getId() );

        preparedStatement.execute();
        preparedStatement.close();
    }

    @Override
    public void updateRoleUser(UserDto userDto) throws Exception {
        User user = Helper.parse(userDto);
        String query = "UPDATE USER SET ROLE = ? WHERE ID = ? ";

        PreparedStatement preparedStatement = MYSQLConnection.getConnection().prepareStatement(query);
        preparedStatement.setString( 1, user.getRole() );
        preparedStatement.setLong( 2, user.getId() );

        preparedStatement.execute();
        preparedStatement.close();
    }

    @Override
    public void deleteUser(UserDto userDto) throws Exception {
        User user = Helper.parse(userDto);
        String query = "DELETE FROM USER WHERE ID = ?";

        PreparedStatement preparedStatement = MYSQLConnection.getConnection().prepareStatement(query);
        preparedStatement.setLong( 1, user.getId() );

        preparedStatement.execute();
        preparedStatement.close();
    }
}
