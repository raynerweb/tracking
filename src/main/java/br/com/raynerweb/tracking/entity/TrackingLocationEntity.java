package br.com.raynerweb.tracking.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "TB_TRACKING_LOCATION")
public class TrackingLocationEntity {

    @Id
    @SequenceGenerator(name = "TB_TRACKING_LOCATION_COD_TRACKING_LOCATION_SEQ", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "COD_TRACKING_LOCATION", updatable = false)
    private Long id;

    @Column(name = "COD_VEHICLE", updatable = false, nullable = false)
    private Long vehicleId;

    @Column(name = "NUM_LATITUDE", updatable = false, nullable = false, columnDefinition = "NUMERIC(10,7)")
    private BigDecimal latitude;

    @Column(name = "NUM_LONGITUDE", updatable = false, nullable = false, columnDefinition = "NUMERIC(10,7)")
    private BigDecimal longitude;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DTH_TRACKING", updatable = false, nullable = false)
    private Date trackingDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(Long vehicleId) {
        this.vehicleId = vehicleId;
    }

    public BigDecimal getLatitude() {
        return latitude;
    }

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }

    public BigDecimal getLongitude() {
        return longitude;
    }

    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }

    public Date getTrackingDate() {
        return trackingDate;
    }

    public void setTrackingDate(Date trackingDate) {
        this.trackingDate = trackingDate;
    }

}
