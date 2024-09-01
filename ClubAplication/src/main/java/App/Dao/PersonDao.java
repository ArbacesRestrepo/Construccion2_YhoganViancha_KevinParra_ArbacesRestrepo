package App.Dao;

/**
 * @author Arbaces Restrepo, Jhogan Viancha
 */


import java.sql.PreparedStatement;
import java.sql.ResultSet;

import App.Config.MYSQLConnection;
import App.Dto.PersonDto;
import App.Helper.Helper;
import App.Model.Person;
import App.Dao.Interfaces.PersonDaoInterface;
import App.Dto.InvoiceDto;
import App.Dto.UserDto;

public class PersonDao implements PersonDaoInterface {

    @Override
    public boolean existsByDocument(PersonDto personDto) throws Exception {
        String query = "SELECT 1 FROM PERSON WHERE DOCUMENT = ?";
        PreparedStatement preparedStatement = MYSQLConnection.getConnection().prepareStatement(query);
        preparedStatement.setLong( 1, personDto.getDocument());
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
        preparedStatement.setString( 1, person.getName() );
        preparedStatement.setLong( 2, person.getDocument() );
        preparedStatement.setLong( 3, person.getCellPhone() );
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
            person.setId( resulSet.getLong("ID"));
            person.setName( resulSet.getString("NAME"));
            person.setDocument(resulSet.getLong("DOCUMENT"));
            person.setCellPhone(resulSet.getLong("CELLPHONE"));
            resulSet.close();
            preparedStatement.close();
            return Helper.parse(person);
        }
        resulSet.close();
        preparedStatement.close();
        return null;
    }

    @Override
    public PersonDto findByUserId( UserDto userDto ) throws Exception {
        String query = "SELECT ID, NAME, DOCUMENT, CELLPHONE FROM PERSON WHERE ID = ?";
        PreparedStatement preparedStatement = MYSQLConnection.getConnection().prepareStatement(query);
        preparedStatement.setLong(1, userDto.getPersonId() );
        ResultSet resulSet = preparedStatement.executeQuery();
        if (resulSet.next()) {
            Person person = new Person();
            person.setId( resulSet.getLong("ID"));
            person.setName( resulSet.getString("NAME"));
            person.setDocument(resulSet.getLong("DOCUMENT"));
            person.setCellPhone(resulSet.getLong("CELLPHONE"));
            resulSet.close();
            preparedStatement.close();
            return Helper.parse(person);
        }
        resulSet.close();
        preparedStatement.close();
        return null;
    }

    @Override
    public PersonDto findByPersonId( InvoiceDto invoiceDto ) throws Exception {
        String query = "SELECT ID, NAME, DOCUMENT, CELLPHONE FROM PERSON WHERE ID = ?";
        PreparedStatement preparedStatement = MYSQLConnection.getConnection().prepareStatement(query);
        preparedStatement.setLong(1, invoiceDto.getPersonId() );
        ResultSet resulSet = preparedStatement.executeQuery();
        if (resulSet.next()) {
            Person person = new Person();
            person.setId( resulSet.getLong("ID"));
            person.setName( resulSet.getString("NAME"));
            person.setDocument(resulSet.getLong("DOCUMENT"));
            person.setCellPhone(resulSet.getLong("CELLPHONE"));
            resulSet.close();
            preparedStatement.close();
            return Helper.parse(person);
        }
        resulSet.close();
        preparedStatement.close();
        return null;
    }

    @Override
    public void updatePerson(PersonDto personDto) throws Exception {
        String query = "UPDATE PERSON SET CELLPHONE = ? WHERE DOCUMENT = ?";
        PreparedStatement preparedStatement = MYSQLConnection.getConnection().prepareStatement(query);
        preparedStatement.setLong(1, personDto.getCellPhone());
        preparedStatement.setLong(2, personDto.getDocument());
        preparedStatement.execute();
        preparedStatement.close();
    }
}
