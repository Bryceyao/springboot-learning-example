package org.spring.springboot.domain.po;

import com.google.common.base.MoreObjects;
import org.spring.springboot.domain.tables.TCitya;
import com.welab.xdao.annotation.Entity;
import java.io.Serializable;


/**
 * 4444
 */

@Entity(table=TCitya.class)
public class Citya implements Serializable {

	private static final long serialVersionUID = -2260388125919493487L;
	private String CODENO;//11
	private String ITEMNO;//22
	private String ITEMNAME;//33

    public String getCODENO () {
        return CODENO;
    }

    public void setCODENO (String CODENO) {
        this.CODENO = CODENO;
    }

    public String getITEMNO () {
        return ITEMNO;
    }

    public void setITEMNO (String ITEMNO) {
        this.ITEMNO = ITEMNO;
    }

    public String getITEMNAME () {
        return ITEMNAME;
    }

    public void setITEMNAME (String ITEMNAME) {
        this.ITEMNAME = ITEMNAME;
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).toString();
    }
}