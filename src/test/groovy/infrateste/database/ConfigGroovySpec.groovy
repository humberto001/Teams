package infrateste.database

import com.example.wordcup.MyEndpointsApplication
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext
import spock.lang.Specification


@SpringBootTest(classes = MyEndpointsApplication, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ConfigGroovySpec extends Specification {

    protected MockMvc mockMvc

    @Autowired
    protected WebApplicationContext context

    def setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(context).build()
    }


}
