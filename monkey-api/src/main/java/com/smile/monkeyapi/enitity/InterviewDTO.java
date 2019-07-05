package com.smile.monkeyapi.enitity;

/**
 * @ClassName InterviewDTO
 * @Author kris
 * @Date 2019/7/5
 **/
public class InterviewDTO {

    private Long id;
    private String ip;
    private String location;
    private Long loadTime;
    private String account;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Long getLoadTime() {
        return loadTime;
    }

    public void setLoadTime(Long loadTime) {
        this.loadTime = loadTime;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    @Override
    public String toString() {
        return "InterviewDTO{" +
                "id=" + id +
                ", ip='" + ip + '\'' +
                ", location='" + location + '\'' +
                ", loadTime=" + loadTime +
                ", account='" + account + '\'' +
                '}';
    }
}
