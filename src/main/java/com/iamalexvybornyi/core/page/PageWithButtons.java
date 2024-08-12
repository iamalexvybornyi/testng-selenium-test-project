package com.iamalexvybornyi.core.page;

import com.iamalexvybornyi.core.element.Button;
import com.iamalexvybornyi.util.buttons.ButtonName;
import lombok.NonNull;

import java.util.Map;

public interface PageWithButtons {
    @NonNull
    String getPageName();
    @NonNull
    Map<ButtonName, Button> getButtons();
}
