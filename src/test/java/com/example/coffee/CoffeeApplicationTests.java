package com.example.coffee;

import com.example.coffee.domain.GitInfo;
import com.example.coffee.repository.GitInfoRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@Slf4j
class CoffeeApplicationTests {

	@Autowired
	private GitInfoRepository gitInfoRepository;

	@Test
	void testJpa() {
//		GitRepoInfo gitRepo = new GitRepoInfo("11","22","33","44");
//		gitRepoInfoRepository.save(gitRepo);

	}
	@Test
	List<GitInfo> findAll(){
		List<GitInfo> all = this.gitInfoRepository.findAll();
		return all;
	}

}
