package com.felfeit.readly.domain.mapper;

import com.felfeit.readly.data.source.remote.response.search.DocsItem;
import com.felfeit.readly.domain.model.Work;

import java.util.ArrayList;
import java.util.List;

public class WorkMapper {

    public static Work mapWorkResponseToWorkDomain(DocsItem response) {
        return new Work(
                response.getTitle(),
                response.getAuthorName() != null && !response.getAuthorName().isEmpty()
                        ? response.getAuthorName().get(0)
                        : "Unknown Author",
                response.getFirstPublishYear(),
                response.getKey(),
                response.hasCover() ? response.getCoverEditionKey() : "https://placehold.co/300x400", // Handle cover
                response.getEditionCount()
        );
    }

    public static List<Work> mapWorkResponseListToWorkDomainList(List<DocsItem> responses) {
        List<Work> entities = new ArrayList<>();
        for (DocsItem response : responses) {
            entities.add(mapWorkResponseToWorkDomain(response));
        }
        return entities;
    }
}
