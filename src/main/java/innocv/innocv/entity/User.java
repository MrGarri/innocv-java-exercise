package innocv.innocv.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Entity
@Data
public class User {

    @Id
    Integer id;
    String name;
    @JsonProperty("birth_date")
    Date birthDate;

}
