package App.Dao;

/**
 * @author Arbaces Restrepo, Jhogan Viancha, Kevin Parra
 */

import App.Dto.PersonDto;
import App.Helper.Helper;
import App.Model.Person;
import App.Dao.Interfaces.PersonDaoInterface;
import App.Dao.Repository.PersonRepository;
import App.Dto.InvoiceDto;
import App.Dto.UserDto;

public class PersonDao implements PersonDaoInterface {
    PersonRepository personRepository;

    @Override
    public boolean existsByDocument(PersonDto personDto) throws Exception {
        return personRepository.existsByPersonDocument( personDto.getDocument() );
    }

    @Override
    public void createPerson(PersonDto personDto) throws Exception {
        Person person = Helper.parse(personDto);
        personRepository.save( person );
    }

    @Override
    public void deletePerson(PersonDto personDto) throws Exception {
        Person person = Helper.parse(personDto);
        personRepository.deleteById( person.getId() );
    }

    @Override
    public PersonDto findByDocument( PersonDto personDto ) throws Exception {
        return Helper.parse( personRepository.findByPersonDocument( personDto.getDocument() ) );
    }

    @Override
    public PersonDto findByUserId( UserDto userDto ) throws Exception {
        return Helper.parse( personRepository.findByPersonId( userDto.getPersonId() ) );
    }

    @Override
    public PersonDto findByPersonId( InvoiceDto invoiceDto ) throws Exception {
        return Helper.parse( personRepository.findByPersonId( invoiceDto.getPersonId() ) );
    }

    @Override
    public void updatePerson(PersonDto personDto) throws Exception {
        Person person = Helper.parse(personDto);
        personRepository.save( person );
    }
}
