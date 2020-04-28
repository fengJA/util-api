package com.fj.pagehelper.entity;


/**
 * @author liubin
 * @DESC 操作日志
 * @createTime 2020-02-24
 */

public class OperateHistoryDto extends BaseDto {

    /**
     * 渠道类型 业务类型：1.圈子修改历史；2.话题修改历史；3.帖子修改历史
     */
    public String businessType;

    /**
     *业务Id（圈子Id等）
     */
    public String businessId;

    /**
     *机构业务Id
     */
    public String belongId;

    /**
     *机构名称
     */
    public String belongName;

    /**
     *操作账号
     */
    public String userName;

    /**
     *操作人
     */
    public String haveName;

    /**
     *操作类型 1.新增；2.修改;3.删除；4.启用；5.停用
     */
    public String operateType;

    /**
     *状态变更
     */
    public String stateChange;

    /**
     *修改前的信息
     */
    public String modifyBefore;

    /**
     *修改后的信息
     */
    public String modifyAfter;

    /**
     *备注
     */
    public String remark;

    public OperateHistoryDto(){}

    public OperateHistoryDto(String businessType, String businessId, String belongId, String belongName, String userName, String haveName, String operateType, String remark){
        this.businessType = businessType;
        this.businessId = businessId;
        this.belongId = belongId;
        this.belongName = belongName;
        this.userName = userName;
        this.haveName = haveName;
        this.operateType = operateType;
        this.remark = remark;
    }

    public String getBusinessType() {
        return businessType;
    }

    public void setBusinessType(String businessType) {
        this.businessType = businessType;
    }

    public String getBusinessId() {
        return businessId;
    }

    public void setBusinessId(String businessId) {
        this.businessId = businessId;
    }

    public String getBelongId() {
        return belongId;
    }

    public void setBelongId(String belongId) {
        this.belongId = belongId;
    }

    public String getBelongName() {
        return belongName;
    }

    public void setBelongName(String belongName) {
        this.belongName = belongName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getHaveName() {
        return haveName;
    }

    public void setHaveName(String haveName) {
        this.haveName = haveName;
    }

    public String getOperateType() {
        return operateType;
    }

    public void setOperateType(String operateType) {
        this.operateType = operateType;
    }

    public String getStateChange() {
        return stateChange;
    }

    public void setStateChange(String stateChange) {
        this.stateChange = stateChange;
    }

    public String getModifyBefore() {
        return modifyBefore;
    }

    public void setModifyBefore(String modifyBefore) {
        this.modifyBefore = modifyBefore;
    }

    public String getModifyAfter() {
        return modifyAfter;
    }

    public void setModifyAfter(String modifyAfter) {
        this.modifyAfter = modifyAfter;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
