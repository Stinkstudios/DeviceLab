package eu.revevol.calendar.model;

import com.googlecode.objectify.annotation.*;
import java.util.Date;

/**
 *
 * @author Admin
 */
@Entity
@Cache
public class News {
    @Id public Long id; //autogenerated
    @Index public Date date;
    public String title;
    public String content;
    public String author;
    public String authorName;
    public boolean broadcast;
    @Index public Date expire;
}
