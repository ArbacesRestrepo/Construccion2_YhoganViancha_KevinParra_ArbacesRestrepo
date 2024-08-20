package App.Dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import App.Config.MYSQLConnection;
import App.Dao.Interfaces.PersonDao;
import App.Dto.PersonDto;
import App.Helper.Helper;
import App.Model.Person;

/**
 * @author Arbaces Restrepo, Jhogan Viancha
 */

public class PersonDaoImplementation implements PersonDao {

    @Override
    public boolean existsByDocument(PersonDto personDto) throws Exception {
        String query = "SELECT 1 FROM PERSON WHERE DOCUMENT = ?";
        PreparedStatement preparedStatement = MYSQLConnection.getConnection().prepareStatement(query);
        preparedStatement.setLong(1, personDto.getDocument());
        ResultSet resulSet = preparedStatement.executeQuery();
        boolean exists = resulSet.next();
        resulSet.close();
        preparedStatement.close();
        return exists;
    }

    @Override
    public void createPerson(PersonDto personDto) throws Exception {
        Person person = Helper.parse(personDto);
        String query = "INSERT INTO PERSON(NAME, DOCUMENT, CELLPHONE) VALUES (?,?,?) ";
        PreparedStatement preparedStatement = MYSQLConnection.getConnection().prepareStatement(query);
        preparedStatement.setString(1, person.getPersonName());
        preparedStatement.setLong(2,person.getDocument());
        preparedStatement.setLong(2,person.getPhoneNumber());
        preparedStatement.execute();
        preparedStatement.close();
    }

    @Override
    public void deletePerson(PersonDto personDto) throws Exception {
        Person person = Helper.parse(personDto);
        String query = "DELETE FROM PERSON WHERE DOCUMENT = ?";
        PreparedStatement preparedStatement = MYSQLConnection.getConnection().prepareStatement(query);
        preparedStatement.setLong(1,person.getDocument());
        preparedStatement.execute();
        preparedStatement.close();
    }

    @Override
    public PersonDto findByDocument(PersonDto personDto) throws Exception {
        String query = "SELECT ID, NAME, DOCUMENT, CELLPHONE FROM PERSON WHERE DOCUMENT = ?";
        PreparedStatement preparedStatement = MYSQLConnection.getConnection().prepareStatement(query);
        preparedStatement.setLong(1, personDto.getDocument());
        ResultSet resulSet = preparedStatement.executeQuery();
        if (resulSet.next()) {
            Person person = new Person();
            person.setPersonId(resulSet.getLong("ID"));
            person.setPersonName(resulSet.getString("NAME"));
            person.setDocument(resulSet.getLong("DOCUMENT"));
            person.setPhoneNumber(resulSet.getLong("CELLPHONE"));
            resulSet.close();
            preparedStatement.close();
            return Helper.parse(person);
        }
        resulSet.close();
        preparedStatement.close();
        return null;
    }
}
