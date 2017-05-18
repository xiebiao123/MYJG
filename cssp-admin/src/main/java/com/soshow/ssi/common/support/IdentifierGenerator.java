package com.soshow.ssi.common.support;

/**
 * Created by fangyt on 2016/12/15
 */
public interface IdentifierGenerator {

    /**
     * Gets the next identifier in the sequence.
     *
     * @return the next identifier in sequence
     */
    Object nextIdentifier();
}
