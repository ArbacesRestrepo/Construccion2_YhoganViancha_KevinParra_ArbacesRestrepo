package App.Service;

/**
 * @author Arbaces Restrepo, Yhogan Viancha, Kevin Parra
 */

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import App.Controllers.Utils;
import App.Service.Intefaces.PartnerServiceInterface;
import App.Helper.Helper;

import App.Dao.PersonDao;
import App.Dao.PartnerDao;
import App.Dao.InvoiceDao;
import App.Dao.InvoiceDetailDao;
import App.Dao.UserDao;
import App.Dto.InvoiceDto;
import App.Dto.PersonDto;
import App.Dto.PartnerDto;
import App.Dto.UserDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Getter
@Setter
@NoArgsConstructor
@Service
public class PartnerService implements PartnerServiceInterface {
    @Autowired
    private final PersonDao personDao = new PersonDao();
    @Autowired
    private final UserDao userDao = new UserDao();
    @Autowired
    private final PartnerDao partnerDao = new PartnerDao();
    @Autowired
    private final InvoiceDao invoiceDao = new InvoiceDao();
    @Autowired
    private final InvoiceDetailDao invoiceDetailDao = new InvoiceDetailDao();

    @Override
    public void createPartner( ) throws Exception {
        PersonDto personDtoLocale = new PersonDto();
        personDtoLocale.getPersonDocumentDto();
        personDtoLocale = this.personDao.findByDocument( personDtoLocale );
        if ( personDtoLocale == null ){
            throw new Exception("No existe la persona");
        }

        UserDto userDtoLocate = this.userDao.findByPersonId( personDtoLocale );
        if ( userDtoLocate == null ) {
            throw new Exception("No se encontró ningún usuario con el número de identificación ingresado");            
        }
        
        PartnerDto partnerDto = this.partnerDao.findByUserId( userDtoLocate );
        if ( partnerDto != null ){
            throw new Exception( personDtoLocale.getName() + " ya es SOCIO del club");
        }
        
        partnerDto = new PartnerDto();
        
        partnerDto.setUserId( Helper.parse( userDtoLocate ) );
        
        partnerDto.getPartnerTypeDto();
        if ( partnerDto.getType().equals( "VIP" ) ){
            long numberVIP = this.partnerDao.numberPartnersVIP();
            if ( numberVIP >= 5 ){
                throw new Exception("Cupo de socios VIP copado");                
            }
        }
        
        partnerDto.getPartnerAmountDto();

        this.partnerDao.createPartner( partnerDto );
    }
    
    @Override
    public void updateAmountPartner( ) throws Exception {
        PersonDto personDtoLocale = new PersonDto();
        personDtoLocale.getPersonDocumentDto();
        personDtoLocale = this.personDao.findByDocument( personDtoLocale );
        if ( personDtoLocale == null ){
            throw new Exception("No existe la persona");
        }

        UserDto userDtoLocate = this.userDao.findByPersonId( personDtoLocale );
        if ( userDtoLocate == null ) {
            throw new Exception("No se encontró ningún usuario para: " + personDtoLocale.getName() );            
        }
        
        PartnerDto partnerDtoLocale = this.partnerDao.findByUserId( userDtoLocate );
        if ( partnerDtoLocale == null ){
            throw new Exception( personDtoLocale.getName() +  " NO es SOCIO del club");
        }
        
        double amountDao = partnerDtoLocale.getAmount();
        
        partnerDtoLocale.setUserId(Helper.parse( userDtoLocate ) );
        
        partnerDtoLocale.getPartnerAmountIncraseDto();
        
        if ( partnerDtoLocale.getType().equals( "REGULAR" ) ){
            if ( ( partnerDtoLocale.getAmount() + amountDao ) > 1000000 ){
                throw new Exception( personDtoLocale.getName() +  " excede el monto permitido de inversión");
            }
        }
        else{
            if ( ( partnerDtoLocale.getAmount() + amountDao ) > 5000000 ){
                throw new Exception( personDtoLocale.getName() +  " excede el monto permitido de inversión");
            }            
        }

        PartnerDto partnerDtoDao = this.partnerDao.findByUserId( userDtoLocate );
        
        partnerDtoLocale.setAmount( partnerDtoLocale.getAmount() + partnerDtoDao.getAmount() );
        
        this.partnerDao.updateAmountPartner( partnerDtoLocale );
        
        ArrayList<InvoiceDto> listInvoice =  this.invoiceDao.listPartnerInvoices( partnerDtoLocale );
          
        List<InvoiceDto> filteredAndSorted = listInvoice.stream()
                .filter(invoice -> "PENDIENTE".equals(invoice.getStatus())) 
                .sorted(Comparator.comparing(InvoiceDto::getCreationDate)) 
                .collect(Collectors.toList());
        
        for ( InvoiceDto invoiceDtoList : filteredAndSorted ) {
            partnerDtoDao = this.partnerDao.findByUserId( userDtoLocate );
            if ( partnerDtoDao.getAmount() >= invoiceDtoList.getAmount() ){
                this.invoiceDao.cancelInvoice( invoiceDtoList );

                partnerDtoDao.setAmount( partnerDtoDao.getAmount() - invoiceDtoList.getAmount() );
                this.partnerDao.updateAmountPartner( partnerDtoDao );
            }
            else {
                break;
            }
        }
    }

    @Override
    public void deletePartner( ) throws Exception {
        PersonDto personDtoLocale = new PersonDto();
        personDtoLocale.getPersonDocumentDto();
        personDtoLocale = this.personDao.findByDocument( personDtoLocale );
        if ( personDtoLocale == null ){
            throw new Exception("No existe la persona");
        }
        
        double amountActiveInvoices = this.invoiceDao.amountActiveInvoices( personDtoLocale );
        if ( amountActiveInvoices > 0 ){
            throw new Exception( personDtoLocale.getName() + " tiene facturas pendientes de pago");
        }
        
        UserDto userDtoLocate = this.userDao.findByPersonId( personDtoLocale );
        if ( userDtoLocate == null ) {
            throw new Exception("No se encontró ningún usuario con el número de identificación ingresado");            
        }
        
        PartnerDto partnerDto = this.partnerDao.findByUserId( userDtoLocate );
        
        if ( partnerDto == null ){
            throw new Exception( "No existe el socio");                            
        }
        
        ArrayList<InvoiceDto> listInvoice =  this.invoiceDao.listPartnerInvoices( partnerDto );
        for ( InvoiceDto invoiceDto : listInvoice ){
            this.invoiceDetailDao.deleteInvoiceDetail( invoiceDto );
            this.invoiceDao.deleteInvoice( invoiceDto );            
        }

        this.partnerDao.deletePartner( partnerDto );
    }    

    @Override
    public void deletePartner( UserDto userDto ) throws Exception {
        PersonDto personDtoLocale = this.personDao.findByUserId( userDto );
        if ( personDtoLocale == null ){
            throw new Exception("No existe la persona");
        }
        
        double amountActiveInvoices = this.invoiceDao.amountActiveInvoices( personDtoLocale );
        if ( amountActiveInvoices > 0 ){
            throw new Exception( personDtoLocale.getName() + " tiene facturas pendientes de pago");
        }
        
        PartnerDto partnerDto = this.partnerDao.findByUserId( userDto );
        
        if ( partnerDto == null ){
            throw new Exception("No existe el socio");                            
        }
        
        ArrayList<InvoiceDto> listInvoice =  this.invoiceDao.listPartnerInvoices( partnerDto );
        for ( InvoiceDto invoiceDto : listInvoice ){
            this.invoiceDetailDao.deleteInvoiceDetail( invoiceDto );
            this.invoiceDao.deleteInvoice( invoiceDto );            
        }
        
        this.partnerDao.deletePartner( partnerDto );
    }    

    @Override
    public void updateTypePartner( PartnerDto partnerDto ) throws Exception {
        partnerDto.setType( "PIDE CAMBIO A VIP" );
        this.partnerDao.updateTypePartner( partnerDto );
    }

    @Override
    public void changePartnersToVIP() throws Exception {
        long numberVIP = this.partnerDao.numberPartnersVIP();
        if ( numberVIP >= 5 ){
            throw new Exception("Cupo de socios VIP copado");                
        }
        
        long available = 5 - numberVIP;
        long numberRequesVIP = this.partnerDao.numberPartnersRequestVIP();
        if ( numberRequesVIP > available ){
            System.out.println( "Hay " + String.valueOf( numberRequesVIP ) + " candidatos y " + String.valueOf( available ) + " cupos disponibles");
        }

        ArrayList<PartnerDto> listPartners = this.partnerDao.listPartnerRequestVIP();
        PersonDto personDto;
        PartnerDto partnerDto;
        UserDto userDto;
        double amountInvoice;
        for ( int i=0; i < listPartners.size(); i++ ){
            partnerDto = listPartners.get(i);
            userDto = this.userDao.findByUserId( partnerDto );
            personDto = this.personDao.findByUserId( userDto );
            amountInvoice = this.invoiceDao.amountInvoicesByPartner( partnerDto );
            System.out.println( personDto.getName() + " fondos: " + partnerDto.getAmount() + " ingreso: " + partnerDto.getCreationDate() + " facturado: " + amountInvoice);
        }
        
        String authorizeVIP ;
        for ( int i=0; i < listPartners.size(); i++ ){
            partnerDto = listPartners.get(i);
            userDto = this.userDao.findByUserId( partnerDto );
            personDto = this.personDao.findByUserId( userDto );
            amountInvoice = this.invoiceDao.amountInvoicesByPartner( partnerDto );
            
            System.out.println( "Autorizar promoción a: " + personDto.getName() + " fondos: " 
                    + partnerDto.getAmount() + " ingreso: " + partnerDto.getCreationDate() + " facturado: " + amountInvoice);
            System.out.println("1. Autoriza cambio a VIP. 2. Rechaza cambio");
            authorizeVIP = Utils.getReader().nextLine();
            if ( authorizeVIP.equals( "1" ) ){
                partnerDto.setType( "VIP" );
            }
            else{
                partnerDto.setType( "REGULAR" );
            }
            this.partnerDao.updateTypePartner( partnerDto );
            numberVIP = this.partnerDao.numberPartnersVIP();
            if ( numberVIP >= 5 ){
                i = listPartners.size() + 1;
            }            
        }        
    }
}
