package com.kida.kidastore.shared;

import com.kida.kidastore.models.dto.Response.PagenatedResponse;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
public class PagenatedMapper<T> {
    public PagenatedResponse<T> mapToPagenatedResponse(Page<T> page, String baseUrl) {
        String nextPageLink = page.hasNext() ? String.format("%s?page=%d&size=%d", baseUrl, page.getNumber() + 1, page.getSize()) : null;
        String previousPageLink = page.hasPrevious() ? String.format("%s?page=%d&size=%d", baseUrl, page.getNumber() - 1, page.getSize()) : null;
        return new PagenatedResponse<>(page.getContent(), page.getNumber() + 1, page.getTotalPages(), page.getTotalElements(), page.hasPrevious(), page.hasNext(), previousPageLink, nextPageLink);
    }

}
