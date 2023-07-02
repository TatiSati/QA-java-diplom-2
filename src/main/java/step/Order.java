package step;

import constants.PathApi;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import model.OrderCreate;

import static io.restassured.RestAssured.given;


// Вызовы ручек вынесены в отдельный класс PathApi
public class Order extends PathApi { // класс Order - наследник класса PathAPI

    @Step("Метод для шага Получить заказы конкретного пользователя")
    @Description("GET на ручку api/orders")
    public Response getUserOrders(String accessToken){
        return given()
                .header("Authorization", accessToken)
                .header("Content-type", "application/json")
                .get(ORDER_BASE_URL);
    }

    @Step("Метод для шага Создание заказа")
    @Description("POST на ручку api/orders")
    public Response createOrder(OrderCreate orderCreate, String accessToken){
        return given()
                .header("Authorization", accessToken)
                .header("Content-type", "application/json")
                .and()
                .body(orderCreate)
                .post(ORDER_BASE_URL);
    }

}
