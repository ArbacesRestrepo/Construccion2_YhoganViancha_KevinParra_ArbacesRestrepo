package App.Dto;

/**
 * @author Arbaces Restrepo, Jhogan Viancha
 */

import java.sql.Date;

import App.Controllers.Utils;

import App.Dto.Interfaces.PartnerDtoInterface;

import App.Dao.PersonDao;
import App.Dao.UserDao;
import App.Dao.PartnerDao;

import App.Controllers.Validator.PartnerValidator;
import App.Dao.Interfaces.PersonDaoInterface;
import App.Dao.Interfaces.UserDaoInteface;
import App.Dao.Interfaces.PartnerDaoInterface;

public class PartnerDto implements PartnerDtoInterface{
    private long id;
    private PersonDto personDto;
    private PersonDaoInterface personDao;
    private UserDaoInteface userDao;
    private PartnerDaoInterface partnerDao;
    private long userId;
    private double amount;
    private String type;
    private Date creationDate;

    private PartnerValidator partnerValidator;

    public PartnerDto() {
        this.personDao = new PersonDao();
        this.userDao = new UserDao();
        this.partnerDao = new PartnerDao();
        this.partnerValidator = new PartnerValidator();
    }

    @Override
    public void getPartnerDto() throws Exception {
        PersonDto personDocument = new PersonDto();
        personDocument.getPersonDocumentDto();
        this.personDto = this.personDao.findByDocument( personDocument );
        if ( this.personDto == null ) {
            throw new Exception("No se encontró ninguna persona con el número de identificación ingresado");
        }

        UserDto userDtoId = this.userDao.findByPersonId( personDto );
        if ( userDtoId == null ) {
            throw new Exception("No se encontró ningún usuario con el número de identificación ingresado");
        }
        
        this.userId = userDtoId.getId();
        
        String partnerTypeDto = "";
        boolean continueRead = true;
        while ( continueRead ){
            System.out.println("Ingrese el tipo de socio \n 1. REGULAR \n 2. VIP \n 3. INVITADO \n 4. CANCELAR \n");
            partnerTypeDto = Utils.getReader().nextLine();
            
            switch ( partnerTypeDto ){
                case "1": {
                    partnerTypeDto = "REGULAR";
                    continueRead = false;
                    break;
                }
                case "2": {
                    partnerTypeDto = "VIP";
                    continueRead = false;
                    break;
                }
                case "3": {
                    partnerTypeDto = "INVITADO";
                    continueRead = false;
                    break;
                }
                case "4": {
                    partnerTypeDto = "";
                    continueRead = false;
                    break;
                }
                default: {
                    System.out.println("Ingrese una opcion valida");
                }
            }            
        }
        
        this.partnerValidator.validType( partnerTypeDto );
        
        if ( partnerTypeDto == "VIP" ){
            long numberVIP = partnerDao.numberVIP();
            if ( numberVIP >= 5 ){
                throw new Exception("Cupo de socios VIP copado");                
            }
        }
        this.type = partnerTypeDto;

        
        System.out.println("Ingrese el monto de inversión");
        String partnerAmountDto = Utils.getReader().nextLine();
        this.amount = partnerValidator.validAmount( partnerAmountDto );
        
    }

    @Override
    public void getPartnerIdDto() throws Exception {
        PersonDto personDocument = new PersonDto();
        personDocument.getPersonDocumentDto();
        this.personDto = this.personDao.findByDocument( personDocument );
        if ( this.personDto == null ) {
            throw new Exception("No se encontró ninguna persona con el número de identificación ingresado");
        }

        UserDto userDtoId = this.userDao.findByPersonId( personDto );
        if ( userDtoId == null ) {
            throw new Exception("No se encontró ningún usuario con el número de identificación ingresado");
        }
        
        this.userId = userDtoId.getId();
    }
    
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }
    
}
