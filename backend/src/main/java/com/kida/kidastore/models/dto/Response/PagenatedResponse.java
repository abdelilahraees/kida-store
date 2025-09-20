package com.kida.kidastore.models.dto.Response;

import java.util.List;

public record PagenatedResponse<T>(
        List<T> content,
        int pageNumber,
        int totalPages,
        long totalElements,
        boolean hasPreviousPage,
        boolean hasNextPage,
        String previousPageUrl,
        String nextPageUrl
) {

}
