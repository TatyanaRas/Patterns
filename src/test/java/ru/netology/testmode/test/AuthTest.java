package ru.netology.testmode.test;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static ru.netology.testmode.test.data.DataGenerator.Registration.getUser;
import static ru.netology.testmode.test.data.DataGenerator.getRandomLogin;
import static ru.netology.testmode.test.data.DataGenerator.getRandomPassword;


class AuthTest {

    @BeforeEach
    void setup() {
        open("http://localhost:9999");
    }

    @Test
    @DisplayName("Should successfully login with active registered user")
    void shouldSuccessfulLoginIfRegisteredActiveUser() {
        var registeredUser = getRegisteredUser("active");

        // TODO: добавить логику теста, в рамках которого будет выполнена попытка входа в личный кабинет с учётными
        //  данными зарегистрированного активного пользователя, для заполнения полей формы используйте
        //  пользователя registeredUser

    }

    private Object getRegisteredUser(String active) {
        return null;
    }

    @Test
    @DisplayName("Should get error message if login with not registered user")
    void shouldGetErrorIfNotRegisteredUser() {
        var notRegisteredUser = getUser("active");
        $("[data-test-id='login'] input").setValue(notRegisteredUser.getlogin());
        $("[data-test-id='password'] input").setValue(notRegisteredUser.getpassword());
       $("button.button").click();
       $( "[data-test-id='error-notification'] .notification__content").shouldBe((Condition.visible)).shouldHave(Condition.text("Ошибка! Неверно указан логин или пароль"), Duration.ofSeconds(15));
        // TODO: добавить логику теста в рамках которого будет выполнена попытка входа в личный кабинет
        //  незарегистрированного пользователя, для заполнения полей формы используйте пользователя notRegisteredUser
    }

    @Test
    @DisplayName("Should get error message if login with blocked registered user")
    void shouldGetErrorIfBlockedUser() {
        var blockedUser = getRegisteredUser("blocked");
        $("[data-test-id=login] input").setValue(blockedUser.getlogin());
        $("[data-test-id=password] input").setValue(blockedUser.getpassword());
        $("button.button").click();
        $( "[data-test-id = error-notification].notification__content").shouldBe((Condition.visible)).shouldHave(Condition.text("Ошибка! Пользователь заблокирован"), Duration.ofSeconds(15));

        // TODO: добавить логику теста в рамках которого будет выполнена попытка входа в личный кабинет,
        //  заблокированного пользователя, для заполнения полей формы используйте пользователя blockedUser
    }

    @Test
    @DisplayName("Should get error message if login with wrong login")
    void shouldGetErrorIfWrongLogin() {
        var registeredUser = getRegisteredUser("active");
        var wrongLogin = getRandomLogin();
        $("[data-test-id=login] input").setValue(wrongLogin());
        $("[data-test-id=password] input").setValue(registeredUser.getpassword());
        $("button.button").click();
        $( "[data-test-id = error-notification] .notification__content").shouldBe((Condition.visible)).shouldHave(Condition.text("Ошибка! Неверно указан логин или пароль"), Duration.ofSeconds(15));
        // TODO: добавить логику теста в рамках которого будет выполнена попытка входа в личный кабинет с неверным
        //  логином, для заполнения поля формы "Логин" используйте переменную wrongLogin,
        //  "Пароль" - пользователя registeredUser
    }

    @Test
    @DisplayName("Should get error message if login with wrong password")
    void shouldGetErrorIfWrongPassword() {
        var registeredUser = getRegisteredUser("active");
        var wrongPassword = getRandomPassword();
        $("[data-test-id=login] input").setValue(registeredUser.getLogin());
        $("[data-test-id=password] input").setValue(wrongPassword());
        $("button.button").click();
        $( "[data-test-id = error-notification] .notification__content").shouldBe((Condition.visible)).shouldHave(Condition.text("Ошибка! Неверно указан логин или пароль"), Duration.ofSeconds(15));
        // TODO: добавить логику теста в рамках которого будет выполнена попытка входа в личный кабинет с неверным
        //  паролем, для заполнения поля формы "Логин" используйте пользователя registeredUser,
        //  "Пароль" - переменную wrongPassword
    }
}