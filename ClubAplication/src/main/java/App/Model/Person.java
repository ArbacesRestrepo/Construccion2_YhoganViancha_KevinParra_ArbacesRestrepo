package App.Model;

/**
 * @author Arbaces Restrepo, Jhogan Viancha
 */

public class Person {
    private long personId;
    private long document;
    private String personName;
    private long cellPhone;

    public Person() {
    }

    public long getPersonId() {
        return personId;
    }

    public void setPersonId(long personId) {
        this.personId = personId;
    }

    public long getDocument() {
        return document;
    }

    public void setDocument(long document) {
        this.document = document;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public long getCellPhone() {
        return cellPhone;
    }

    public void setCellPhone(long cellPhone) {
        this.cellPhone = cellPhone;
    }
}
