package com.fullcycle.admin.catalogo.infrastructure.services.local;

import com.fullcycle.admin.catalogo.domain.resource.Resource;
import com.fullcycle.admin.catalogo.infrastructure.services.StorageService;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class InMemoryStorageService implements StorageService {

    private final Map<String, Resource> storage;

    public InMemoryStorageService() {
        this.storage = new ConcurrentHashMap<>();
    }

    public void clear() {
        this.storage.clear();
    }

    public Map<String, Resource> storage(){
        return this.storage;
    }

    @Override
    public void store(String name, Resource resource) {
        this.storage.put(name, resource);
    }

    @Override
    public Optional<Resource> get(final String name) {
        return Optional.ofNullable(this.storage.get(name));
    }

    @Override
    public List<String> list(final String prefix) {
        if(prefix == null) {
            return Collections.emptyList();
        }

        return this.storage.keySet()
                .stream()
                .filter(name -> name.startsWith(prefix))
                .toList();
    }

    @Override
    public void deleteAll(final List<String> ids) {
        ids.forEach(this.storage::remove);
    }
}
