package models;

import play.db.jpa.Model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import java.util.Set;

@Entity
public class Site extends Model {

    //    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    private Long id;
    private String domain;
    private String plan;
    private Long ownerId;
    private Integer leadCount;

    @OneToMany
    @JoinColumn(name = "siteId")
    private Set<SiteLabel> labels;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getPlan() {
        return plan;
    }

    public void setPlan(String plan) {
        this.plan = plan;
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }

    public Integer getLeadCount() {
        return leadCount;
    }

    public void setLeadCount(Integer leadCount) {
        this.leadCount = leadCount;
    }

    public Set<SiteLabel> getLabels() {
        return labels;
    }

    public void setLabels(Set<SiteLabel> labels) {
        this.labels = labels;
    }

    @Override
    public String toString() {
        return "Site{" +
                "id='" + id + '\'' +
                ", domain='" + domain + '\'' +
                ", plan='" + plan + '\'' +
                ", ownerId='" + ownerId + '\'' +
                ", leadCount=" + leadCount +
                ", labels=" + labels.toString() +
                '}';
    }
}
