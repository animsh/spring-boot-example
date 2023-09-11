package com.tadashi.customer;

public record CustomerUpdateRequest(
        String name,
        String email,
        int age
) {
}
