package com.iamalexvybornyi.core.element;

import lombok.NonNull;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.function.Supplier;

public class Select extends AbstractWebElement implements Selectable, Clickable {

    public Select(@NonNull By locator, @NonNull Supplier<WebElement> webElementSupplier) {
        super(locator, webElementSupplier);
    }

    @NonNull
    public org.openqa.selenium.support.ui.Select getElement() {
        return new org.openqa.selenium.support.ui.Select(getRootWebElement());
    }

    @Override
    public void click() {
        getRootWebElement().click();
    }

    @Override
    public boolean isMultiple() {
        return new org.openqa.selenium.support.ui.Select(getRootWebElement()).isMultiple();
    }

    @Override
    public List<WebElement> getOptions() {
        return new org.openqa.selenium.support.ui.Select(getRootWebElement()).getOptions();
    }

    @Override
    public List<WebElement> getAllSelectedOptions() {
        return new org.openqa.selenium.support.ui.Select(getRootWebElement()).getAllSelectedOptions();
    }

    @Override
    public WebElement getFirstSelectedOption() {
        return new org.openqa.selenium.support.ui.Select(getRootWebElement()).getFirstSelectedOption();
    }

    @Override
    public void selectByVisibleText(String text) {
        new org.openqa.selenium.support.ui.Select(getRootWebElement()).selectByVisibleText(text);
    }

    @Override
    public void selectByIndex(int index) {
        new org.openqa.selenium.support.ui.Select(getRootWebElement()).selectByIndex(index);
    }

    @Override
    public void selectByValue(String value) {
        new org.openqa.selenium.support.ui.Select(getRootWebElement()).selectByValue(value);
    }

    @Override
    public void deselectAll() {
        new org.openqa.selenium.support.ui.Select(getRootWebElement()).deselectAll();
    }

    @Override
    public void deselectByValue(String value) {
        new org.openqa.selenium.support.ui.Select(getRootWebElement()).deselectByValue(value);
    }

    @Override
    public void deselectByIndex(int index) {
        new org.openqa.selenium.support.ui.Select(getRootWebElement()).deselectByIndex(index);
    }

    @Override
    public void deselectByVisibleText(String text) {
        new org.openqa.selenium.support.ui.Select(getRootWebElement()).deselectByVisibleText(text);
    }
}
