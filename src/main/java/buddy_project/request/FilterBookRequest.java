package buddy_project.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FilterBookRequest {
    private String name;
    private String author;
    private String category;
    private Double sellingPrice;
    private Date publishDate;
}
