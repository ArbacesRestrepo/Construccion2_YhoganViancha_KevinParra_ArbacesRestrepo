package App.Model;

/**
 * @author Arbaces Restrepo, Jhogan Viancha
 */


import java.util.Date;

public class Partner {
    private long id;
    private Person person;
    private long funds;
    private int suscription;
    private Date affiliationDate;

    public Partner() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public long getFunds() {
        return funds;
    }

    public void setFounds(long funds) {
        this.funds = funds;
    }

    public int getSuscription() {
        return suscription;
    }

    public void setSuscription(int suscription) {
        this.suscription = suscription;
    }

    public Date getAffiliationDate() {
        return affiliationDate;
    }

    public void setAffiliationDate(Date affiliationDate) {
        this.affiliationDate = affiliationDate;
    }
    
    
}
