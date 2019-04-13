package com.mv.common.vo;

public class PageDownload {
	private String filmName;
	private String operation;
	private long downloadTimes;
	public String getFilmName() {
		return filmName;
	}
	public void setFilmName(String filmName) {
		this.filmName = filmName;
	}
	public String getOperation() {
		return operation;
	}
	public void setOperation(String operation) {
		this.operation = operation;
	}
	public long getDownloadTimes() {
		return downloadTimes;
	}
	public void setDownloadTimes(long downloadTimes) {
		this.downloadTimes = downloadTimes;
	}
}
