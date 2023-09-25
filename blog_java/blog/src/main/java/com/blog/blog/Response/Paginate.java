package com.blog.blog.Response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Paginate<T> {
    private int pageIndex;
    private int pageSize;
    private Long totalElement;
    private T content;
}