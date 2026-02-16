package com.devscompass.quarkustemplate.api.rest.note;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.emptyOrNullString;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.notNullValue;

import com.devscompass.quarkustemplate.dto.note.NoteCreateDTO;
import com.devscompass.quarkustemplate.dto.note.NoteUpdateDTO;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.MediaType;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.sql.DataSource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

@QuarkusTest
public class NoteResourceTest {

  @Inject DataSource dataSource;

  @BeforeEach
  void setup() throws SQLException {
    try (Connection conn = dataSource.getConnection()) {
      try (PreparedStatement truncateStmt =
          conn.prepareStatement("TRUNCATE TABLE quarkustemplate.notes RESTART IDENTITY CASCADE")) {
        truncateStmt.execute();
      }

      try (PreparedStatement insertStmt =
          conn.prepareStatement(
              "INSERT INTO quarkustemplate.notes (id, optlock, title, content, created_at,"
                  + " updated_at) VALUES (?, 0, ?, ?, now(), now())")) {

        insertStmt.setString(1, "3282249b-19ee-4c5e-9be2-b9f714610aa6");
        insertStmt.setString(2, "Note 1");
        insertStmt.setString(3, "Content 1");
        insertStmt.addBatch();

        insertStmt.setString(1, "1d7539f9-e9b7-4a06-9e6c-d5d6cf74d87a");
        insertStmt.setString(2, "Note 2");
        insertStmt.setString(3, "Content 2");
        insertStmt.addBatch();

        insertStmt.setString(1, "e969ffd7-b4ce-47e1-8f43-8811ae576392");
        insertStmt.setString(2, "Note 3");
        insertStmt.setString(3, "Content 3");
        insertStmt.addBatch();

        insertStmt.executeBatch();
      }
    }
  }

  @Test
  void testNoteListing() {
    given()
        .when()
        .get("/v1/note")
        .then()
        .statusCode(200)
        .contentType(MediaType.APPLICATION_JSON)
        .body("size()", is(3))
        .body(
            "id",
            hasItems(
                "e969ffd7-b4ce-47e1-8f43-8811ae576392",
                "1d7539f9-e9b7-4a06-9e6c-d5d6cf74d87a",
                "3282249b-19ee-4c5e-9be2-b9f714610aa6"));
  }

  @Test
  void testNoteGetById() {
    String expectedTitle = "Note 2";

    given()
        .when()
        .get("/v1/note/1d7539f9-e9b7-4a06-9e6c-d5d6cf74d87a")
        .then()
        .statusCode(200)
        .contentType(MediaType.APPLICATION_JSON)
        .body("title", is(expectedTitle));
  }

  @Test
  void testNoteCreation() {
    String testTitle = "Test note title";
    String testContent = "Test note description";

    given()
        .when()
        .contentType(MediaType.APPLICATION_JSON)
        .body(new NoteCreateDTO(testTitle, testContent))
        .post("/v1/note")
        .then()
        .statusCode(201)
        .contentType(MediaType.APPLICATION_JSON)
        .body("title", is(testTitle))
        .body("content", is(testContent))
        .body("id", notNullValue())
        .body("id", not(emptyOrNullString()));
  }

  @Test
  void testNoteCreationWithNullTitle() {
    String testContent = "Test note description";

    given()
        .when()
        .contentType(MediaType.APPLICATION_JSON)
        .body(new NoteCreateDTO(null, testContent))
        .post("/v1/note")
        .then()
        .statusCode(400)
        .contentType(MediaType.APPLICATION_JSON)
        .body("error", notNullValue());
  }

  @Test
  void testNotePutNotAllowed() {
    given()
        .when()
        .contentType(MediaType.APPLICATION_JSON)
        .body(new NoteUpdateDTO(null, null))
        .put("/v1/note/1d7539f9-e9b7-4a06-9e6c-d5d6cf74d87a")
        .then()
        .statusCode(405)
        .contentType(MediaType.APPLICATION_JSON)
        .body("error", notNullValue());
    ;
  }

  @Test
  void testNoteUpdateTitle() {
    String testUpdatedTitle = "Test updated title";

    given()
        .when()
        .contentType(MediaType.APPLICATION_JSON)
        .body(new NoteUpdateDTO(testUpdatedTitle, null))
        .patch("/v1/note/1d7539f9-e9b7-4a06-9e6c-d5d6cf74d87a")
        .then()
        .statusCode(200)
        .contentType(MediaType.APPLICATION_JSON)
        .body("title", is(testUpdatedTitle))
        .body("content", notNullValue());
  }

  @Test
  void testNoteUpdateContent() {
    String testUpdatedContent = "Test updated content";

    given()
        .when()
        .contentType(MediaType.APPLICATION_JSON)
        .body(new NoteUpdateDTO(null, testUpdatedContent))
        .patch("/v1/note/1d7539f9-e9b7-4a06-9e6c-d5d6cf74d87a")
        .then()
        .statusCode(200)
        .contentType(MediaType.APPLICATION_JSON)
        .body("title", notNullValue())
        .body("content", is(testUpdatedContent));
  }

  @Test
  void testNoteUpdateToNull() {
    given()
        .when()
        .contentType(MediaType.APPLICATION_JSON)
        .body(new NoteUpdateDTO(null, null))
        .patch("/v1/note/1d7539f9-e9b7-4a06-9e6c-d5d6cf74d87a")
        .then()
        .statusCode(200)
        .contentType(MediaType.APPLICATION_JSON)
        .body("title", notNullValue())
        .body("content", notNullValue());
  }

  @Test
  void testNoteUpdateDoesNotExist() {
    given()
        .when()
        .contentType(MediaType.APPLICATION_JSON)
        .body(new NoteUpdateDTO(null, null))
        .patch("/v1/note/8aba169f-f901-4d71-94e2-1251690aa0c9")
        .then()
        .statusCode(404)
        .contentType(MediaType.APPLICATION_JSON)
        .body("error", notNullValue());
  }

  @Test
  void testNoteDeletion() {
    given().when().delete("/v1/note/1d7539f9-e9b7-4a06-9e6c-d5d6cf74d87a").then().statusCode(204);
  }

  @Test
  void testNoteDeletionDoesNotExist() {
    given()
        .when()
        .delete("/v1/note/8aba169f-f901-4d71-94e2-1251690aa0c9")
        .then()
        .statusCode(404)
        .contentType(MediaType.APPLICATION_JSON)
        .body("error", notNullValue());
  }
}
