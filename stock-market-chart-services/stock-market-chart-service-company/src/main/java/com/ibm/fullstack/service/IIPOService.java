package com.ibm.fullstack.service;

import com.ibm.fullstack.entity.IPOsPlanned;

import java.util.List;

public interface IIPOService {

    IPOsPlanned createNewIPO(IPOsPlanned ipOsPlanned);

    List<IPOsPlanned> getIPOList();
}
