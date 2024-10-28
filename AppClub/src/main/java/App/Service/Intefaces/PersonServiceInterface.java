package App.Service.Intefaces;

/**
 * @author Arbaces Restrepo, Yhogan Viancha, Kevin Parra
 */

import App.Dto.PersonDto;

public interface PersonServiceInterface {
    public PersonDto createPerson( PersonDto personDto ) throws Exception;    
    public PersonDto updatePerson( PersonDto personDto ) throws Exception;    
    public void deletePerson( PersonDto personDto ) throws Exception;    
}
