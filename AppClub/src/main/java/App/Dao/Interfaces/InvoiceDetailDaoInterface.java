package App.Dao.Interfaces;

/**
 * @author Arbaces Restrepo, Yhogan Viancha, Kevin Parra
 */

import App.Dto.InvoiceDetailDto;
import App.Dto.InvoiceDto;
import java.util.ArrayList;

public interface InvoiceDetailDaoInterface {
    public void createInvoiceDetail( InvoiceDetailDto invoiceDetailDto ) throws Exception ;
    public void deleteInvoiceDetail( InvoiceDto invoiceDto ) throws Exception ;
    public double totalInvoiceDetails( InvoiceDto invoiceDto ) throws Exception ;
    public int countInvoiceDetails( InvoiceDto invoiceDto ) throws Exception ;
    public ArrayList<InvoiceDetailDto> listInvoiceDetails( InvoiceDto invoiceDto ) throws Exception ;

}
