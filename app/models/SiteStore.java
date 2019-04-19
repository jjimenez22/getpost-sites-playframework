package models;

import java.util.HashMap;
import java.util.Map;

public class SiteStore {
    private static Map<Long, Site> store = new HashMap<>();
    private static long count = 0;

    public static Site add(Site site) {
        site.setId(count++);
        store.put(site.getId(), site);
        return site;
    }

    public static Site get(Long id) {
        return store.get(id);
    }
}
