package com.tadashi.customer;

public record CustomerRegistrationRequest(
        String name,
        String email,
        int age
) {
}
