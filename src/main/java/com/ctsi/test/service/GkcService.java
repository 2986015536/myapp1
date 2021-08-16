package com.ctsi.test.service;



import com.ctsi.ssdc.model.PageResult;
import org.springframework.data.domain.Pageable;

import com.ctsi.test.domain.Gkc;
import com.ctsi.test.domain.GkcExample;
import com.ctsi.ssdc.service.StrengthenBaseService;

/**
 * Service Interface for managing Gkc.
 *
 * @author ctsi-biyi-generator
 *
 */
public interface GkcService 
	extends StrengthenBaseService<Gkc, Integer, GkcExample>{


    /**
    * GET  /gkcs : get the gkcs firstStringBaseColumn.
    */
    PageResult<Gkc> findFirstStringColumn(String name ,Pageable pageable);

}
