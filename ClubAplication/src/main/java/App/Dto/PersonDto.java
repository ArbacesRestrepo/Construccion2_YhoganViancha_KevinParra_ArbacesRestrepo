package App.Dto;

/**
 * @author Arbaces Restrepo, Jhogan Viancha
 */

import App.Controllers.Utils;
import App.Controllers.Validator.PersonValidator;
import App.Dto.Interfaces.PersonDtoInterface;

public class PersonDto implements PersonDtoInterface{
    private long id;
    private String name;
    private long document;
    private long cellPhone;

    private PersonValidator personValidator;

    public PersonDto() {
    }
    
    @Override
    public void getPersonDto() throws Exception {
        System.out.println("Ingrese el nombre");
        String personNameDto = Utils.getReader().nextLine();
        personValidator.validName( personNameDto );
        this.name = personNameDto;
        
        System.out.println("Ingrese el documento de identidad");
        String personDocumentDto = Utils.getReader().nextLine();
        this.document = personValidator.validDocument( personDocumentDto );
        
        System.out.println("Ingrese el número de celular");
        String personCellNumberDto = Utils.getReader().nextLine();
        this.cellPhone = personValidator.validCellPhone( personCellNumberDto );
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
