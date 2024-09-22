package App.Dao.Interfaces;

/**
 * @author Arbaces Restrepo, Yhogan Viancha, Kevin Parra
 */

import App.Dto.PersonDto;
import App.Dto.PartnerDto;

import App.Dto.InvoiceDto;
import java.util.ArrayList;


public interface InvoiceDaoInterface {
    public void createInvoice( InvoiceDto invoiceDto ) throws Exception ;
    public void updateInvoiceAmount( InvoiceDto invoiceDto ) throws Exception ;
    public void cancelInvoice( InvoiceDto invoiceDto ) throws Exception ;
    public void deleteInvoice( InvoiceDto invoiceDto ) throws Exception ;
    public double amountActiveInvoices( PersonDto personDto ) throws Exception ;
    public double amountInvoicesByPartner( PartnerDto partnerDto ) throws Exception ;
    public InvoiceDto firstActiveInvoice( PartnerDto partnerDto ) throws Exception ;
    public InvoiceDto firstInvoiceByPartnerId( PartnerDto partnerDto ) throws Exception ;
    public InvoiceDto firstInvoiceByPersonId( PersonDto personDto ) throws Exception ;
    public long lastInvoiceByPartnerId( PartnerDto partnerDto ) throws Exception ;
    public long lastInvoiceByPersonId( PersonDto personDto ) throws Exception ;
    public ArrayList<InvoiceDto> listClubInvoices( ) throws Exception ;
    public ArrayList<InvoiceDto> listPartnerInvoices( PartnerDto partnerDto ) throws Exception ;
    public ArrayList<InvoiceDto> listPersonInvoices( PersonDto personDto ) throws Exception ;
}
