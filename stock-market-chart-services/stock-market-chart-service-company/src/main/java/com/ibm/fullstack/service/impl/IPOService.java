package com.ibm.fullstack.service.impl;

import com.ibm.fullstack.dao.IPODao;
import com.ibm.fullstack.entity.IPOsPlanned;
import com.ibm.fullstack.service.IIPOService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class IPOService implements IIPOService {

    @Autowired
    private IPODao ipoDao;

    public IPOService(IPODao ipoDao) {
        this.ipoDao = ipoDao;
    }

    @Override
    public IPOsPlanned createNewIPO(IPOsPlanned ipOsPlanned) {
        return this.ipoDao.save(ipOsPlanned);
    }

    @Override
    public List<IPOsPlanned> getIPOList() {
        Sort sort = new mySort(new ArrayList<Sort.Order>());
        sort.by(Sort.Direction.DESC, "openDateTime");
        return ipoDao.findAll(sort);
    }
}

class mySort extends Sort {

    protected mySort(List<Order> orders) {
        super(orders);
    }
}