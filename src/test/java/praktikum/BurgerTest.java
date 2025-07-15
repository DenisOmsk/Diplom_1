package praktikum;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BurgerTest {

    Ingredient ingredient1 = new Ingredient(IngredientType.SAUCE, "sour cream", 20); // Первый ингредиент
    Ingredient ingredient2 = new Ingredient(IngredientType.FILLING, "cutlet", 200);  // Второй ингредиент
    Ingredient ingredient3 = new Ingredient(IngredientType.SAUCE, "chili sauce", 300); // Третий ингредиент
    Burger burger; // Объект тестируемого бургера

    @BeforeEach
    void setUp() {
        burger = new Burger(); // Инициализация нового бургера перед каждым тестом
    }

    @Test
    @DisplayName("Добавление ингредиента для бургера")
    void addIngredient() {
        burger.addIngredient(ingredient1); // Добавляем первый ингредиент
        assertEquals(1, burger.ingredients.size()); // Проверяем размер списка ингредиентов
        assertTrue(burger.ingredients.contains(ingredient1)); // Проверяем, что ингредиент добавлен
        assertAll("Проверка полей Ingredient", // Проверяем поля добавленного ингредиента
                () -> assertEquals(ingredient1.type, burger.ingredients.get(0).type),
                () -> assertEquals(ingredient1.name, burger.ingredients.get(0).name),
                () -> assertEquals(ingredient1.price, burger.ingredients.get(0).price));
    }

    @Test
    @DisplayName("Удаление ингредиента из бургера")
    void removeIngredient() {
        burger.addIngredient(ingredient1); // Добавляем первый ингредиент
        burger.addIngredient(ingredient2); // Добавляем второй ингредиент
        assertEquals(2, burger.ingredients.size()); // Проверяем размер списка

        burger.removeIngredient(0); // Удаляем ингредиент по индексу 0
        assertEquals(1, burger.ingredients.size()); // Проверяем, что размер уменьшился
        assertFalse(burger.ingredients.contains(ingredient1)); // Проверяем, что первый ингредиент удалён
        assertTrue(burger.ingredients.contains(ingredient2)); // Проверяем, что второй ингредиент остался
    }

    @Test
    @DisplayName("Перемещение ингредиента у бургера")
    void moveIngredient() {
        burger.addIngredient(ingredient1); // Добавляем первый ингредиент
        burger.addIngredient(ingredient2); // Добавляем второй ингредиент
        burger.addIngredient(ingredient3); // Добавляем третий ингредиент
        assertEquals(3, burger.ingredients.size()); // Проверяем размер списка

        burger.moveIngredient(1, 2); // Перемещаем ингредиент с позиции 1 на позицию 2
        assertEquals(3, burger.ingredients.size()); // Проверяем, что размер не изменился
        assertEquals(ingredient1, burger.ingredients.get(0)); // Первый ингредиент на месте
        assertEquals(ingredient2, burger.ingredients.get(2)); // Второй ингредиент перемещён на позицию 2
        assertEquals(ingredient3, burger.ingredients.get(1)); // Третий ингредиент на позиции 1
    }
}
