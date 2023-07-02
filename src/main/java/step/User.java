package step;

import constants.PathApi;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import model.UserChangeData;
import model.UserLogin;
import model.UserRegistration;

import static io.restassured.RestAssured.given;


// Вызовы ручек вынесены в отдельный класс PathApi
public class User extends PathApi {

    @Step("Метод для шага Создание пользователя")
    @Description("POST на ручку api/auth/register")
    public Response registrationUser(UserRegistration userRegistration){
        // метод given() помогает сформировать запрос
        return given()
                .header("Content-type", "application/json")
                .and()
                .body(userRegistration)
                .when()
                .post(USER_REGISTRATION);
    }

    @Step("Метод для шага Авторизация пользователя")
    @Description("POST на ручку api/auth/login")
    public Response loginUser(UserLogin userLogin){
        return given()
                .header("Content-type", "application/json")
                .and()
                .body(userLogin)
                .when()
                .post(USER_LOGIN);
    }

    @Step("Метод для шага Обновление информации о пользователе")
    @Description("PATCH на ручку api/auth/user")
    public Response changeUserData(UserChangeData userChangeData, String accessToken){
        return given()
                .header("Authorization", accessToken)
                .header("Content-type", "application/json")
                .and()
                .body(userChangeData)
                .patch(USER_BASE_URL);
    }

    @Step("Метод для шага Удаление пользователя")
    @Description("DELETE на ручку api/auth/user")
    public void deleteUser(String accessToken){
        given()
                .header("Authorization", accessToken)
                .header("Content-type", "application/json")
                .delete(USER_BASE_URL);
    }

}
