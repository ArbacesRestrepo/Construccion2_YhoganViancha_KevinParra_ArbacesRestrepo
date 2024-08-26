package App.Dao.Interfaces;

/**
 * @author Arbaces Restrepo, Jhogan Viancha
 */


import App.Dto.UserDto;
import App.Dto.PartnerDto;
import App.Dto.PersonDto;

public interface PartnerDaoInterface {
    public PartnerDto existsByUserId( UserDto userDto ) throws Exception;
    public void createPartner( PartnerDto partnerDto ) throws Exception;
    public void deletePartner( PartnerDto partnerDto ) throws Exception;
    public PartnerDto findByDocument( PersonDto personDto ) throws Exception;
    public long numberVIP( ) throws Exception;
}
