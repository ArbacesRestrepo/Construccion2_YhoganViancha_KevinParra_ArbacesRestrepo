package App.Dao;

/**
 * @author Arbaces Restrepo, Jhogan Viancha
 */

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import App.Config.MYSQLConnection;
import App.Dao.Interfaces.UserDao;
import App.Dto.UserDto;
import App.Helper.Helper;
import App.Model.Person;
import App.Model.User;

public class UserDaoImplementation implements UserDao {

    @Override
    public UserDto findByUserName(UserDto userDto) throws Exception {
        String query = "SELECT ID, USERNAME, PASSWORD, ROLE, PERSONNID FROM USER WHERE USERNAME = ?";
        PreparedStatement preparedStatement = MYSQLConnection.getConnection().prepareStatement(query);
        preparedStatement.setString(1, userDto.getUserName());
        ResultSet resulSet = preparedStatement.executeQuery();
        if (resulSet.next()) {
                User user = new User();
                user.setUserId(resulSet.getLong("ID"));
                user.setUserName(resulSet.getString("USERNAME"));
                user.setPassword(resulSet.getString("PASSWORD"));
                user.setRole(resulSet.getString("ROLE"));
                Person person = new Person();
                person.setDocument(resulSet.getLong("PERSONNID"));
                user.setPerson(person);
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
        String query = "INSERT INTO USER( USERNAME, PASSWORD, PERSONID, ROLE) VALUES (?,?,?,?) ";
        PreparedStatement preparedStatement = MYSQLConnection.getConnection().prepareStatement(query);
        preparedStatement.setString( 1, user.getUserName() );
        preparedStatement.setString( 2, user.getPassword() );
        preparedStatement.setLong( 3, user.getPerson().getPersonId() );
        preparedStatement.setString( 4, user.getRole() );
        preparedStatement.execute();
        preparedStatement.close();
    }
    
}
