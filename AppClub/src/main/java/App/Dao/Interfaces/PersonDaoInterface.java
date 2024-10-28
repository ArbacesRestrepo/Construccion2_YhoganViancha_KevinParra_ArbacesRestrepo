package App.Dao.Interfaces;

/**
 * @author Arbaces Restrepo, Yhogan Viancha, Kevin Parra
 */

import App.Dto.InvoiceDto;
import App.Dto.PersonDto;
import App.Dto.UserDto;

public interface PersonDaoInterface {
    public boolean existsByDocument(PersonDto personDto ) throws Exception;
    public void createPerson(PersonDto personDto ) throws Exception;
    public void updatePerson(PersonDto personDto ) throws Exception;
    public void deletePerson(PersonDto personDto ) throws Exception;
    public PersonDto findByDocument(PersonDto personDto ) throws Exception;
    public PersonDto findByPersonId( InvoiceDto invoiceDto ) throws Exception;    
    public PersonDto findByUserId( UserDto userDto ) throws Exception;    
}
