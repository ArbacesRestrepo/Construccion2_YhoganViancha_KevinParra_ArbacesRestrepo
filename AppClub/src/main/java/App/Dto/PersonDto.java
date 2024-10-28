package App.Dto;

/**
 * @author Arbaces Restrepo, Yhogan Viancha, Kevin Parra
 */

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import App.Controllers.Utils;
import App.Controllers.Validator.PersonValidator;
import App.Dto.Interfaces.PersonDtoInterface;

@Getter
@Setter
@NoArgsConstructor
public class PersonDto implements PersonDtoInterface{
    private long id;
    private long document;
    private String name;
    private long cellphone;

    private final PersonValidator personValidator = new PersonValidator();
    
    @Override
    public void getPersonNameDto() throws Exception {
        System.out.println("Ingrese el nombre de la persona");
        String personNameDto = Utils.getReader().nextLine();
        this.personValidator.validName( personNameDto );
        this.name = personNameDto;
    }

    @Override
    public void getPersonCellNumberDto() throws Exception {
        System.out.println("Ingrese el número de celular");
        String personCellNumberDto = Utils.getReader().nextLine();
        this.cellphone = personValidator.validCellPhone( personCellNumberDto );
    }

    @Override
    public void getPersonDocumentDto() throws Exception {
        System.out.println("Ingrese el documento de identidad");
        String personDocumentDto = Utils.getReader().nextLine();
        this.document = personValidator.validDocument( personDocumentDto );
    }
    
    @Override
    public void getPersonDocumentDto( String message ) throws Exception {
        System.out.println( message );
        String personDocumentDto = Utils.getReader().nextLine();
        this.document = personValidator.validDocument( personDocumentDto );
    }
}
