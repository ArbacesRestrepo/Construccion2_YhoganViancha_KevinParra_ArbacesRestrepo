package App.Dao.Repository;
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
    
    
}
