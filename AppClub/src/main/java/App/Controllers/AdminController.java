package App.Controllers;

/**
 * @author Arbaces Restrepo, Yhogan Viancha, Kevin Parra
 */

import App.Controllers.Validator.PersonValidator;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Getter
@Setter
@NoArgsConstructor
@RestController

public class AdminController implements ControllerInterface{
    @Override
    public void session() throws Exception {
    }
    
    @GetMapping("/")
    public String Active(){
        return "Está corriendo";
    }    
}
