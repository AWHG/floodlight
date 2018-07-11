package benbi.util;

import net.floodlightcontroller.routing.Link;

import java.util.Set;

/**
 * Created by jessy on 2017/12/2.
 */
public class CollectionUtil {

    public static void setIterator(Set<Object> set)
    {
        for (Object obj: set) {
            if (obj instanceof Link) {
                Link link = (Link) obj;
                String linkStr = link.toString();
                DatabaseUtil.write(linkStr);
            }
        }
    }
}
