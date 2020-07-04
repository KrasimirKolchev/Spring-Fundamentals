package com.westcoastdealershop.models.serviceModels;

import java.util.Date;

public abstract class BaseServiceModel {
    private String id;
    private Date created;
    private Date modified;

    public BaseServiceModel() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getModified() {
        return modified;
    }

    public void setModified(Date modified) {
        this.modified = modified;
    }
}
