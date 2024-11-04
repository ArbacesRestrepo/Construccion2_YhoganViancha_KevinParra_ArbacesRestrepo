package App.Service;

/**
 * @author Arbaces Restrepo, Yhogan Viancha, Kevin Parra
 */

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

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
import App.Dto.PartnerInvoiceAmountDto;
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
    private PersonDao personDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private PartnerDao partnerDao;
    @Autowired
    private InvoiceDao invoiceDao;
    @Autowired
    private InvoiceDetailDao invoiceDetailDao;

    @Override
    public void createPartner( PersonDto personDto, PartnerDto partnerDto ) throws Exception {
        personDto = this.personDao.findByDocument( personDto );
        if ( personDto == null ){
            throw new Exception("No existe la persona");
        }

        UserDto userDto = this.userDao.findByPersonId( personDto );
        if ( userDto == null ) {
            throw new Exception("No se encontró ningún usuario con el número de identificación ingresado");            
        }
        
        if ( this.partnerDao.existsByUserId( userDto ) ){
            throw new Exception( personDto.getName() + " ya es SOCIO del club");
        }
        
        partnerDto.setUserId( Helper.parse( userDto ) );
        
        if ( partnerDto.getType().equals( "VIP" ) ){
            long numberVIP = this.partnerDao.numberPartnersVIP();
            if ( numberVIP >= 5 ){
                throw new Exception("Cupo de socios VIP copado");                
            }
        }
        
        this.partnerDao.createPartner( partnerDto );
    }
    
    @Override
    public void updatePartnerAmount( PersonDto personDto, PartnerDto partnerDto ) throws Exception {
        PersonDto personDtoLocal = this.personDao.findByDocument( personDto );
        if ( personDtoLocal == null ){
            throw new Exception("No existe la persona");
        }

        UserDto userDto = this.userDao.findByPersonId( personDtoLocal );
        if ( userDto == null ) {
            throw new Exception("No se encontró ningún usuario para: " + personDto.getName() );            
        }
        
        if ( !this.partnerDao.existsByUserId( userDto ) ){
            throw new Exception( personDto.getName() +  " NO es SOCIO del club");
        }
        
        PartnerDto partnerDtoLocal = this.partnerDao.findByUserId( userDto );
        partnerDtoLocal.setAmount( partnerDtoLocal.getAmount() + partnerDto.getAmount() );
        
        if ( partnerDtoLocal.getType().equals( "REGULAR" ) || partnerDtoLocal.getType().equals( "PIDE CAMBIO A VIP" ) ){
            if ( ( partnerDtoLocal.getAmount() ) > 1000000 ){
                throw new Exception( personDtoLocal.getName() +  " excede el monto permitido de inversión");
            }
        }
        else{
            if ( ( partnerDtoLocal.getAmount() ) > 5000000 ){
                throw new Exception( personDtoLocal.getName() +  " excede el monto permitido de inversión");
            }            
        }

        this.partnerDao.updatePartner( partnerDtoLocal );
        
        ArrayList<InvoiceDto> listInvoice =  this.invoiceDao.listPartnerInvoices( partnerDtoLocal );

        List<InvoiceDto> filteredAndSorted = listInvoice.stream()
                .filter(invoice -> "PENDIENTE".equals(invoice.getStatus())) 
                .sorted(Comparator.comparing(InvoiceDto::getCreationDate)) 
                .collect(Collectors.toList());
        
        for ( InvoiceDto invoiceDtoList : filteredAndSorted ) {
            partnerDtoLocal = this.partnerDao.findByUserId( userDto );
            if ( partnerDtoLocal.getAmount() >= invoiceDtoList.getAmount() ){
                this.invoiceDao.cancelInvoice( invoiceDtoList );

                partnerDtoLocal.setAmount( partnerDtoLocal.getAmount() - invoiceDtoList.getAmount() );
                this.partnerDao.updatePartner( partnerDtoLocal );
            }
            else {
                break;
            }
        }
    }

    @Override
    public void deletePartner( PersonDto personDto ) throws Exception {
        personDto = this.personDao.findByDocument( personDto );
        if ( personDto == null ){
            throw new Exception("No existe la persona");
        }
        
        UserDto userDto = this.userDao.findByPersonId( personDto );
        if ( userDto == null ) {
            throw new Exception("No se encontró ningún usuario con el número de identificación ingresado");            
        }
        
        PartnerDto partnerDto = this.partnerDao.findByUserId( userDto );
        
        if ( partnerDto == null ){
            throw new Exception( "No existe el socio");
        }

        double amountActiveInvoices = this.invoiceDao.amountActiveInvoicesByPartner( partnerDto );
        if ( amountActiveInvoices > 0 ){
            throw new Exception( personDto.getName() + " tiene facturas pendientes de pago");
        }
                
        if ( partnerDto.getAmount() > 0 ){
            throw new Exception( "El socio tiene INVERSION disponible");
        }
        
        this.partnerDao.deletePartner( partnerDto );
    }
    
    @Override
    public void updatePartnerType( PersonDto personDto ) throws Exception {
        PersonDto personDtoLocal = this.personDao.findByDocument( personDto );
        if ( personDtoLocal == null ){
            throw new Exception("No existe la persona");
        }

        UserDto userDto = this.userDao.findByPersonId( personDtoLocal );
        if ( userDto == null ) {
            throw new Exception("No se encontró ningún usuario para: " + personDto.getName() );            
        }
        
        PartnerDto partnerDto = this.partnerDao.findByUserId( userDto );
        if ( partnerDto == null ){
            throw new Exception( personDto.getName() + " no es socio del club");                        
        }
        partnerDto.setType( "PIDE CAMBIO A VIP" );
        this.partnerDao.updatePartner( partnerDto );
    }

    @Override
    public void changePartnersToVIP( PersonDto personDto ) throws Exception {
        PersonDto personDtoLocal = this.personDao.findByDocument( personDto );
        if ( personDtoLocal == null){
            throw new Exception("No hay ninguna persona con el numero de identificación: " + String.valueOf( personDto.getDocument() ) );
        }

        UserDto userDto = this.userDao.findByPersonId( personDto );
        if ( userDto == null ){
            throw new Exception("No hay ningún usuario para: " + personDtoLocal.getName() );
        }
        
        if ( !userDto.getRole().equals("ADMINISTRADOR") ){
            throw new Exception( userDto.getUserName() + " no es ADMINISTRADOR" );            
        }

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
        
        ArrayList<PartnerInvoiceAmountDto> listPartnersInvoiceAmount = new ArrayList<PartnerInvoiceAmountDto>() ;

        double invoicesAmount;
        
        for ( PartnerDto partnersDto : listPartners ){
            PartnerInvoiceAmountDto partnerInvoiceAmountDto = new PartnerInvoiceAmountDto();
            partnerInvoiceAmountDto.setId( partnersDto.getId() );
            partnerInvoiceAmountDto.setUserId( partnersDto.getUserId() );
            partnerInvoiceAmountDto.setType( partnersDto.getType() );
            partnerInvoiceAmountDto.setAmount( partnersDto.getAmount() );
            partnerInvoiceAmountDto.setCreationDate( partnersDto.getCreationDate() );
            invoicesAmount = this.invoiceDao.amountCancelInvoicesByPartner( partnersDto );            
            partnerInvoiceAmountDto.setInvoiceAmount( invoicesAmount );
            
            listPartnersInvoiceAmount.add( partnerInvoiceAmountDto );
        }

        List<PartnerInvoiceAmountDto> partersInvoiceAmountSorted = listPartnersInvoiceAmount.stream()
                .sorted( Comparator.comparing( PartnerInvoiceAmountDto::getInvoiceAmount ).reversed()
                        .thenComparing( Comparator.comparing( PartnerInvoiceAmountDto::getAmount ).reversed() )
                        .thenComparing( PartnerInvoiceAmountDto::getCreationDate ) 
                )
                .collect(Collectors.toList());

        for ( PartnerInvoiceAmountDto partnerInvoiceAmountDto : partersInvoiceAmountSorted ){
            PartnerDto partnerDto = new PartnerDto();
            partnerDto.setId( partnerInvoiceAmountDto.getId() );
            partnerDto.setUserId( partnerInvoiceAmountDto.getUserId() );
            partnerDto.setType( partnerInvoiceAmountDto.getType() );
            partnerDto.setAmount( partnerInvoiceAmountDto.getAmount() );
            partnerDto.setCreationDate( partnerInvoiceAmountDto.getCreationDate() );
                        
            partnerDto.setType( "VIP" );

            this.partnerDao.updatePartner( partnerDto );
            numberVIP = this.partnerDao.numberPartnersVIP();
            if ( numberVIP >= 5 ){
                break;
            }
        }
    }
}
