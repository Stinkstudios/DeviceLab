package eu.revevol.calendar.model;

import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.Ref;
import com.googlecode.objectify.annotation.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Clement Hannicq <clement.hannicq@revevol.eu>
 */
@Entity
@Cache
public class Reservation {

    @Id public Long id; //Autogenerated
    public String title;
    private @Load List<Ref<Asset>> assets = new ArrayList<Ref<Asset>>();
    @Index public String person;
    public int start;
    public int end;
    @Index public Date date;
    @Index public String realDate; //this is the real date used for the search and matching in datastore filter
    @Index public Long idPurpose;
    @Index public String dayString;
    
    @Ignore public Purpose purpose;

    public List<Asset> getAssets() {
        List<Asset> r = new ArrayList<Asset>();
        for (Ref<Asset> ref : assets) {
            // For some reason, r.add(ref.get()) or r.add(ref.safe()) doesn't find the entities
            // namespaces+Objectify compatibility issues?
            r.add(ObjectifyService.ofy().load().type(Asset.class).id(ref.getKey().getId()).safe());
        }
        return r;
    }

    public void setAssets(List<Asset> values) {
        List<Ref<Asset>> l = new ArrayList<Ref<Asset>>();
        for (Asset value : values) {
            l.add(Ref.create(value));
        }
        assets = l;
    }
}
