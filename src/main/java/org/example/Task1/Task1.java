package org.example.Task1;


import java.io.IOException;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Paths;



public class Task1 {
    HttpClient client = HttpClient.newHttpClient();
    public String createUser(String   userFilePath) throws IOException, InterruptedException {
        String uri = "https://jsonplaceholder.typicode.com/users";
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(uri))
                .header("Content-Type", "application/json; charset=UTF-8")
                .POST(HttpRequest.BodyPublishers.ofFile(Paths.get(userFilePath)))
                .build();

        var response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return response.body();
    }
    public String updateUserById(int userId) throws IOException, InterruptedException {
        Task1 t1Test = new Task1();
        String uri = "https://jsonplaceholder.typicode.com/users/" + userId;
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(uri))
                .header("Content-Type", "application/json; charset=UTF-8")
                .PUT(HttpRequest.BodyPublishers.ofString(t1Test.getUserById(userId)))
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return response.body();
    }
    public int deleteUserById(int id) throws IOException, InterruptedException {
        String uri = "https://jsonplaceholder.typicode.com/users/" + id;
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(uri))
                .DELETE()
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return response.statusCode();
    }
    public String getAllUsers() throws IOException, InterruptedException {
        String uri = "https://jsonplaceholder.typicode.com/users";
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(uri))
                .GET()
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return response.body();
    }
    public String getUserById(int id) throws IOException, InterruptedException {
        String uri = "https://jsonplaceholder.typicode.com/users/" + id;
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(uri))
                .GET()
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return response.body();
    }
    public String getUserByName(String userName) throws IOException, InterruptedException {
        String uri = "https://jsonplaceholder.typicode.com/users?username=" + userName;
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(uri))
                .GET()
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return response.body();
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        String userPath = "src/main/java/org/example/Task1/User.json";
        Task1 task1Test = new Task1();
        // Creation of User
        System.out.println("Creation user response = " + task1Test.createUser(userPath));

        // Updating User
        System.out.println("Updating user With id 1 = " + task1Test.updateUserById(1));

        // Deleting User
        System.out.println("Deleting user with id 2 response = " + task1Test.deleteUserById(2));

        // Getting info about all Users
        System.out.println("All Users " + task1Test.getAllUsers());

        // Getting user by Id
        System.out.println("User with id 10 = " + task1Test.getUserById(10));

        //Getting user by Username
        System.out.println("User with name Moriah.Stanton = " + task1Test.getUserByName("Moriah.Stanton"));



    }
}
