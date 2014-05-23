package com.tyrael.ninja.web.resource;

import java.util.List;

import org.dozer.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baldy.commons.models.firm.Firm;
import com.baldy.commons.models.firm.FirmInfo;
import com.baldy.commons.repo.firm.FirmService;
import com.google.common.collect.Lists;

import static org.springframework.web.bind.annotation.RequestMethod.*;
import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/firm")
public class FirmResource {

    private static final Logger LOG = LoggerFactory.getLogger(FirmResource.class);

    @Autowired
    private Mapper mapper;

    @Autowired
    private FirmService firmService;

    @RequestMapping(method = GET)
    public ResponseEntity<List<FirmInfo>> get() {
        Iterable<Firm> firms = firmService.findAll();
        List<FirmInfo> firmDtos = Lists.newArrayList();
        for (Firm firm : firms) {
            firmDtos.add(mapper.map(firm, FirmInfo.class));
        }
        return new ResponseEntity<>(firmDtos, OK);
    }

    @RequestMapping(method = POST)
    public ResponseEntity<FirmInfo> newFirm(@RequestBody FirmInfo firmInfo) {
        LOG.debug("Firm save request. firm={}", firmInfo);
        Firm firm = mapper.map(firmInfo, Firm.class);
        return new ResponseEntity<>(mapper.map(firmService.save(firm), FirmInfo.class), OK);
    }
}
