package App.Service;

/**
 * @author Arbaces Restrepo, Jhogan Viancha
 */


import App.Service.Intefaces.PartnerServiceInterface;

import App.Dao.PersonDao;
import App.Dao.PartnerDao;
import App.Dao.InvoiceDao;

import App.Dto.PersonDto;
import App.Dto.PartnerDto;

public class PartnerService implements PartnerServiceInterface {
    private final PersonDao personDao = new PersonDao();
    private final PartnerDao partnerDao = new PartnerDao();
    private final InvoiceDao invoiceDao = new InvoiceDao();

    @Override
    public void createPartner( ) throws Exception {
        PartnerDto partnerDto = new PartnerDto();        
        partnerDto.getPartnerDto();
        
        if ( partnerDto.getType() == "VIP" ){
            long numberVIP = this.partnerDao.numberVIP();
            if ( numberVIP >= 5 ){
                throw new Exception("Cupo de socios VIP copado");                
            }
        }

        this.partnerDao.createPartner( partnerDto );
    }
    
    @Override
    public void updatePartner( ) throws Exception {
        
    }

    @Override
    public void deletePartner( ) throws Exception {
        PersonDto personDto = new PersonDto();
        personDto.getPersonDocumentDto();
        personDto = this.personDao.findByDocument( personDto );
        
        if ( personDto == null ){
            throw new Exception("No existe la persona");
        }
        
        double amountActiveInvoices = this.invoiceDao.amountActiveInvoices( personDto );
        if ( amountActiveInvoices > 0 ){
            throw new Exception("La persona tiene facturas pendientes de pago");
        }
        
        PartnerDto partnerDto = partnerDao.findByDocument( personDto );
        
        if ( partnerDto == null ){
            throw new Exception("No existe el socio");                            
        }
        
        this.partnerDao.deletePartner( partnerDto );
    }    
}
