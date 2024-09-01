package App.Dao.Interfaces;

/**
 * @author Arbaces Restrepo, Jhogan Viancha
 */

import App.Dto.InvoiceDetailDto;
import App.Dto.InvoiceDto;

public interface InvoiceDetailDaoInterface {
    public void createInvoiceDetail( InvoiceDetailDto invoiceDetailDto ) throws Exception ;
    public void deleteInvoiceDetail( InvoiceDto invoiceDto ) throws Exception ;
    public InvoiceDetailDto listInvoiceDetails( InvoiceDetailDto invoiceDetailDto ) throws Exception ;
    public double totalInvoiceDetails( InvoiceDto invoiceDto ) throws Exception ;
}
