package com.jbajak.treasurehunt.controller

import io.micronaut.http.client.exceptions.HttpClientResponseException
import io.micronaut.test.annotation.MicronautTest
import spock.lang.Specification

import javax.inject.Inject

@MicronautTest
class TreasureHuntControllerTest extends Specification {
    @Inject
    TreasureHuntClient client

    void "find treasure, start at 11, recursive"() {
        given:
        def startPos = "11"

        when:
        def result = client.findRecursive(startPos)

        then:
        result.code() == 200
        result.getContentType().get().toString() == "application/json"
        def body = ((LinkedHashMap) result.getBody(LinkedHashMap.class).get())
        def messages = (ArrayList) body.get("messages")
        messages.size() == 9
        messages.get(0) == "1 1"
        messages.get(1) == "5 5"
        messages.get(2) == "1 5"
        messages.get(3) == "2 1"
        messages.get(4) == "4 4"
        messages.get(5) == "3 2"
        messages.get(6) == "1 3"
        messages.get(7) == "2 5"
        messages.get(8) == "4 3"
    }

    void "find treasure, start at 11, not recursive"() {
        given:
        def startPos = "11"

        when:
        def result = client.findNotRecursive(startPos)

        then:
        result.code() == 200
        result.getContentType().get().toString() == "application/json"
        def body = ((LinkedHashMap) result.getBody(LinkedHashMap.class).get())
        def messages = (ArrayList) body.get("messages")
        messages.size() == 9
        messages.get(0) == "1 1"
        messages.get(1) == "5 5"
        messages.get(2) == "1 5"
        messages.get(3) == "2 1"
        messages.get(4) == "4 4"
        messages.get(5) == "3 2"
        messages.get(6) == "1 3"
        messages.get(7) == "2 5"
        messages.get(8) == "4 3"
    }

    void "find treasure, start at 13, recursive"() {
        given:
        def startPos = "13"

        when:
        def result = client.findRecursive(startPos)

        then:
        result.code() == 200
        result.getContentType().get().toString() == "application/json"
        def body = ((LinkedHashMap) result.getBody(LinkedHashMap.class).get())
        def messages = (ArrayList) body.get("messages")
        messages.size() == 3
        messages.get(0) == "1 3"
        messages.get(1) == "2 5"
        messages.get(2) == "4 3"
    }

    void "find treasure, start at 13, not recursive"() {
        given:
        def startPos = "13"

        when:
        def result = client.findNotRecursive(startPos)

        then:
        result.code() == 200
        result.getContentType().get().toString() == "application/json"
        def body = ((LinkedHashMap) result.getBody(LinkedHashMap.class).get())
        def messages = (ArrayList) body.get("messages")
        messages.size() == 3
        messages.get(0) == "1 3"
        messages.get(1) == "2 5"
        messages.get(2) == "4 3"
    }

    void "no treasure, start at 42, recursive"() {
        given:
        def startPos = "42"

        when:
        def result = client.findRecursive(startPos)

        then:
        result.code() == 200
        result.getContentType().get().toString() == "application/json"
        def body = ((LinkedHashMap) result.getBody(LinkedHashMap.class).get())
        def messages = (ArrayList) body.get("messages")
        messages.size() == 1
        messages.get(0) == "NO TREASURE"
    }

    void "no treasure, start at 42, not recursive"() {
        given:
        def startPos = "42"

        when:
        def result = client.findNotRecursive(startPos)

        then:
        result.code() == 200
        result.getContentType().get().toString() == "application/json"
        def body = ((LinkedHashMap) result.getBody(LinkedHashMap.class).get())
        def messages = (ArrayList) body.get("messages")
        messages.size() == 1
        messages.get(0) == "NO TREASURE"
    }

    void "server error when start at 19, recursive"() {
        given:
        def startPos = "19"

        when:
        def result = client.findRecursive(startPos)

        then:
        def ex = thrown(HttpClientResponseException)
        ex.response.code() == 500
    }

    void "server error when start at 91, recursive"() {
        given:
        def startPos = "91"

        when:
        def result = client.findRecursive(startPos)

        then:
        def ex = thrown(HttpClientResponseException)
        ex.response.code() == 500
    }

    void "server error when start at 19, not recursive"() {
        given:
        def startPos = "19"

        when:
        def result = client.findNotRecursive(startPos)

        then:
        def ex = thrown(HttpClientResponseException)
        ex.response.code() == 500
    }

    void "server error when start at 91, not recursive"() {
        given:
        def startPos = "91"

        when:
        def result = client.findNotRecursive(startPos)

        then:
        def ex = thrown(HttpClientResponseException)
        ex.response.code() == 500
    }
}
