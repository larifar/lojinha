package com.larissa.virtual.lojinha;

import com.larissa.virtual.lojinha.controller.AccessController;
import com.larissa.virtual.lojinha.model.Access;
import com.larissa.virtual.lojinha.repository.AccessRepository;
import com.larissa.virtual.lojinha.service.AccessService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = LojinhaApplication.class)
class LojinhaApplicationTests {

	@Autowired
	private AccessController accessController;

	@Test
	public void testInsertAccess() {
		Access access = new Access();
		access.setDescription("ROLE_ADMIN");
		accessController.saveAccess(access);
	}

}
