package com.example.wordcup.team.view

import groovy.json.JsonBuilder
import groovy.json.JsonSlurper
import infrateste.database.ConfigGroovySpec
import org.dbunit.IDatabaseTester
import org.dbunit.JdbcDatabaseTester
import org.dbunit.dataset.IDataSet
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders


class TeamEndpointTest extends ConfigGroovySpec {

    IDatabaseTester databaseTester

    def setup(){
        databaseTester = new JdbcDatabaseTester("com.mysql.jdbc.Driver", "jdbc:mysql://localhost:3306/team_db", "root", "1234")
        IDataSet dataSet = new FlatXmlDataSetBuilder().build(new FileInputStream("dataset.xml"))
        databaseTester.setDataSet(dataSet)
        databaseTester.onSetup()
    }


    def "Deve retornar todos os times"() {

        given: "Uma requisição para trazer todos os times cadastrados"

        def uri = "http://localhost:8080/team/all"

        when: "acessar a uri"

            def mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(uri)).andReturn()
            def jsonResult = new JsonSlurper().parseText(mvcResult.response.getContentAsString())

        then: "o resultado esperado será 200 OK e todos os valores devem ser retornados"
            mvcResult.response.status == HttpStatus.OK.value()
            jsonResult.size == 4
            jsonResult[0].id== 1
            jsonResult[0].name == "Corinthians"
            jsonResult[0].numberOfPlayers == 5
    }

    def "Deve retornar um time ao buscar pelo id"(){

        given: "Uma requisição para buscar um time pelo id"

            def id = 35
            def uri = "http://localhost:8080/team/${id}"

        when: "Fazer a requisição"

            def mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(uri)).andReturn()
            def jsonResult = new JsonSlurper().parseText(mvcResult.response.getContentAsString())

        then: "Um time com todos atributos salvos no banco"

            mvcResult.response.status == HttpStatus.OK.value()
            jsonResult.name == "Sants"
            jsonResult.numberOfPlayers == 10
            jsonResult.id == 35
    }

    def "Deve salvar um time no bd e retornar o cliente salvo"(){

        given: "Uma requisição para adicionar um time"
            def uri = "http://localhost:8080/team"

        and: "Houver um payload com dados válidos"

            def json = new JsonBuilder()
            json name : "Brasuca",
                 numberOfPlayers: 9


        when: "Realizar a requisição"
            def mvcResult = mockMvc.perform(MockMvcRequestBuilders.post(uri)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(json.toString()))
                    .andReturn()

            def jsonResult = new JsonSlurper().parseText(mvcResult.response.getContentAsString())

        then: "Deverá salvar no banco o time e retorna-lo"

            mvcResult.response.status == HttpStatus.OK.value()
            jsonResult.id != null
            jsonResult.name == "Brasuca"
            jsonResult.numberOfPlayers == 9
    }

    def "Deve atualizar um time no BD e retornar o time atualizado"(){

        given: "Uma requisição para atualizar um time no bd"

            def id = 3
            def uri = "http://localhost:8080/team/${id}"

        and: "Um payload válido"

            def json = new JsonBuilder()

        json name: "Neymar",
            numberOfPlayers : 10


        when: "Realizar uma requisição"

            def mvcResult = mockMvc.perform(MockMvcRequestBuilders.put(uri).contentType(MediaType.APPLICATION_JSON)
                .content(json.toString()))
                .andReturn()

            def jsonResult = new JsonSlurper().parseText(mvcResult.response.getContentAsString())

        then:"Deve alterar o time no banco e retornar o time alterado"

            mvcResult.response.status == HttpStatus.OK.value()
            jsonResult.name == "Neymar"
            jsonResult.numberOfPlayers == 10
    }

    def "Deve excluir um time do banco"(){

        given:"Uma requisição para excluir um time"

            def id = 23
            def uri = "http://localhost:8080/team/${id}"

        when: "Realizar a requisição"

            def mvcResult = mockMvc.perform(MockMvcRequestBuilders.delete(uri)
                .contentType(MediaType.APPLICATION_JSON)).andReturn()

        then: "Deve retornar status 200 e o time não deve mais existir no banco"

            mvcResult.response.status == HttpStatus.OK.value()

    }

    def "Deve retornar status 404 quando não encontrar o time"(){

        given: "Um time não exista no BD"
            def id= 50

        and: "Uma requisição para buscar esse time"
            def uri = "http://localhost:8080/team/${id}"

        when: "Realizar uma requisição"

            def mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(uri)).andReturn()

        then: "Deve retornar status Not Found"

            mvcResult.response.status == HttpStatus.NOT_FOUND.value()

    }

    def "Deve retornar status 404 quando tentar atualizar um time não existente"(){

        given: "Um time não existente no BD"

            def id = 50

        and: "Uma requisição para atualizar esse time"

            def uri = "http://localhost:8080/team/${id}"

        and: "Um payload válido"

        def json = new JsonBuilder()
        json name: "Russia",
             numberOfPlayers: 8


        when: "Realizar a requisição"

        def mvcResult = mockMvc.perform(MockMvcRequestBuilders.put(uri).contentType(MediaType.APPLICATION_JSON)
                        .content(json.toString()))
                        .andReturn()


        then: "Deve retornar status 404 e não atualizar o time"

        mvcResult.response.status == HttpStatus.NOT_FOUND.value()

    }

    def "Deve retornar status 400 quando tentar atualizar um time com número de jogadores maior que 10"(){

        given : "Uma requisição para atualizar um time"

            def id =3
            def uri = "http://localhost:8080/team/${id}"

        and : "O atributo numberOfPlayer for maior que 10 "

            def json = new JsonBuilder()
            json numberOfPlayers: 11,
                 name: "NoMore"

        when: "realizar a requisição"

            def mvcResult = mockMvc.perform(MockMvcRequestBuilders.put(uri)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(json.toString()))
                    .andReturn()

        then: "Deverá exibir status de bad request"

        mvcResult.response.status == HttpStatus.BAD_REQUEST.value()

    }

    def "Deve retornar status 400 quando tentar salvar um time com nome nulo"(){

        given: "Uma requisição para salvar um time"

            def uri = "http://localhost:8080/team"

        and: "O time possuir valor nome nulo"

            def json = new JsonBuilder()
            json name: "",
                 numberOfPlayers: 10

        when: "Realizar a requisição"
            def mvcResult = mockMvc.perform(MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON).content(json.toString())).andReturn()

        then: "Deve retornar bad request"

            mvcResult.response.status == HttpStatus.BAD_REQUEST.value()
    }
}
