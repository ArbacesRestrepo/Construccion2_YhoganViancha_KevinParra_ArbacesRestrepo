package App.Dto;

/**
 * @author Arbaces Restrepo, Jhogan Viancha
 */

import App.Controllers.Utils;
import App.Controllers.Validator.PersonValidator;
import App.Dto.Interfaces.PersonDtoInterface;

public class PersonDto implements PersonDtoInterface{
    private long id;
    private long document;
    private String name;
    private long cellPhone;

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
        this.cellPhone = personValidator.validCellPhone( personCellNumberDto );
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
    
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getDocument() {
        return document;
    }

    public void setDocument(long document) {
        this.document = document;
    }

    public long getCellPhone() {
        return cellPhone;
    }

    public void setCellPhone(long cellPhone) {
        this.cellPhone = cellPhone;
    }

}
