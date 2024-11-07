package info.touret.apifirst.sample;

import info.touret.apifirst.sample.generated.Guitar;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.List;

@Service
public class GuitarService {

    public List<Guitar> findAllGuitars() {
        return List.of(new Guitar("Gibson", "ES-335", OffsetDateTime.of(1958, 1, 1, 0, 0, 0, 0, ZoneOffset.UTC)),
                new Guitar("Gibson", "Lucille", OffsetDateTime.of(1980, 1, 1, 0, 0, 0, 0, ZoneOffset.UTC)));
    }

    public Guitar updateGuitar(Guitar guitar) {
        return guitar;
    }
}
