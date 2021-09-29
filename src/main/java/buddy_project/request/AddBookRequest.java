package buddy_project.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AddBookRequest {
    private String name;
    private String author;
    private String category;
    private Double sellingPrice;
    private Date publishDate;
}
