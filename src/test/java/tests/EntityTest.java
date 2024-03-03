package tests;

import helpers.EntityRequests;
import org.junit.jupiter.api.*;
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
    /**
     * Создание экземпляра класса Entity,
     * который ожидается при тестах
     */
    static Entity expectedEntity = EntityRequests.buildEntity();
    @Test
    @DisplayName("Запрос на добавление новой сущности")
    public void createEntity(){
        int entityId = EntityRequests.createEntity(expectedEntity);
        entityIdListToDeleteAfterTests.add(entityId);
        Entity actualEntity = EntityRequests.getEntity(entityId);
        Assertions.assertEquals(expectedEntity, actualEntity);
    }

    @Test
    @DisplayName("Запрос на удаление сущности и ее дополнений")
    public void deleteEntity(){
        int entityId = EntityRequests.createEntity(expectedEntity);
        EntityRequests.deleteEntity(entityId);
        String expectedResponse = "{\"error\":\"no rows in result set\"}";
        String actualResponse = EntityRequests.getDeletedEntity(entityId);
        Assertions.assertEquals(expectedResponse, actualResponse);
    }

    @Test
    @DisplayName("Запрос на получение одной сущности")
    public void getEntity(){
        int entityId = EntityRequests.createEntity(expectedEntity);
        entityIdListToDeleteAfterTests.add(entityId);
        Entity actualEntity = EntityRequests.getEntity(entityId);
        Assertions.assertEquals(expectedEntity, actualEntity);
    }

    @Test
    @DisplayName("Отправка событий (Обновление сущности и ее дополнений)")
    public void patch(){
        int entityId = EntityRequests.createEntity(expectedEntity);
        entityIdListToDeleteAfterTests.add(entityId);
        Entity patchEntity = Entity
                .builder()
                .verified(false)
                .build();
        EntityRequests.patchEntity(patchEntity, entityId);
        Entity actualEntity = EntityRequests.getEntity(entityId);
        Assertions.assertEquals(patchEntity, actualEntity);
    }

    @Test
    @DisplayName("Запрос на получение списка сущностей")
    public void entityList(){
        List<Entity> entityList = EntityRequests.getEntityList();
        List<Entity> expectedEntityList = new ArrayList<>(entityList);
        expectedEntityList.add(expectedEntity);
        int entityId = EntityRequests.createEntity(expectedEntity);
        entityIdListToDeleteAfterTests.add(entityId);
        List<Entity> actualEntityList = EntityRequests.getEntityList();
        Assertions.assertEquals(expectedEntityList, actualEntityList);
    }

    /**
     * Выполняется функция,
     * которая удаляет все Entity, созданные в процессе
     * выполнения тестов
     */
    @AfterAll
    public static void deleteAllTestEntities(){
        for (Integer id : entityIdListToDeleteAfterTests) {
            EntityRequests.deleteEntity(id);
            System.out.println("Тестовая сущность с id:"+id+" удалена");
        }
    }
}
