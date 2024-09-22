package App.Dao.Repository;
<<<<<<< HEAD
import App.Model.Invoice;
import App.Model.Partner;
import App.Model.Person;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author USUARIO
 */
public interface InvoiceRepository extends JpaRepository<Invoice,Long> {

    public List<Invoice> findByPersonId(Person person);

    public List<Invoice> findByPartnerId(Partner partner);

    public List<Invoice> findById();
    
=======

/**
 * @author Arbaces Restrepo, Yhogan Viancha, Kevin Parra
 */

import App.Model.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvoiceRepository extends JpaRepository<Invoice, Long>{
>>>>>>> 87f852b3a3abf6447aaeddfbdf5233fff6793629
    
}
