package com.iamalexvybornyi.core.element.collection;

import com.iamalexvybornyi.core.element.AbstractWebElement;
import lombok.NonNull;

import java.util.List;

public interface WebElementCollection<T extends AbstractWebElement> {
    @NonNull
    List<T> getElements();
    @NonNull
    T getElement(int index);
}
