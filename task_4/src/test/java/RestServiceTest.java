import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.task_4.database.model.Citizen;
import org.task_4.database.repository.CitizenRepo;

import java.lang.reflect.Field;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class RestServiceTest {
    @Autowired
    CitizenRepo citizenRepo;

    @Test
    @SneakyThrows
    public void dateTest() {
        Citizen citizen = Citizen.builder()
                .firstName("1")
                .lastName("1")
                .phone("+7(961)681-97-08")
                .dulSerie("1111")
                .dulNumber("111111")
                .build();
        Citizen citizen2 = Citizen.builder()
                .firstName("2")
                .lastName("2")
                .phone("+7(222)681-97-08")
                .dulSerie("2222")
                .dulNumber("222222")
                .build();
        citizenRepo.save(citizen);
        citizenRepo.save(citizen2);


        Field[] fields = citizen.getClass().getFields();
        Field[] fields2 = citizen2.getClass().getFields();

        for (int i = 0; i < fields.length; i++) {
            if (fields2[i] != null) {
                fields[i].setAccessible(true);
                fields[i].set(citizen, fields2[i]);
            }
        }
        System.out.println(citizen);
    }
}
