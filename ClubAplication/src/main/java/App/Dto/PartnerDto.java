package App.Dto;

import App.Dto.Interfaces.PartnerDtoInterface;
import java.sql.Date;

/**
 * @author Arbaces Restrepo, Jhogan Viancha
 */

public class PartnerDto implements PartnerDtoInterface{
    private long id;
    private PersonDto personId;
    private long userId;
    private double amount;
    private String type;
    private Date creationDate;

    public PartnerDto() {
    }

    @Override
    public void getPartnerDto() throws Exception {
        personId.getPersonDto();
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
