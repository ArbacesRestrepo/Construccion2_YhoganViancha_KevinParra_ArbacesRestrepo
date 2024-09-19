package App.Dto;

/**
 * @author Arbaces Restrepo, Yhogan Viancha, Kevin Parra
 */

import java.sql.Date;

import App.Controllers.Utils;
import App.Dto.Interfaces.PartnerDtoInterface;

import App.Controllers.Validator.PartnerValidator;
import App.Model.User;

public class PartnerDto implements PartnerDtoInterface{
    private final PartnerValidator partnerValidator = new PartnerValidator();

    private long id;
    private User userId;
    private double amount;
    private String type;
    private Date creationDate;

    @Override
    public void getPartnerTypeDto() throws Exception {
        String partnerTypeDto = "";
        boolean continueRead = true;
        while ( continueRead ){
            System.out.println("Ingrese el tipo de socio \n 1. REGULAR \n 2. VIP \n 4. CANCELAR \n");
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
        
        this.type = partnerTypeDto;
    }

    @Override
    public void getPartnerAmountDto() throws Exception {
        System.out.println("Ingrese el monto de inversión");
        String partnerAmountDto = Utils.getReader().nextLine();
        this.amount = partnerValidator.validAmount( partnerAmountDto );
    }
    
    @Override
    public void getPartnerAmountIncraseDto() throws Exception {
        System.out.println("Ingrese el monto a AUMENTAR la inversión");
        String partnerAmountDto = Utils.getReader().nextLine();
        this.amount = partnerValidator.validAmount( partnerAmountDto );
    }
    
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
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
