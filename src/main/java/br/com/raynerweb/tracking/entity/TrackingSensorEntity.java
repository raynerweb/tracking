package br.com.raynerweb.tracking.entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "TB_TRACKING_SENSOR")
public class TrackingSensorEntity {

    @Id
    @SequenceGenerator(name = "TB_TRACKING_SENSOR_COD_TRACKING_SENSOR_SEQ", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "COD_TRACKING_SENSOR", updatable = false)
    private Long id;

    @Column(name = "COD_VEHICLE", updatable = false, nullable = false)
    private Long vehicleId;

    @Column(name = "COD_SENSOR", updatable = false, nullable = false, columnDefinition = "VARCHAR(36)")
    private String sensorId;

    @Column(name = "TXT_DATA", updatable = false, nullable = false)
    private String sensorData;

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

    public String getSensorId() {
        return sensorId;
    }

    public void setSensorId(String sensorId) {
        this.sensorId = sensorId;
    }

    public String getSensorData() {
        return sensorData;
    }

    public void setSensorData(String sensorData) {
        this.sensorData = sensorData;
    }

    public Date getTrackingDate() {
        return trackingDate;
    }

    public void setTrackingDate(Date trackingDate) {
        this.trackingDate = trackingDate;
    }
}
