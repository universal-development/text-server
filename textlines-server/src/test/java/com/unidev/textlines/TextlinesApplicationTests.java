package com.unidev.textlines;

import com.unidev.textlines.dto.FileListRequest;
import com.unidev.textlines.dto.LineRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class TextlinesApplicationTests {

	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	public void testListingFiles() throws Exception {
		FileListRequest pathRequest = FileListRequest.builder().path("*.txt").build();
		ArrayList list = restTemplate.postForObject("http://localhost:" + port + TextLineController.API_PATH + "/list", pathRequest,
				ArrayList.class);

		assertThat(list).isNotNull();
		assertThat(list.isEmpty()).isFalse();
		assertThat(list).contains("test.txt");
	}

	@Test
	public void testFileContentLoading() {
		LineRequest lineRequest = LineRequest.builder().path("test.txt").count(1).build();
		ArrayList list = restTemplate.postForObject("http://localhost:" + port + TextLineController.API_PATH + "/lines", lineRequest,
				ArrayList.class);

		assertThat(list).isNotNull();
		assertThat(list.isEmpty()).isFalse();

		List<String> oneOf = Arrays.asList("line1", "line2", "line3");
		boolean containsElement = false;
		for(String item : oneOf) {
			if (list.contains(item)) {
				containsElement = true;
				break;
			}
		}
		assertThat(containsElement).isTrue();
	}

	@Test
	public void testListingFilesFromFolder() throws Exception {
		FileListRequest pathRequest = FileListRequest.builder().path("folder1/*.txt").build();
		ArrayList list = restTemplate.postForObject("http://localhost:" + port + TextLineController.API_PATH + "/list", pathRequest,
				ArrayList.class);

		assertThat(list).isNotNull();
		assertThat(list.isEmpty()).isFalse();
		assertThat(list).contains("folder1/test2.txt");
	}

	@Test
	public void testListingMissingFiles() {
		FileListRequest pathRequest = FileListRequest.builder().path("folder2/*.txt").build();
		ArrayList list = restTemplate.postForObject("http://localhost:" + port + TextLineController.API_PATH + "/list", pathRequest,
				ArrayList.class);

		assertThat(list).isNotNull();
		assertThat(list.isEmpty()).isTrue();
	}


	@Test
	public void contextLoads() {
	}

}
