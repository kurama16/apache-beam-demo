package org.nahu.client;

import lombok.Getter;
import lombok.experimental.UtilityClass;
import okhttp3.OkHttpClient;

@UtilityClass
public class APIClient {

    @Getter
    private static final OkHttpClient client = new OkHttpClient();

}
