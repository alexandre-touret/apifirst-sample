package info.touret.apifirst.sample;

import info.touret.apifirst.sample.generated.Guitar;
import info.touret.apifirst.sample.generated.Guitars;
import info.touret.apifirst.sample.generated.GuitarsApi;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
public class GuitarController implements GuitarsApi {

    private final GuitarService guitarService;

    public GuitarController(GuitarService guitarService) {
        this.guitarService = guitarService;
    }


    @Override
    public ResponseEntity<Guitars> findAllGuitars() {
        return ResponseEntity.ok(new Guitars(guitarService.findAllGuitars()));
    }

    @Override
    public ResponseEntity<Object> createGuitar(@Parameter(description = "Guitar object that needs to be added")
                                             @RequestBody Guitar guitar) {
        return ResponseEntity.status(201).build();
    }

    @Override
    public ResponseEntity<Guitar> updateGuitar(@Parameter(description = "ID of the guitar to update")
                                               @PathVariable String id,
                                               @RequestBody Guitar guitar) {
        return ResponseEntity.status(200).body(guitar);
    }

    @Override
    public ResponseEntity<Object> deleteGuitar(@Parameter(description = "ID of the guitar to delete")
                                               @PathVariable String id) {
        return ResponseEntity.noContent().build();
    }

}
