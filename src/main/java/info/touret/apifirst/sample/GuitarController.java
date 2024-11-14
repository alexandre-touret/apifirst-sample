package info.touret.apifirst.sample;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/guitars")
public class GuitarController {

    private final GuitarService guitarService;

    public GuitarController(GuitarService guitarService) {
        this.guitarService = guitarService;
    }

    @Operation(summary = "Retrieve all guitars",
            description = "Fetches a list of all guitars available in the system.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful retrieval of guitars",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Guitars.class,
                                    example = "{ \"guitars\": [ { \"id\": 1, \"brand\": \"Fender\", \"model\": \"Stratocaster\" }, " +
                                            "{ \"id\": 2, \"brand\": \"Gibson\", \"model\": \"Les Paul\" } ] } }"))),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping
    public ResponseEntity<Guitars> findAllGuitars() {
        return ResponseEntity.ok(new Guitars(guitarService.findAllGuitars()));
    }

    @Operation(summary = "Create a new guitar", description = "Creates a new guitar with the provided details.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Guitar created successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Guitar.class,
                                    example = "{ \"id\": 3, \"brand\": \"Yamaha\", \"model\": \"FG800\" }"))),
            @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<Object> createGuitar(@Parameter(description = "Guitar object that needs to be added")
                                             @RequestBody Guitar guitar) {
        return ResponseEntity.status(201).build();
    }

    @Operation(summary = "Update an existing guitar", description = "Updates the details of an existing guitar.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Guitar updated successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Guitar.class,
                                    example = "{ \"id\": 1, \"brand\": \"Fender\", \"model\": \"Stratocaster (Updated)\" }"))),
            @ApiResponse(responseCode = "404", description = "Guitar not found"),
            @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    @PutMapping(value = "/{id}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Guitar> updateGuitar(@Parameter(description = "ID of the guitar to update")
                                               @PathVariable String id,
                                               @RequestBody Guitar guitar) {
        return ResponseEntity.status(200).body(guitar);
    }

    @Operation(summary = "Delete a guitar", description = "Deletes the guitar identified by the given ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Guitar deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Guitar not found")
    })
    @DeleteMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<Object> deleteGuitar(@Parameter(description = "ID of the guitar to delete")
                                             @PathVariable String id) {
        return ResponseEntity.noContent().build();
    }

}
