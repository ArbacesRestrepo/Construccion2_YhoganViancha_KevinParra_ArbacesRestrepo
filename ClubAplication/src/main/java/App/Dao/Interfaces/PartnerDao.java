package App.Dao.Interfaces;

/**
 * @author Arbaces Restrepo, Jhogan Viancha
 */


import App.Dto.PartnerDto;

public interface PartnerDao {
    public boolean existsByUserId(PartnerDto partnerDto) throws Exception;
    public void createPartner(PartnerDto partnerDto) throws Exception;
    public void deletePerson(PartnerDto partnerDto) throws Exception;
    public PartnerDto findByDocument(PartnerDto partnerDto) throws Exception;
}
