package App.Dao.Interfaces;

/**
 * @author Arbaces Restrepo, Jhogan Viancha
 */

import App.Dto.InvoiceDto;

import App.Dto.PersonDto;
import App.Dto.PartnerDto;

public interface InvoiceDaoInterface {
    public void createInvoice( InvoiceDto invoiceDto ) throws Exception ;
    public void updateInvoiceAmount( InvoiceDto invoiceDto ) throws Exception ;
    public void cancelInvoice( InvoiceDto invoiceDto ) throws Exception ;
    public void deleteInvoice( InvoiceDto invoiceDto ) throws Exception ;
    public double amountActiveInvoices( PersonDto personDto ) throws Exception ;
    public InvoiceDto listActiveInvoices( PartnerDto partnerDto ) throws Exception ;
    public InvoiceDto listInvoicesByPertnerId( PartnerDto partnerDto ) throws Exception ;
    public InvoiceDto listInvoicesByPersonId( PersonDto personDto ) throws Exception ;
    public long lastInvoiceByPartnerId( PartnerDto partnerDto ) throws Exception ;
}
