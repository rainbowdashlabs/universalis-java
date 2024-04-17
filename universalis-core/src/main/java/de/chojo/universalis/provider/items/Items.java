/*
 *     SPDX-License-Identifier: LGPL-3.0-or-later
 *
 *     Copyright (C) RainbowDashLabs and Contributor
 */

package de.chojo.universalis.provider.items;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.chojo.universalis.entities.Name;
import de.chojo.universalis.provider.NameSupplier;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Collections;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * A class which is able to act as a {@link NameSupplier} for items
 */
public class Items implements NameSupplier {
    private final Map<Integer, Name> ids;
    private final Map<String, Integer> en = new HashMap<>();
    private final Map<String, Integer> de = new HashMap<>();
    private final Map<String, Integer> fr = new HashMap<>();
    private final Map<String, Integer> jp = new HashMap<>();

    private Items(Map<Integer, Name> ids) {
        this.ids = ids;
        for (var entry : ids.entrySet()) {
            en.put(entry.getValue().english().toLowerCase(Locale.ROOT), entry.getKey());
            de.put(entry.getValue().german().toLowerCase(Locale.ROOT), entry.getKey());
            fr.put(entry.getValue().french().toLowerCase(Locale.ROOT), entry.getKey());
            jp.put(entry.getValue().japanese().toLowerCase(Locale.ROOT), entry.getKey());
        }
    }

    /**
     * Create a new items instance which will load the items from a GitHub dump.
     *
     * @return item instance
     * @throws IOException          if the response could not be read
     * @throws InterruptedException if the current thread gets interrupted
     */
    public static Items create() throws IOException, InterruptedException {
        HttpClient client = HttpClient.newBuilder().build();
        HttpResponse<String> response = client.send(HttpRequest.newBuilder()
                .uri(URI.create("https://raw.githubusercontent.com/ffxiv-teamcraft/ffxiv-teamcraft/staging/libs/data/src/lib/json/items.json"))
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
    public Map<Integer, Name> ids() {
        return Collections.unmodifiableMap(ids);
    }

    @Override
    public Map<String, Integer> en() {
        return Collections.unmodifiableMap(en);
    }

    @Override
    public Map<String, Integer> de() {
        return Collections.unmodifiableMap(de);
    }

    @Override
    public Map<String, Integer> fr() {
        return Collections.unmodifiableMap(fr);
    }

    @Override
    public Map<String, Integer> jp() {
        return Collections.unmodifiableMap(jp);
    }
}
