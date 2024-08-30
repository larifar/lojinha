package com.larissa.virtual.lojinha;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.larissa.virtual.lojinha.controller.AccessController;
import com.larissa.virtual.lojinha.exception.ExceptionLoja;
import com.larissa.virtual.lojinha.model.Access;
import com.larissa.virtual.lojinha.repository.AccessRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.DefaultMockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(classes = LojinhaApplication.class)
public class LojinhaApplicationTests{

	@Autowired
	private AccessController accessController;

	@Autowired
	private AccessRepository accessRepository;

	@Autowired
	private WebApplicationContext webContext;

	//teste para salvar um acesso
	@Test
	public void testRestApiInsertAccess() throws Exception {
		DefaultMockMvcBuilder builder = MockMvcBuilders.webAppContextSetup(this.webContext); //traz info do contexto pro mockito, fazem requisição para a api
		MockMvc mockMvc = builder.build();

		Access access = new Access();
		access.setDescription("ROLE_MockTest");
		ObjectMapper mapper = new ObjectMapper();

		ResultActions apiResult = mockMvc
				.perform(MockMvcRequestBuilders.post("/saveAccess")
						.content(mapper.writeValueAsString(access))
						.accept(MediaType.APPLICATION_JSON)
						.contentType(MediaType.APPLICATION_JSON));

		Access objectResponse = mapper
				.readValue(apiResult.andReturn().getResponse().getContentAsString(), Access.class);
		assertEquals(access.getDescription(), objectResponse.getDescription());
	}

	//teste para deletar um acesso
	@Test
	public void testRestApiDeleteAccess() throws Exception {
		DefaultMockMvcBuilder builder = MockMvcBuilders.webAppContextSetup(this.webContext); //traz info do contexto pro mockito, fazem requisição para a api
		MockMvc mockMvc = builder.build();

		Access access = new Access();
		access.setDescription("ROLE_DeleteTest");
		accessRepository.save(access);
		ObjectMapper mapper = new ObjectMapper();

		ResultActions apiResult = mockMvc
				.perform(MockMvcRequestBuilders.post("/deleteAccess")
						.content(mapper.writeValueAsString(access))
						.accept(MediaType.APPLICATION_JSON)
						.contentType(MediaType.APPLICATION_JSON));

		System.out.println(apiResult.andReturn().getResponse().getContentAsString());
		assertEquals("Acesso removido", apiResult.andReturn().getResponse().getContentAsString());
		assertEquals(200, apiResult.andReturn().getResponse().getStatus());
	}

	//teste para deletar um acesso pelo id
	@Test
	public void testRestApiDeleteAccessById() throws Exception {
		DefaultMockMvcBuilder builder = MockMvcBuilders.webAppContextSetup(this.webContext); //traz info do contexto pro mockito, fazem requisição para a api
		MockMvc mockMvc = builder.build();

		Access access = new Access();
		access.setDescription("ROLE_DeleteTest");
		access = accessRepository.save(access);
		ObjectMapper mapper = new ObjectMapper();

		ResultActions apiResult = mockMvc
				.perform(MockMvcRequestBuilders.delete("/deleteAccessById/"+access.getId())
						.content(mapper.writeValueAsString(access))
						.accept(MediaType.APPLICATION_JSON)
						.contentType(MediaType.APPLICATION_JSON));

		System.out.println(apiResult.andReturn().getResponse().getContentAsString());
		assertEquals("Acesso removido", apiResult.andReturn().getResponse().getContentAsString());
		assertEquals(200, apiResult.andReturn().getResponse().getStatus());
	}

	@Test
	public void testRestApiGetAccessById() throws Exception {
		DefaultMockMvcBuilder builder = MockMvcBuilders.webAppContextSetup(this.webContext); //traz info do contexto pro mockito, fazem requisição para a api
		MockMvc mockMvc = builder.build();

		Access access = new Access();
		access.setDescription("ROLE_GetTest");
		access = accessRepository.save(access);
		ObjectMapper mapper = new ObjectMapper();

		ResultActions apiResult = mockMvc
				.perform(MockMvcRequestBuilders.get("/getAccessById/"+access.getId())
						.content(mapper.writeValueAsString(access))
						.accept(MediaType.APPLICATION_JSON)
						.contentType(MediaType.APPLICATION_JSON));

		Access objectResponse = mapper
				.readValue(apiResult.andReturn().getResponse().getContentAsString(), Access.class);
		assertEquals(access.getDescription(), objectResponse.getDescription());
	}
	@Test
	public void testRestApiGetAccessByDesc() throws Exception {
		DefaultMockMvcBuilder builder = MockMvcBuilders.webAppContextSetup(this.webContext); //traz info do contexto pro mockito, fazem requisição para a api
		MockMvc mockMvc = builder.build();

		Access access = new Access();
		access.setDescription("ROLE_GetTestList");
		access = accessRepository.save(access);
		ObjectMapper mapper = new ObjectMapper();

		ResultActions apiResult = mockMvc
				.perform(MockMvcRequestBuilders.get("/findAccess/List")
						.content(mapper.writeValueAsString(access))
						.accept(MediaType.APPLICATION_JSON)
						.contentType(MediaType.APPLICATION_JSON));

		assertEquals(200, apiResult.andReturn().getResponse().getStatus());
		List<Access> apiResponse = mapper
				.readValue(apiResult.andReturn().getResponse().getContentAsString(),
						new TypeReference<List<Access>>() {
						});
		assertEquals(1, apiResponse.size());

		accessRepository.delete(access);
	}
	@Test
	public void testInsertAccess() throws ExceptionLoja {
		Access access = new Access();
		access.setDescription("ROLE_Teste");
		access = accessController.saveAccess(access).getBody();
        assertTrue(access.getId() > 0);

		Access access2 = accessRepository.findById(access.getId()).get();
		assertEquals(access.getId(), access2.getId());
	}

}
