package com.caveofprogramming.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Calendar;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.caveofprogramming.App;
import com.caveofprogramming.model.StatusUpdate;
import com.caveofprogramming.model.StatusUpdateDao;

@SuppressWarnings("deprecation")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(App.class)
@WebAppConfiguration
@Transactional
public class StatusTest {
	
	@Autowired
	private StatusUpdateDao statusUpdateDao;

	@Test
	public void testSave() {
		StatusUpdate status = new StatusUpdate("This is a test status update");
		
		statusUpdateDao.save(status);
		assertNotNull("Non null ID", status.getId());
		assertNotNull("Non null Date", status.getAdded());
		
		StatusUpdate retrived = statusUpdateDao.findOne(status.getId());
		assertEquals("Matching statusUpdate", status, retrived);	
	}
	
	
	@Test
	public void testFindLatest() {
		
		Calendar calendar = Calendar.getInstance();
		
		StatusUpdate lastStatusUpdate = null;
		
		for(int i=0; i<10; i++) {
			calendar.add(Calendar.DAY_OF_YEAR, 1);
			
			StatusUpdate status = new StatusUpdate("Status Update" + i, calendar.getTime());
			statusUpdateDao.save(status);
			lastStatusUpdate =  status;	
		}
		
		StatusUpdate retrived = statusUpdateDao.findFirstByOrderByAddedDesc();
		
		assertEquals("Last Updated: ",lastStatusUpdate, retrived);
		
	}	
}
	
