package com.diamond.iain.spring.web.test.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import javax.sql.DataSource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.diamond.iain.spring.web.dao.Offer;
import com.diamond.iain.spring.web.dao.OffersDao;
import com.diamond.iain.spring.web.dao.User;
import com.diamond.iain.spring.web.dao.UsersDao;

@ActiveProfiles("dev")
@ContextConfiguration(locations = {
		"classpath:com/diamond/iain/spring/web/config/dao-context.xml",
		"classpath:com/diamond/iain/spring/web/config/security-context.xml",
		"classpath:com/diamond/iain/spring/web/test/config/datasource.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class OfferDaoTests {

	@Autowired
	private OffersDao offersDao;

	@Autowired
	private UsersDao usersDao;

	@Autowired
	private DataSource dataSource;

	// Some Pre-prepared Test Data
	private User user1 = new User("VladtheImpaler", "Vlad Impaler",
			"deathtotheinfidel", "vlad@caveofprogramming.com", true,
			"ROLE_ADMIN");
	private User user2 = new User("Bilbo", "Bilbo Baggins",
			"whatsforbreakfasttoday", "bilble@caveofprogramming.com", true,
			"ROLE_USER");
	private User user3 = new User("Harry Hoodini", "Harold Hillson",
			"imnotreallyhere", "harry@caveofprogramming.com", true, "ROLE_USER");
	private User user4 = new User("BarrytheBear", "Barry Bear", "sleepybear",
			"barry@caveofprogramming.com", false, "ROLE_USER");

	private Offer offer1 = new Offer(user1, "This is the first test offer text");
	private Offer offer2 = new Offer(user1,
			"This is the second test offer text");
	private Offer offer3 = new Offer(user2, "This is the third test offer text");
	private Offer offer4 = new Offer(user3,
			"This is the fourth test offer text");
	private Offer offer5 = new Offer(user4, "This is the fifth test offer text");
	private Offer offer6 = new Offer(user2, "This is the sixth test offer text");
	private Offer offer7 = new Offer(user1,
			"This is the seventh test offer text");

	@Before
	public void init() {
		JdbcTemplate jdbc = new JdbcTemplate(dataSource);

		jdbc.execute("delete from offers");
		jdbc.execute("delete from messages");
		jdbc.execute("delete from users");
	}

	private void initAllUsersAndOffers() {
		usersDao.create(user1);
		usersDao.create(user2);
		usersDao.create(user3);
		usersDao.create(user4);
		offersDao.saveOrUpdate(offer1);
		offersDao.saveOrUpdate(offer2);
		offersDao.saveOrUpdate(offer3);
		offersDao.saveOrUpdate(offer4);
		offersDao.saveOrUpdate(offer5);
		offersDao.saveOrUpdate(offer6);
		offersDao.saveOrUpdate(offer7);
	}

	@Test
	public void testCreateRetrieveOffers() {

		usersDao.create(user1);
		usersDao.create(user2);
		usersDao.create(user3);
		usersDao.create(user4);

		offersDao.saveOrUpdate(offer1);

		List<Offer> offers = offersDao.getOffers();

		assertEquals("Should be one offer", 1, offers.size());
		assertEquals("Retrieved offer should be first offer", offer1,
				offers.get(0));

		offersDao.saveOrUpdate(offer2);
		offersDao.saveOrUpdate(offer3);
		offersDao.saveOrUpdate(offer4);
		offersDao.saveOrUpdate(offer5);
		offersDao.saveOrUpdate(offer6);
		offersDao.saveOrUpdate(offer7);

		offers = offersDao.getOffers();
		assertEquals("Should be six offers for enabled users", 6, offers.size());
	}

	@Test
	public void getOfferByUsername() {

		initAllUsersAndOffers();

		List<Offer> offers = offersDao.getOffers(user1.getUsername());
		assertEquals("Should be three offers", 3, offers.size());

		offers = offersDao.getOffers("Vlad");
		assertEquals("Should be no offers", 0, offers.size());

		offers = offersDao.getOffers(user3.getUsername());
		assertEquals("Should be one offer", 1, offers.size());
	}

	@Test
	public void testUpdateOffers() {

		initAllUsersAndOffers();
		
		offer1.setText("This Offer has updated text.");
		offersDao.saveOrUpdate(offer3);
		
		Offer retrieved = offersDao.getOffer(offer3.getId());

		assertEquals("Retrieved offer should be updated.", offer3, retrieved);
	}

	@Test 
	public void testOfferDelete() {
		
		initAllUsersAndOffers();
		
		offersDao.delete(offer4.getId());
		
		List<Offer> retrieved = offersDao.getOffers();
		assertTrue ("Number of offers should be 5.", retrieved.size() == 5);
		
		offersDao.delete(offer4.getId());
		retrieved = offersDao.getOffers();
		assertTrue ("Number of offers should still be 5.", retrieved.size() == 5);
		
		offersDao.delete(offer3.getId());
		retrieved = offersDao.getOffers();
		assertTrue ("Number of offers should now be 4.", retrieved.size() == 4);
	}
	
	@Test
	public void testGetOfferById() {
		
		initAllUsersAndOffers();
		
		Offer retrieved = offersDao.getOffer(offer1.getId());
		assertEquals("First offers is retrieved.", offer1, retrieved);
		
		retrieved = offersDao.getOffer(offer5.getId());
		assertEquals("Disabled User's (user4) offer is not retrieved", null, retrieved);
		
	}
}
