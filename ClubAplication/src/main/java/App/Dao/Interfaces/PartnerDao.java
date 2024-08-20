package App.Dao.Interfaces;

import App.Dto.PartnerDto;

/**
 * @author Arbaces Restrepo, Jhogan Viancha
 */

public interface PartnerDao {
    public boolean existsByUserId(PartnerDto partnerDto) throws Exception;
    public void createPartner(PartnerDto partnerDto) throws Exception;
    public void deletePerson(PartnerDto partnerDto) throws Exception;
    public PartnerDto findByDocument(PartnerDto partnerDto) throws Exception;
}
