package com.example.coffee;

import com.example.coffee.model.GitRepoInfo;
import com.example.coffee.repository.GitRepoInfoRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@Slf4j
class CoffeeApplicationTests {

	@Autowired
	private GitRepoInfoRepository gitRepoInfoRepository;
//	private static final Logger logger = (Logger) LoggerFactory.getLogger(CoffeeApplicationTests.class);

	@Test
	void testJpa() {
//		GitRepoInfo gitRepo = new GitRepoInfo("11","22","33","44");
//		gitRepoInfoRepository.save(gitRepo);

	}
	@Test
	List<GitRepoInfo> findAll(){
		List<GitRepoInfo> all = this.gitRepoInfoRepository.findAll();
//		logger.info("[findAll] {}");
//		logger.info(all.toString());
//		logger.info(String.valueOf(all));
//		logger.info((Supplier<String>) all);
		return all;
	}

}
