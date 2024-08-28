package App.Dao;

/**
 * @author Arbaces Restrepo, Jhogan Viancha
 */

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import App.Config.MYSQLConnection;

import App.Dao.Interfaces.InvoiceDaoInterface;
import App.Dto.InvoiceDto;
import App.Dto.PartnerDto;
import App.Dto.PersonDto;
import App.Helper.Helper;
import App.Model.Invoice;
import java.time.LocalDateTime;

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
    
    @Override
    public InvoiceDto listActiveInvoices( PartnerDto partnerDto ) throws Exception {
        String query = "SELECT 1 ID, PERSONID, PARTNERID, CREATIONDATE, AMOUNT, STATUS FROM INVOICE WHERE PARTNERID = ? AND STATUS = ? ORDER BY CREATIONDATE DESC";
        PreparedStatement preparedStatement = MYSQLConnection.getConnection().prepareStatement(query);
        preparedStatement.setLong( 1, partnerDto.getId() );
        preparedStatement.setString( 2, "PENDIENTE" );
        ResultSet resulSet = preparedStatement.executeQuery();

        if (resulSet.next()) {
            Invoice invoice = new Invoice();
            invoice.setId( resulSet.getLong( "ID" ) );
            invoice.setPersonId( resulSet.getLong( "PERSONID" ) );
            invoice.setPartnerId( resulSet.getLong( "PARTNERID" ) );
            invoice.setCreationDate( resulSet.getDate( "CREATIONDATE" ) );
            invoice.setAmount( resulSet.getDouble( "AMOUNT" ) );
            invoice.setStatus( resulSet.getString( "STATUS" ) );

            resulSet.close();
            preparedStatement.close();
            return Helper.parse(invoice);
        }
                
        resulSet.close();
        preparedStatement.close();        
        return null;
    }

    @Override
    public InvoiceDto listInvoicesByPertnerId( PartnerDto partnerDto ) throws Exception {
        String query = "SELECT 1 ID, PERSONID, PARTNERID, CREATIONDATE, AMOUNT, STATUS FROM INVOICE WHERE PARTNERID = ? ORDER BY CREATIONDATE DESC";
        PreparedStatement preparedStatement = MYSQLConnection.getConnection().prepareStatement(query);
        preparedStatement.setLong( 1, partnerDto.getId() );
        ResultSet resulSet = preparedStatement.executeQuery();

        if (resulSet.next()) {
            Invoice invoice = new Invoice();
            invoice.setId( resulSet.getLong( "ID" ) );
            invoice.setPersonId( resulSet.getLong( "PERSONID" ) );
            invoice.setPartnerId( resulSet.getLong( "PARTNERID" ) );
            invoice.setCreationDate( resulSet.getDate( "CREATIONDATE" ) );
            invoice.setAmount( resulSet.getDouble( "AMOUNT" ) );
            invoice.setStatus( resulSet.getString( "STATUS" ) );

            resulSet.close();
            preparedStatement.close();
            return Helper.parse(invoice);
        }
                
        resulSet.close();
        preparedStatement.close();        
        return null;
    }
    
    @Override
    public long lastInvoiceByPartnerId( PartnerDto partnerDto ) throws Exception {
        String query = "SELECT MAX( ID ) AS ID FROM INVOICE WHERE PARTNERID = ? ";
        PreparedStatement preparedStatement = MYSQLConnection.getConnection().prepareStatement(query);
        preparedStatement.setLong( 1, partnerDto.getId() );
        ResultSet resulSet = preparedStatement.executeQuery();

        if (resulSet.next()) {
            long lastInvoice = resulSet.getLong( "ID" );
            return lastInvoice;
        }
                
        resulSet.close();
        preparedStatement.close();        
        return 0;
    }

    @Override
    public void createInvoice( InvoiceDto invoiceDto ) throws Exception {
        String query = "INSERT INTO INVOICE ( PERSONID, PARTNERID, CREATIONDATE, AMOUNT, STATUS ) VALUES ( ?, ?, ?, 0, ? )";
        PreparedStatement preparedStatement = MYSQLConnection.getConnection().prepareStatement(query);
        preparedStatement.setLong( 1, invoiceDto.getPersonId() );
        preparedStatement.setLong( 2, invoiceDto.getPartnerId() );
        preparedStatement.setString( 3, LocalDateTime.now().toString() );
        preparedStatement.setString( 4, "PENDIENTE" );
        
        preparedStatement.execute();
        preparedStatement.close();                
    }
    
    @Override
    public void updateInvoiceAmount( InvoiceDto invoiceDto ) throws Exception {
        String query = "UPDATE INVOICE SET AMOUNT = ? WHERE ID = ?";
        PreparedStatement preparedStatement = MYSQLConnection.getConnection().prepareStatement(query);
        preparedStatement.setDouble( 1, invoiceDto.getAmount() );
        preparedStatement.setLong( 2, invoiceDto.getId() );

        preparedStatement.execute();
        preparedStatement.close();                
    }

    @Override
    public void deleteInvoice( InvoiceDto invoiceDto ) throws Exception {
        String query = "DELETE FROM INVOICE WHERE ID = ?";
        PreparedStatement preparedStatement = MYSQLConnection.getConnection().prepareStatement(query);
        preparedStatement.setLong( 1, invoiceDto.getId() );

        preparedStatement.execute();
        preparedStatement.close();                
    }

    @Override
    public void cancelInvoice( InvoiceDto invoiceDto ) throws Exception {
        String query = "UPDATE INVOICE SET STATUS = ? WHERE ID = ?";
        PreparedStatement preparedStatement = MYSQLConnection.getConnection().prepareStatement(query);
        preparedStatement.setString( 1, "CANCELADA" );
        preparedStatement.setLong( 2, invoiceDto.getId() );

        preparedStatement.execute();
        preparedStatement.close();                
    }
}
