package helpers;

import pojo.Entity;
import specifications.Specifications;

import java.util.List;
import static io.restassured.RestAssured.given;

public class EntityRequests {
    /**
     * GET-метод для получения одной сущности
     * @param id - id сущности в БД
     * @return - объект сущности
     */
    public static Entity getEntity(int id){
        Specifications.installSpecification(Specifications.requestSpec(ConfProperties.getProperty("url")), Specifications.responseSpecOK200());
        return given()
                .when()
                .get("get/"+id)
                .then()
                .extract().as(Entity.class);
    }

    /**
     * GET-метод для получения одной сущности
     * @param id - id сущности в БД
     * @return - тело ответа сервера
     */
    public static String getDeletedEntity(int id){

        Specifications.installSpecification(Specifications.requestSpec(ConfProperties.getProperty("url")), Specifications.responseSpecServerError500());
        return given()
                .when()
                .get("get/"+id)
                .then()
                .extract().body().asString();
    }

    /**
     * POST-метод для создания сущности
     * @param entity - сущность
     * @return - id созданной сущности
     */
    public static int createEntity(Entity entity){
        Specifications.installSpecification(Specifications.requestSpec(ConfProperties.getProperty("url")), Specifications.responseSpecOK200());
        String id = given()
                .when()
                .body(entity)
                .post("create")
                .then()
                .extract().asString();
        return Integer.parseInt(id);
    }

    /**
     * PATCH-метод для изменения параметра сущности
     * @param entity - сущность
     * @param entityId - id сущности
     */
    public static void patchEntity(Entity entity, int entityId){
        Specifications.installSpecification(Specifications.requestSpec(ConfProperties.getProperty("url")), Specifications.responseSpecOK204());
        given()
                .when()
                .body(entity)
                .patch("patch/"+entityId)
                .then();
    }

    /**
     * DELETE-метод для удаления сущности
     * @param entityId - id сущности
     */
    public static void deleteEntity(int entityId){
        Specifications.installSpecification(Specifications.requestSpec(ConfProperties.getProperty("url")), Specifications.responseSpecOK204());
        given()
                .when()
                .delete("delete/"+entityId)
                .then();
    }

    /**
     * GET-метод для получения списка всех сущностей
     * @return - список всех сущностей
     */
    public static List<Entity> getEntityList(){
        Specifications.installSpecification(Specifications.requestSpec(ConfProperties.getProperty("url")), Specifications.responseSpecOK200());
        return given()
                .when()
                .get("getAll")
                .then()
                .extract().body().jsonPath().getList("entity", Entity.class);
    }
    public static Entity buildEntity(){
        return Entity.builder().build();
    }
}