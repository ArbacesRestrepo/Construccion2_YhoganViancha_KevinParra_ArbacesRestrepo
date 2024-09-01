package App.Dao;

/**
 * @author Arbaces Restrepo, Jhogan Viancha
 */

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import App.Config.MYSQLConnection;
import App.Dao.Interfaces.InvoiceDetailDaoInterface;

import App.Dto.InvoiceDto;
import App.Dto.InvoiceDetailDto;
import App.Model.InvoiceDetail;

import App.Helper.Helper;

public class InvoiceDetailDao implements InvoiceDetailDaoInterface {

    @Override
    public void createInvoiceDetail( InvoiceDetailDto invoiceDetailDto ) throws Exception {
        String query = "INSERT INTO INVOICEDETAIL ( INVOICEID, ITEM, DESCRIPTION, AMOUNT ) VALUES ( ?, ?, ?, ? )";
        PreparedStatement preparedStatement = MYSQLConnection.getConnection().prepareStatement(query);
        preparedStatement.setLong( 1, invoiceDetailDto.getInvoiceId() );
        preparedStatement.setLong( 2, invoiceDetailDto.getItemNumber() );
        preparedStatement.setString( 3, invoiceDetailDto.getDescription() );
        preparedStatement.setDouble( 4, invoiceDetailDto.getItemValue() );
        
        preparedStatement.execute();
        preparedStatement.close();                
    }
    
    @Override
    public void deleteInvoiceDetail( InvoiceDto invoiceDto ) throws Exception {
        String query = "DELETE FROM INVOICEDETAIL WHERE INVOICEID = ?";
        PreparedStatement preparedStatement = MYSQLConnection.getConnection().prepareStatement(query);
        preparedStatement.setLong( 1, invoiceDto.getId() );

        preparedStatement.execute();
        preparedStatement.close();                
    }

    @Override
    public InvoiceDetailDto listInvoiceDetails(InvoiceDetailDto invoiceDetailDto) throws Exception {
        String query = "SELECT 1 ID, INVOICEID, ITEM, DESCRIPTION, AMOUNT FROM INVOICEDETAIL WHERE INVOICEID = ? AND ITEM > ? ";
        PreparedStatement preparedStatement = MYSQLConnection.getConnection().prepareStatement(query);
        preparedStatement.setLong( 1, invoiceDetailDto.getInvoiceId() );
        preparedStatement.setInt( 2, invoiceDetailDto.getItemNumber() );
        ResultSet resulSet = preparedStatement.executeQuery();

        if (resulSet.next()) {
            InvoiceDetail invoiceDetail = new InvoiceDetail();
            invoiceDetail.setId( resulSet.getLong( "ID" ) );
            invoiceDetail.setInvoiceId( resulSet.getLong( "INVOICEID" ) );
            invoiceDetail.setItemNumber( resulSet.getInt( "ITEM" ) );
            invoiceDetail.setDescription( resulSet.getString( "DESCRIPTION" ) );
            invoiceDetail.setItemValue( resulSet.getDouble( "AMOUNT" ) );

            resulSet.close();
            preparedStatement.close();
            return Helper.parse(invoiceDetail);
        }
                
        resulSet.close();
        preparedStatement.close();        
        return null;
    }

    @Override
    public double totalInvoiceDetails( InvoiceDto invoiceDto ) throws Exception {
        String query = "SELECT SUM( AMOUNT ) AS AMOUNT FROM INVOICEDETAIL WHERE INVOICEID = ? ";
        PreparedStatement preparedStatement = MYSQLConnection.getConnection().prepareStatement(query);
        preparedStatement.setLong( 1, invoiceDto.getId() );
        ResultSet resulSet = preparedStatement.executeQuery();

        if (resulSet.next()) {
            double amount = resulSet.getDouble( "AMOUNT" );

            resulSet.close();
            preparedStatement.close();
            return amount;
        }
                
        resulSet.close();
        preparedStatement.close();        
        return 0;
    }

}
