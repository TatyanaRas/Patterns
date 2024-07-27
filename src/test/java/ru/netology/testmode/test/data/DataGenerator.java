package ru.netology.testmode.test.data;

import com.codeborne.selenide.Selenide;
import com.github.javafaker.Faker;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import lombok.Value;
import java.util.Locale;
//import org.junit.jupiter.api.BeforeAll;
import static io.restassured.RestAssured.given;

public class DataGenerator {

    //private static final Faker faker = new Faker(new Locale("en"));

    private static final RequestSpecification requestSpec = new RequestSpecBuilder()
            .setBaseUri("http://localhost")
            .setPort(9999)
            .setAccept(ContentType.JSON)
            .setContentType(ContentType.JSON)
            .log(LogDetail.ALL)
            .build();

      private static final Faker faker = new Faker(new Locale("en"));

    private DataGenerator() {
    }

    private static void sendRequest(RegistrationDto user) {

     //static DataGeneranor.RegistrationDto sendRequest(DataGenerator.RegistrationDto user){
    Selenide.sleep(500);
        given() // "дано"
                .spec(requestSpec) // указываем, какую спецификацию используем
               // .body(new RegistrationDto("vasya", "password", "active")) // передаём в теле объект, который будет преобразован в JSON
                .body(user)
                .when().log().all() // "когда"
                .post("/api/system/users") // на какой путь относительно BaseUri отправляем запрос
                .then().log().all() // "тогда ожидаем"
                .statusCode(200);// код 200 OK

        // TODO: отправить запрос на указанный в требованиях path, передав в body запроса объект user
        //  и не забудьте передать подготовленную спецификацию requestSpec.
        //  Пример реализации метода показан в условии к задаче.

    }

    public static String getRandomLogin() {
        // TODO: добавить логику для объявления переменной login и задания её значения, для генерации
        //  случайного логина используйте faker

        return DataGenerator.faker.name().username();
           }

    public static String getRandomPassword() {
        // TODO: добавить логику для объявления переменной password и задания её значения, для генерации
        //  случайного пароля используйте faker

        return faker.internet().password();

    }

    public static class Registration {
        private Registration() {
        }

        public static RegistrationDto getUser(String status) {
            // TODO: создать пользователя user используя методы getRandomLogin(), getRandomPassword() и параметр status
var user = new RegistrationDto(getRandomLogin(), getRandomPassword(), status);
return user;
          //  return new RegistrationDto(getRandomLogin(), getRandomPassword(), status);  //user;
        }

        public static RegistrationDto getRegisteredUser(String status) {
            // TODO: объявить переменную registeredUser и присвоить ей значение возвращённое getUser(status).
            // Послать запрос на регистрацию пользователя с помощью вызова sendRequest(registeredUser)
          var registeredUser = getUser(status);
           sendRequest(registeredUser);
           return registeredUser;
            // return registeredUser.sendRequest(getUser(status));
        }
    }

    @Value
    public static class RegistrationDto {
        String login;
        String password;
        String status;


    }
}
