package com.example.wordcup.team.view

import infrateste.database.ConfigGroovySpec
import org.springframework.http.HttpStatus
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
class TeamEndpointTest extends ConfigGroovySpec {

    def "Deve retornar todos os times"() {

        given: "Uma requisição para trazer todos os times cadastrados"

        def uri = "http://localhost:8080/team/all"

        and: "Existir times no repositório"

        when: "acessar a uri"

            def mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(uri)).andReturn()


        then: "o resultado esperado será 200 OK e todos os valores devem ser retornados"
            mvcResult.response.status == HttpStatus.OK.value()
    }

}
