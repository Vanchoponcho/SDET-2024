package tests;

import helpers.ApiHelper;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pojo.Entity;
import java.util.ArrayList;
import java.util.List;

/**
 * Класс тестов
 */
public class EntityTest {

    /**
     * Список собирает id сущностей, которые созданы в ходе тестов
     * для последующего удаления
     */
    static List <Integer> entityIdListToDeleteAfterTests = new ArrayList<>();

    @Test
    @DisplayName("Запрос на добавление новой сущности")
    public void createEntity(){
        Entity expectedEntity = Entity.builder().build();
        int entityId = ApiHelper.createEntity(expectedEntity);
        entityIdListToDeleteAfterTests.add(entityId);
        Entity actualEntity = ApiHelper.getEntity(entityId);
        Assertions.assertEquals(expectedEntity, actualEntity);
    }

    @Test
    @DisplayName("Запрос на удаление сущности и ее дополнений")
    public void deleteEntity(){
        Entity entityToDelete = Entity.builder().build();
        int entityId = ApiHelper.createEntity(entityToDelete);
        ApiHelper.deleteEntity(entityId);
        String expectedResponse = "{\"error\":\"no rows in result set\"}";
        String actualResponse = ApiHelper.getDeletedEntity(entityId);
        Assertions.assertEquals(expectedResponse, actualResponse);
    }

    @Test
    @DisplayName("Запрос на получение одной сущности")
    public void getEntity(){
        Entity expectedEntity = Entity.builder().build(); //сбилдил сущность
        int entityId = ApiHelper.createEntity(expectedEntity);// в метод создание сущности передал сбилженную сущность и записал ее id
        entityIdListToDeleteAfterTests.add(entityId);
        Entity actualEntity = ApiHelper.getEntity(entityId);
        Assertions.assertEquals(expectedEntity, actualEntity);
    }

    @Test
    @DisplayName("Отправка событий (Обновление сущности и ее дополнений)")
    public void patch(){
        Entity entity = Entity.builder().build();
        int entityId = ApiHelper.createEntity(entity);
        entityIdListToDeleteAfterTests.add(entityId);
        Entity patchEntity = Entity
                .builder()
                .verified(false)
                .build();
        ApiHelper.patchEntity(patchEntity, entityId);
        Entity actualEntity = ApiHelper.getEntity(entityId);
        Assertions.assertEquals(patchEntity, actualEntity);
    }

    @Test
    @DisplayName("Запрос на получение списка сущностей")
    public void entityList(){
        Entity expectedEntity = Entity.builder().build();
        List<Entity> entityList = ApiHelper.getEntityList();
        List<Entity> expectedEntityList = new ArrayList<>(entityList);
        expectedEntityList.add(expectedEntity);
        int entityId = ApiHelper.createEntity(expectedEntity);
        entityIdListToDeleteAfterTests.add(entityId);
        List<Entity> actualEntityList = ApiHelper.getEntityList();
        Assertions.assertEquals(expectedEntityList, actualEntityList);
    }

    //Почистить сущности, которые создали в ходе тестов
    @AfterAll
    public static void deleteAllTestEntities(){
        for (Integer id : entityIdListToDeleteAfterTests) {
            ApiHelper.deleteEntity(id);
            System.out.println("Тестовая сущность с id:"+id+" удалена");
        }
    }
}
