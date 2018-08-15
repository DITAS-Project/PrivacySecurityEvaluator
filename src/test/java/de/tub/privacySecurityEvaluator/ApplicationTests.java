package de.tub.privacySecurityEvaluator;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ApplicationTests {
    private static String body;
    private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            Charset.forName("utf8"));

    @Autowired
    private MockMvc mockMvc;



    @BeforeClass
    public static void loadJsons() {
        body = readToString("/newInput.json");

    }


    private static String readToString(String filename) {
        final StringBuilder sb = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(ApplicationTests.class.getResourceAsStream(filename)))) {
            reader.lines().forEach(s -> sb.append(s));
        } catch (IOException ex) {
            Assert.fail("could not load json data");
            ex.printStackTrace();
        }
        return sb.toString();
    }


    /**
     * @Test public void contextLoads() {
     * String out = body;
     * out = out.replace("\n", "");
     * out = out.replaceAll("\\s+","");
     * System.out.println(out);
     * <p>
     * }
     */
    @Test
    public void inputOutputFilterTest() throws Exception {

        ResultActions perform = mockMvc.perform(post("/filter")
                .content(body)
                .contentType(contentType));
        perform.andExpect(status().isOk());

    }


}
