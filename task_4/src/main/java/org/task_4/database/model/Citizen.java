package org.task_4.database.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import org.task_4.interfaces.Marker;
import org.yaml.snakeyaml.error.Mark;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

//- last_name - фамилия, не может быть пустым
//- first_name - имя, не может быть пустым
//- middle_name - отчество
//- birth_date - дата рождения в формате dd.MM.yyyy
//- phone - контактный телефон в формате +7(XXX)XXX-XX-XX, не может быть пустым
//- extra_phone - дополнительный номер телефона в формате +7(XXX)XXX-XX-XX
//- dul_serie - серия паспорта, допускаются 4 цифры, не может быть пустым
//- dul_number - номер паспорта, допускаются 6 цифр, не может быть пустым

@Entity
@Table(name = "citizens")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Citizen {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "last_name")
    @NotNull(message = "Last name may not be null!", groups = Marker.onCreate.class)
    @NotEmpty(message = "Last name may not be empty!", groups = Marker.onCreate.class)
    private String lastName;

    @Column(name = "first_name")
    @NotNull(message = "First name may not be null!", groups = Marker.onCreate.class)
    @NotEmpty(message = "First name may not be empty!", groups = Marker.onCreate.class)
    private String firstName;

    @Column(name = "middle_name")
    private String middleName;

    @Column(name = "birth_date")
    private Date birthDate;

    @Column(name = "phone")
    @NotNull(message = "Phone may not be null")
    @Pattern(regexp = "^((\\+7)[\\-]?)(\\(\\d{3}\\)[\\-]?)[\\d\\-]{9}$", message = "Invalid phone number!")
    private String phone;

    @Column(name = "extra_phone")
    @Pattern(regexp = "^((\\+7)[\\-]?)(\\(\\d{3}\\)[\\-]?)[\\d\\-]{9}$", message = "Invalid phone number!")
    private String extraPhone;

    @Column(name = "dul_serie")
    @NotNull(message = "Dul serie may not be null!")

    @Pattern(regexp = "^[0-9]\\d*[1-9]\\d*[0-9]*\\d[1-9]\\d*$", message = "Invalid dul serie!")
    private String dulSerie;

    @Column(name = "dul_number")
    @NotNull(message = "Dul number may not be null!")
    @Pattern(regexp = "^[0-9]\\d*[0-9]\\d*[0-9]*\\d[0-9]\\d*[0-9]\\d*[1-9]\\d*$", message = "Invalid dul number!")
    private String dulNumber;

    public void setBirthDate(Date date) {
        this.birthDate = date;
    }

    public void setBirthDate(String birthDate) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        this.birthDate = sdf.parse(birthDate);
    }
}
