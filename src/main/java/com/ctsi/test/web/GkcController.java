package com.ctsi.test.web;

import java.net.URI;
import java.net.URISyntaxException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;
import java.lang.Integer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ctsi.test.service.GkcService;
import com.ctsi.test.domain.Gkc;

import com.ctsi.ssdc.exception.BadRequestAlertException;
import com.ctsi.ssdc.model.PageResult;
import com.ctsi.ssdc.util.HeaderUtil;
import com.ctsi.ssdc.util.ResponseUtil;


/**
 * REST controller for managing Gkc.
 *
 * @author ctsi-biyi-generator
 *
 */
@RestController
@RequestMapping("/api")
public class GkcController {

    private final Logger log = LoggerFactory.getLogger(GkcController.class);

    private static final String ENTITY_NAME = "gkc";

    private final GkcService gkcService;

    public GkcController(GkcService gkcService) {
        this.gkcService = gkcService;
    }

    @InitBinder   
    public void initBinder(WebDataBinder binder) {   
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");   
        dateFormat.setLenient(true);   
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));   
    } 
   
    /**
     * POST  /gkcs : Create a new gkc.
     *
     * @param gkc the gkc to create
     * @return the ResponseEntity with status 201 (Created) and with body the new gkc, or with status 400 (Bad Request) if the gkc has already an id
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/gkcs")
    public ResponseEntity<Gkc> createGkc(@RequestBody Gkc gkc) throws URISyntaxException {
        log.debug("REST request to save Gkc : {}", gkc);
        if (gkc.getId() != null) {
            throw new BadRequestAlertException("A new gkc cannot already have an id", ENTITY_NAME, "idexists");
        }
        Gkc result = gkcService.insert(gkc);
        return ResponseEntity.created(new URI("/api/gkcs" + "/" +result.getId() ))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }
	
    /**
     * PUT  /gkcs : Updates an existing gkc.
     *
     * @param gkc the gkc to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated gkc,
     * or with status 400 (Bad Request) if the gkc is not valid,
     * or with status 500 (Internal Server Error) if the gkc couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/gkcs")
    public ResponseEntity<Gkc> updateGkc(@RequestBody Gkc gkc) throws URISyntaxException {
        log.debug("REST request to update Gkc : {}", gkc);
	    if (gkc.getId() == null) {
	    	return createGkc(gkc);
	    }
        Gkc result = gkcService.update(gkc);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * GET  /gkcs/:id : get the "id" gkc.
     *
     * @param id the id of the gkc to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the gkc, or with status 404 (Not Found)
     */
    @GetMapping("/gkcs/{id}")
    public ResponseEntity<Gkc> getGkc(@PathVariable Integer id) {
        log.debug("REST request to get Gkc : {}", id);
        Gkc gkc = gkcService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(gkc));
    }

    /**
    * GET  /gkcs : get the gkcs firstStringBaseColumn.
    *
    * @return the ResponseEntity with status 200 (OK) and the list of gkcs in body
    */
    @GetMapping("/gkcs")
    public PageResult<Gkc> getGkcsList(String name ,Pageable pageable) {
        log.debug("REST request to get GkcsList");
        return gkcService.findFirstStringColumn(name ,pageable);
    }
	
    /**
     * DELETE  /gkcs/:id : delete the "id" gkc.
     *
     * @param id the id of the gkc to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/gkcs/{id}")
    public ResponseEntity<Void> deleteGkc(@PathVariable Integer id) {
        log.debug("REST request to delete Gkc : {}", id);
        gkcService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }


    @RequestMapping("/gkcs/findAll")
    public PageResult<Gkc> getGkcsLists(String name ,Pageable pageable) {
        log.debug("REST request to get GkcsList");
        return gkcService.findFirstStringColumn(name ,pageable);
    }

    
}
