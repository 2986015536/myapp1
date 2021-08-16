package com.ctsi.test.service.impl;


import org.springframework.stereotype.Service;
import org.apache.commons.collections.CollectionUtils;
import com.ctsi.test.domain.Gkc;
import com.ctsi.test.domain.GkcExample;
import com.ctsi.test.service.GkcService;
import com.ctsi.test.repository.GkcRepository;
import org.apache.commons.lang3.StringUtils;
import java.util.List;
import java.util.Objects;
import com.ctsi.ssdc.service.impl.StrengthenBaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import com.ctsi.ssdc.model.PageResult;
import org.springframework.data.domain.Pageable;
import com.github.pagehelper.PageHelper;
/**
 * Service Implementation for managing Gkc.
 *
 * @author ctsi-biyi-generator
 *
 */
@Service
public class GkcServiceImpl 
	extends StrengthenBaseServiceImpl<GkcRepository, Gkc, Integer, GkcExample> 
	implements GkcService {

    @Autowired
    GkcRepository gkcRepository;


    /**
    * GET  /gkcs : get the gkcs firstStringBaseColumn.
    */
    @Override
    public PageResult<Gkc> findFirstStringColumn(String name,Pageable pageable){
        String str = name;
        if (Objects.nonNull(pageable)) {
            PageHelper.startPage(pageable.getPageNumber() + 1, pageable.getPageSize());
        }
        GkcExample gkcExample = new GkcExample();
        String orderBy = getPageOrderBy(pageable);
        if(StringUtils.isNotEmpty(orderBy)) {
            gkcExample.setOrderByClause(orderBy);
        }
        if (StringUtils.isEmpty(str)){
            gkcExample.createCriteria();
        } else{
            gkcExample.createCriteria().andNameLike("%" + str +"%");
        }
        List<Gkc>  data = gkcRepository.selectByExample(gkcExample);

        long count = 0L;
        if (CollectionUtils.isNotEmpty(data))
        {
            count = gkcRepository.countByExample(gkcExample);
        }
        return new PageResult<Gkc>(data,count,count);
    }
    private String getPageOrderBy(Pageable page) {
        if(page!= null && page.getSort() != null) {
            StringBuilder sb = new StringBuilder();
            page.getSort().forEach(sort -> sb.append(sort.getProperty())
            .append(" ").append(sort.getDirection()).append(","));
            if(sb.length() > 1) {
                return (sb.substring(0,sb.length()-1));
             }
        }
        return null;
    }
}
