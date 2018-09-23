package org.aviation.control.queue.domain.entity;

import java.io.Serializable;

import javax.persistence.MappedSuperclass;

import org.apache.commons.lang3.ClassUtils;
import org.springframework.data.domain.Persistable;

@MappedSuperclass
public abstract class AbstractUUIDBaseEntity implements Serializable, Persistable<String> {

	private static final long serialVersionUID = -2856591138558240708L;

    // The primary key column name
    public static final String ID = "id";

    public abstract String getId();

    public abstract void setId(String id);

	@Override
    public boolean isNew() {
        return getId() == null;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.getId() == null) ? 0 : this.getId().hashCode());
        return result;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        AbstractUUIDBaseEntity other = (AbstractUUIDBaseEntity) obj;
        if (this.getId() == null) {
            if (other.getId() != null)
                return false;
        } else if (!this.getId().equals(other.getId()))
            return false;
        return true;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(128);
        sb.append(ClassUtils.getShortClassName(this.getClass()));
        sb.append(", Id: ").append(this.getId());
        return sb.toString();
    }
    
}
