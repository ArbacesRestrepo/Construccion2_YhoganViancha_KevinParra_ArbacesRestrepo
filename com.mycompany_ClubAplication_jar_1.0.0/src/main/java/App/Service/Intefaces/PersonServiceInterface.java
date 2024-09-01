package App.Service.Intefaces;

/**
 * @author Arbaces Restrepo, Jhogan Viancha
 */

import App.Dto.PersonDto;

public interface PersonServiceInterface {
    public PersonDto createPerson( ) throws Exception;    
    public PersonDto updatePerson( ) throws Exception;    
    public void deletePerson( ) throws Exception;    
}
