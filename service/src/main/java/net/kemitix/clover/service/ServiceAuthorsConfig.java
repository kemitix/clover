package net.kemitix.clover.service;

import lombok.Getter;
import lombok.Setter;
import net.kemitix.clover.spi.AuthorsConfig;
import net.kemitix.clover.spi.TextEffect;

import javax.enterprise.inject.Vetoed;

@Vetoed
@Setter
@Getter
public class ServiceAuthorsConfig implements AuthorsConfig {
    private int top;
    private int width;
    private int left;
    private TextEffect.HAlignment alignment;
}
