package com.diamond.iain.spring.web.test.tests;

import static org.junit.Assert.assertEquals;

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

import com.diamond.iain.spring.web.dao.Message;
import com.diamond.iain.spring.web.dao.MessagesDao;
import com.diamond.iain.spring.web.dao.User;
import com.diamond.iain.spring.web.dao.UsersDao;

@ActiveProfiles("dev")
@ContextConfiguration(locations = {
		"classpath:com/diamond/iain/spring/web/config/dao-context.xml",
		"classpath:com/diamond/iain/spring/web/config/security-context.xml",
		"classpath:com/diamond/iain/spring/web/test/config/datasource.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class MessageDaoTests {

	@Autowired
	private MessagesDao messagesDao;

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
			"barry@caveofprogramming.com", true, "ROLE_USER");

	@Before
	public void init() {
		JdbcTemplate jdbc = new JdbcTemplate(dataSource);

		jdbc.execute("delete from offers");
		jdbc.execute("delete from messages");
		jdbc.execute("delete from users");
	}

	@Test
	public void testSave() {
		usersDao.create(user1);
		usersDao.create(user2);
		usersDao.create(user3);
		usersDao.create(user4);

		Message message = new Message("Run!",
				"Smash the hobbit!", "Vlad Impaler",
				"vlad@caveofprogramming.com", "Bilbo");
		
		messagesDao.saveOrUpdate(message);
		
		List<Message> messages = messagesDao.getMessages();

		assertEquals("One message should have been created and retrieved", 1,
				messages.size());
	}
}
