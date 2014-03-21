package com.diamond.iain.spring.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

import com.diamond.iain.spring.web.dao.Offer;
import com.diamond.iain.spring.web.dao.OffersDao;

@Service("offersService")
public class OffersService {
	
	private OffersDao offersDao;
	
	@Autowired
	public void setOffersDao(OffersDao offersDao) {
		this.offersDao = offersDao;
	}

	public List<Offer> getCurrent() {
		return offersDao.getOffers();
	}

	@Secured({"ROLE_USER", "ROLE_ADMIN"} )
	public void create(Offer offer) {
		offersDao.create(offer);
	}

	public boolean hasOffer(String name) {
		
		List<Offer> offers = offersDao.getOffers(name);
		
		return (name == null || offers.size() == 0) ? false : true;
	}

	public Offer getOffer(String name) {
		
		if (name == null) return null;

		List<Offer> offers = offersDao.getOffers(name);
		
		if (offers.size() == 0) return null;
		
		return offers.get(0);
	}

	public void update(Offer offer) {
		
		if (offer.getId() != 0) {
			offersDao.update(offer);
		}
		else {
			offersDao.create(offer);
		}
	}

	public void delete(int id) {
		offersDao.delete(id);
	}
}
	
