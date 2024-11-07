package info.touret.apifirst.sample;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
class GuitarServiceTest {

    private GuitarService guitarService;

    @BeforeEach
    void setUp() {
        guitarService = new GuitarService();
    }

    @Test
    void should_find_all_guitars_successfully() {
        assertEquals(2, guitarService.findAllGuitars().size());
    }
}
