package com.diamond.iain.spring.web.test.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

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

import com.diamond.iain.spring.web.dao.User;
import com.diamond.iain.spring.web.dao.UsersDao;

@ActiveProfiles("dev")
@ContextConfiguration(locations = {
		"classpath:com/diamond/iain/spring/web/config/dao-context.xml",
		"classpath:com/diamond/iain/spring/web/config/security-context.xml",
		"classpath:com/diamond/iain/spring/web/test/config/datasource.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class UserDaoTests {

	@Autowired
	private UsersDao usersDao;

	@Autowired
	private DataSource dataSource;

	private User user1 = new User("VladtheImpaler", "Vlad Impaler",
			"deathtotheinfidel", "vlad@caveofprogramming.com", true,
			"ROLE_ADMIN");
	private User user2 = new User("Bilbo", "Bilbo Baggins",
			"whatsforbreakfasttoday", "bilble@caveofprogramming.com", true,
			"ROLE_USER");
	private User user3 = new User("Harry Hoodini", "Harold Hillson",
			"imnotreallyhere", "harry@caveofprogramming.com", true, "ROLE_USER");
	private User user4 = new User("BarrytheBear", "Barry Bear", "sleepybear",
			"barry@caveofprogramming.com", true, "ROLE_USER");

	@Before
	public void init() {
		JdbcTemplate jdbc = new JdbcTemplate(dataSource);

		jdbc.execute("delete from offers");
		jdbc.execute("delete from users");
	}

	@Test
	public void testCreateRetrieve() {
		usersDao.create(user1);
		
		List<User> users = usersDao.getAllUsers();
		
		assertEquals("One user should have been created and retrieved", 1, users.size());
	
		assertEquals("Inserted user should match retrieved", user1, users.get(0));

		usersDao.create(user2);
		usersDao.create(user3);
		usersDao.create(user4);

		users = usersDao.getAllUsers();

		assertEquals("Four users should have been created and retrieved", 4, users.size());
	
	}
	
	@Test
	public void testExists(){

		usersDao.create(user1);
		usersDao.create(user2);
		usersDao.create(user3);
		
		assertTrue("User should exist.", usersDao.exists(user2.getUsername()));
		assertFalse("User should not exist.", usersDao.exists("Vlad"));
	}
}
