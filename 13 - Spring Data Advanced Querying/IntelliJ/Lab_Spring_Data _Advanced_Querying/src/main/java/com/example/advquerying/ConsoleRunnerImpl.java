package com.example.advquerying;

import com.example.advquerying.serices.ShampooService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;

@Component
public class ConsoleRunnerImpl implements CommandLineRunner {
    private final ShampooService shampooService;

    public ConsoleRunnerImpl(ShampooService shampooService) {
        this.shampooService = shampooService;
    }

    @Override
    public void run(String... args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
this.shampooService.getAllShampoosByGivenSize(reader.readLine())
        .forEach(System.out::println);


    }
}
