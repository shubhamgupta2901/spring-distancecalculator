package com.sg.distcalc;

public class Rate {
	private final String srcCode;
    private final String destCode;
    private final String rate;
    
    public Rate(String srcCode, String destCode, String rate) {
		this.srcCode = srcCode;
		this.destCode = destCode;
		this.rate = rate;
	}
    
	public String getSrcCode() {
		return srcCode;
	}
	public String getDestCode() {
		return destCode;
	}
	public String getRate() {
		return rate;
	}   

}
