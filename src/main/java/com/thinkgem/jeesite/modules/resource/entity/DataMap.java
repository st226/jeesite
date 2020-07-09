package com.thinkgem.jeesite.modules.resource.entity;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlTransient;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.thinkgem.jeesite.common.persistence.Page;

public class DataMap  extends HashMap{
    /**
     * 当前实体分页对象
     */
    protected Page<Map> page;

    @JsonIgnore
    @XmlTransient
    public Page<Map> getPage() {
        if (page == null){
            page = new Page<Map>();
        }
        return page;
    }

    public Page<Map> setPage(Page<Map> page) {
        this.page = page;
        return page;
    }
}


