package com.larissa.virtual.lojinha;

import com.larissa.virtual.lojinha.controller.AccessController;
import com.larissa.virtual.lojinha.model.Access;
import com.larissa.virtual.lojinha.repository.AccessRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(classes = LojinhaApplication.class)
public class LojinhaApplicationTests{

	@Autowired
	private AccessController accessController;

	@Autowired
	private AccessRepository accessRepository;

	@Test
	public void testInsertAccess() {
		Access access = new Access();
		access.setDescription("ROLE_Teste");
		access = accessController.saveAccess(access).getBody();
        assertTrue(access.getId() > 0);

		Access access2 = accessRepository.findById(access.getId()).get();
		assertEquals(access.getId(), access2.getId());
	}

}
