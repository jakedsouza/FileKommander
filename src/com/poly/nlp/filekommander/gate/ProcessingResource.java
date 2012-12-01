/**
 * 
 */
package com.poly.nlp.filekommander.gate;

import java.util.HashMap;
import java.util.Map;

/**
 * @author neha , jake
 *
 */
public class ProcessingResource {
	
	public String prClass ;
	public Map<String, String> params;
	
	public ProcessingResource(){}
	
	public ProcessingResource(String prClass) {
		this.setPrClass(prClass); 
		this.params = new HashMap<String, String>();
	}
	
	public void addParam(String key , String value){
		this.params.put(key, value);		
	}
	
	public void removeParam(String key){
		this.params.remove(key);		
	}

	public String getPrClass() {
		return prClass;
	}

	public void setPrClass(String prClass) {
		this.prClass = prClass;
	}

	public Map<String, String> getParams() {
		return params;
	}

	public void setParams(Map<String, String> params) {
		this.params = params;
	}
	
	

}
