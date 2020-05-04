package com.demo;

import io.quarkus.test.junit.NativeImageTest;

@NativeImageTest
public class NativeUserInfoResourceIT extends UserInfoResourceTest {

    // Execute the same tests but in native mode.
}