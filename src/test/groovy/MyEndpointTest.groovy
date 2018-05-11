import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import spock.lang.Specification

class MyEndpointTest extends Specification  {

    @Autowired
    private MockMvc mockMvc

    def "Deve retornar o status ok da requisição ao acessar a url"() {

        given: "Uma url válida do endpoint "
            def uri = "localhost:8080/teste"

        when: "Acessarmos a url"
            def mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(uri)).andReturn()

        then: "Deve retornar o status OK"
            mvcResult.response.status == HttpStatus.OK.value()

    }
}
