package com.thesevensky.ttms.moviesmanageapi.pojo.user;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.thesevensky.ttms.moviesmanageapi.commons.jsonserializer.LongJsonDeserializer;
import com.thesevensky.ttms.moviesmanageapi.commons.jsonserializer.LongJsonSerializer;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * @Author TheSevenSky
 * @Date: 2019/5/19 21:03
 * @Version 1.0
 */
public class Authority implements Serializable {

    private static final long serialVersionUID = 6277769910061116789L;
    @JsonDeserialize(using = LongJsonDeserializer.class)
    @JsonSerialize(using = LongJsonSerializer.class)
    @ApiModelProperty("权限ID")
    private Long authorityId;
    @ApiModelProperty("权限的中文名显示用的")
    private String authorityName;
    @ApiModelProperty("权限的英文名 前台不用管")
    private String authoritySimpleName;
    @ApiModelProperty("权限的位操作")
    private Byte authorityBit;
    @ApiModelProperty("不用管")
    private Byte authorityState = 0;
    @ApiModelProperty("不用管")
    private String authorityUpdateTime;

    public Authority() {
    }

    public Authority(Long authorityId, String authorityName, String authoritySimpleName, Byte authorityBit, Byte authorityState, String authorityUpdateTime) {
        this.authorityId = authorityId;
        this.authorityName = authorityName;
        this.authoritySimpleName = authoritySimpleName;
        this.authorityBit = authorityBit;
        this.authorityState = authorityState;
        this.authorityUpdateTime = authorityUpdateTime;
    }

    public Long getAuthorityId() {
        return authorityId;
    }

    public void setAuthorityId(Long authorityId) {
        this.authorityId = authorityId;
    }

    public String getAuthorityName() {
        return authorityName;
    }

    public void setAuthorityName(String authorityName) {
        this.authorityName = authorityName;
    }

    public String getAuthoritySimpleName() {
        return authoritySimpleName;
    }

    public void setAuthoritySimpleName(String authoritySimpleName) {
        this.authoritySimpleName = authoritySimpleName;
    }

    public Byte getAuthorityBit() {
        return authorityBit;
    }

    public void setAuthorityBit(Byte authorityBit) {
        this.authorityBit = authorityBit;
    }

    public Byte getAuthorityState() {
        return authorityState;
    }

    public void setAuthorityState(Byte authorityState) {
        this.authorityState = authorityState;
    }

    public String getAuthorityUpdateTime() {
        return authorityUpdateTime;
    }

    public void setAuthorityUpdateTime(String authorityUpdateTime) {
        this.authorityUpdateTime = authorityUpdateTime;
    }

    @Override
    public String toString() {
        return "Authority{" +
                "authorityId=" + authorityId +
                ", authorityName='" + authorityName + '\'' +
                ", authoritySimpleName='" + authoritySimpleName + '\'' +
                ", authorityBit=" + authorityBit +
                ", authorityState=" + authorityState +
                ", authorityUpdateTime='" + authorityUpdateTime + '\'' +
                '}';
    }
}
