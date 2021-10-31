package net.kemitix.clover.spi;

import java.util.List;
import java.util.Optional;

public interface Contents {

    TextEffect.HAlignment getAlignment();
    int getFontSize();
    List<? extends Section> getSections();
    List<Integer> getRows();

    default Optional<? extends Section> findSection(Section.Label label) {
        return getSections()
                .stream()
                .filter(section -> section.getLabel().equals(label))
                .findFirst();
    }
}
