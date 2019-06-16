package edu.pl.masi.yellow.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

@Entity
@Table(name = "USABILITY_DATA")
public class MetricDataEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "IP")
    private String ipAddress;

    @Column(name = "BROWSER")
    private String browser;

    @JsonProperty("userName")
    @Column(name = "USERNAME")
    private String userName;

    @JsonProperty("mID")
    @Column(name = "M_ID")
    private int numberOfMeasurment;

    @Column(name = "SAVETIME")
    private int timestampSave;

    @JsonProperty("ScreenResW")
    @Column(name = "RES_W")
    private int resolutionW;

    @JsonProperty("ScreenResH")
    @Column(name = "RES_H")
    private int resolutionH;

    @JsonProperty("Clicks")
    @Column(name = "MC")
    private int numberOfClicks;

    @JsonProperty("time")
    @Column(name = "TIME")
    private int time;

    @JsonProperty("distance")
    @Column(name = "DIST")
    private float distance;

    @JsonProperty("fail")
    @Column(name = "FAIL")
    private int failFlag;

    @JsonProperty("error")
    @Column(name = "ERROR")
    private int errorCode;

    public MetricDataEntity() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getBrowser() {
        return browser;
    }

    public void setBrowser(String browser) {
        this.browser = browser;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getNumberOfMeasurment() {
        return numberOfMeasurment;
    }

    public void setNumberOfMeasurment(int numberOfMeasurment) {
        this.numberOfMeasurment = numberOfMeasurment;
    }

    public int getTimestampSave() {
        return timestampSave;
    }

    public void setTimestampSave(int timestampSave) {
        this.timestampSave = timestampSave;
    }

    public int getResolutionW() {
        return resolutionW;
    }

    public void setResolutionW(int resolutionW) {
        this.resolutionW = resolutionW;
    }

    public int getResolutionH() {
        return resolutionH;
    }

    public void setResolutionH(int resolutionH) {
        this.resolutionH = resolutionH;
    }

    public int getNumberOfClicks() {
        return numberOfClicks;
    }

    public void setNumberOfClicks(int numberOfClicks) {
        this.numberOfClicks = numberOfClicks;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public float getDistance() {
        return distance;
    }

    public void setDistance(float distance) {
        this.distance = distance;
    }

    public int getFailFlag() {
        return failFlag;
    }

    public void setFailFlag(int failFlag) {
        this.failFlag = failFlag;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }
}
