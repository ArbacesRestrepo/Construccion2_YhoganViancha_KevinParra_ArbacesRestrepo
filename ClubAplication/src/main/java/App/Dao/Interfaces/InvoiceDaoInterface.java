package App.Dao.Interfaces;

/**
 * @author Arbaces Restrepo, Jhogan Viancha
 */

import App.Dto.PersonDto;

public interface InvoiceDaoInterface {
    public double amountActiveInvoices( PersonDto personDto ) throws Exception ;
}
