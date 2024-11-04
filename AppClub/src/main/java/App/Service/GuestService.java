package App.Service;

/**
 * @author Arbaces Restrepo, Yhogan Viancha, Kevin Parra
 */

import App.Service.Intefaces.GuestServiceInterface;

import App.Dao.PersonDao;
import App.Dao.UserDao;
import App.Dao.PartnerDao;
import App.Dao.GuestDao;
import App.Dao.InvoiceDao;
import App.Dao.InvoiceDetailDao;

import App.Dto.PersonDto;
import App.Dto.UserDto;
import App.Dto.GuestDto;
import App.Dto.PartnerDto;
import App.Helper.Helper;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Getter
@Setter
@NoArgsConstructor
@Service
public class GuestService implements GuestServiceInterface {
    @Autowired
    private UserService userService;
    @Autowired
    private PersonDao personDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private PartnerDao partnerDao;
    @Autowired
    private GuestDao guestDao;
    @Autowired
    private InvoiceDao invoiceDao;
    @Autowired
    private InvoiceDetailDao invoiceDetailDao;

    @Override
    public void createGuest( PersonDto personDto, PersonDto personInviteDto ) throws Exception {
        if ( !this.personDao.existsByDocument( personInviteDto ) ) {
            throw new Exception("No se encontró ningúna persona con el numero de identificación: " + String.valueOf( personInviteDto.getDocument() ));
        }
        personInviteDto = this.personDao.findByDocument( personInviteDto );

        if ( !this.personDao.existsByDocument( personDto ) ) {
            throw new Exception("No se encontró ningúna persona con el numero de identificación: " + String.valueOf( personDto.getDocument() ));
        }
        personDto = this.personDao.findByDocument( personDto );
        
        UserDto userDtoInvite = this.userDao.findByPersonId( personInviteDto );
        PartnerDto partnerDto = this.partnerDao.findByUserId( userDtoInvite );        
        if ( partnerDto == null ) {
            throw new Exception( personDto.getName() + " no es socio del club");            
        }

        UserDto userDto = this.userDao.findByPersonId( personDto );
                        
        GuestDto guestDto = new GuestDto();
        guestDto.setUserId( Helper.parse( userDto ) );
        guestDto.setPartnerId( Helper.parse( partnerDto ) );
        guestDto.setStatus( "ACTIVO" );
        
        this.guestDao.createGuest( guestDto );
    }
    
    @Override
    public void deleteGuest( PersonDto personDto ) throws Exception {
        personDto = this.personDao.findByDocument( personDto );
        
        if ( personDto == null ){
            throw new Exception("No existe la persona");
        }
        
        double amountActiveInvoices = this.invoiceDao.amountActiveInvoices( personDto );
        if ( amountActiveInvoices > 0 ){
            throw new Exception( personDto.getName() + " tiene facturas pendientes de pago");
        }
        
        UserDto userDto = this.userDao.findByPersonId( personDto );
        if ( userDto == null ) {
            throw new Exception("No se encontró ningún usuario con el número de identificación ingresado");            
        }

        GuestDto guestDto = this.guestDao.findByUserId( userDto );
        
        if ( guestDto == null ){
            throw new Exception("No existe el invitado");                            
        }
        
        this.guestDao.deleteGuest( guestDto );
    }    

    @Override
    public void changeGuestToPartner( PersonDto personDto ) throws Exception {
        if ( !this.personDao.existsByDocument( personDto ) ) {
            throw new Exception("No se encontró ningúna persona con el numero de identificación: " + String.valueOf( personDto.getDocument() ));
        }
        personDto = this.personDao.findByDocument( personDto );
        
        double amountActiveInvoices = this.invoiceDao.amountActiveInvoices( personDto );
        if ( amountActiveInvoices > 0 ){
            throw new Exception( personDto.getName() + " tiene facturas pendientes de pago");
        }
        
        UserDto userDto = this.userDao.findByPersonId( personDto );

        GuestDto guestDto = this.guestDao.findByUserId( userDto );
        if ( guestDto == null ){
            throw new Exception( personDto.getName() + " no es un invitado");            
        }

        PartnerDto partnerDto = this.partnerDao.findByUserId( userDto );
        if ( partnerDto != null ){
            throw new Exception( personDto.getName() + " ya es SOCIO del club");
        }
        
        partnerDto = new PartnerDto();
        partnerDto.setUserId( Helper.parse( userDto ) );
        partnerDto.setType( "REGULAR" );
        partnerDto.setAmount( 0 );
        
        userDto.setRole( "SOCIO" );
        
        this.guestDao.deleteGuest( guestDto );
        this.partnerDao.createPartner( partnerDto );
        this.userDao.updateUser( userDto );
    }
}
