package com.kurui.kums.message;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public abstract class BaseDomainObject implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public boolean equals(Object o) {
        if (o == null) {
            return false;
        } else {
            String a = toString();
            String b = o.toString();
            return (a.equals(b));
        }
    }

    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }

    public int hashCode() {
        return System.identityHashCode(this);
    }

}
