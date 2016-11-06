package me.hxkandwal.spring_mongo.services.integration;

import static me.hxkandwal.spring_mongo.common.dto.builders.Events.CREATE_EVENT;
import static me.hxkandwal.spring_mongo.common.dto.builders.Events.copy;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.RequestPostProcessor;

import me.hxkandwal.spring_mongo.common.dto.Event;
import me.hxkandwal.spring_mongo.services.config.CustomizedJacksonMapper;

/**
 * A sophisticated test-bed for front end testing.
 * 
 * @author Heman
 *
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SpringJUnit4ClassRunner.class)
public class EventServiceIntegrationTest extends AbstractContextControllerTests {

	private MockMvc mockMvc;
	
	@Autowired
	private CustomizedJacksonMapper jacksonMapper;

	@Before
	public void setup() {
		this.mockMvc = webAppContextSetup(this.wac).build();
	}

	@Test
	public void a_testCreateEvent() throws Exception {
		MvcResult mvcResult = mockMvc
				.perform(post("/api/event").with(new RequestPostProcessor() {
					public MockHttpServletRequest postProcessRequest(MockHttpServletRequest request) {
						request.setRemoteAddr("127.0.0.1");
						return request;
					}})
				.contentType(MediaType.APPLICATION_JSON).content(CREATE_EVENT.toString()))
				.andExpect(status().isOk()).andExpect(jsonPath("$.eventName", equalTo(CREATE_EVENT.getEventName())))
				.andReturn();

		Event event = jacksonMapper.readValue(mvcResult.getResponse().getContentAsString() , Event.class);

		assertNotNull(event.getId());
		
		// for future handles (update event scenarios)
		CREATE_EVENT.setId(event.getId());
	}

	@Test
	public void b_testUpdateEvent() throws Exception {
		String updatedEventName = CREATE_EVENT.getEventName() + "-updated";
		
		Event createEventCopy = copy(CREATE_EVENT);
		createEventCopy.setEventName(updatedEventName);

		MvcResult mvcResult = mockMvc
				.perform(put("/api/event").contentType(MediaType.APPLICATION_JSON).content(createEventCopy.toString()))
				.andExpect(status().isOk()).andExpect(jsonPath("$.eventName", containsString(updatedEventName)))
				.andReturn();

		Event event = jacksonMapper.readValue(mvcResult.getResponse().getContentAsString() , Event.class);
		assertEquals(updatedEventName, event.getEventName());
		
		// for future handles (find event scenarios)
		CREATE_EVENT.setEventName(event.getEventName());
	}
	
	@Test
	public void c_testGetEvent() throws Exception {
		mockMvc.perform(get("/api/event/" + CREATE_EVENT.getId())).andExpect(status().isOk())
				.andExpect(jsonPath("$.id", equalTo(CREATE_EVENT.getId())))
				.andExpect(jsonPath("$.eventName", containsString(CREATE_EVENT.getEventName())));
	}
	
	@Test
	public void c_testGetEvents() throws Exception {
		mockMvc.perform(get("/api/events")).andExpect(status().isOk())
				//.andExpect(jsonPath("$.*", hasSize(CREATE_EVENT.getId())))
				.andExpect(jsonPath("$[0].eventName", containsString(CREATE_EVENT.getEventName())));
	}
	
	@Test
	public void d_testDeleteEvent() throws Exception {
		mockMvc.perform(delete("/api/event/" + CREATE_EVENT.getId())).andExpect(status().isOk())
				.andExpect(jsonPath("$", containsString("success")));
	}
	
}