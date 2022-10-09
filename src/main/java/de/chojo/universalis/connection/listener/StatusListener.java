/*
 *     SPDX-License-Identifier: AGPL-3.0-only
 *
 *     Copyright (C) 2022 RainbowDashLabs and Contributor
 */

package de.chojo.universalis.connection.listener;

import com.neovisionaries.ws.client.WebSocket;
import com.neovisionaries.ws.client.WebSocketAdapter;
import com.neovisionaries.ws.client.WebSocketException;
import com.neovisionaries.ws.client.WebSocketFrame;
import de.chojo.universalis.subscriber.Subscription;
import org.bson.BSONEncoder;
import org.bson.BasicBSONEncoder;
import org.bson.BasicBSONObject;
import org.slf4j.Logger;

import java.util.List;
import java.util.Map;

import static org.slf4j.LoggerFactory.getLogger;

public class StatusListener extends WebSocketAdapter {
    private static final Logger log = getLogger(StatusListener.class);
    private final BSONEncoder encoder = new BasicBSONEncoder();
    private final List<Subscription> subscriptions;

    public StatusListener(List<Subscription> subscriptions) {
        this.subscriptions = subscriptions;
    }

    @Override
    public void onConnected(WebSocket websocket, Map<String, List<String>> headers) throws Exception {
        log.trace("WebSocket Connected");
        for (var entry : headers.entrySet()) {
            log.trace("{}:{}", entry.getKey(), String.join(", ", entry.getValue()));
        }

        for (Subscription subscription : subscriptions) {
            for (String channel : subscription.channel()) {
                BasicBSONObject payload = new BasicBSONObject().append("event", "subscribe")
                                                               .append("channel", channel);
                websocket.sendBinary(encoder.encode(payload));
                log.trace("Subscribed to channel {}", channel);
            }
        }
    }

    @Override
    public void handleCallbackError(WebSocket websocket, Throwable cause) throws Exception {
        log.error("Callback error", cause);
    }

    @Override
    public void onError(WebSocket websocket, WebSocketException cause) throws Exception {
        log.error("Error in websocket", cause);
        log.error("Reason: {}", cause.getError());
    }

    @Override
    public void onDisconnected(WebSocket websocket, WebSocketFrame serverCloseFrame, WebSocketFrame clientCloseFrame, boolean closedByServer) throws Exception {
        System.out.println(serverCloseFrame);
        System.out.println(closedByServer);
        System.out.println("Disconnected");
    }

    @Override
    public void onUnexpectedError(WebSocket websocket, WebSocketException cause) throws Exception {
        log.error("Ups", cause);
    }
}
