package models;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Objects.nonNull;

/**
 * Class for sending and receiving Sites through REST
 */
public class SiteDTO {
    private Long id;
    private String domain;
    private String plan;
    private Long ownerId;
    private Integer leadCount;
    private String[] labels;

    /**
     * Create {@link SiteDTO} instance from {@link Site}
     */
    public static SiteDTO fromSite(Site site) {
        SiteDTO result = new SiteDTO();
        result.domain = site.getDomain();
        result.id = site.getId();
        result.leadCount = site.getLeadCount();
        result.ownerId = site.getOwnerId();
        result.plan = site.getPlan();
        if (nonNull(site.getLabels())) {
            result.labels = site.getLabels().stream()
                    .map(SiteLabel::getLabel)
                    .toArray(String[]::new);
        }
        return result;
    }

    /**
     * Create {@link Site} instance from this instance.
     */
    public Site toSite() {
        Site result = new Site();
        result.setDomain(domain);
        result.setId(id);
        result.setLeadCount(leadCount);
        result.setPlan(plan);
        result.setOwnerId(ownerId);
        if (nonNull(labels)) {
            result.setLabels(Stream.of(labels)
                    .map(s -> new SiteLabel(id, s))
                    .collect(Collectors.toSet()));
        }
        return result;
    }

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

    public String[] getLabels() {
        return labels;
    }

    public void setLabels(String[] labels) {
        this.labels = labels;
    }

    @Override
    public String toString() {
        return "SiteDTO{" +
                "id=" + id +
                ", domain='" + domain + '\'' +
                ", plan='" + plan + '\'' +
                ", ownerId=" + ownerId +
                ", leadCount=" + leadCount +
                ", labels=" + Arrays.toString(labels) +
                '}';
    }
}
