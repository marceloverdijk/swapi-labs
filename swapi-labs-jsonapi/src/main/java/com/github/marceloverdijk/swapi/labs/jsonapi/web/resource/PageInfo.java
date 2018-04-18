/*
 * Copyright 2018 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.github.marceloverdijk.swapi.labs.jsonapi.web.resource;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.data.domain.Page;

import java.util.Objects;

/**
 * The page info object.
 *
 * @author Marcel Overdijk
 */
public class PageInfo {

    private int number;
    private int size;
    private int numberOfElements;
    private long totalElements;
    private int totalPages;

    public PageInfo(final Page page) {
        Objects.requireNonNull(page, "'page' must not be null");
        this.number = page.getNumber() + 1; // Page is 0-based so adding 1.
        this.size = page.getSize();
        this.numberOfElements = page.getNumberOfElements();
        this.totalElements = page.getTotalElements();
        this.totalPages = page.getTotalPages();
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(final int number) {
        this.number = number;
    }

    public int getSize() {
        return size;
    }

    public void setSize(final int size) {
        this.size = size;
    }

    public int getNumberOfElements() {
        return numberOfElements;
    }

    public void setNumberOfElements(final int numberOfElements) {
        this.numberOfElements = numberOfElements;
    }

    public long getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(final long totalElements) {
        this.totalElements = totalElements;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(final int totalPages) {
        this.totalPages = totalPages;
    }

    @Override
    public boolean equals(final Object obj) {
        return EqualsBuilder.reflectionEquals(this, obj);
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
