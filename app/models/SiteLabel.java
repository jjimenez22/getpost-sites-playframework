package models;

import play.db.jpa.Model;

import javax.persistence.Entity;

@Entity
public class SiteLabel extends Model {

//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    private Long id;

    private Long siteId;
    private String label;

    public SiteLabel() {
        super();
    }

    public SiteLabel(Long siteId, String label) {
        super();
        this.siteId = siteId;
        this.label = label;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSiteId() {
        return siteId;
    }

    public void setSiteId(Long siteId) {
        this.siteId = siteId;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
