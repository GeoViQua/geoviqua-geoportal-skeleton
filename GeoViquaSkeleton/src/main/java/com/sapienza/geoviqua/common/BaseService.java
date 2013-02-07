package com.sapienza.geoviqua.common;

import com.sapienza.geoportal.chore.common.model.UserGlobalSearch;

/**
 *
 * @author kmadonna
 */
public abstract  class BaseService  {

    
	protected UserGlobalSearch userGlobalSearch;
	
    
	public UserGlobalSearch getUserGlobalSearch() {
		return this.userGlobalSearch;
	}
	public void setUserGlobalSearch(UserGlobalSearch userGlobalSearch) {
		this.userGlobalSearch = userGlobalSearch;
	}

        
}
