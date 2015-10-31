package ar.com.gaf.mycashflow.dao.filters.commons;

import java.io.Serializable;

public class Paginator implements Serializable {
	private static final long serialVersionUID = -5171527757290135L;
	
	private int first;
	private int pageSize;
	private int rowCount;
    private boolean onlyCount;

    public int getFirst() {
		return first;
	}
	
	public void setFirst(int first) {
		this.first = first;
	}
	
	public int getPageSize() {
		return pageSize;
	}
	
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	
	public int getRowCount() {
		return rowCount;
	}
	
	public void setRowCount(int rowCount) {
		this.rowCount = rowCount;
	}

    public boolean isOnlyCount() {
        return onlyCount;
    }

    public void setOnlyCount(boolean onlyCount) {
        this.onlyCount = onlyCount;
    }
}
