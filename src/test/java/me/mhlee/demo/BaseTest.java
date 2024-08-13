package me.mhlee.demo;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;


@SpringBootTest
@Transactional
@ActiveProfiles("junit")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class BaseTest {
	protected static final Logger log = LoggerFactory.getLogger(BaseTest.class);

	@BeforeAll
	void beforeAll() {
		log.info("여기에 beforeAll 스크립트 추가");
	}
}
