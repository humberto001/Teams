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
}
