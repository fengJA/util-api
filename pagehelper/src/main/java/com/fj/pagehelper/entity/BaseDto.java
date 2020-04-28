package com.fj.pagehelper.entity;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * @author liubin
 * @DESC 类描述信息
 * @createTime 2020-02-24
 */

public class BaseDto implements Serializable {

    /**
     *主键
     */
    @ApiModelProperty(value = "主键")
    public String id;

    /**
     *创建时间
     */
    @ApiModelProperty(value = "创建时间")
    public String createdAt;

    /**
     *创建人
     */
    @ApiModelProperty(value = "创建人")
    public String createdById;

    /**
     *修改时间
     */
    @ApiModelProperty(value = "修改时间")
    public String updatedAt;

    /**
     *修改人
     */
    @ApiModelProperty(value = "修改人")
    public String updatedById;

    /**
     *删除时间
     */
    @ApiModelProperty(value = "删除时间")
    public String deletedAt;

    /**
     *删除人id
     */
    @ApiModelProperty(value = "删除人id")
    public String deletedById;

    /**
     *删除标志位
     */
    public String deleted;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getCreatedById() {
        return createdById;
    }

    public void setCreatedById(String createdById) {
        this.createdById = createdById;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getUpdatedById() {
        return updatedById;
    }

    public void setUpdatedById(String updatedById) {
        this.updatedById = updatedById;
    }

    public String getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(String deletedAt) {
        this.deletedAt = deletedAt;
    }

    public String getDeletedById() {
        return deletedById;
    }

    public void setDeletedById(String deletedById) {
        this.deletedById = deletedById;
    }

    public String getDeleted() {
        return deleted;
    }

    public void setDeleted(String deleted) {
        this.deleted = deleted;
    }
}
