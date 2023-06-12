package com.sebastian.lil.lq;

import javax.enterprise.context.ApplicationScoped;

import org.eclipse.microprofile.config.inject.ConfigProperty;

@ApplicationScoped
public class FizzBuzz {

    @ConfigProperty(defaultValue = "100", name = "application.maxNumber")
    int maxNumber;

    public void execute() {
        for(int i=1;i<=maxNumber;i++){
            String result = "";
            result+=(i%3)==0?"fizz":"";
            result+=(i%5)==0?"buzz":"";
            System.out.println(!result.isEmpty() ? result: i);
        }
    }
}
