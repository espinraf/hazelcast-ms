package se.voipbusiness.ms;

import com.hazelcast.config.Config;
import com.hazelcast.config.EntryListenerConfig;
import com.hazelcast.config.XmlConfigBuilder;
import com.hazelcast.core.EntryEvent;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;
import com.hazelcast.map.listener.EntryAddedListener;
import com.hazelcast.map.listener.EntryRemovedListener;
import com.hazelcast.map.listener.EntryUpdatedListener;
import com.hazelcast.map.listener.MapListener;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.bind.annotation.RequestMethod.*;

@Configuration
@EnableCaching
@Slf4j
public class TestHazelRest {

    @Bean
    public Config getConfig(MapListener listener) {
        final Config config = new XmlConfigBuilder().build();
        EntryListenerConfig listenerConfig = new EntryListenerConfig();
        listenerConfig
                .setIncludeValue(true)
                .setImplementation(listener);
        config.getMapConfig("my-cache").addEntryListenerConfig(listenerConfig);
        return config;
    }

    @Bean
    public IMap<String, String> getCacheMap(HazelcastInstance hazelcast) {
        return hazelcast.getMap("my-cache");
    }

    /**
     * | HTTP     | CRUD      | Hazelcast   | httpie
     * ----------------------------------------------
     * | POST     |  create   | set         | `http POST :8080/caching/1 value==test`
     * | GET      | read      | get         | `http :8080/caching/1`
     * | PUT      | update    | put         | `http PUT :8080/caching/1 value==test2`
     * | PATCH    | update    | replace     | `http PATCH :8080/caching/1 oldValue==test2 newValue=="hey hey"`
     * | DELETE   | delete    | remove      | `http DELETE :8080/caching/1`
     * ----------------------------------
     */
    @RestController
    @RequestMapping("caching")
    public class MyController {

        @Autowired
        private IMap<String, String> cacheMap;

        @RequestMapping(method = POST,
                path = "{key}",
                params = {"value"})
        public void post(@PathVariable String key,
                         @RequestParam String value) {
            cacheMap.set(key, value);
        }

        @RequestMapping(
                method = GET,
                path = "/{key}")
        public String get(@PathVariable("key") String key) {
            return cacheMap.get(key);
        }

        @RequestMapping(
                method = PUT,
                path = "{key}",
                params = {"value"})
        public String put(@PathVariable String key, @RequestParam String value) {
            return cacheMap.put(key, value);
        }

        @RequestMapping(
                method = PATCH,
                path = "/{key}",
                params = {"oldValue", "newValue"})
        public boolean patch(@PathVariable("key") String key,
                             @RequestParam("oldValue") String value,
                             @RequestParam("newValue") String newValue) {
            return cacheMap.replace(key, value, newValue);
        }

        @RequestMapping(
                method = DELETE,
                path = "/{key}")
        public String delete(@PathVariable String key) {
            return cacheMap.remove(key);
        }

    }

    @Component
    public class MyListener implements
            EntryAddedListener<String, String>,
            EntryUpdatedListener<String, String>,
            EntryRemovedListener<String, String> {

        private final Logger log = LoggerFactory.getLogger(MyListener.class);

        @Override
        public void entryAdded(EntryEvent<String, String> event) {
            log.info("Entry added [{} : {}]", event.getKey(), event.getValue());
        }

        @Override
        public void entryUpdated(EntryEvent<String, String> event) {
            log.info("Entry updated [{} : {}]. Old value {}", event.getKey(), event.getValue(), event.getOldValue());
        }

        @Override
        public void entryRemoved(EntryEvent<String, String> event) {
            log.info("Entry removed [{} : {}]", event.getKey(), event.getOldValue());
        }
    }

}
