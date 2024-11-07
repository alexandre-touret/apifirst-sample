package info.touret.apifirst.sample;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@Service
public class GuitarService {

    public List<Guitar> findAllGuitars() {
        return List.of(new Guitar("Gibson", "ES-335", Date.from(LocalDate.of(1958, 1, 1).atStartOfDay(ZoneId.systemDefault()).toInstant())),
                new Guitar("Gibson", "Lucille", Date.from(LocalDate.of(1980, 1, 1).atStartOfDay(ZoneId.systemDefault()).toInstant())));
    }

    public Guitar updateGuitar(Guitar guitar) {
        return guitar;
    }
}
