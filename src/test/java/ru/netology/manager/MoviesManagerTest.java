// Пакет, содержащий тестовые классы для проверки функциональности менеджера фильмов
package ru.netology.manager;

// Импорт необходимых классов для тестирования
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Класс MoviesManagerTest содержит модульные тесты для проверки корректности
 * работы класса MoviesManager.
 * Тесты покрывают следующие сценарии:
 * 1. Создание менеджера с разными настройками лимита
 * 2. Добавление фильмов
 * 3. Получение всех фильмов в порядке добавления
 * 4. Получение последних фильмов в обратном порядке
 * Используется фреймворк JUnit 5 (Jupiter).
 */
public class MoviesManagerTest {

    /**
     * Тест проверяет создание менеджера с лимитом по умолчанию.
     * Ожидаемое поведение: менеджер должен быть создан с пустой коллекцией фильмов.
     * Конструктор по умолчанию устанавливает лимит 5.
     */
    @Test
    void shouldCreateWithDefaultLimit() {
        // Создание менеджера с лимитом по умолчанию (5)
        MoviesManager manager = new MoviesManager();

        // Проверка, что коллекция фильмов изначально пуста
        assertEquals(0, manager.findAll().length); // Пустой
    }

    /**
     * Тест проверяет создание менеджера с пользовательским лимитом.
     * Ожидаемое поведение: менеджер должен быть создан с пустой коллекцией фильмов
     * и установленным лимитом 3.
     */
    @Test
    void shouldCreateWithCustomLimit() {
        // Создание менеджера с пользовательским лимитом (3)
        MoviesManager manager = new MoviesManager(3);

        // Проверка, что коллекция фильмов изначально пуста
        assertEquals(0, manager.findAll().length); // Пустой
    }

    /**
     * Тест проверяет добавление одного фильма.
     * Ожидаемое поведение: после добавления фильма метод findAll()
     * должен вернуть массив с одним элементом.
     */
    @Test
    void shouldAddOneMovie() {
        MoviesManager manager = new MoviesManager();

        // Добавление одного фильма
        manager.add("Movie1");

        // Ожидаемый результат
        String[] expected = {"Movie1"};

        // Проверка, что фильм был добавлен корректно
        assertArrayEquals(expected, manager.findAll());
    }

    /**
     * Тест проверяет добавление нескольких фильмов и получение их всех.
     * Ожидаемое поведение: фильмы должны возвращаться в порядке их добавления.
     */
    @Test
    void shouldFindAllMultiple() {
        MoviesManager manager = new MoviesManager();

        // Добавление трех фильмов
        manager.add("Movie1");
        manager.add("Movie2");
        manager.add("Movie3");

        // Ожидаемый результат (в порядке добавления)
        String[] expected = {"Movie1", "Movie2", "Movie3"};

        // Проверка, что все фильмы возвращаются в правильном порядке
        assertArrayEquals(expected, manager.findAll());
    }

    /**
     * Тест проверяет получение последних фильмов из пустой коллекции.
     * Ожидаемое поведение: для пустой коллекции метод findLast()
     * должен вернуть пустой массив.
     */
    @Test
    void shouldFindLastEmpty() {
        MoviesManager manager = new MoviesManager();

        // Ожидаемый результат для пустой коллекции
        String[] expected = {};

        // Проверка, что для пустой коллекции возвращается пустой массив
        assertArrayEquals(expected, manager.findLast());
    }

    /**
     * Тест проверяет получение последних фильмов, когда их количество
     * меньше лимита по умолчанию (5).
     * Ожидаемое поведение: должны быть возвращены все фильмы в обратном порядке.
     */
    @Test
    void shouldFindLastLessThanLimit() {
        MoviesManager manager = new MoviesManager();

        // Добавление 3 фильмов (меньше лимита 5)
        manager.add("Movie1");
        manager.add("Movie2");
        manager.add("Movie3");

        // Ожидаемый результат: все фильмы в обратном порядке
        String[] expected = {"Movie3", "Movie2", "Movie1"};

        // Проверка корректности работы метода findLast()
        assertArrayEquals(expected, manager.findLast());
    }

    /**
     * Тест проверяет получение последних фильмов, когда их количество
     * равно лимиту по умолчанию (5).
     * Ожидаемое поведение: должны быть возвращены все 5 фильмов в обратном порядке.
     */
    @Test
    void shouldFindLastEqualLimit() {
        MoviesManager manager = new MoviesManager();

        // Добавление ровно 5 фильмов (равно лимиту по умолчанию)
        manager.add("Movie1");
        manager.add("Movie2");
        manager.add("Movie3");
        manager.add("Movie4");
        manager.add("Movie5");

        // Ожидаемый результат: все 5 фильмов в обратном порядке
        String[] expected = {"Movie5", "Movie4", "Movie3", "Movie2", "Movie1"};

        // Проверка корректности работы метода findLast()
        assertArrayEquals(expected, manager.findLast());
    }

    /**
     * Тест проверяет получение последних фильмов, когда их количество
     * больше лимита по умолчанию (5).
     * Ожидаемое поведение: должны быть возвращены только последние 5 фильмов
     * в обратном порядке (первые добавленные фильмы не попадают в результат).
     */
    @Test
    void shouldFindLastMoreThanLimit() {
        MoviesManager manager = new MoviesManager();

        // Добавление 6 фильмов (больше лимита 5)
        manager.add("Movie1");
        manager.add("Movie2");
        manager.add("Movie3");
        manager.add("Movie4");
        manager.add("Movie5");
        manager.add("Movie6");

        // Ожидаемый результат: только последние 5 фильмов в обратном порядке
        // Movie1 не попадает в результат
        String[] expected = {"Movie6", "Movie5", "Movie4", "Movie3", "Movie2"};

        // Проверка корректности работы метода findLast() с ограничением
        assertArrayEquals(expected, manager.findLast());
    }

    /**
     * Тест проверяет получение последних фильмов с пользовательским лимитом (3),
     * когда фильмов меньше лимита.
     * Ожидаемое поведение: должны быть возвращены все фильмы в обратном порядке.
     */
    @Test
    void shouldFindLastWithCustomLimitLess() {
        // Создание менеджера с пользовательским лимитом (3)
        MoviesManager manager = new MoviesManager(3);

        // Добавление 2 фильмов (меньше лимита 3)
        manager.add("Movie1");
        manager.add("Movie2");

        // Ожидаемый результат: все фильмы в обратном порядке
        String[] expected = {"Movie2", "Movie1"};

        // Проверка работы с пользовательским лимитом
        assertArrayEquals(expected, manager.findLast());
    }

    /**
     * Тест проверяет получение последних фильмов с пользовательским лимитом (3),
     * когда фильмов больше лимита.
     * Ожидаемое поведение: должны быть возвращены только последние 3 фильма
     * в обратном порядке.
     */
    @Test
    void shouldFindLastWithCustomLimitMore() {
        // Создание менеджера с пользовательским лимитом (3)
        MoviesManager manager = new MoviesManager(3);

        // Добавление 4 фильмов (больше лимита 3)
        manager.add("Movie1");
        manager.add("Movie2");
        manager.add("Movie3");
        manager.add("Movie4");

        // Ожидаемый результат: только последние 3 фильма в обратном порядке
        // Movie1 не попадает в результат
        String[] expected = {"Movie4", "Movie3", "Movie2"};

        // Проверка работы с пользовательским лимитом
        assertArrayEquals(expected, manager.findLast());
    }

    /**
     * Тест проверяет получение последних фильмов с лимитом 0.
     * Ожидаемое поведение: всегда должен возвращаться пустой массив,
     * независимо от количества добавленных фильмов.
     * Примечание: В текущей реализации нет защиты от лимита = 0,
     * что может быть нежелательным поведением.
     */
    @Test
    void shouldFindLastWithZeroLimit() {
        // Создание менеджера с лимитом 0 (потенциально проблемное значение)
        MoviesManager manager = new MoviesManager(0);

        // Добавление фильма
        manager.add("Movie1");

        // Ожидаемый результат: пустой массив
        String[] expected = {};

        // Проверка поведения при лимите 0
        assertArrayEquals(expected, manager.findLast());
    }
}