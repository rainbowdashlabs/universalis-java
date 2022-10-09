/*
 *     SPDX-License-Identifier: AGPL-3.0-only
 *
 *     Copyright (C) 2022 RainbowDashLabs and Contributor
 */

package de.chojo.universalis.items;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.chojo.universalis.entities.Name;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Map;

public class Items implements NameSupplier {
    private final Map<Integer, Name> names;

    private Items(Map<Integer, Name> names) {
        this.names = names;
    }

    public static Items create() throws IOException, InterruptedException {
        HttpClient client = HttpClient.newBuilder().build();
        HttpResponse<String> response = client.send(HttpRequest.newBuilder()
                                                               .uri(URI.create("https://raw.githubusercontent.com/ffxiv-teamcraft/ffxiv-teamcraft/master/apps/client/src/assets/data/items.json"))
                                                               .GET()
                                                               .build(), HttpResponse.BodyHandlers.ofString());
        ObjectMapper mapper = new ObjectMapper();
        String body = response.body();
        Map<String, Name> names = mapper.readValue(body, mapper.getTypeFactory()
                                                               .constructMapType(Map.class, String.class, Name.class));

        Map<Integer, Name> idNames = new HashMap<>();

        for (Map.Entry<String, Name> entry : names.entrySet()) {
            idNames.put(Integer.parseInt(entry.getKey()), entry.getValue());
        }
        return new Items(idNames);
    }

    @Override
    public Name fromId(int id) {
        return names.get(id);
    }
}
