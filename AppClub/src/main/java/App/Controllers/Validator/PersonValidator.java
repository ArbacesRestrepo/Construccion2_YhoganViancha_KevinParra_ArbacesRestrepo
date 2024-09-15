package App.Controllers.Validator;

/**
 * @author Arbaces Restrepo, Jhogan Viancha, Kevin Parra
 */

public class PersonValidator extends CommonsValidator {	
    public PersonValidator() {
        super();
    }

    public void validName(String personName) throws Exception{
        super.isValidString("el nombre de la persona ", personName);
    }

    public long validDocument(String document) throws Exception{
        return super.isValidLong("la cédula de la persona ", document);
    }

    public long validCellPhone(String cellPhone) throws Exception{
        return super.isValidLong("el numero de celular ", cellPhone);
    }
}
