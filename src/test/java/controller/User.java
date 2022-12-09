package controller;

import Setup.Setup;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import model.UserModel;
import org.apache.commons.configuration.ConfigurationException;
import org.junit.Test;
import utils.Utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import static io.restassured.RestAssured.given;

public class User extends Setup {
    public User() throws IOException {
        initConfig();
    }
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void callingLoginAPI(String email, String password) throws ConfigurationException, IOException {
        RestAssured.baseURI = prop.getProperty("BASE_URL");
        UserModel userModel = new UserModel(email, password);
        Response res =
                given()
                        .contentType("application/json")
                        .body(userModel)
                        .when()
                        .post("/user/login")
                        .then()
                        .assertThat().statusCode(200).extract().response();

        JsonPath jsonpath = res.jsonPath();
        String token = jsonpath.get("token");
        String message=jsonpath.get("message");
        setMessage(message);
        Utils.setEnvVariable("TOKEN", token);
    }

    public String callingUserListAPI() throws IOException {
        RestAssured.baseURI = prop.getProperty("BASE_URL");
        Response res =
                given()
                        .contentType("application/json")
                        .header("Authorization", prop.getProperty("TOKEN"))
                        .when()
                        .get("/user/list")
                        .then()
                        .assertThat().statusCode(200).extract().response();

        JsonPath response = res.jsonPath();
        String msg = response.get("message").toString();
        return msg;
    }

    public String CreatingUser(String name, String email, String password, String phone_number,String nid, String role) throws ConfigurationException, IOException {
        RestAssured.baseURI = prop.getProperty("BASE_URL");
        Map<String,String> userDetails = new HashMap<>();
        userDetails.put("name", name);
        userDetails.put("email", email);
        userDetails.put("password", password);
        userDetails.put("phone_number",phone_number);
        userDetails.put("nid",nid);
        userDetails.put("role",role);

        Response res =
                given()
                        .contentType("application/json")
                        .header("Authorization", prop.getProperty("TOKEN"))
                        .header("X-AUTH-SECRET-KEY", "ROADTOSDET")
                        .body(userDetails)
                        .when()
                        .post("/user/create")
                        .then()
                        .assertThat().statusCode(201).extract().response();


        JsonPath response = res.jsonPath();
        String msg = response.get("message").toString();
        return msg;
    }

    public String Get_Customer0Id() throws IOException {
        RestAssured.baseURI = prop.getProperty("BASE_URL");
        Response res =
                given()
                        .contentType("application/json")
                        .header("Authorization", prop.getProperty("TOKEN"))
                        .header("X-AUTH-SECRET-KEY", "ROADTOSDET")
                        .when()
                        .get("/user/search?email="+Utils.email)
                        .then()
                        .assertThat().statusCode(200).extract().response();

        JsonPath response = res.jsonPath();
        String msg = response.get("user.id").toString();
        return msg;
    }

    public String UpdateCustomer(String Phone_Number) throws ConfigurationException, IOException {
        RestAssured.baseURI = prop.getProperty("BASE_URL");
        Map<String,String> userDetails = new HashMap<>();
        userDetails.put("phone_number",Phone_Number);
        Utils.Customer_Number=Phone_Number;

        Response res =
                given()
                        .contentType("application/json")
                        .header("Authorization", prop.getProperty("TOKEN"))
                        .header("X-AUTH-SECRET-KEY", "ROADTOSDET")
                        .body(userDetails)
                        .when()
                        .patch("/user/update/"+Utils.id)
                        .then()
                        .assertThat().statusCode(200).extract().response();


        JsonPath response = res.jsonPath();
        String msg = response.get("message").toString();
        return msg;
    }

    public String Get_User_Number() throws IOException {
        RestAssured.baseURI = prop.getProperty("BASE_URL");
        Response res =
                given()
                        .contentType("application/json")
                        .header("Authorization", prop.getProperty("TOKEN"))
                        .header("X-AUTH-SECRET-KEY", "ROADTOSDET")
                        .when()
                        .get("/user/search?email="+Utils.email)
                        .then()
                        .assertThat().statusCode(200).extract().response();

        JsonPath response = res.jsonPath();
        String msg = response.get("user.phone_number").toString();
        return msg;
    }

    public String DepositToAgent(String amount) throws ConfigurationException, IOException {
        RestAssured.baseURI = prop.getProperty("BASE_URL");
        Map<String,String> userDetails = new HashMap<>();
        userDetails.put("from_account","SYSTEM");
        userDetails.put("to_account",Utils.Agent_Number);
        userDetails.put("amount",amount);

        Response res =
                given()
                        .contentType("application/json")
                        .header("Authorization", prop.getProperty("TOKEN"))
                        .header("X-AUTH-SECRET-KEY", "ROADTOSDET")
                        .body(userDetails)
                        .when()
                        .post("/transaction/deposit")
                        .then()
                        .assertThat().statusCode(201).extract().response();


        JsonPath response = res.jsonPath();
        String msg = response.get("message").toString();
        return msg;
    }

    public String CheckAgentBalance() throws ConfigurationException, IOException {
        RestAssured.baseURI = prop.getProperty("BASE_URL");

        Response res =
                given()
                        .contentType("application/json")
                        .header("Authorization", prop.getProperty("TOKEN"))
                        .header("X-AUTH-SECRET-KEY", "ROADTOSDET")
                        .when()
                        .get("/transaction/balance/"+Utils.Agent_Number)
                        .then()
                        .assertThat().statusCode(200).extract().response();


        JsonPath response = res.jsonPath();
        String msg = response.get("balance").toString();
        return msg;
    }

    public String DepositToCustomer(String amount) throws ConfigurationException, IOException {
        RestAssured.baseURI = prop.getProperty("BASE_URL");
        Map<String,String> userDetails = new HashMap<>();
        userDetails.put("from_account",Utils.Agent_Number);
        userDetails.put("to_account",Utils.Customer_Number);
        userDetails.put("amount",amount);

        Response res =
                given()
                        .contentType("application/json")
                        .header("Authorization", prop.getProperty("TOKEN"))
                        .header("X-AUTH-SECRET-KEY", "ROADTOSDET")
                        .body(userDetails)
                        .when()
                        .post("/transaction/deposit")
                        .then()
                        .assertThat().statusCode(201).extract().response();


        JsonPath response = res.jsonPath();
        String msg = response.get("message").toString();
        return msg;
    }

    public String CheckCustomerBalance() throws ConfigurationException, IOException {
        RestAssured.baseURI = prop.getProperty("BASE_URL");

        Response res =
                given()
                        .contentType("application/json")
                        .header("Authorization", prop.getProperty("TOKEN"))
                        .header("X-AUTH-SECRET-KEY", "ROADTOSDET")
                        .when()
                        .get("/transaction/balance/"+Utils.Customer_Number)
                        .then()
                        .assertThat().statusCode(200).extract().response();


        JsonPath response = res.jsonPath();
        String msg = response.get("balance").toString();
        return msg;
    }

    public String CashoutByCustomer(String amount) throws ConfigurationException, IOException {
        RestAssured.baseURI = prop.getProperty("BASE_URL");
        Map<String,String> userDetails = new HashMap<>();
        userDetails.put("from_account",Utils.Customer_Number);
        userDetails.put("to_account",Utils.Agent_Number);
        userDetails.put("amount",amount);

        Response res =
                given()
                        .contentType("application/json")
                        .header("Authorization", prop.getProperty("TOKEN"))
                        .header("X-AUTH-SECRET-KEY", "ROADTOSDET")
                        .body(userDetails)
                        .when()
                        .post("/transaction/withdraw")
                        .then()
                        .assertThat().statusCode(201).extract().response();


        JsonPath response = res.jsonPath();
        String msg = response.get("message").toString();
        return msg;
    }
}
