/*
 *     SPDX-License-Identifier: LGPL-3.0-or-later
 *
 *     Copyright (C) RainbowDashLabs and Contributor
 */

package de.chojo.universalis.provider.items;

import tools.jackson.databind.ObjectMapper;
import tools.jackson.databind.json.JsonMapper;
import de.chojo.universalis.entities.Name;
import de.chojo.universalis.provider.NameSupplier;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.Collections;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * A class which is able to act as a {@link NameSupplier} for items
 */
public class Items implements NameSupplier {
    /**
     * Default source URL for the item name dump.
     */
    public static final URI DEFAULT_SOURCE_URI =
            URI.create("https://raw.githubusercontent.com/ffxiv-teamcraft/ffxiv-teamcraft/staging/libs/data/src/lib/json/items.json");
    private static final Duration DEFAULT_TIMEOUT = Duration.ofSeconds(30);

    private final Map<Integer, Name> ids;
    private final Map<String, Integer> en = new HashMap<>();
    private final Map<String, Integer> de = new HashMap<>();
    private final Map<String, Integer> fr = new HashMap<>();
    private final Map<String, Integer> jp = new HashMap<>();

    private Items(Map<Integer, Name> ids) {
        this.ids = ids;
        for (var entry : ids.entrySet()) {
            Name name = entry.getValue();
            Integer id = entry.getKey();
            putLower(en, name.english(), id);
            putLower(de, name.german(), id);
            putLower(fr, name.french(), id);
            putLower(jp, name.japanese(), id);
        }
    }

    private static void putLower(Map<String, Integer> target, String key, Integer id) {
        if (key == null || key.isBlank()) return;
        target.put(key.toLowerCase(Locale.ROOT), id);
    }

    /**
     * Create a new items instance which will load the items from the {@link #DEFAULT_SOURCE_URI default source}.
     *
     * @return item instance
     * @throws IOException          if the response could not be read
     * @throws InterruptedException if the current thread gets interrupted
     */
    public static Items create() throws IOException, InterruptedException {
        return create(DEFAULT_SOURCE_URI);
    }

    /**
     * Create a new items instance which will load the items from the given URL.
     *
     * @param source source URI
     * @return item instance
     * @throws IOException          if the response could not be read or is not 2xx
     * @throws InterruptedException if the current thread gets interrupted
     */
    public static Items create(URI source) throws IOException, InterruptedException {
        try (HttpClient client = HttpClient.newBuilder().connectTimeout(DEFAULT_TIMEOUT).build()) {
            HttpResponse<String> response = client.send(HttpRequest.newBuilder()
                    .uri(source)
                    .timeout(DEFAULT_TIMEOUT)
                    .GET()
                    .build(), HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() / 100 != 2) {
                throw new IOException("Failed to load items from " + source
                        + " — status " + response.statusCode());
            }
            ObjectMapper mapper = JsonMapper.builder().build();
            Map<String, Name> names = mapper.readValue(response.body(), mapper.getTypeFactory()
                    .constructMapType(Map.class, String.class, Name.class));

            Map<Integer, Name> idNames = new HashMap<>();
            for (Map.Entry<String, Name> entry : names.entrySet()) {
                try {
                    idNames.put(Integer.parseInt(entry.getKey()), entry.getValue());
                } catch (NumberFormatException e) {
                    throw new IOException("Item id '" + entry.getKey() + "' from " + source + " is not an integer", e);
                }
            }
            return new Items(idNames);
        }
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
