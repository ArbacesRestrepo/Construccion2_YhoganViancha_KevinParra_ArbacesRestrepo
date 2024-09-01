package App.Service;

/**
 * @author Arbaces Restrepo, Jhogan Viancha
 */


import App.Service.Intefaces.GuestServiceInterface;

import App.Dao.PersonDao;
import App.Dao.UserDao;
import App.Dao.PartnerDao;
import App.Dao.GuestDao;
import App.Dao.InvoiceDao;

import App.Dto.PersonDto;
import App.Dto.PartnerDto;
import App.Dto.UserDto;
import App.Dto.GuestDto;

public class GuestService implements GuestServiceInterface {
    private final UserService userService = new UserService();
    private final PersonDao personDao = new PersonDao();
    private final UserDao userDao = new UserDao();
    private final PartnerDao partnerDao = new PartnerDao();
    private final GuestDao guestDao = new GuestDao();
    private final InvoiceDao invoiceDao = new InvoiceDao();

    @Override
    public void createGuest( ) throws Exception {
        UserDto userDtoLocate = this.userService.createUserGuest();

        if ( userDtoLocate == null ) {
            throw new Exception("No se encontró ningún usuario");            
        }
        
        PersonDto personDto = this.personDao.findById( userDtoLocate );
        
        GuestDto guestDto = new GuestDto();
        guestDto.setUserId( userDtoLocate.getId() );
        guestDto.setPartnerId( personDto.getId() );
        guestDto.setStatus( "ACTIVO" );
        
        this.guestDao.createGuest( guestDto );
    }
    
    @Override
    public void createGuest( UserDto userDto ) throws Exception {
        UserDto userDtoLocate = this.userService.createUserGuest();

        if ( userDtoLocate == null ) {
            throw new Exception("No se encontró ningún usuario");            
        }

        PersonDto personDto = this.personDao.findById( userDto );
        
        GuestDto guestDto = new GuestDto();
        guestDto.setUserId( userDto.getId() );
        guestDto.setPartnerId( personDto.getId() );
        guestDto.setStatus( "ACTIVO" );
        
        this.guestDao.createGuest( guestDto );
    }
    
    @Override
    public void updateGuest( ) throws Exception {
        
    }

    @Override
    public void deleteGuest( ) throws Exception {
        PersonDto personDtoLocale = new PersonDto();
        personDtoLocale.getPersonDocumentDto();
        personDtoLocale = this.personDao.findByDocument( personDtoLocale );
        
        if ( personDtoLocale == null ){
            throw new Exception("No existe la persona");
        }
        
        double amountActiveInvoices = this.invoiceDao.amountActiveInvoices( personDtoLocale );
        if ( amountActiveInvoices > 0 ){
            throw new Exception("La persona tiene facturas pendientes de pago");
        }
        
        UserDto userDtoLocate = this.userDao.findByPersonId( personDtoLocale );
        if ( userDtoLocate == null ) {
            throw new Exception("No se encontró ningún usuario con el número de identificación ingresado");            
        }

        PartnerDto partnerDto = partnerDao.findByUserId( userDtoLocate );
        
        if ( partnerDto == null ){
            throw new Exception("No existe el socio");                            
        }
        
        this.partnerDao.deletePartner( partnerDto );
    }    
}
