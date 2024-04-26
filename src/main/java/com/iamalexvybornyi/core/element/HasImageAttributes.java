package com.iamalexvybornyi.core.element;

import org.springframework.lang.Nullable;

public interface HasImageAttributes {
    @Nullable
    String getImageAlt();
    @Nullable
    String getImageSource();
}
