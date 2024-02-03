package note_taking_frontend.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NoteTakingResult {

    private Long id;

    private String note;

    private Date created_on;

    private Date update_on;
}