package App.Service;

/**
 * @author Arbaces Restrepo, Yhogan Viancha, Kevin Parra
 */

import App.Controllers.Utils;
import App.Dao.GuestDao;
import App.Dao.InvoiceDao;
import App.Dao.InvoiceDetailDao;
import App.Dao.PartnerDao;
import App.Dao.PersonDao;
import App.Dao.UserDao;
import App.Dto.GuestDto;
import App.Dto.InvoiceDetailDto;
import App.Dto.InvoiceDto;
import App.Dto.PartnerDto;
import App.Dto.PersonDto;
import App.Dto.UserDto;
import App.Helper.Helper;
import App.Service.Intefaces.InvoiceServiceInterface;
import java.util.ArrayList;

public class InvoiceService implements InvoiceServiceInterface {
    private final PersonDao personDao = new PersonDao();
    private final UserDao userDao = new UserDao();
    private final PartnerDao partnerDao = new PartnerDao();
    private final GuestDao guestDao = new GuestDao();
    private final InvoiceDao invoiceDao = new InvoiceDao();
    private final InvoiceDetailDao invoiceDetailDao = new InvoiceDetailDao();

    @Override
    public void createInvoice() throws Exception {
        PersonDto personDto = new PersonDto();
        personDto.getPersonDocumentDto();
        
        if ( !this.personDao.existsByDocument( personDto ) ) {
            throw new Exception( "No existe ninguna persona con el documento: " + personDto.getDocument() );
        }
        
        personDto = this.personDao.findByDocument( personDto );
        
        UserDto userDto = this.userDao.findByPersonId( personDto ) ;
        
        if ( userDto == null ){
            throw new Exception( personDto.getName() + " no tiene USUARIO " );
        }
        
        InvoiceDto invoiceDto = new InvoiceDto();
        PartnerDto partnerDto = new PartnerDto();
        
        if ( userDto.getRole().equals( "SOCIO" ) ){
            partnerDto = this.partnerDao.findByUserId( userDto );
            if ( partnerDto == null ){
                throw new Exception( personDto.getName() + " no es SOCIO " );                
            }
            invoiceDto.setPersonId( Helper.parse( personDto ) );
            invoiceDto.setPartnerId( Helper.parse( partnerDto ) );
        }
        else{
            GuestDto guestDto = this.guestDao.findByUserId( userDto );
            if ( guestDto == null ){
                throw new Exception( personDto.getName() + " no es INVITADO " );                
            }
            partnerDto = this.partnerDao.findByGuestPartnerId( guestDto );
            invoiceDto.setPersonId( Helper.parse( personDto ) );
            invoiceDto.setPartnerId( Helper.parse( partnerDto ) );
        }
        
        this.invoiceDao.createInvoice( invoiceDto );
        invoiceDto.setId( this.invoiceDao.lastInvoiceByPersonId( personDto ));

        InvoiceDetailDto invoiceDetailDto = new InvoiceDetailDto();
        invoiceDetailDto.setInvoiceId( invoiceDto.getId() );
        
        boolean continueRead = true;
        while ( continueRead ){
            invoiceDetailDto.getDescription();
            invoiceDetailDto.getInvoiceDetailAmountDto();
            invoiceDetailDto.setItemNumber( this.invoiceDetailDao.countInvoiceDetails( invoiceDto ) );
            this.invoiceDetailDao.createInvoiceDetail( invoiceDetailDto );
            
            System.out.println("1. Para más detalles");
            String continueReadConsole = Utils.getReader().nextLine();
            if ( continueReadConsole.equals( "1" ) ){
                continueRead = true;
            }
            else{
                continueRead = false;                
            }
        }
        invoiceDto.setAmount( this.invoiceDetailDao.totalInvoiceDetails( invoiceDto ) );
        this.invoiceDao.updateInvoiceAmount( invoiceDto );
        if ( partnerDto.getAmount() >= invoiceDto.getAmount() ){
            this.invoiceDao.cancelInvoice(invoiceDto);
            invoiceDto.setAmount( partnerDto.getAmount() - invoiceDto.getAmount() );
            this.partnerDao.updateAmountPartner(partnerDto);
        }
    }

    @Override
    public void createPartnerInvoice( PartnerDto partnerDto ) throws Exception {
        UserDto userDto = this.userDao.findByUserId( partnerDto );
        PersonDto personDto = this.personDao.findByUserId( userDto );
        InvoiceDto invoiceDto = new InvoiceDto();

        invoiceDto.setPersonId( Helper.parse( personDto ) );
        invoiceDto.setPartnerId( Helper.parse( partnerDto ) );
        
        this.invoiceDao.createInvoice( invoiceDto );
        invoiceDto.setId( this.invoiceDao.lastInvoiceByPersonId( personDto ));

        InvoiceDetailDto invoiceDetailDto = new InvoiceDetailDto();
        invoiceDetailDto.setInvoiceId( invoiceDto.getId() );
        
        boolean continueRead = true;
        while ( continueRead ){
            invoiceDetailDto.getInvoiceDetailDescriptionDto();
            invoiceDetailDto.getInvoiceDetailAmountDto();
            invoiceDetailDto.setItemNumber( this.invoiceDetailDao.countInvoiceDetails( invoiceDto ) );
            this.invoiceDetailDao.createInvoiceDetail( invoiceDetailDto );
            
            System.out.println("1. Para más detalles");
            String continueReadConsole = Utils.getReader().nextLine();
            if ( continueReadConsole.equals( "1" ) ){
                continueRead = true;
            }
            else{
                continueRead = false;                
            }
        }
        invoiceDto.setAmount( this.invoiceDetailDao.totalInvoiceDetails( invoiceDto ) );
        this.invoiceDao.updateInvoiceAmount( invoiceDto );
        if ( partnerDto.getAmount() >= invoiceDto.getAmount() ){
            this.invoiceDao.cancelInvoice(invoiceDto);
            partnerDto.setAmount( partnerDto.getAmount() - invoiceDto.getAmount() );
            this.partnerDao.updateAmountPartner(partnerDto);
        }
    }

    @Override
    public void createGuestInvoice( GuestDto guestDto ) throws Exception {
        UserDto userDto = this.userDao.findByGuestUserId( guestDto );
        PersonDto personDto = this.personDao.findByUserId( userDto );
        InvoiceDto invoiceDto = new InvoiceDto();
        PartnerDto partnerDto = this.partnerDao.findByGuestPartnerId( guestDto );
        invoiceDto.setPersonId( Helper.parse( personDto ) );
        invoiceDto.setPartnerId( Helper.parse( partnerDto ) );

        this.invoiceDao.createInvoice( invoiceDto );
        invoiceDto.setId( this.invoiceDao.lastInvoiceByPersonId( personDto ));

        InvoiceDetailDto invoiceDetailDto = new InvoiceDetailDto();
        invoiceDetailDto.setInvoiceId( invoiceDto.getId() );

        boolean continueRead = true;
        while ( continueRead ){
            invoiceDetailDto.getInvoiceDetailDescriptionDto();
            invoiceDetailDto.getInvoiceDetailAmountDto();
            invoiceDetailDto.setItemNumber( this.invoiceDetailDao.countInvoiceDetails( invoiceDto ) );
            this.invoiceDetailDao.createInvoiceDetail( invoiceDetailDto );
            
            System.out.println("1. Para más detalles");
            String continueReadConsole = Utils.getReader().nextLine();
            if ( continueReadConsole.equals( "1" ) ){
                continueRead = true;
            }
            else{
                continueRead = false;                
            }
        }
        invoiceDto.setAmount( this.invoiceDetailDao.totalInvoiceDetails( invoiceDto ) );
        this.invoiceDao.updateInvoiceAmount( invoiceDto );
        if ( partnerDto.getAmount() >= invoiceDto.getAmount() ){
            this.invoiceDao.cancelInvoice( invoiceDto );
            partnerDto.setAmount( partnerDto.getAmount() - invoiceDto.getAmount() );
            this.partnerDao.updateAmountPartner(partnerDto);
        }
    }

    @Override
    public void historyInvoice() throws Exception {
        ArrayList<InvoiceDto> listInvoices = this.invoiceDao.listClubInvoices();
        if ( listInvoices.isEmpty() ){
            throw new Exception( "No hay historial de facturación" );
        }
        for ( int i=0; i < listInvoices.size(); i++){
            InvoiceDto invoiceDto = listInvoices.get( i );
            PersonDto personDto = this.personDao.findByPersonId( invoiceDto );
            PartnerDto partnerDto = this.partnerDao.findByPartnerId( invoiceDto );
            UserDto userDto = this.userDao.findByUserId( partnerDto );
            PersonDto personPartnerDto = this.personDao.findByUserId( userDto );
            System.out.println( "Responsable: " + personDto.getName() + ", Socio; " + personPartnerDto.getName()  + ", Fecha: " + invoiceDto.getCreationDate() + ", Monto: " + invoiceDto.getAmount() + ", Estado: " + invoiceDto.getStatus() );
        }        
    }

    @Override
    public void historyPartnerInvoice( PartnerDto partnerDto ) throws Exception {
        ArrayList<InvoiceDto> listInvoices = this.invoiceDao.listPartnerInvoices( partnerDto );
        if ( listInvoices.isEmpty() ){
            throw new Exception( "No hay historial de facturación" );
        }
        for ( int i=0; i < listInvoices.size(); i++){
                InvoiceDto invoiceDto = listInvoices.get( i );
                PersonDto personDto = this.personDao.findByPersonId( invoiceDto );
                    System.out.println( "Responsable: " + personDto.getName()  + ", Fecha: " + invoiceDto.getCreationDate() + ", Monto: " + invoiceDto.getAmount() + ", Estado: " + invoiceDto.getStatus() );
            
        }
    }

    @Override
    public void historyGuestInvoice( PersonDto personDto ) throws Exception {
        ArrayList<InvoiceDto> listInvoices = this.invoiceDao.listGuestInvoices( personDto );
        if ( listInvoices.isEmpty() ){
            throw new Exception( "No hay historial de facturación" );
        }
        for ( int i=0; i < listInvoices.size(); i++){
            
            InvoiceDto invoiceDto = listInvoices.get( i );
            PartnerDto partnerDto = this.partnerDao.findByPartnerId( invoiceDto );
            UserDto userDto = this.userDao.findByUserId( partnerDto );
            PersonDto personPartnerDto = this.personDao.findByUserId( userDto );
            System.out.println( "Responsable: " + personDto.getName() + ", Socio; " + personPartnerDto.getName()  + ", Fecha: " + invoiceDto.getCreationDate() + ", Monto: " + invoiceDto.getAmount() + ", Estado: " + invoiceDto.getStatus() );
        }
    }
}
