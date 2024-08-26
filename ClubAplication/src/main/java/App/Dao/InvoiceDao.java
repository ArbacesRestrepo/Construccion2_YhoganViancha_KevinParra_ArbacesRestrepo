package App.Dao;

/**
 * @author Arbaces Restrepo, Jhogan Viancha
 */

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import App.Config.MYSQLConnection;

import App.Dao.Interfaces.InvoiceDaoInterface;
import App.Dto.PersonDto;

public class InvoiceDao implements InvoiceDaoInterface {

    @Override
    public double amountActiveInvoices( PersonDto personDto) throws Exception {
        String query = "SELECT SUM( AMOUNT ) AS AMOUNT FROM INVOICE WHERE PERSONID = ? AND STATUS = ?";
        PreparedStatement preparedStatement = MYSQLConnection.getConnection().prepareStatement(query);
        preparedStatement.setLong( 1, personDto.getId() );
        preparedStatement.setString( 2, "PENDIENTE" );
        ResultSet resulSet = preparedStatement.executeQuery();
        if (resulSet.next()) {
            double amointActiveInvoicesDao = resulSet.getDouble( "AMOUNT");
            resulSet.close();
            preparedStatement.close();
            return amointActiveInvoicesDao;
        }
                
        resulSet.close();
        preparedStatement.close();        
        return 0;
    }
    
}
